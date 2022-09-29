"""
Cade Theriault
101 192 252
"""

# 1 - The key should be the continent and the number of students should be the
# value.


dict = { 'America':100, 'Asia':2000, 'Europe':3000, 'Africa':1000 } 

if 'Europe' in dict.keys():
    print('Europe is in the dictionary.')
else:
    print('Europe is not in the dictinoary.')

(keys, values) = dict.items()
if 'Europe' in keys:
    print('Europe is in the dictionary.')
else:
    print('Europe is not in the dictinoary.')    