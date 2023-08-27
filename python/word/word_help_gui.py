# 单词助手GUI
# 打包命令:  pyinstaller --onefile --noconsole --name 单词助手V1 .\word_help_gui.py

import os
import tkinter as tk
import webbrowser
from tkinter import filedialog

from word_helper import find_article_words_not_in_word_list

CONFIG_FILE_NAME = ".word_help"
CONFIG_ARTICLE_PATH = "article_path"
CONFIG_WORDLIST_PATH = "wordlist_path"

# 生成的生词文件路径
unfamiliar_word_path = ''


def get_config_path():
    return os.path.expanduser("~/%s" % CONFIG_FILE_NAME)


def load_config():
    config_path = get_config_path()
    config = {}

    if os.path.exists(config_path):
        with open(config_path, "r", encoding="utf-8") as f:
            for line in f:
                key, value = line.strip().split("=")
                config[key] = value

    return config


def save_config(config_key, config_value):
    if not config_key or not config_value:
        return

    config = load_config()
    config[config_key] = config_value

    with open(get_config_path(), "w", encoding="utf-8") as f:
        for key, value in config.items():
            f.write(f"{key}={value}\n")


def generate_unfamiliar_word():
    selected_article = article_entry.get()
    selected_wordlist = wordlist_entry.get()

    if not selected_article or not selected_wordlist:
        result_label.config(text="请选择两个文件")
        return

    try:
        unfamiliar_words_content = find_article_words_not_in_word_list(selected_article, selected_wordlist)
        if not unfamiliar_words_content.strip():
            result_label.config(text="选择的文章中没有生词")
            return

        # Generate the initial_unfamiliar_words_filename
        selected_article_base_name = os.path.basename(selected_article)
        selected_article_file_name, selected_article_file_extension = os.path.splitext(selected_article_base_name)
        initial_unfamiliar_words_filename = f"{selected_article_file_name}_生词{selected_article_file_extension}"

        save_path = filedialog.asksaveasfilename(initialfile=initial_unfamiliar_words_filename,
                                                 initialdir=os.path.dirname(selected_article),
                                                 defaultextension=".txt",
                                                 filetypes=[("Text files", "*.txt")])

        if save_path:
            with open(save_path, 'w', encoding="utf-8") as combined_file:
                combined_file.write(unfamiliar_words_content)
            global unfamiliar_word_path
            unfamiliar_word_path = save_path
            result_label.config(text="生词表保存成功")
        else:
            result_label.config(text="保存文件取消")
    except Exception as e:
        result_label.config(text=f"Error: {str(e)}")


def open_unfamiliar_word():
    if unfamiliar_word_path.strip():
        webbrowser.open(unfamiliar_word_path)
    else:
        result_label.config(text="请先生成生词表")


# Create the main window
root = tk.Tk()
root.title("单词助手V1")

# Load the saved paths from the config
saved_config = load_config()
saved_article_path = saved_config.get(CONFIG_ARTICLE_PATH, "")
saved_wordlist_path = saved_config.get(CONFIG_WORDLIST_PATH, "")

# Calculate the screen width and height
screen_width = root.winfo_screenwidth()
screen_height = root.winfo_screenheight()
# Calculate the x and y positions to center the window
x_position = (screen_width - root.winfo_reqwidth()) // 2
y_position = (screen_height - root.winfo_reqheight()) // 2
# Set the geometry to center the window
root.geometry(f"+{x_position}+{y_position}")


def select_file(entry, entry_config_key, open_filename_title):
    entry_text = entry.get()
    if entry_text:
        file_path = filedialog.askopenfilename(initialdir=os.path.dirname(entry_text),
                                               initialfile=os.path.basename(entry_text),
                                               title=open_filename_title)
    else:
        file_path = filedialog.askopenfilename()

    if file_path:
        entry.delete(0, tk.END)  # 清除已有内容
        entry.insert(0, file_path)
        entry.xview_moveto(1)  # Scroll to the end
        save_config(entry_config_key, file_path)


# Create and place the widgets
article_button = tk.Button(root, text="选择文章",
                           command=lambda: select_file(article_entry, CONFIG_ARTICLE_PATH, "选择文章"))
article_entry = tk.Entry(root)
article_entry.insert(0, saved_article_path)
article_entry.xview_moveto(1)  # Scroll to the end
article_button.grid(row=0, column=0, padx=10, pady=10)
article_entry.grid(row=0, column=1, padx=10, pady=10)

wordlist_button = tk.Button(root, text="选择单词表",
                            command=lambda: select_file(wordlist_entry, CONFIG_WORDLIST_PATH, "选择单词表"))
wordlist_entry = tk.Entry(root)
wordlist_entry.insert(0, saved_wordlist_path)
wordlist_entry.xview_moveto(1)  # Scroll to the end
wordlist_button.grid(row=1, column=0, padx=10, pady=10)
wordlist_entry.grid(row=1, column=1, padx=10, pady=10)

generate_button = tk.Button(root, text="生成生词表", bg="lightgray", command=generate_unfamiliar_word)
open_unfamiliar_button = tk.Button(root, text="打开生词表", bg="lightgray", command=open_unfamiliar_word)
generate_button.grid(row=2, column=0, padx=10, pady=10)  # Use columnspan to span both columns
open_unfamiliar_button.grid(row=2, column=1, padx=10, pady=10)  # Use columnspan to span both columns

result_label = tk.Label(root, text="")
result_label.grid(row=3, column=0, columnspan=2, padx=10, pady=10)  # Use columnspan to span both columns
root.grid_rowconfigure(3, weight=1)  # Center result_label horizontally

# Run the GUI event loop
root.mainloop()
