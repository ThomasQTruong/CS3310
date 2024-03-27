import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

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
   * @param filePath - the path of the data file.
   * @return ArrayList[String] - the list of words from the data file.
   */
  public static ArrayList<String> extractData(String filePath) {
    // List that contains the words from the data file.
    ArrayList<String> words = new ArrayList<String>();

    // Try to read file.
    try {
      File data = new File(filePath);
      Scanner fileReader = new Scanner(data);

      // Add every word into the list.
      while (fileReader.hasNextLine()) {
        words.add(fileReader.nextLine());
      }

      // Finished reading from file, close Scanner.
      fileReader.close();
    } catch (FileNotFoundException e) {
      // Something went wrong.
      System.out.println("Unable to extract from data file: " + filePath + ".");
      System.exit(1);
    }

    return words;
  }


  /**
   * Presorts a String alphabetically by character.
   *
   * @param word - the string to sort.
   * @return String - the alphabetically sorted word.
   */
  public static String presortString(String word) {
    // Convert into array and sort each letter alphabetically.
    char[] letters = word.toCharArray();
    Arrays.sort(letters);

    // Convert back into a String.
    return new String(letters);
  }


  /**
   * Gets the sets of anagrams of a data file.
   *
   * @param words - the list of words from the data file. 
   * @return TreeMap[String, Set[String]] - the sets of every anagram.
   */
  public static TreeMap<String, Set<String>> getAnagramsSet(List<String> words) {
    TreeMap<String, Set<String>> anagramsSet = new TreeMap<String, Set<String>>();
    Set<String> filteredSet = new HashSet<String>();
    
    // For every word in the list.
    for (String word : words) {
      // Make lowercased, get rid of non-alphabetical characters, and sort.
      String filtered = word.toLowerCase().replaceAll("[^a-z]", "");
      String sorted = presortString(filtered);

      // Key never existed yet.
      if (!anagramsSet.containsKey(sorted)) {
        // Create set for the key.
        anagramsSet.put(sorted, new HashSet<String>());
      }
      
      // Does not already exist, add word into the set.
      if (!filteredSet.contains(filtered)) {
        anagramsSet.get(sorted).add(word);
        filteredSet.add(filtered);
      }
    }

    // For every item in the TreeMap.
    for (Map.Entry<String, Set<String>> entry : anagramsSet.entrySet()) {
      // Sort every list by using Collections.sort().
      ArrayList<String> toSort = new ArrayList<String>(entry.getValue());
      Collections.sort(toSort);
      entry.setValue(new LinkedHashSet<String>(toSort));
    }

    return anagramsSet;
  }


  /**
   * Prints out every anagram set.
   *
   * @param anagrams - the list of anagram sets.
   */
  public static void printAnagramSets(Map<String, Set<String>> anagrams) {
    for (Map.Entry<String, Set<String>> entry : anagrams.entrySet()) {
      if (entry.getValue().size() > 1) {
        System.out.println(entry.getValue());
      }
    }

    return;
  }


  /**
   * The start of the program.
   *
   * @param args - the command-line arguments (expected data file path).
   */
  public static void main(String[] args) {
    List<String> words = extractData(args[0]);
    Map<String, Set<String>> anagramSet = getAnagramsSet(words);

    System.out.println("Anagram Sets:");
    printAnagramSets(anagramSet);

    return;
  }
}
