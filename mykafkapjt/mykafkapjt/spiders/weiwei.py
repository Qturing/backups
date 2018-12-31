# -*- coding: utf-8 -*-
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
from mykafkapjt.items import MykafkapjtItem

class WeiweiSpider(CrawlSpider):
    name = 'weiwei'
    allowed_domains = ['sina.com.cn']
    start_urls = ['https://news.sina.com.cn/']
    rules = (
        # Rule(LinkExtractor(allow=('.*?/[0-9]{4}.[0-9]{2}.[0-9]{2}.doc-.*?shtml'),allow_domains=('sina.com.cn')), callback='parse_item', follow=True),
        Rule(LinkExtractor(allow=('.*?/2018-12-31/doc-.*?shtml'),allow_domains=('sina.com.cn')), callback='parse_item', follow=True),

    )

    def parse_item(self, response):
        i = MykafkapjtItem()
        #print("hello")
        i["name"]=response.xpath("/html/head/meta[@property='og:title']/@content").extract()
        i["keywd"]=response.xpath("/html/head/meta[@name='keywords']/@content").extract()
        i["url"]=response.xpath("/html/head/meta[@property='og:url']/@content").extract()
        i["date"]="2018-12-31"
        print(i['name'],i['url'],i['date'])
        #print("world")
        #print(i)
        #i['domain_id'] = response.xpath('//input[@id="sid"]/@value').extract()
        #i['name'] = response.xpath('//div[@id="name"]').extract()
        #i['description'] = response.xpath('//div[@id="description"]').extract()
        yield i