# 一个简单的字典
alien_0 = {'color': 'green', 'point': 5}
print(alien_0['color'])
print(alien_0['point'])
print(alien_0)
# 使用字典
alien_0['x_position'] = 0
alien_0['y_position'] = 25
print(alien_0)
del alien_0['point']
print(alien_0)
# 遍历字典
for key, value in alien_0.items():
    print('\nKey: ' + key)
    print('Value: ' + str(value))
for propertie in alien_0.keys():
    print(propertie)
# 嵌套
# 字典列表
# 在字典中存储列表
# 在字典中存储字典
