# [CS 3310] Programming Assignment 2 - Anagram Detector
By: Thomas Truong\
Bronco ID: 014426906

## Writeup
  1. What is the theoretical worst-case running time of the algorithm you implemented (i.e. in Θ-notation), expressed in terms of the number of words n in the input file? Justify your answer.
      #### Analysis of Functions
      - extractData()
        - Reads every line in the file once: O(n).
      - presortString()
        - Uses toCharArray(): O(n).
        - Uses Arrays.sort() to sort: O(n log n).
        - Overall: O(n + n log n) = O(n log n).
      - getAnagramsSet()
        - Loops through every word: O(n).
          - toLowerCase().replaceAll(): O(n + n) = O(n).
          - Uses presortString(): O(n log n).
          - TreeMap.containsKey(): O(log n).
          - HashSet.contains(): O(1).
            - TreeMap.get().add(): O(log n + log n) = O(log n).
            - HashSet.add: O(1).
            - Overall: O(1 + log n + 1) = O(log n).
          - Loops through TreeMap: O(n).
            - Collections.sort(): O(n log n).
            - TreeMap.setValue(): O(log n).
            - Overall: O(n[log n + n log n]) = O(n^2 log n).
        - Overall: O(n[n + n log n + log n + log n + n^2 log n]) = **<u>O(n^3 log n)</u>**.