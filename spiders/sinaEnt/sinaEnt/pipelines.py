# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html


class SinaentPipeline(object):
    def process_item(self, item, spider):
        with open('D:\\qxf\\Temp\\sinaents.csv','a') as f:
            f.write(item['name'][0] + "," + item['url'] + '\n') 
        print('--------------')
        return item
