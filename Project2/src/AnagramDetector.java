import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * <p>
 * * Thomas Truong
 * Bronco ID: 014426906
 * CS 3310, Spring 2024
 * Programming Assignment 2
 * 
 * AnagramDetector.java
 * Detects and creates a set of all anagrams in a data file.
 *
 * Copyright (c) 2024, Thomas Truong
 * </p>
 */
public class AnagramDetector {
  /**
   * Extracts data from a file into an ArrayList.
   *
   * @param fileName - the name of the data file.
   * @return ArrayList[String] - the list of words from the data file.
   */
  public static ArrayList<String> extractData(String fileName) {
    // List that contains the words from the data file.
    ArrayList<String> words = new ArrayList<String>();

    // Try to read file.
    try {
      File data = new File(fileName);
      Scanner fileReader = new Scanner(data);

      // Add every word into the list.
      while (fileReader.hasNextLine()) {
        words.add(fileReader.nextLine());
      }

      // Finished reading from file, close Scanner.
      fileReader.close();
    } catch (FileNotFoundException e) {
      // Something went wrong.
      System.out.println("Unable to extract from data file: " + fileName + ".");
      System.exit(1);
    }

    return words;
  }

  /**
   * Gets the sets of anagrams of a data file.
   *
   * @param words - the list of words from the data file. 
   * @return - HashSet[ArrayList[String]] the set of every anagram.
   */
  public static HashSet<ArrayList<String>> getAnagramsSet(ArrayList<String> words) {
    return new HashSet<ArrayList<String>>();
  }

  public static void main(String[] args) {
    System.out.println(extractData(args[0]));

    return;
  }
}
