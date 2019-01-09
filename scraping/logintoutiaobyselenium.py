from bs4 import BeautifulSoup
import requests
from PIL import Image
import http.cookiejar as cookielib
import os
import json
import chardet
import base64
from selenium import webdriver


class loginCase():
    def setUp(self):
        self.dr = webdriver.Chrome()
        self.dr.maximize_window()
    def login(self, mobile):
        url = 'https://sso.toutiao.com/login/?service=https://mp.toutiao.com/sso_confirm/?redirect_url=JTJG'
        self.dr.get(url)
        img = self.dr.find_element_by_class_name("captcha-wrap").find_element_by_xpath("/img@src")
        captcha = self.get_captcha(img.split(";")[1])
        self.dr.find_element_by_id
        self.dr.find_element_by_id('captcha1').send_keys(captcha)
        # self.dr.find_element_by_class_name('code-btn').click()
        # self.dr.find_element_by_id('code').send_keys(self.get_code())
        # self.dr.find_element_by_id('submitBtn').click()
    def get_captcha(self,base64code):
        convert_img_raw_data = base64.b64decode(base64code)
        with open('captcha.gif','wb') as f:
            f.write(convert_img_raw_data)
            f.close()
        try:
            im = Image.open('captcha.gif')
            im.show()
            im.close()
        except:
            print(u'请到 %s 目录找到captcha.gif 手动输入' %os.path.abspath('captcha.gif'))
        captcha = input("Please input the captcha\n>")
        return captcha
    def get_code(self):
        code = input('Please input the code\n')
        return code


session = requests.session()
session.cookies = cookielib.LWPCookieJar(filename='cookies')
try:
     session.cookies.load(ignore_discard=True)
except:
    print("Cookie cannot be loaded")

def isLogin():
    url = "https://mp.toutiao.com/profile_v3/index"
    login_url = session.get(url, headers=headers, allow_redirects=False).url
    if login_url != url:
        return False
    else:
        return True

def login(name, pwd, captcha):
    post_url = 'https://sso.toutiao.com/login/?service=https://mp.toutiao.com/sso_confirm/?redirect_url=JTJG'
    postdata = {
        'account':name,
        'password':pwd,
        'captcha':captcha
    }
    try:
        login_page = session.post(post_url, data=postdata, headers=headers)
        # login_code = login_page.text
        # # print(login_code)
        print(login_page.status_code)
        print(login_page.text)
    except:
        pass
    session.cookies.save()

def get_captcha():
    captcha_url = 'https://sso.toutiao.com/refresh_captcha'
    headers = {
        "Host":"sso.toutiao.com",
        "Referer":"https://www.toutiao.com/",
        "User-Agent": 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36',
    }
    captcha_page = session.get(captcha_url, headers=headers)
    captcah_dict = json.loads(captcha_page.text)
    si_code = captcah_dict["captcha"]
    convert_img_raw_data = base64.b64decode(si_code)
    with open('captcha.gif','wb') as f:
        f.write(convert_img_raw_data)
        f.close()
    try:
        im = Image.open('captcha.gif')
        im.show()
        im.close()
    except:
        print(u'请到 %s 目录找到captcha.gif 手动输入' %os.path.abspath('captcha.gif'))
    captcha = input("Please input the captcha\n>")
    return captcha

if __name__ == '__main__':
    headers = {
        "Host":"sso.toutiao.com",
        "Referer":"https://www.toutiao.com/",
        "User-Agent": 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36',
    }
    # if isLogin():
    #     print('login')
    # else:
    #     log = loginCase()
    #     log.login('', '', get_captcha())
    # log = loginCase()
    # log.setUp()
    # log.login('', get_captcha())


# def get_captcha():
#     url = 'https://sso.toutiao.com/login'
#     res = requests.get(url)
#     index_page = BeautifulSoup(res.text, 'html.parser')
