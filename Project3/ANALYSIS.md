# [CS 3310] Programming Assignment 3 - Canoe Rental Pathing
By: Thomas Truong\
Bronco ID: 014426906

## Analysis
  1. A description of the (recursive) formula used to calculate the optimal costs between posts.
      - I used Floyd's algorithm to calculate the optimal costs between the posts since it is an algorithm that determines the shortest path between every pair of verticies.
      - According to the slide, the formula is:
        - D<sup>(k)</sup>[i,j] = min{D<sup>(k-1)</sup>[i,j], D<sup>(k-1)</sup>[i,k] + D<sup>(k-1)</sup>[k,j]}
  2. A theoretical analysis of the run time of your program, based upon that recursive formula.
      - The runtime of my program based on that recursive formula is O(n<sup>3</sup>).
        - We can see that in the program, I used 3 nested for loops of 0 - (n - 1) in order to implement the formula.