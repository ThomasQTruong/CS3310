# [CS 3310] Programming Assignment 2 - Anagram Detector
By: Thomas Truong\
Bronco ID: 014426906

## Info
- An anagram is a word or phrase that is created by rearranging the letters of another word or phrase. For example, the words “eat”, “ate”, and “tea” are anagrams of one another.

## Instructions
  1. Write a Java program which accepts the name of a file on the command line. The file will contain an unknown number of words, with a single word on each line. Your program will read the file and store the words internally in an appropriate format.
      - Your program will then find all sets of anagrams of words in the file in an efficient manner. Your anagram-detection algorithms should ignore distinctions between upper- case and lower-case letters. For example, “Elvis” and “lives” should be identified as anagrams of one another.
      - Upon completion, the program will print the detected sets of anagrams to standard output. The manner in which you print those sets is up to you; as long as the results are neat and readable, you are free to use any format you like.
  2. Create an ordinary text file called README with instructions on how to compile and run your programs.
  3. Create an ordinary text file called WRITEUP with answers to the following question:
      - What is the theoretical worst-case running time of the algorithm you implemented (i.e. in Θ-notation), expressed in terms of the number of words n in the input file? Justify your answer.

## Usage
#### Unix
  1. Enter the build directory: `cd build`.
  2. Use the command: `make run`.
      - OR use the command: `make clean && make && java -cp ../bin/ AnagramDetector ../data/words.txt`
        - `../data/words.txt` = data file path.

#### Windows
  1. Open command prompt.
  2. Change to the project directory.
  3. Compile with the command: `javac -d bin\ src\AnaGramDetector.java`
  4. Run with the command: `java -cp bin\ AnagramDetector data\words.txt`.
     - `data\words.txt` = data file path.