import os

fileName = '/tmp/testPyIo.txt'
if os.path.exists(fileName):
    os.remove(fileName)

try:
    file = open(fileName, 'w')
    file.write('hello\n')
finally:
    if file:
        file.close()

with open(fileName, 'a') as file:
    file.write('Hello, world!\n')

with open(fileName, 'r') as file:
    print(file.read())

print('-----------')

with open(fileName, 'r') as file:
    for line in file.readlines():
        print(line.strip())

print('------------')

from io import StringIO
stringIO = StringIO()
stringIO.write('hello')
stringIO.write(' ')
stringIO.write('world')
print(stringIO.getvalue())
