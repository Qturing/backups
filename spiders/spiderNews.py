from bs4 import BeautifulSoup
import requests
import urllib
import os





'''
输入url和存储路径，下载图片到指定路径
'''
def downloadpics(url,name,id, website):
    try:
        r = requests.get(url)
        with open('D:\\qxf\\Temp\\'+ website +'\\'+ id + '\\' + str(name) + '.jpg', 'wb') as f:
            f.write(r.content) 
    except Exception as e:
        print(e) 


'''
生成内容
'''
def genText(title, contents, id, website):
    if len(contents) == 0:
        print('no contents')
    try:
        with open('D:\\qxf\\Temp\\'+ website +'\\' + id + '\\content.txt', 'w', encoding="utf-8") as f:
            f.write(title.text + '\n')
            print(title.text+'\n')
            for content in contents:
                f.write(content.text + '\n')
                print(content.text+'\n')
    except Exception as e:
        print(e)


'''
生成图片
'''
def genPics(pics,id,website):
    if len(pics) == 0:
        print('no pics')
    try:
        for i in range(len(pics)):
            picsPath = 'http:' + pics[i].img['src']
            print(picsPath)
            try:
                downloadpics(picsPath, i ,id,website)
            except Exception as e:
                print(e)
    except:
        print(id + ' cannot generate pics')


'''
输入url,获取新闻，存储到固定路径
'''
def spidernews(url,id,website):
    res = requests.get(url)
    res.encoding = 'utf-8'
    web = BeautifulSoup(res.text, 'html.parser')
    os.makedirs('D:\\qxf\\Temp\\'+ website +'\\'+ id, exist_ok=True)
    # print('D:\\qxf\\Temp\\'+ website +'\\'+ id)
    result = 0
    try:
        title = web.find('title')
        article = web.find('div',class_='article')
        pics = article.find_all('div', class_='img_wrapper')
        # videos = article.find_all('div', class_='video-2017')
        contents = article.find_all('p')
        genText(title, contents, id, website)
        genPics(pics, id, website)
        print(id + " Done")
        result = 1
    except Exception as e:        
        try:
            os.removedirs('D:\\qxf\\Temp\\'+ website +'\\'+ id)
            print(id +" remove")
        except Exception as e:
            print(id + ' cannot be removed. ')
            print(e)
        print(e)
        result = 0
    return result
    # print("All Done!")

# date = '2019-01-02'
# results = readurl.readinfo(date)

