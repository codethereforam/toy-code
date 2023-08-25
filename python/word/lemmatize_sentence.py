# 参考: https://gaurav5430.medium.com/using-nltk-for-lemmatizing-sentences-c1bfff963258

import re

import nltk
from nltk.stem import WordNetLemmatizer
from nltk.corpus import wordnet
from typing import Set

WORD_PATTERN = re.compile("\\b[a-zA-Z'-]+\\b")
EXCLUDE_WORD_WITH_SUFFIX = ["'t", "'m", "'re", "'d", "'ve", "'ll"]

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
    lemmatized_sentence = []
    for word, tag in wordnet_tagged:
        if tag is None:
            # if there is no available tag, append the token as is
            converted_word = word
        else:
            # else use the tag to lemmatize the token
            converted_word = lemmatizer.lemmatize(word, tag)
        lemmatized_sentence.append(converted_word.lower())
    return lemmatized_sentence


# @deprecated 是否是需要的单词
def is_desired_word(word):
    for suffix in EXCLUDE_WORD_WITH_SUFFIX:
        if word.endswith(suffix):
            return False
    return True


def lemmatize_sentence(sentence):
    return " ".join(get_lemmatized_word_list(sentence))


# print(lemmatizer.lemmatize("I am loving it")) #I am loving it
# print(lemmatizer.lemmatize("loving")) #loving
# print(lemmatizer.lemmatize("loving", "v")) #love
# print(lemmatize_sentence("I am loving it")) #I be love it
# print(lemmatize_sentence("representing shopping"))

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


ARTICLE_FILE = "C:\\Users\\thinkam\\DATA\\GoogleDrive\\英语视频学习\\soho, new york\\soho, new york - 英语 (自动生成).txt"
WORD_FILE = "C:\\Users\\thinkam\\Downloads\\my words.txt"

unique_word_set = get_unique_word_set(WORD_FILE)
word_list = extract_word_from_article(ARTICLE_FILE)
# print("\n".join(unique_words_set))
# print("\n".join(word_list)

print("\n".join(find_elements_not_in_set(word_list, unique_word_set)))
