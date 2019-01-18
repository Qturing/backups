# -*- coding: utf-8 -*-
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
from sinaEnt.items import SinaentItem
import time

class WeiweiSpider(CrawlSpider):
    name = 'spider'
    allowed_domains = ['sina.com.cn']
    start_urls = ['https://ent.sina.com.cn/']
    date = time.strftime('%Y-%m-%d',time.localtime(time.time()))

    rules = (
        # Rule(LinkExtractor(allow=('.*?/[0-9]{4}.[0-9]{2}.[0-9]{2}.doc-.*?shtml'),allow_domains=('sina.com.cn')), callback='parse_item', follow=True),
        Rule(LinkExtractor(allow=('.*?/'+ date +'/doc-.*?shtml'),allow_domains=('sina.com.cn')), callback='parse_item', follow=True),

    )

    def parse_item(self, response):
        i = SinaentItem()
        date = time.strftime('%Y-%m-%d',time.localtime(time.time()))
        i["name"]=response.xpath("/html/head/title/text()").extract()
        i["keywd"]=response.xpath("/html/head/meta[@name='keywords']/@content").extract()
        i["url"]=response.url
        i["date"]=date
        print(i['name'],i['url'])
        yield i