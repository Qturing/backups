bicycle = ['trek', 'cannondale','redline','specialized']
print(bicycle)
print(bicycle[0])
print(bicycle[1])
print(bicycle[3])
print(bicycle[-1])
message = "My first bicycle was a " + bicycle[0].title() + "."
print(message)
bicycle.append('ducati')
print(bicycle)
bicycle.insert(0, 'ducati')
print(bicycle)
del bicycle[2]
print(bicycle)
poped_motocycle = bicycle.pop()
print(bicycle)
print(poped_motocycle)
bicycle.remove('ducati')
print(bicycle)
bicycle.sort(reverse=True)
print(bicycle)
print(sorted(bicycle))
bicycle.reverse()
print(bicycle)
print(len(bicycle))