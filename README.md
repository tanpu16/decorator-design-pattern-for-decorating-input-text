# CSX42: Assignment 4
**Name:** Priyanka Prakash Tanpure

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [arrayvisitors/src](./arrayvisitors/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile arrayvisitors/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile arrayvisitors/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile arrayvisitors/src/build.xml run -Dinput1="input1.txt" -Dinput2="input2.txt" -Dcommonintsout="output1.txt" -Dmissingintsout1="output2.txt" -Dmissingintsout2="output3.txt" -Ddebug="2"
```
Note: Arguments accept the absolute path of the files


## Description:
I used Hashset for storing the integers in Results. Because,
1. The underlying data structure for HashSet is hashtable. So average or usual case time complexity for add, remove and look-up operation of HashSet takes O(1) time.
2. As we shoudn't store duplicate values, so it's convinient to use set here because set only store unique values.

Note : clone method is public because driver and adt are different packages so for creating clone into driver class changed it from private to public.

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 20 July 2020