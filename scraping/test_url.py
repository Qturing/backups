import requests

ent_sina = 'http://ent.sina.com.cn/'

url = 'http://ent.sina.com.cn/s/h/2018-12-28/doc-ihqfskcn1919826.shtml'



res = requests.get(url)
print(res.status_code,res.url)