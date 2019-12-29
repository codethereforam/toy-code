dx = 0.000001
def d(f) :
    return lambda x : (f(x+dx) - f(x)) / dx
f = lambda x : x**2 + x + 1
f1 = d(f)
print(f1(3))
f2 = d(f1)
print(f2(1))