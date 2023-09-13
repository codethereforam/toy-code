# 单词助手CLI

from python.word.word_helper import find_article_words_not_in_word_list

ARTICLE_FILE_PATH = "C:\\Users\\thinkam\\DATA\\GoogleDrive\\英语视频学习\\soho, new york\\soho, new york - 英语 (自动生成).txt"
MY_WORD_FILE_PATH = "C:\\Users\\thinkam\\Downloads\\my words.txt"
article_words_not_in_word_list = find_article_words_not_in_word_list(ARTICLE_FILE_PATH, MY_WORD_FILE_PATH)
print(article_words_not_in_word_list)