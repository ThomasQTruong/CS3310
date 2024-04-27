# [CS 3310] Programming Assignment 3 - Canoe Rental Pathing
By: Thomas Truong\
Bronco ID: 014426906

## Instructions
  - Write a Java program which takes as input the name of a file in the current directory. The first line of the file will contain an integer n, giving the number of posts along the river. The remaining n − 1 lines of the file will contain the integers of the cost matrix, delimited by white space, omitting the unnecessary entries.
    - For example, the matrix shown above could be represented by the following data file:
      |    |    |    |
      |----|----|----|
      | 4  |    |    |
      | 10 | 15 | 50 |
      |    | 40 | 20 |
      |    |    | 35 |
      - (Note that white space is not significant in the file.)
  - Using a dynamic programming algorithm, your program will then compute the optimal costs of traveling between any two posts (i, j) where i < j. The goal is to determine the optimal cost for (0, n − 1).
  - After performing that calculation, your program will print the optimal cost matrix: i.e. the optimal cost between any two posts (i, j) for all values 0 ≤ i < j ≤ n − 1. Additionally, your program will print the actual sequence of rentals to be used for the route between posts 0 and n − 1 (not just its optimal cost).

## Usage
#### Unix
  1. Enter the build directory: `cd build`.
  2. Use the command: `make clean && make && java -cp ../bin/ CanoeRentalPathing ../test/inputB`.
      - `../test/inputB` = data file path.
      - Alternate: `make run`, which defaults to using `../test/inputA` as the data file.

#### Windows
  1. Open command prompt.
  2. Change to the project directory.
  3. Compile with the command: `javac -d bin\ src\CanoeRentalPathing.java`
  4. Run with the command: `java -cp bin\ CanoeRentalPathing test\inputB`.
     - `test\inputB` = data file path.