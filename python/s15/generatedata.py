# 安装matplotlib
# 绘制简单的折线图
import matplotlib.pyplot as plt
input_value = [1, 2, 3, 4, 5]
squares = [1, 4, 9, 16, 25]
plt.plot(input_value, squares, linewidth=5)
# set title
plt.title("Squares Numbers", fontsize=24)
plt.xlabel("Value", fontsize=14)
plt.ylabel("Square of Value", fontsize=14)
# set mark
plt.tick_params(axis='both', labelsize=14)
plt.show()


# 使用Pygal模拟掷骰子