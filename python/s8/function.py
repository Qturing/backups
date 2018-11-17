# 定义函数
def greet_user(username):
    """显示问候语"""
    print("hello, " + username.title() + "!")


greet_user("jessi")

# 传递实参


# 返回值

def get_formatted_name(first_name, last_name, middle_name = ''):
    """返回整洁的姓名"""
    if middle_name:
        full_name = first_name + ' ' + middle_name + ' ' + last_name
    else:
        full_name = first_name + ' ' + last_name
    return full_name.title()


musician = get_formatted_name('jimi', 'hendrix')
print(musician)
musician = get_formatted_name('john', 'hooker', 'lee')
print(musician)

# 传递列表


def print_models(unprinted_designs, completed_models):
    while unprinted_designs:
        current_design = unprinted_designs.pop()
        print("Printing model: " + current_design)
        completed_models.append(current_design)


def show_completed_models(completed_model):
    print("\nThe following models have been printed:")
    for completed_model in completed_models:
        print(completed_model)


unprinted_designs = ['iphone_case', 'robot pendent', 'dodecahedron']
completed_models = []
print_models(unprinted_designs[:], completed_models)
show_completed_models(completed_models)
print(unprinted_designs)
# 传递任意数量的实参


def make_pizza(size, *toppings):
    print("\nMaking a " + str(size) + " inch pizza with the following topping")
    for topping in toppings:
        print("-" + topping)


make_pizza(13, 'mushrooms', 'green peppers', 'extra cheese')


def build_profile(first, last, **user_info):
    profile = {'first name': first, 'last name': last}
    for key, value in user_info.items():
        profile[key] = value
    return profile


user_profile = build_profile('albert', 'einstein', location='princeton', field='physics')
print(user_profile)
# 将函数存储在模块中


# 函数编写指南
