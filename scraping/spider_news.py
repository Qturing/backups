# from selenium import webdriver

# driver = webdriver.Chrome()
# url = 'http://ent.sina.com.cn/s/h/2018-12-28/doc-ihqfskcn1919827.shtml'
# driver.get(url)
# title = driver.find_element_by_css_selector('h1.main-title')
# article = driver.find_element_by_id('artibody')
# pics = article.find_elements_by_css_selector('div.img_wrapper')
# videos = article.find_elements_by_css_selector('div.video-2017')
# contents = article.find_elements_by_tag_name('p')

# print(title.text)
# print(len(pics),'pics')
# print(len(videos), 'videos')
# for content in contents:
#     print(content.text)

# driver.close()

from bs4 import BeautifulSoup
import requests
import urllib
import os
import readurl

'''
输入url和存储路径，下载图片到指定路径
'''
def downloadpics(url,name,id):
    r = requests.get(url)
    with open('D:\\qxf\\Temp\\'+ id + '\\' + str(name) + '.jpg', 'wb') as f:
        f.write(r.content)  

def genText(title,contents, id):
    with open('D:\\qxf\\Temp\\' + id + '\\content.txt', 'w') as f:
        f.write(title.text + '\n')
        for content in contents[:-1]:
            f.write(content.text + '\n')
def genPics(pics,id):
    picsPath = []
    for i in range(len(pics)):
        picsPath.append('http:' + pics[i].img['src'])
    imgName = 0
    for imgPath in picsPath:
        try:
            downloadpics(imgPath, imgName,id)
        except Exception as e:
            # print(imgPath+" error")
            print(e)
        imgName += 1

'''
输入url,获取新闻，存储到固定路径
'''
def spidernews(url,id):
    res = requests.get(url)
    res.encoding = 'utf-8'
    web = BeautifulSoup(res.text, 'html.parser')
    result = 0
    try:
        title = web.find('h1', class_='main-title')
        article = web.find(id='artibody')
        pics = article.find_all('div', class_='img_wrapper')
        # videos = article.find_all('div', class_='video-2017')
        contents = article.find_all('p')
        os.makedirs('D:\\qxf\\Temp\\'+ id, exist_ok=True)
        genText(title, contents, id)
        genPics(pics, id)
        print(id + " Done")
        result = 1
    except:
        print("error")
        result = 0
    return result
    # print("All Done!")


results = readurl.readinfo('2018-12-31')
count = 1
for info in results:
    spidernews(info[1], str(count))
    count = count + 1
print("All Done!")
# url = 'http://ent.sina.com.cn/s/h/2018-12-28/doc-ihqfskcn1919827.shtml'

# res = requests.get(url)
# res.encoding = 'utf-8'
# web = BeautifulSoup(res.text, 'html.parser')
# title = web.find('h1', class_='main-title')
# os.makedirs('D:\\qxf\\Temp\\'+ title.text, exist_ok=True)
# article = web.find(id='artibody')
# pics = article.find_all('div', class_='img_wrapper')
# videos = article.find_all('div', class_='video-2017')
# contents = article.find_all('p')

# with open('D:\\qxf\\Temp\\'+ title.text + '\\content.txt', 'w') as f:
#     for content in contents[:-1]:
#         f.write(content.text + '\n')

# picsPath = []
# for i in range(len(pics)):
#     picsPath.append('http:' + pics[i].img['src'])


# imgName = 0
# for imgPath in picsPath:
#     try:
#         downloadpics(imgPath, imgName)
#     except Exception as e:
#         print(imgPath+" error")
#     imgName += 1

# print("All Done!")


