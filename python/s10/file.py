# 从文件读取数据
with open('../words.txt') as file_object:
    lines = file_object.readlines()
#   for line in file_object:
#       print(line)
for line in lines:
    print(len(line))
# 写入文件

fileName = 'program.txt'
with open(fileName, 'w') as file_obj:
    file_obj.write("hello\nworld\n")
# 异常
# try except else
# pass

# 存储数据
import json
numbers = [2, 3, 5, 11, 13]
filename = 'numbers.json'
with open(filename, 'w') as f_obj:
    json.dump(numbers, f_obj)

with open(filename) as f_obj:
    numbers = json.load(f_obj)

print(numbers)
