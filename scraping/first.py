import requests
from bs4 import BeautifulSoup

link = "https://news.sina.com.cn/c/"
year = '2018-12-26'
doc = 'doc-ihqhqcis'
urls = []

headers = {
    'User-Agent':'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.62 Safari/537.36',
    'Host':'news.163.com'
}

url = 'http://news.sina.com.cn/c/2018-12-26/doc-ihqhqcis0571944.shtml'
r = requests.get(url, headers=headers)
print(r.status_code)
# for i in range(571900,572000):
#     id = str(i).zfill(7)
#     url = link + year + '/' + doc + id +'.shtml'
#     urls.append(url)
#     print(url)
#     r = requests.get(url, headers=headers)
#     print(r.status_code)



    

# r = requests.get(link, headers=headers)
# r.encoding = 'utf-8'
# soup = BeautifulSoup(r.text,"html.parser")
# elements = soup.find('div',class_='right-content')
# print(elements)