import random


def get_random_string(length):
    letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*()_+`-=[]\\;',./{}|:\"<>?"
    result_str = ''.join(random.choice(letters) for _ in range(length))
    return result_str


class InputFuzzer:
    def __init__(self, input_col, op):
        self.op = op
        self.input = input_col
        self.letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*()_+`-=[]\\;'," \
                       "./{}|:\"<>? "

    def flip(self):
        flip_character = random.choice(self.input)
        index = self.input.index(flip_character)
        new_character = random.choice(self.letters)
        new_input = self.input[:index] + new_character + self.input[index + 1:]
        return new_input

    def trim(self):
        trim_character1 = random.choice(self.input)
        index1 = self.input.index(trim_character1)
        trim_character2 = random.choice(self.input)
        index2 = self.input.index(trim_character2)
        max_index = max(index1, index2)
        min_index = min(index1, index2)
        new_input = self.input[min_index:max_index + 1]
        return new_input

    def swap(self):
        swap_character = random.choice(self.input)
        index = self.input.index(swap_character)
        new_input = self.input[:index] + self.input[index + 1] + self.input[index] + self.input[index + 2:]
        return new_input

    def insert(self):
        insert_character = random.choice(self.letters)
        index = random.randint(0, len(self.input) - 1)
        new_input = self.input[:index] + insert_character + self.input[index:]
        return new_input

    def show(self):
        if self.op == 1:
            print(self.flip())
        elif self.op == 2:
            print(self.trim())
        elif self.op == 3:
            print(self.swap())
        elif self.op == 4:
            print(self.insert())


result = InputFuzzer("0,1,2", 1)
result.show()




