# 参考: https://gaurav5430.medium.com/using-nltk-for-lemmatizing-sentences-c1bfff963258

import re
import nltk
from nltk.stem import WordNetLemmatizer
from nltk.corpus import wordnet

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

def lemmatize_sentence_list(sentence):
    #tokenize the sentence and find the POS tag for each token
    nltk_tagged = nltk.pos_tag(nltk.word_tokenize(sentence))
    #tuple of (token, wordnet_tag)
    wordnet_tagged = map(lambda x: (x[0], nltk_tag_to_wordnet_tag(x[1])), nltk_tagged)
    lemmatized_sentence = []
    for word, tag in wordnet_tagged:
        if tag is None:
            #if there is no available tag, append the token as is
            converted_word = word
        else:
            #else use the tag to lemmatize the token
            converted_word = lemmatizer.lemmatize(word, tag)
        # todo!: 在这里移除 's 等
        lemmatized_sentence.append(converted_word.lower())
    return lemmatized_sentence

def lemmatize_sentence(sentence):
    return " ".join(lemmatize_sentence_list(sentence))

def lemmatize_sentence_set(sentence):
    return list(dict.fromkeys(lemmatize_sentence_list(sentence)))


# print(lemmatizer.lemmatize("I am loving it")) #I am loving it
# print(lemmatizer.lemmatize("loving")) #loving
# print(lemmatizer.lemmatize("loving", "v")) #love
# print(lemmatize_sentence("I am loving it")) #I be love it
# print(lemmatize_sentence("representing shopping"))

with open("C:\\Users\\thinkam\\DATA\\GoogleDrive\\英语视频学习\\soho, new york\\soho, new york - 英语 (自动生成).txt", 'r') as file:
    article = file.read()
sentence_set = lemmatize_sentence_set(article)
word_pattern = re.compile("\\b[a-zA-Z'-]+\\b")
# 过滤掉非单词
sentence_set = [word for word in sentence_set if word_pattern.match(word)]
print("\n".join(sentence_set))
