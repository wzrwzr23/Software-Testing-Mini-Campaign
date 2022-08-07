import random
import pandas as pd


class CSVFuzzer:
    def __init__(self, rows, name):
        self.rows = rows
        self.name = name
        self.cols = ["Customer ID#", "Account No.", "Currency", "Type"]
        self.cols_all = ["Customer ID#", "Account No.", "Currency", "Type", "Balance"]
        self.types = ["SAVINGS", "CURRENT"]
        self.upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        self.letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~!@#$%^&*()_+`-=[]\\;'," \
                      "./{}|:\"<>? "

    def customer(self):
        ID = random.choice(self.letter) + random.choice(self.letter)
        digit = random.randint(0, 10000000)
        return ID + str(digit)

    def account(self):
        BOS = random.choice(self.letter) + random.choice(self.letter) + random.choice(self.letter)
        digit = random.randint(0, 10000000000000)
        return BOS + str(digit)

    def currency(self):
        return random.choice(self.upper) + random.choice(self.upper) + random.choice(self.upper)

    def type(self):
        return random.choice(self.types)

    def balance(self):
        possibility = random.random()
        if 0.01 < possibility:
            return random.randint(0, 1000000000000000)
        else:
            return None

    def main(self):
        col_number = random.randint(0, 4)
        actual_columns = random.sample(self.cols, col_number)
        possibility = random.random()
        if 0.05 > possibility:
            pass
        else:
            actual_columns.append("Balance")
        df = pd.DataFrame(columns=actual_columns)
        data = []
        for i in range(self.rows):
            row = {}
            for col in actual_columns:
                if col == "Customer ID#":
                    row["Customer ID#"] = self.customer()
                    print(row)
                elif col == "Account No.":
                    row["Account No."] = self.account()
                    print(row)
                elif col == "Currency":
                    row["Currency"] = self.currency()
                    print(row)
                elif col == "Type":
                    row["Type"] = self.type()
                    print(row)
                elif col == "Balance":
                    row["Balance"] = self.balance()
                    print(row)
            # df.append(row, ignore_index=True)
            data.append(row)
            print(data)
        df = df.append(data, ignore_index=True)
            #df.append(row, ignore_index=True)
            # pd.concat([df, pd.DataFrame(columns=df.columns)], ignore_index=True)
        # df = pd.DataFrame(columns=df.columns)
        df.to_csv(f"java/samples/{self.name}", index=False)


csv1 = CSVFuzzer(5, "fuzz1.csv")
csv1.main()

csv2 = CSVFuzzer(5, "fuzz2.csv")
csv2.main()

csv3 = CSVFuzzer(5, "fuzz3.csv")
csv3.main()

csv4 = CSVFuzzer(5, "fuzz4.csv")
csv4.main()

csv5 = CSVFuzzer(5, "fuzz5.csv")
csv5.main()

csv6 = CSVFuzzer(5, "fuzz6.csv")
csv6.main()

csv7 = CSVFuzzer(5, "fuzz7.csv")
csv7.main()

csv8 = CSVFuzzer(5, "fuzz8.csv")
csv8.main()

csv9 = CSVFuzzer(5, "fuzz9.csv")
csv9.main()

csv10 = CSVFuzzer(5, "fuzz10.csv")
csv10.main()


