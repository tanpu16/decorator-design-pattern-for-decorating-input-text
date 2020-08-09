# CSX42: Assignment 5
**Name:** Priyanka Prakash Tanpure

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [textdecorators/src](./textdecorators/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile textdecorators/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile textdecorators/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile textdecorators/src/build.xml run -Dinput="input.txt" -Dmisspelled="misspell.txt" -Dkeywords="keywords.txt" -Doutput="output.txt" -Dlog="log.txt" -Ddebug="2"
```
Note: Arguments accept the absolute path of the files


## Description:
I used List data structure because we need to process input word by word so it'll be easy to store and access the words 
from the list.

ArrayList has O(n) time complexity for arbitrary indices of add/remove, but O(1) for the operation at the end of the list.
add() – takes O(1) time
add(index, element) – in average runs in O(n) time
get() – is always a constant time O(1) operation
remove() – runs in linear O(n) time. We have to iterate the entire array to find the element qualifying for removal
indexOf() – also runs in linear time. It iterates through the internal array and checking each element one by one. So the time complexity for this operation always requires O(n) time
contains() – implementation is based on indexOf(). So it will also run in O(n) time

code for MostFrequentWOrdDecorator referred from:
https://stackoverflow.com/questions/505928/how-to-count-the-number-of-occurrences-of-an-element-in-a-list
https://stackoverflow.com/questions/5911174/finding-key-associated-with-max-value-in-a-java-map

Assumption : when new sentence starts after period "." character there is space between "." and word. 

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 8 Aug 2020

I am using 3 slack days for this Assignment 5.