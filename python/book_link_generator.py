import os
import re

book_file = "C:/Users/thinkam/Dropbox/private/backup/书籍.md"  # 你的书籍列表文件
book_dir = "D:/reading"  # 你的书籍目录
output_file = "C:/Users/thinkam/Dropbox/private/backup/书籍目录.md"  # 输出的Markdown文件

# 打开新的Markdown文件
with open(output_file, 'w', encoding='utf-8') as f_out:
    # 从书籍列表文件中读取书名和标题
    with open(book_file, 'r', encoding='utf-8') as f_in:
        for line in f_in:
            line = line.strip()
            if line == '':
                f_out.write('\n')
            elif line.startswith('#'):
                f_out.write(line + '\n')
            else:
                # 判断书名是否已经带有链接
                match = re.match(r'\[(.*?)\]\((.*?)\)', line)
                if match:
                    f_out.write(line + '\n')
                else:
                    book_name = line.lstrip('- ')
                    # 遍历书籍目录，查找书的路径
                    found = False
                    for root, dirs, files in os.walk(book_dir):
                        for file in files:
                            if file == book_name:
                                file_path = os.path.join(root, file)
                                file_link = file_path.replace('\\', '/')
                                f_out.write(f'- [{file}](file://{file_link})\n')
                                found = True
                                break
                        if found:
                            break
                    if not found:
                        f_out.write(f'- {book_name}\n')
