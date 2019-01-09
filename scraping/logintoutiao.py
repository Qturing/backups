from bs4 import BeautifulSoup
import requests
from PIL import Image
import http.cookiejar as cookielib
import os
import json
import chardet
import base64


session = requests.session()
session.cookies = cookielib.LWPCookieJar(filename='cookies')
try:
     session.cookies.load(ignore_discard=True)
except:
    print("Cookie cannot be loaded")

def isLogin():
    url = "https://mp.toutiao.com/profile_v3/index"
    login_code = session.get(url, headers=headers, allow_redirects=False).status_code
    if login_code == 200:
        return True
    else:
        return False

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
    if isLogin():
        print('login')
    else:
        login('18537372504', 'KFqxf9201', get_captcha())


# def get_captcha():
#     url = 'https://sso.toutiao.com/login'
#     res = requests.get(url)
#     index_page = BeautifulSoup(res.text, 'html.parser')
