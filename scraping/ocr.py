from PIL import Image
import pytesseract

im = Image.open('admin.gif')
'''
转化为灰度图像
'''
gray = im.convert('L')
# gray.show()
'''
二值化处理
'''
threshold = 180
table = []
for i in range(256):
    if i < threshold:
        table.append(0)
    else:
        table.append(1)
out = gray.point(table,'1')
out.show()
out.save("capcha_threshold.gif")

'''
图像识别
'''
th = Image.open('capcha_threshold.gif')
print(pytesseract.image_to_string(th))