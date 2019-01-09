import requests
import http.cookiejar as cookielib

session = requests.session()
session.cookies = cookielib.LWPCookieJar(filename='cookies')
try:
     session.cookies.load(ignore_discard=True)
except:
    print("Cookie cannot be loaded")

def isLogin():
    url = "http://www.santostang.com/wp-login.php/profile.php"
    login_code = session.get(url, headers=headers, allow_redirects=False).status_code
    if login_code == 200:
        return True
    else:
        return False

def login(secret, account):
    post_url = 'http://www.santostang.com/wp-login.php'
    postdata = {
        'pwd':'a12345',
        'log':'test',
        'rememberme':'forever',
        'redirect_to':'http://www.santostang.com/wp-admin/',
        'testcookie': 1,
    }
    try:
        login_page = session.post(post_url, data=postdata, headers=headers)
        # login_code = login_page.text
        # # print(login_code)
        print(login_page.status_code)
    except:
        pass
    session.cookies.save()

if __name__ == '__main__':
    headers = {
        "Host":"www.santostang.com",
        "Origin":"http://www.santostang.com",
        "Referer":"http://www.santostang.com/wp-admin/load-styles.php?c=0&dir=ltr&load%5B%5D=dashicons,buttons,forms,l10n,login&ver=4.9.9",
        "User-Agent": 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36',
    }
    if isLogin():
        print('login')
    else:
        login('a12345', 'test')
