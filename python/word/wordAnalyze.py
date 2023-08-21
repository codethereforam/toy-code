from nltk.stem import WordNetLemmatizer
wnl = WordNetLemmatizer()
print(wnl.lemmatize('generous'))
print(wnl.lemmatize('dogs'))
print(wnl.lemmatize('doing'))
print(wnl.lemmatize('representing'))
print(wnl.lemmatize('representing', pos="v"))
print(wnl.lemmatize('shopping'))
print(wnl.lemmatize('shopping', pos="v"))
print(wnl.lemmatize('loafers'))
print(wnl.lemmatize('screwed'))
print(wnl.lemmatize('women'))
print(wnl.lemmatize('children'))