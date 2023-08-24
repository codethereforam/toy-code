def find_elements_not_in_set(input_list, input_set):
    result = [word for word in input_list if word.strip() and word.strip().lower() not in input_set]
    return result


# 示例用法
my_list = ["Apple", "Banana", "Orange", "Grapes", "", "Kiwi"]
my_set = {"apple", "banana", "mango"}

result = find_elements_not_in_set(my_list, my_set)
print("Elements not in the set:", result)

my_list = [1, 2, 2, 3, 4, 4, 5]
my_set = set(my_list)
print(my_set)


def does_not_end_with_suffix(word, suffixes):
    for suffix in suffixes:
        if word.endswith(suffix):
            return False
    return True


suffixes = ["'t", "'m", "'re", "'d", "'ve", "'ll"]

if does_not_end_with_suffix("example", suffixes):
    print("1")
else:
    print("2")

if does_not_end_with_suffix("don't", suffixes):
    print("1")
else:
    print("2")
