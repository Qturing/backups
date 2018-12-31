from selenium import webdriver
from selenium.webdriver.firefox.firefox_binary import FirefoxBinary
import time

driver = webdriver.Chrome()
driver.get("http://www.santostang.com/2018/07/04/hello-world/")
print(driver.title)
driver.close()