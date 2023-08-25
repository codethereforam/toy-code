# 单词助手：从文章中找出不在我的词汇表中的单词
# 有两个文件和词汇表
# 词汇表格式：每行一个单词，然后换行
# 文章格式：多行或者一行都可以
# lemmatize参考: https://gaurav5430.medium.com/using-nltk-for-lemmatizing-sentences-c1bfff963258

import re
import nltk
from nltk.stem import WordNetLemmatizer
from nltk.corpus import wordnet
from typing import Set

WORD_PATTERN = re.compile("\\b[a-zA-Z'-]+\\b")
lemmatizer = WordNetLemmatizer()


# function to convert nltk tag to wordnet tag
def nltk_tag_to_wordnet_tag(nltk_tag):
    if nltk_tag.startswith('J'):
        return wordnet.ADJ
    elif nltk_tag.startswith('V'):
        return wordnet.VERB
    elif nltk_tag.startswith('N'):
        return wordnet.NOUN
    elif nltk_tag.startswith('R'):
        return wordnet.ADV
    else:
        return None


def get_lemmatized_word_list(sentence):
    # tokenize the sentence and find the POS tag for each token
    nltk_tagged = nltk.pos_tag(nltk.word_tokenize(sentence))
    # tuple of (token, wordnet_tag)
    wordnet_tagged = map(lambda x: (x[0], nltk_tag_to_wordnet_tag(x[1])), nltk_tagged)
    lemmatized_word_list = []
    for word, tag in wordnet_tagged:
        if tag is None:
            # if there is no available tag, append the token as is
            converted_word = word
        else:
            # else use the tag to lemmatize the token
            converted_word = lemmatizer.lemmatize(word, tag)
        lemmatized_word_list.append(converted_word.lower())
    return lemmatized_word_list


def get_unique_ordered_lemmatized_word_list(sentence):
    return list(dict.fromkeys(get_lemmatized_word_list(sentence)))


def get_unique_word_set(file_path: str) -> Set[str]:
    with open(file_path, "r", encoding="utf-8") as file:
        lines = [line.strip().lower() for line in file if line.strip()]
    return set(lines)


def extract_word_from_article(file_path):
    with open(file_path, 'r', encoding="utf-8") as file:
        lines = file.readlines()
    # 读取多行文本文件到一行，用空格分隔，否则每行末尾的单词处理可能有问题
    merged_line_article = ' '.join(line.strip() for line in lines if line.strip())
    unique_ordered_lemmatized_word_list = get_unique_ordered_lemmatized_word_list(merged_line_article)
    # 过滤掉非单词
    return [word for word in unique_ordered_lemmatized_word_list if WORD_PATTERN.match(word)]


def find_elements_not_in_set(input_list, input_set):
    result = [word for word in input_list if word.strip() and word.strip().lower() not in input_set]
    return result


# ======== main ========

ARTICLE_FILE_PATH = "C:\\Users\\thinkam\\DATA\\GoogleDrive\\英语视频学习\\soho, new york\\soho, new york - 英语 (自动生成).txt"
MY_WORD_FILE_PATH = "C:\\Users\\thinkam\\Downloads\\my words.txt"

article_word_list = extract_word_from_article(ARTICLE_FILE_PATH)
my_word_set = get_unique_word_set(MY_WORD_FILE_PATH)

# print("\n".join(article_word_list))
# print("\n".join(my_word_set))
print("\n".join(find_elements_not_in_set(article_word_list, my_word_set)))
