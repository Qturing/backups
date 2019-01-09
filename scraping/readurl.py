import pymysql
'''
从MySQL中读取指定时间的新闻数据,返回结果
'''
def readinfo(date):
    db =  pymysql.connect(host="127.0.0.1",user="root",passwd="123456",db="mypydb")
    cursor = db.cursor()
    sql = "select title,url from news where date='" + date + "'" 
    try:
        cursor.execute(sql)
        results = cursor.fetchall()  
    except:
        print("Error: unable to fetch data!")
    return results

def readtxt():
    url = []
    with open('D:\\qxf\\Temp\\news.csv','r') as f:
        for line in f:
            info = line.split(',')
            url.append(info[1])
    return url

# for info in readtxt():
#     print(info)

# 测试函数
# results = readinfo('2018-12-31')
# for info in results:
#     print(info[0])
#     print(info[1])


# db =  pymysql.connect(host="127.0.0.1",user="root",passwd="123456",db="mypydb")
# cursor = db.cursor()
# sql = "select title,url from news where date='2018-12-31'"
# try:
#     cursor.execute(sql)
#     results = cursor.fetchall()  
# except:
#     print("Error: unable to fetch data!")

# for info in results:
#     print(info[0])
#     print(info[1])
