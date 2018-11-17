# from car import Car, ElectricCar
# import car
from car import *

my_beetle = Car('volkswagon', 'beetle', 2016)
print(my_beetle.get_descriptive_name())

my_tesla = ElectricCar('tesla', 'model s', '2016')
print(my_tesla.get_descriptive_name())