from bs4 import BeautifulSoup
import requests
import urllib
import os
import readurl
'''
输入url和存储路径，下载图片到指定路径
'''
def downloadpics(url,name,id):
    try:
        r = requests.get(url)
        with open('D:\\qxf\\Temp\\test\\'+ id + '\\' + str(name) + '.jpg', 'wb') as f:
            f.write(r.content) 
    except Exception as e:
        print(e) 


'''
生成内容
'''
def genText(title,contents, id):
    try:
        with open('D:\\qxf\\Temp\\test\\' + id + '\\content.txt', 'w', encoding="utf-8") as f:
            f.write(title.text + '\n')
            print(title.text+'\n')
            for content in contents:
                print(content.text+'\n')
                f.write(content.text + '\n')
    except Exception as e:
        print(id + ' cannot generate contents')
        print(e)


'''
生成图片
'''
def genPics(pics,id):
    try:
        for i in range(len(pics)):
            picsPath = 'http:' + pics[i].img['src']
            print(picsPath)
            try:
                downloadpics(picsPath, i ,id)
            except Exception as e:
                print(e)
    except:
        print(id + ' cannot generate pics')

def spidernews(url,id):
    res = requests.get(url)
    res.encoding = 'utf-8'
    web = BeautifulSoup(res.text, 'html.parser')
    os.makedirs('D:\\qxf\\Temp\\test\\'+ id, exist_ok=True)
    result = 0
    try:
        title = web.find('title')
        article = web.find('div',class_='article')
        pics = article.find_all('div', class_='img_wrapper')
        try:
            if len(pics) == 0:
                print(id +' no pics')
        except:
            pass
        # videos = article.find_all('div', class_='video-2017')
        contents = article.find_all('p')
        try:
            if len(contents) == 0:
                print(id + ' no contents')
        except:
            pass
        genText(title, contents, id)
        genPics(pics, id)
        print(id + " Done")
        result = 1
    except:        
        try:
            os.removedirs('D:\\qxf\\Temp\\test\\'+ id)
            print(id +" remove")
        except:
            print(id + 'cannot be remove')
        result = 0
    return result

url = 'https://news.sina.com.cn/w/2019-01-03/doc-ihqfskcn3548426.shtml'
spidernews(url,'test1')
print('Done')