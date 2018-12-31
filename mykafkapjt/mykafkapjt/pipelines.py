# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html

# from scrapy.utils.serialize import ScrapyJSONEncoder
# from kafka.client import KafkaClient
# from kafka.producer import SimpleProducer
import json
# from kafka import KafkaProducer


class MykafkapjtPipeline(object):
    
   # def __init__(self,producer,topic):
   #    self.producer = producer
   #     self.topic = topic
   #     self.encoder =ScrapyJSONEncoder()
        
   # def process_item(self,item,spider):
   #     item = dict(item)
   #     item['spider'] = spider.name
   #     msg = self.encoder.encode(item)
   #     self.producer.send_message(self.topic,msg)
    
   #  def from_setting(cls,settings):
   #    k_hosts = settings.get('SCRAPY_KAFKA_HOSTS',['localhost:9092'])
   #     topic = settings.get('SCRAPY_KAFKA_ITEM_PIPELINE_TOPIC','scrapy_kafka_item')
   #     kafka = KafkaClient(k_hosts)
   #     conn = SimpleProducer(kafka)
   #     return cls(conn,topic)

    # def __init__(self):
    #     self.producer = KafkaProducer(bootstrap_servers='localhost:9092')
    #     print("init is ok kafka")

    # def process_item(self, item, spider):
         
    #      print("hello-kafka")
         
    #      #for i in range(100):
    #      msg_dict = {
    #         "sleep_time": 10,
    #         "value": item["name"][0]
    #      }
    #      print("msg_dict=")
    #      print(msg_dict)
    #      msg_str = json.dumps(msg_dict)   
    #      msg = str.encode(msg_str) 
    #      self.producer.send('test_ptt',msg)
         
    #      print("world-kafka")
    #      return item

    # def close_spider(self,spider):
    #     self.producer.close()
    def process_item(self,item,spider):
        # print(item['name'])
        # print(item['keywd'])
        print('--------------')
        return item


import pymysql
class MysqlpjtPipeline(object):
    def __init__(self):
        self.conn = pymysql.connect(host="127.0.0.1",user="root",passwd="123456",db="mypydb")
        print("init is ok")
    
    def process_item(self, item, spider):
       
        # print("hello-mysql")
        name = item["name"][0]
        #print(item["name"][0])
        #print(name)
        key = item["keywd"][0]
        url = item["url"][0]
        date = item["date"]
        # sql = "insert into mytb_1(title,keywd) values('"+name+"','"+key+"')"
        sql = "insert into news(title,keywd,url,date) values('" + name + "','" + key + "','" + url + "','" + date + "')"
        #print(sql)
        #conn1.query("INSERT INTO mytb2(title,keywd) VALUES('second title','secondkeywd')")
        self.conn.query(sql)
        self.conn.commit()
        #print("world-mysql")
        #print(item)
        return item
    def close_spider(self,spider):
        self.conn.close()


