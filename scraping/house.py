from selenium import webdriver

driver = webdriver.Chrome()
url = 'http://ent.sina.com.cn/s/h/2018-12-28/doc-ihqfskcn1919827.shtml'
driver.get(url)
info = driver.find_element_by_tag_name('head')
title = info.find_element_by_tag_name('title')
keywords = info.find_element_by_name('keywords')
description = info.find_element_by_name('description')
article = driver.find_element_by_id('artibody')
pics = article.find_elements_by_css_selector('div.img_wrapper')
videos = article.find_elements_by_css_selector('div.video-2017')
contents = article.find_elements_by_tag_name('p')

print(title.text, keywords.text, description.text)
print(len(pics),'pics')
print(len(videos), 'videos')
for content in contents:
    print(content.text)

driver.close()