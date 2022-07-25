# Software-Testing-Mini-Campaign
The Java class is to solve Data Reconciliation. It reads two CSV files, compares records stored in these CSV files row by row against a unique combination and records all mismatches as exceptions.
## Running the Code Guidelines
First, you must make sure your system can run Java code. 
* Clone this repository:
```
$ git clone https://github.com/wzrwzr23/Software-Testing-Mini-Campaign.git
```
* Go to CompareCSV.java file, change the path line
```java
String path = "E:\\COMPUTER\\term5_software\\Software-Testing-Mini-Campaign\\samples\\";
```
to the path of your CSV files location, please make sure the two files are in the same location.
* Go to the main method
```java
public static void main(String[] args)
```
and change the parameters of ```compareCSV()``` to your CSV files' names.
* Now you can run the code and checkout the ```path```specified folder for the output ```exception.csv```! 
* If you do not want to name the output ```exception.csv```, you can change the line
```java
String output="exceptions.csv";
```
to output the name you want, but remember to write the suffix as ```.csv```.
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
| ----------- | ----------- | ----------- |
| png, docx | csv, xlxs | pptx, mp4 |

## Input File Path
### Partition 1. Valid input file path.
*The 2 input files need to be in the same location which the path is directed to. And extra "\\" is also required at the end of path.*
  * Boundary value<br>
    * C:\\Users\\MyName\\Desktop\\
    * D:\\Download\\
  * Middle value<br>
    * E:\\COMPUTER\\term5_software\\Software-Testing-Mini-Campaign\\samples\\
    * F:\\MyDocument\\TotallyValidPath\\ThisIsWhereMyFilesAre\\
### Partition 2. Invalid input file path.
  * Boundary value<br>
    * D:\\Download
  * Middle value<br>
    * D:\\Download\\ and C:\\Users\\MyName\\Desktop\\
    * A:\\ThisIsNoneSense\\AndNoFileInside
    
| Invalid test cases | Valid test cases | Invalid test cases |
| ----------- | ----------- | ----------- |
| D:\\Download | C:\\Users\\MyName\\Desktop\\ | D:\\Download\\ and C:\\Users\\MyName\\Desktop\\ |

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
| ----------- | ----------- | ----------- |
| 1 | 2 | 3, 50 |

## Input File Content
### Partition 1. Two input files have completely same contents
* Boundary value<br>

#### Input file 1
| Customer ID# | Account No. | Currency | Type | Balance |
| ----------- | ------|----- | -------|---- |
| ID1 | BOS963211 | USD | SAVINGS | 962510 |
| ID2 | BOS85992 | AUD | CURRENT | 989898 |
| ID3 | BOS656613 | USD | CURRENT | 595290 |
| ID4 | BOS14824 | INR | SAVINGS | 772578 |
| ID5 | BOS657505 | CAD | SAVINGS | 95538 |
#### Input file 2
| Customer ID# | Account No. | Currency | Type | Balance |
| ----------- | ------|----- | -------|---- |
| ID1 | BOS963211 | USD | SAVINGS | 962510 |
| ID2 | BOS85992 | AUD | CURRENT | 989898 |
| ID3 | BOS656613 | USD | CURRENT | 595290 |
| ID4 | BOS14824 | INR | SAVINGS | 772578 |
| ID5 | BOS657505 | CAD | SAVINGS | 95538 |

* Middle value<br>

#### Input file 1
| Customer ID# | Account No. | Currency | Type | Balance |
| ----------- | ------|----- | -------|---- |
| ID1 | BOS963211 | USD | SAVINGS | 962510 |
| ID2 | BOS85992 | AUD | CURRENT | 989898 |
| ID3 | BOS656613 | USD | CURRENT | 595290 |
| ID4 | BOS14824 | INR | SAVINGS | 772578 |
| ID5 | BOS657505 | CAD | SAVINGS | 95538 |
#### Input file 2
| Customer ID# | Account No. | Currency | Type | Balance |
| ----------- | ------|----- | -------|---- |
| ID1 | BOS963211 | USD | SAVINGS | 962510 |
| ID2 | BOS85992 | AUD | CURRENT | 989898 |
| ID3 | BOS656613 | USD | CURRENT | 595290 |
| ID4 | BOS14824 | INR | SAVINGS | 772578 |
| ID5 | BOS657505 | CAD | SAVINGS | 95538 |

### Partition 2. Two input files have completely different contents.
* Boundary value<br>
#### Input file 1
| Customer ID# | Account No. | Currency | Type | Balance |
| ----------- | ------|----- | -------|---- |
| ID1 | BOS963211 | USD | SAVINGS | 962510 |
| ID2 | BOS85992 | AUD | CURRENT | 989898 |
| ID3 | BOS656613 | USD | CURRENT | 595290 |
| ID4 | BOS14824 | INR | SAVINGS | 772578 |
| ID5 | BOS657505 | CAD | SAVINGS | 95538 |
#### Input file 2
| Customer ID# | Account No. | Currency | Type | Balance |
| ----------- | ------|----- | -------|---- |
| ID5 | BOS657505 | CAD | SAVINGS | 95538 |
| ID6 | BOS66196 | SGD | CURRENT | 961629 |
| ID7 | BOS401497 | CHF | CURRENT | 873783 |
| ID8 | BOS928728 | GBP | CURRENT | 887322 |
| ID9 | BOS296569 | HKD | SAVINGS | 293795 |

* Middle value<br>
#### Input file 1
| Customer ID# | Account No. | Currency | Type | Balance |
| ----------- | ------|----- | -------|---- |
| ID1 | BOS963211 | USD | SAVINGS | 962510 |
| ID2 | BOS85992 | AUD | CURRENT | 989898 |
| ID3 | BOS656613 | USD | CURRENT | 595290 |
| ID4 | BOS14824 | INR | SAVINGS | 772578 |
| ID5 | BOS657505 | CAD | SAVINGS | 95538 |
#### Input file 2
| Customer ID# | Account No. | Currency | Type | Balance |
| ----------- | ------|----- | -------|---- |
| ID10 | BOS920505 | SGD | CURRENT | 86143 |
| ID11 | BOS10096 | USD | CURRENT | 813465 |
| ID12 | BOS411117 | CAD | SAVINGS | 973783 |
| ID13 | BOS123458 | USD | CURRENT | 987322 |
| ID14 | BOS976529 | USD | SAVINGS | 593795 |

### Partition 3. Two input files have partially different contents.
* Boundary value<br>
#### Input file 1
| Customer ID# | Account No. | Currency | Type | Balance |
| ----------- | ------|----- | -------|---- |
| ID1 | BOS963211 | USD | SAVINGS | 962510 |
| ID2 | BOS85992 | AUD | CURRENT | 989898 |
| ID3 | BOS656613 | USD | CURRENT | 595290 |
| ID4 | BOS14824 | INR | SAVINGS | 772578 |
| ID5 | BOS657505 | CAD | SAVINGS | 95538 |
#### Input file 2
| Customer ID# | Account No. | Currency | Type | Balance |
| ----------- | ------|----- | -------|---- |
| ID11 | BOS963211 | USD | SAVINGS | 962510 |
| ID2 | BOS85992 | AUD | CURRENT | 989898 |
| ID3 | BOS656613 | USD | CURRENT | 595290 |
| ID4 | BOS14824 | INR | SAVINGS | 772578 |
| ID5 | BOS657505 | CAD | SAVINGS | 95538 |

* Middle value<br>
#### Input file 1
| Customer ID# | Account No. | Currency | Type | Balance |
| ----------- | ------|----- | -------|---- |
| ID1 | BOS963211 | USD | SAVINGS | 962510 |
| ID2 | BOS85992 | AUD | CURRENT | 989898 |
| ID3 | BOS656613 | USD | CURRENT | 595290 |
| ID4 | BOS14824 | INR | SAVINGS | 772578 |
| ID5 | BOS657505 | CAD | SAVINGS | 95538 |
#### Input file 2
| Customer ID# | Account No. | Currency | Type | Balance |
| ----------- | ------|----- | -------|---- |
| ID1 | BOS963211 | USD | SAVINGS | 962510 |
| ID2 | BOS85992 | AUD | CURRENT | 989898 |
| ID3 | BOS656613 | USD | CURRENT | 595290 |
| ID4 | BOS14824 | INR | SAVINGS | 772578 |
| ID14 | BOS976529 | USD | SAVINGS | 593795 |

