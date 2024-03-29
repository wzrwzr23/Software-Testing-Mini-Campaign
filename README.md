# Software-Testing-Mini-Campaign

The Java class is to solve Data Reconciliation. It reads two CSV files, compares records stored in these CSV files row by row against a unique combination and records all mismatches as exceptions.

## Running the Code Guidelines

First, you must make sure your system can run Java code.

* Clone this repository:

```
$ git clone https://github.com/wzrwzr23/Software-Testing-Mini-Campaign.git
```

* Go to CompareCSV.java file, find the ```main``` method

```java
public static void main(String[] args) throws IOException {
        CompareCSV compareCSV = new CompareCSV("sample_file_1", "sample_file_2");
```

change  ```sample_file_1``` and ```sample_file_2``` to your CSV files' names, or keep them as I stored them in the ```samples``` folder just for testing case.

* Use terminal to go to the folder that ```CompareCSV.java``` stores.

```
cd app/src/main/java
```

* Compile the code

```
javac CompareCSV.java
```

* After compilation, run

```
java CompareCSV
```

to start the program.

* Now you are supposed to see the column name of your CSV files and their corresponding index number, and a line telling you to input the choice of combination. Take ```sample_file_1``` and ```sample_file_2``` as example, you will see

```
Customer ID#,Account No.,Currency,Type,Balance
0. Customer ID#
1. Account No.
2. Currency
3. Type
Please give the unique combination in terms of column index(e.g. 0,1,2):
```

* After providing the index number, you will find the exception csv file ```output.csv``` at ```samples``` folder.
* You can change the exception file's name by changing the "output" in ```compareCSV``` function

```java
writeOutput("output");
```

to your desired name.

## Running Tests Guidelines

### Unit Testing

Add new configration under Junit module for ```CompareCSVunitTest```, as shown in the screenshot below.

[![SqCHrJ.md.png](https://iili.io/SqCHrJ.md.png)](https://freeimage.host/i/SqCHrJ)

### System Testing

Add new configration under Junit module for ```CompareCSVsystemTest```, as shown in the screenshot below.

[![Um7L1s.md.jpg](https://iili.io/Um7L1s.md.jpg)](https://freeimage.host/i/Um7L1s)

### Fuzzing Testing

```Fuzzer.py``` generates mutated column indexes with given input and index of mutation. For example,
```python
result = InputFuzzer("0,1,2", 1)
result.show()
```
will execute flip mutation and give output ```0f1,2```.

```csvFuzzer.py``` generates randomized csv files with given row number and file name for ```FuzzingCompareCSV.java``` to run. For example,
```python
csv1 = CSVFuzzer(5, "fuzz1.csv")
csv1.main()
csv2 = CSVFuzzer(5, "fuzz2.csv")
csv2.main()
```
Then we can put these two files into ```FuzzingCompareCSV.java``` to compare and check for exceptions.
```java
public static void main(String[] args) throws IOException {
        FuzzingCompareCSV compareCSV = new FuzzingCompareCSV("fuzz1", "fuzz2");
        compareCSV.compareCSV();
    }
```

## Use Case Diagram

![image.png](https://s2.loli.net/2022/07/11/cm6rhe4KdZobaB5.png)

# Equivalence Class Partitioning and Boundary Value Analysis Report

**Equivalence Class Partitioning**
: Divide input data into different classes, each class behave similarly.

**Boundary value analysis**
: Test boundary value between partitions (both valid boundary partition and invalid boundary partition).
A boundary value is an input or output value on the border of an equivalence partition, includes minimum and maximum values at inside and outside boundaries.

## Input File Format

### Partition 1. Valid input file format.

* Boundary value<br>
  * csv
  * xlxs
* Middle value<br>
  * csv
  * xlxs

### Partition 2. Invalid input file format.

*Any other file types besides csv and xlxs are invalid.*

* Boundary value<br>
  * pptx
  * docx
* Middle value<br>
  * jpg
  * mp4


| Invalid test cases | Valid test cases | Invalid test cases |
| -------------------- | ------------------ | -------------------- |
| png, docx          | csv, xlxs        | pptx, mp4          |

## Input File Number

### Partition 1. Valid input file number.

*It can only compare 2 csv files.*

* Boundary value<br>
  * 2
* Middle value<br>
  * 2

### Partition 2. Invalid input file number.

* Boundary value<br>
  * 1
  * 3
* Middle value<br>
  * 100
  * 50


| Invalid test cases | Valid test cases | Invalid test cases |
| -------------------- | ------------------ | -------------------- |
| 1                  | 2                | 3, 50              |

## Input File Content

### Partition 1. Two input files have completely same contents

* Boundary value<br>

#### Input file 1


| Customer ID# | Account No. | Currency | Type    | Balance |
| -------------- | ------------- | ---------- | --------- | --------- |
| ID1          | BOS963211   | USD      | SAVINGS | 962510  |
| ID2          | BOS85992    | AUD      | CURRENT | 989898  |
| ID3          | BOS656613   | USD      | CURRENT | 595290  |
| ID4          | BOS14824    | INR      | SAVINGS | 772578  |
| ID5          | BOS657505   | CAD      | SAVINGS | 95538   |

#### Input file 2


| Customer ID# | Account No. | Currency | Type    | Balance |
| -------------- | ------------- | ---------- | --------- | --------- |
| ID1          | BOS963211   | USD      | SAVINGS | 962510  |
| ID2          | BOS85992    | AUD      | CURRENT | 989898  |
| ID3          | BOS656613   | USD      | CURRENT | 595290  |
| ID4          | BOS14824    | INR      | SAVINGS | 772578  |
| ID5          | BOS657505   | CAD      | SAVINGS | 95538   |

* Middle value<br>

#### Input file 1


| Customer ID# | Account No. | Currency | Type    | Balance |
| -------------- | ------------- | ---------- | --------- | --------- |
| ID1          | BOS963211   | USD      | SAVINGS | 962510  |
| ID2          | BOS85992    | AUD      | CURRENT | 989898  |
| ID3          | BOS656613   | USD      | CURRENT | 595290  |
| ID4          | BOS14824    | INR      | SAVINGS | 772578  |
| ID5          | BOS657505   | CAD      | SAVINGS | 95538   |

#### Input file 2


| Customer ID# | Account No. | Currency | Type    | Balance |
| -------------- | ------------- | ---------- | --------- | --------- |
| ID1          | BOS963211   | USD      | SAVINGS | 962510  |
| ID2          | BOS85992    | AUD      | CURRENT | 989898  |
| ID3          | BOS656613   | USD      | CURRENT | 595290  |
| ID4          | BOS14824    | INR      | SAVINGS | 772578  |
| ID5          | BOS657505   | CAD      | SAVINGS | 95538   |

### Partition 2. Two input files have completely different contents.

* Boundary value<br>

#### Input file 1


| Customer ID# | Account No. | Currency | Type    | Balance |
| -------------- | ------------- | ---------- | --------- | --------- |
| ID1          | BOS963211   | USD      | SAVINGS | 962510  |
| ID2          | BOS85992    | AUD      | CURRENT | 989898  |
| ID3          | BOS656613   | USD      | CURRENT | 595290  |
| ID4          | BOS14824    | INR      | SAVINGS | 772578  |
| ID5          | BOS657505   | CAD      | SAVINGS | 95538   |

#### Input file 2


| Customer ID# | Account No. | Currency | Type    | Balance |
| -------------- | ------------- | ---------- | --------- | --------- |
| ID5          | BOS657505   | CAD      | SAVINGS | 95538   |
| ID6          | BOS66196    | SGD      | CURRENT | 961629  |
| ID7          | BOS401497   | CHF      | CURRENT | 873783  |
| ID8          | BOS928728   | GBP      | CURRENT | 887322  |
| ID9          | BOS296569   | HKD      | SAVINGS | 293795  |

* Middle value<br>

#### Input file 1


| Customer ID# | Account No. | Currency | Type    | Balance |
| -------------- | ------------- | ---------- | --------- | --------- |
| ID1          | BOS963211   | USD      | SAVINGS | 962510  |
| ID2          | BOS85992    | AUD      | CURRENT | 989898  |
| ID3          | BOS656613   | USD      | CURRENT | 595290  |
| ID4          | BOS14824    | INR      | SAVINGS | 772578  |
| ID5          | BOS657505   | CAD      | SAVINGS | 95538   |

#### Input file 2


| Customer ID# | Account No. | Currency | Type    | Balance |
| -------------- | ------------- | ---------- | --------- | --------- |
| ID10         | BOS920505   | SGD      | CURRENT | 86143   |
| ID11         | BOS10096    | USD      | CURRENT | 813465  |
| ID12         | BOS411117   | CAD      | SAVINGS | 973783  |
| ID13         | BOS123458   | USD      | CURRENT | 987322  |
| ID14         | BOS976529   | USD      | SAVINGS | 593795  |

### Partition 3. Two input files have partially different contents.

* Boundary value<br>

#### Input file 1


| Customer ID# | Account No. | Currency | Type    | Balance |
| -------------- | ------------- | ---------- | --------- | --------- |
| ID1          | BOS963211   | USD      | SAVINGS | 962510  |
| ID2          | BOS85992    | AUD      | CURRENT | 989898  |
| ID3          | BOS656613   | USD      | CURRENT | 595290  |
| ID4          | BOS14824    | INR      | SAVINGS | 772578  |
| ID5          | BOS657505   | CAD      | SAVINGS | 95538   |

#### Input file 2


| Customer ID# | Account No. | Currency | Type    | Balance |
| -------------- | ------------- | ---------- | --------- | --------- |
| ID11         | BOS963211   | USD      | SAVINGS | 962510  |
| ID2          | BOS85992    | AUD      | CURRENT | 989898  |
| ID3          | BOS656613   | USD      | CURRENT | 595290  |
| ID4          | BOS14824    | INR      | SAVINGS | 772578  |
| ID5          | BOS657505   | CAD      | SAVINGS | 95538   |

* Middle value<br>

#### Input file 1


| Customer ID# | Account No. | Currency | Type    | Balance |
| -------------- | ------------- | ---------- | --------- | --------- |
| ID1          | BOS963211   | USD      | SAVINGS | 962510  |
| ID2          | BOS85992    | AUD      | CURRENT | 989898  |
| ID3          | BOS656613   | USD      | CURRENT | 595290  |
| ID4          | BOS14824    | INR      | SAVINGS | 772578  |
| ID5          | BOS657505   | CAD      | SAVINGS | 95538   |

#### Input file 2


| Customer ID# | Account No. | Currency | Type    | Balance |
| -------------- | ------------- | ---------- | --------- | --------- |
| ID1          | BOS963211   | USD      | SAVINGS | 962510  |
| ID2          | BOS85992    | AUD      | CURRENT | 989898  |
| ID3          | BOS656613   | USD      | CURRENT | 595290  |
| ID4          | BOS14824    | INR      | SAVINGS | 772578  |
| ID14         | BOS976529   | USD      | SAVINGS | 593795  |

### Test on Groupmates' Programs

Implemented unit testing, system testing, and fuzz testing on namitha9 and kellieay's program.

Unit testing `CompareCSVunitTest` is implemented by JUnit testing on each of their function.

System testing `CompareCSVsystemTest` is implemented by running through their whole program.

Fuzz testing `csvFuzzer` is implemented by generating random illegal csv inputs, then use the csv files in their original programs.
