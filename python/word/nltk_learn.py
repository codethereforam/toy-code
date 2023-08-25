from nltk.stem import WordNetLemmatizer
from nltk.stem import PorterStemmer

from python.word.word_helper import get_lemmatized_word_list

print('---- lemmatize ----')
lemmatizer = WordNetLemmatizer()
print(lemmatizer.lemmatize('generous'))
print(lemmatizer.lemmatize('dogs'))
print(lemmatizer.lemmatize("dogs'", pos='n'))
print(lemmatizer.lemmatize("cat's"))
print(lemmatizer.lemmatize("cat's", pos='n'))
print(lemmatizer.lemmatize('doing'))
print(lemmatizer.lemmatize('representing'))
print(lemmatizer.lemmatize('representing', pos="v"))
print(lemmatizer.lemmatize('shopping'))
print(lemmatizer.lemmatize('shopping', pos="v"))
print(lemmatizer.lemmatize('loafers'))
print(lemmatizer.lemmatize('screwed'))
print(lemmatizer.lemmatize('women'))
print(lemmatizer.lemmatize('children'))
print(lemmatizer.lemmatize('mobile'))
print(lemmatizer.lemmatize("that's"))
print(lemmatizer.lemmatize("that's", pos='n'))

print('---- stem ----')
stemmer = PorterStemmer()
print(stemmer.stem('generous'))
print(stemmer.stem("dogs'"))
print(stemmer.stem("cat's"))
print(stemmer.stem('doing'))
print(stemmer.stem('representing'))
print(stemmer.stem('shopping'))
print(stemmer.stem('loafers'))
print(stemmer.stem('screwed'))
print(stemmer.stem('women'))
print(stemmer.stem('children'))
print(stemmer.stem('mobile'))
print(stemmer.stem("that's"))

print('---- word_helper ----')


def lemmatize_sentence(sentence):
    return " ".join(get_lemmatized_word_list(sentence))


print(lemmatizer.lemmatize("I am loving it"))  # I am loving it
print(lemmatizer.lemmatize("loving"))  # loving
print(lemmatizer.lemmatize("loving", "v"))  # love
print(lemmatize_sentence("I am loving it"))  # I be love it
print(lemmatize_sentence("representing shopping"))