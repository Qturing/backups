from urllib import request
from urllib import error
from urllib import parse
from http import cookiejar



# Save cookie to var
def save_cookie(url):
    cookie = cookiejar.CookieJar()
    handler = request.HTTPCookieProcessor(cookie)
    opener = request.build_opener(handler)
    response = opener.open(url)
    for item in cookie:
        print("Name = %s" % item.name)
        print("Value = %s" % item.value)
    
# save cookie file
def save_cookie_file(url):
    filename = "cookie.txt"
    cookie = cookiejar.MozillaCookieJar(filename)
    handler = request.HTTPCookieProcessor(cookie)
    opener = request.build_opener(handler)
    response = opener.open(url)
    cookie.save(ignore_discard=True, ignore_expires=True)

# read cookie file
def read_cookie_url(url):
    filename = "cookie.txt"
    cookie = cookiejar.MozillaCookieJar()
    cookie.load(filename, ignore_discard=True, ignore_expires=True)
    handler = request.HTTPCookieProcessor(cookie)
    opener = request.build_opener(handler)
    response = opener.open(url)
    print(response.read().decode('utf-8'))

if __name__ == '__main__':
    login_url = 'http://www.baidu.com'
    user_agent = r'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36'
    headers = {
        "Host":"sso.toutiao.com",
        "Referer":"https://www.toutiao.com/",
        "User-Agent":user_agent
    }
    login_data = {}
    login_data['action'] = ''
    login_data['redirect_url'] = ''
    login_data['remember_me'] = ''
    login_data['user_login'] = ''
    login_data['user_pass'] = ''

    logingpostdata = parse.urlencode(login_data).encode('utf-8')