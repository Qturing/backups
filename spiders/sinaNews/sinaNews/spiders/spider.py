# -*- coding: utf-8 -*-
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
from sinaNews.items import SinanewsItem

class WeiweiSpider(CrawlSpider):
    name = 'spider'
    allowed_domains = ['sina.com.cn']
    start_urls = ['https://news.sina.com.cn/']
    rules = (
        # Rule(LinkExtractor(allow=('.*?/[0-9]{4}.[0-9]{2}.[0-9]{2}.doc-.*?shtml'),allow_domains=('sina.com.cn')), callback='parse_item', follow=True),
        Rule(LinkExtractor(allow=('.*?/2019-01-07/doc-.*?shtml'),allow_domains=('sina.com.cn')), callback='parse_item', follow=True),

    )

    def parse_item(self, response):
        i = SinanewsItem()
        i["name"]=response.xpath("/html/head/title/text()").extract()
        i["keywd"]=response.xpath("/html/head/meta[@name='keywords']/@content").extract()
        i["url"]=response.url
        i["date"]="2019-01-07"
        print(i['name'],i['url'])
        yield i