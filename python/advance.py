map = {'a': 1, 'b': 2, 'c': 3}
for key in map:
    print(key)
for v in map.values():
    print(v)
for k, v in map.items():
    print(k, '-', v)
L = [k + '=' + str(v) for k, v in map.items()]
print(L)

for ch in 'abc':
    print(ch)

for i, v in enumerate(['A', 'B', 'C']):
    print(i, '-', v)

L = []
for x in range(1, 11):
    L.append(x * x)
print(L)
L = [x * x for x in range(1, 11)]
print(L)
L = [x * x for x in range(1, 11) if x % 2 == 0]
print(L)
L = [m + n for m in 'ABC' for n in '123']
print(L)

import os
L = [x for x in os.listdir('.')]
print(L)

g = (x * x for x in range(1, 11))
for n in g:
    print(n) 

def fib(max):
    n, a, b = 0, 0, 1
    while n < max:
        yield b
        a, b = b, a + b
        n = n + 1
    return 'done'
g = fib(6)
for n in g:
    print(n)
g = fib(6)
while True:
    try:
        x = next(g)
        print('g: ', x)
    except StopIteration as e:
        print('Generator return value:', e.value)
        break
