from selenium import webdriver
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium.webdriver import ActionChains
from urllib.request import urlretrieve
from bs4 import BeautifulSoup
import re
from PIL import Image
from time import sleep
import os
import json

# 初始化
def init():
    # 定义为全局变量，方便其他模块使用
    global url, browser, username, password, wait
    # 登录界面的url
    url = 'https://sso.toutiao.com/login/?service=https://mp.toutiao.com/sso_confirm/?redirect_url=/'
    # 实例化一个chrome浏览器
    browser = webdriver.Chrome()
    browser.maximize_window()
    # 用户名
    username = '000'
    # 密码
    password = '000'
    # 设置等待超时
    wait = WebDriverWait(browser, 20)

# 登录
def login():
    # 打开登录页面
    browser.get(url)
    # 点击“账密”登录
    click_account = wait.until(EC.presence_of_element_located((By.ID,'login-type-account'))) 
    ActionChains(browser).click(click_account).perform()
    # 获取用户名输入框
    user = browser.find_element_by_id('user_name')
    # 获取密码输入框
    passwd = browser.find_element_by_id('password')
    # 输入用户名
    user.send_keys(username)
    # 输入密码
    passwd.send_keys(password)
    # 点击提交
    click_submit = wait.until(EC.presence_of_element_located((By.ID,'bytedance_SubmitStatic')))
    ActionChains(browser).click(click_submit).perform()
    sleep(1)
    # 获取图片
    try:
        validate_imgs = wait.until(EC.presence_of_element_located((By.CLASS_NAME,'validate-main')))
        big_img = validate_imgs.find_element_by_xpath("//img[@id='validate-big']")
        block_img = validate_imgs.find_element_by_xpath("//img[@class='validate-block']")
        big_img_url = big_img.get_attribute('src')
        block_img_url = block_img.get_attribute('src')
        print(big_img_url)
        print(block_img_url)
        urlretrieve(url=big_img_url, filename='validate_big.jpg')
        urlretrieve(url=block_img_url, filename='validate_block.jpg')
        axis = block_img.get_attribute('style').split(";")
        left = axis[0].split(":")[1].strip()
        top = axis[1].split(":")[1].strip()
        top = re.findall(r"\d+",top)[0]
        return top
    except:
        browser.close()
        return -1
    

# 计算滑块移动距离
def get_distance(top):
    '''
    :param bg_image: (Image)缺口图片
    :param fullbg_image: (Image)完整图片
    :return: (Int)缺口离滑块的距离
    '''
    block = Image.open('validate_block.jpg')
    big = Image.open('validate_big.jpg')
    x1 = [0]
    x2 = [0]
    distances = []
    threshold = 3
    sum = 0
    for y in range(22,32):
        for i in range(big.size[0]):
            if (abs(big.load()[i,top+y][0] - big.load()[i,top+y][1]) < threshold) and (abs(big.load()[i,top+y][1] - big.load()[i,top+y][2])<threshold):
                print(i,top+y)
                x1.append(i)
                break
        for i in range(block.size[0]):
            if block.load()[i,y][0] == 255 and block.load()[i,y][1] == 255 and block.load()[i,y][2] == 255:
                print(i,y)
                x2.append(i)
                break
        distance = x1[-1] - x2[-1]
        if distance > 0:
            distances.append(distance)
            sum = sum + distance
    try:        
        distance = sum//len(distances)
    except:
        distance = 0
    return distance


# 构造滑动轨迹
def get_trace(distance):
    '''
    :param distance: (Int)缺口离滑块的距离
    :return: (List)移动轨迹
    '''
 
    # 创建存放轨迹信息的列表
    trace = []
    # 设置加速的距离
    faster_distance = distance*(4/5)
    # 设置初始位置、初始速度、时间间隔
    start, v0, t = 0, 0, 0.2
    # 当尚未移动到终点时
    while start < distance:
        # 如果处于加速阶段
        if start < faster_distance:
            # 设置加速度为2
            a = 1.5
        # 如果处于减速阶段
        else:
            # 设置加速度为-3
            a = -3
        # 移动的距离公式
        move = v0 * t + 1 / 2 * a * t * t
        # 此刻速度
        v = v0 + a * t
        # 重置初速度
        v0 = v
        # 重置起点
        start += move
        # 将移动的距离加入轨迹列表
        trace.append(round(move))
    # 返回轨迹信息
    return trace

# 模拟拖动
def move_to_gap(trace):
    # 得到滑块标签
    slider = wait.until(EC.presence_of_element_located((By.CLASS_NAME, 'drag-button')))
    # 使用click_and_hold()方法悬停在滑块上，perform()方法用于执行
    ActionChains(browser).click_and_hold(slider).perform()
    for x in trace:
        # 使用move_by_offset()方法拖动滑块，perform()方法用于执行
        ActionChains(browser).move_by_offset(xoffset=x, yoffset=0).perform()
    # 模拟人类对准时间
    sleep(0.5)
    # 释放滑块
    ActionChains(browser).release().perform()
 

# 判断登录
def isLogin():
    # 初始化
    init()
    # 检查cookie
    
    while True:
        cookie = browser.get_cookie("tt_im_token")
        if cookie != None:
            break
        else:
            # 登录
            top = int(login())
            if top == -1:
                sleep(30)
                init()
                continue
            # block = Image.open('validate_block.jpg')
            # big = Image.open('validate_big.jpg')
            # distance = get_distance(top)
            # print(distance)
            # # 计算移动轨迹
            # trace = get_trace(distance-10)
            # # 移动滑块
            # move_to_gap(trace)
            sleep(5)
    print("successfully login")
    return True

# 上传文章
def create_content(path):
    create_url = 'https://mp.toutiao.com/profile_v3/graphic/publish'
    # 创建文章页面
    browser.get(create_url)
    # 填写标题
    sleep(3)
    try:
        with open('D:\\qxf\\Temp\\'+ path +'\\content.json','r') as f:
            js = json.load(f)
    except:
        print("no  such file")
    title = js["title"].split("|")[0].split("_")[0]
    # title = 'title_test'
    tlt = browser.find_element_by_id('title')
    tlt.send_keys(title)
    # 填写文章
    sleep(1)
    upload_click = browser.find_element_by_class_name("figure-write-rule")
    ActionChains(browser).click(upload_click).perform()
    sleep(1)
    upload_file = browser.find_element_by_name("file")
    upload_file.send_keys(os.path.abspath("D:\\qxf\\Temp\\" + path +"\\content.docx"))
    # 设置选项

    sleep(1)
    article_cover = wait.until(EC.presence_of_element_located((By.CLASS_NAME,'article-cover'))).find_element_by_class_name("tui2-radio-group")
    # print(article_cover.text)
    browser.execute_script('window.scrollTo(0, document.documentElement.scrollTop=1000000)')
    sleep(1)
    set_cover = article_cover.find_element_by_xpath("//label[3]").find_element_by_tag_name("input")
    # print(set_cover.text)
    ActionChains(browser).move_to_element(set_cover).click().perform()
    # 提交文章
    sleep(5)
    publish_click = wait.until(EC.presence_of_element_located((By.ID,'publish')))
    ActionChains(browser).click(publish_click).perform()

# 程序入口
if __name__ == '__main__':
    flag = isLogin()
    path = "sinanews\\"
    count = 0
    if flag:
        files = os.listdir("D:\\qxf\\Temp\\sinanews")
        for element in files:
            try:
                create_content(path+element)
            except:
                alert = browser.switch_to.alert
                alert.dismiss()
                sleep(5)
                publish_click = wait.until(EC.presence_of_element_located((By.ID,'publish')))
                ActionChains(browser).click(publish_click).perform()
                sleep(1)
                # tlt = browser.find_element_by_id('title')
                # tlt.clear()
                # clear_article = browser.find_element_by_class_name("ql-editor")
                # clear_article.clear()
                continue
            count = count + 1
            print(count)
            sleep(5)



























# from bs4 import BeautifulSoup
# import requests
# from PIL import Image
# import http.cookiejar as cookielib
# import os
# import json
# import chardet
# import base64
# from selenium import webdriver


# class loginCase():
#     def setUp(self):
#         self.dr = webdriver.Chrome()
#         self.dr.maximize_window()
#     def login(self, mobile):
#         url = 'https://sso.toutiao.com/login/?service=https://mp.toutiao.com/sso_confirm/?redirect_url=JTJG'
#         self.dr.get(url)
#         img = self.dr.find_element_by_class_name("captcha-wrap").find_element_by_xpath("/img@src")
#         captcha = self.get_captcha(img.split(";")[1])
#         self.dr.find_element_by_id
#         self.dr.find_element_by_id('captcha1').send_keys(captcha)
#         # self.dr.find_element_by_class_name('code-btn').click()
#         # self.dr.find_element_by_id('code').send_keys(self.get_code())
#         # self.dr.find_element_by_id('submitBtn').click()
#     def get_captcha(self,base64code):
#         convert_img_raw_data = base64.b64decode(base64code)
#         with open('captcha.gif','wb') as f:
#             f.write(convert_img_raw_data)
#             f.close()
#         try:
#             im = Image.open('captcha.gif')
#             im.show()
#             im.close()
#         except:
#             print(u'请到 %s 目录找到captcha.gif 手动输入' %os.path.abspath('captcha.gif'))
#         captcha = input("Please input the captcha\n>")
#         return captcha
#     def get_code(self):
#         code = input('Please input the code\n')
#         return code


# session = requests.session()
# session.cookies = cookielib.LWPCookieJar(filename='cookies')
# try:
#      session.cookies.load(ignore_discard=True)
# except:
#     print("Cookie cannot be loaded")

# def isLogin():
#     url = "https://mp.toutiao.com/profile_v3/index"
#     login_url = session.get(url, headers=headers, allow_redirects=False).url
#     if login_url != url:
#         return False
#     else:
#         return True

# def login(name, pwd, captcha):
#     post_url = 'https://sso.toutiao.com/login/?service=https://mp.toutiao.com/sso_confirm/?redirect_url=JTJG'
#     postdata = {
#         'account':name,
#         'password':pwd,
#         'captcha':captcha
#     }
#     try:
#         login_page = session.post(post_url, data=postdata, headers=headers)
#         # login_code = login_page.text
#         # # print(login_code)
#         print(login_page.status_code)
#         print(login_page.text)
#     except:
#         pass
#     session.cookies.save()

# def get_captcha():
#     captcha_url = 'https://sso.toutiao.com/refresh_captcha'
#     headers = {
#         "Host":"sso.toutiao.com",
#         "Referer":"https://www.toutiao.com/",
#         "User-Agent": 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36',
#     }
#     captcha_page = session.get(captcha_url, headers=headers)
#     captcah_dict = json.loads(captcha_page.text)
#     si_code = captcah_dict["captcha"]
#     convert_img_raw_data = base64.b64decode(si_code)
#     with open('captcha.gif','wb') as f:
#         f.write(convert_img_raw_data)
#         f.close()
#     try:
#         im = Image.open('captcha.gif')
#         im.show()
#         im.close()
#     except:
#         print(u'请到 %s 目录找到captcha.gif 手动输入' %os.path.abspath('captcha.gif'))
#     captcha = input("Please input the captcha\n>")
#     return captcha

# if __name__ == '__main__':
#     headers = {
#         "Host":"sso.toutiao.com",
#         "Referer":"https://www.toutiao.com/",
#         "User-Agent": 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36',
#     }
#     # if isLogin():
#     #     print('login')
#     # else:
#     #     log = loginCase()
#     #     log.login('', '', get_captcha())
#     # log = loginCase()
#     # log.setUp()
#     # log.login('', get_captcha())


# # def get_captcha():
# #     url = 'https://sso.toutiao.com/login'
# #     res = requests.get(url)
# #     index_page = BeautifulSoup(res.text, 'html.parser')
