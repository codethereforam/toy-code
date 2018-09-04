def f(x):
    return x * x
def add(x, y , f):
    return f(x) + f(y)
print(add(2, 3, f))