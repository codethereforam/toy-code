# 单词助手GUI

import tkinter as tk
from tkinter import filedialog

from python.word.word_helper import find_article_words_not_in_word_list


def generate_unfamiliar_word():
    selected_article = article_entry.get()
    selected_wordlist = wordlist_entry.get()

    if not selected_article or not selected_wordlist:
        result_label.config(text="请选择两个文件")
        return

    try:
        to_save_text = find_article_words_not_in_word_list(selected_article, selected_wordlist)
        save_path = filedialog.asksaveasfilename(defaultextension=".txt", filetypes=[("Text files", "*.txt")])

        if save_path:
            with open(save_path, 'w') as combined_file:
                combined_file.write(to_save_text)
            result_label.config(text="生词表保存成功")
        else:
            result_label.config(text="保存文件取消")
    except Exception as e:
        result_label.config(text=f"Error: {str(e)}")


# Create the main window
root = tk.Tk()
root.title("单词助手V1")

# Create and place the widgets
article_label = tk.Label(root, text="选择文章:")
article_label.pack()

article_entry = tk.Entry(root)
article_entry.pack()

article_button = tk.Button(root, text="选择文件", command=lambda: article_entry.insert(0, filedialog.askopenfilename()))
article_button.pack()

wordlist_label = tk.Label(root, text="选择单词表:")
wordlist_label.pack()

wordlist_entry = tk.Entry(root)
wordlist_entry.pack()

wordlist_button = tk.Button(root, text="选择文件", command=lambda: wordlist_entry.insert(0, filedialog.askopenfilename()))
wordlist_button.pack()

generate_button = tk.Button(root, text="生成生词表", command=generate_unfamiliar_word)
generate_button.pack()

result_label = tk.Label(root, text="")
result_label.pack()

# Run the GUI event loop
root.mainloop()
