# 遍历列表for
magicians = ['alice', 'david', 'carolina']
for magician in magicians:
    print(magician)
    print(magician.title())
print("Thank you")

# 缩进

# 数值列表
for value in range(1, 5):
    print(value)
numbers = list(range(1, 5))
print(numbers)

even_numbers = list(range(2, 11, 2))
print(even_numbers)
print(min(even_numbers))
print(max(even_numbers))
print(sum(even_numbers))

squares = [value**2 for value in range(1, 11)]
print(squares)

print(sum(range(1, 101)))

# 使用列表的一部分
# 切片
players = ['charles', 'martina', 'michael', 'florence', 'eli']
print(players[0:3])
print(players[1:4])
print(players[-3:])
# 遍历切片
for player in players[-3:]:
    print(player.title())
# 复制列表
cp_players = players[:]
cp_players.append('cannoli')
print(cp_players)
print(players)

# 元组
dimensions = (200, 50)
for dimension in dimensions:
    print(dimension)



