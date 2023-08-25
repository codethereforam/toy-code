# 单词助手GUI
import os
import tkinter as tk
from tkinter import filedialog

from word_helper import find_article_words_not_in_word_list


def generate_unfamiliar_word():
    selected_article = article_entry.get()
    # todo: 记住选择的生词路径
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
            with open(save_path, 'w') as combined_file:
                combined_file.write(unfamiliar_words_content)
            result_label.config(text="生词表保存成功")
        else:
            result_label.config(text="保存文件取消")
    except Exception as e:
        result_label.config(text=f"Error: {str(e)}")


# Create the main window
root = tk.Tk()
root.title("单词助手V1")

# Calculate the screen width and height
screen_width = root.winfo_screenwidth()
screen_height = root.winfo_screenheight()
# Calculate the x and y positions to center the window
x_position = (screen_width - root.winfo_reqwidth()) // 2
y_position = (screen_height - root.winfo_reqheight()) // 2
# Set the geometry to center the window
root.geometry(f"+{x_position}+{y_position}")


def select_article():
    article_entry.delete(0, tk.END)  # 清除已有内容
    article_entry.insert(0, filedialog.askopenfilename())
    article_entry.xview_moveto(1)  # Scroll to the end


# Create and place the widgets
article_button = tk.Button(root, text="选择文章", command=lambda: select_article())
article_entry = tk.Entry(root)
article_button.grid(row=0, column=0, padx=10, pady=10)
article_entry.grid(row=0, column=1, padx=10, pady=10)


def select_wordlist():
    wordlist_entry.delete(0, tk.END)  # 清除已有内容
    wordlist_entry.insert(0, filedialog.askopenfilename())
    wordlist_entry.xview_moveto(1)  # Scroll to the end


wordlist_button = tk.Button(root, text="选择单词表", command=lambda: select_wordlist())
wordlist_entry = tk.Entry(root)
wordlist_button.grid(row=1, column=0, padx=10, pady=10)
wordlist_entry.grid(row=1, column=1, padx=10, pady=10)

generate_button = tk.Button(root, text="生成生词表", bg="lightgray", command=generate_unfamiliar_word)
generate_button.grid(row=2, column=0, columnspan=2, padx=10, pady=10)  # Use columnspan to span both columns

result_label = tk.Label(root, text="")
result_label.grid(row=3, column=0, columnspan=2, padx=10, pady=10)  # Use columnspan to span both columns

# Center the generate_button and result_label horizontally
root.grid_rowconfigure(2, weight=1)
root.grid_rowconfigure(3, weight=1)

# Run the GUI event loop
root.mainloop()
