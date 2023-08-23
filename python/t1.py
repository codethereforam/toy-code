def find_elements_not_in_set(input_list, input_set):
    result = [word for word in input_list if word.strip() and word.strip().lower() not in input_set]
    return result

# 示例用法
my_list = ["Apple", "Banana", "Orange", "Grapes", "", "Kiwi"]
my_set = {"apple", "banana", "mango"}

result = find_elements_not_in_set(my_list, my_set)
print("Elements not in the set:", result)