import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <p>
 * Thomas Truong
 * Bronco ID: 014426906
 * CS 3310, Spring 2024
 * Programming Assignment 3
 * 
 * CanoeRentalPathing.java
 * Computes the most optimal path for a canoe trip.
 * 
 * Copyright (c) 2024, Thomas Truong.
 * </p>
 */
public class CanoeRentalPathing {
  /**
   * Extracts the data from the file.
   *
   * @param path - the path to the data file.
   * @return int[][] - the extracted data.
   */
  public static int[][] extractFromFile(String path) {
    // Try to read file.
    try {
      File data = new File(path);
      Scanner fileReader = new Scanner(data);
      
      // Obtain the n value.
      int n = -1;
      while (fileReader.hasNext() && n == -1) {
        if (!fileReader.hasNextInt()) {
          fileReader.next();
        } else {
          n = fileReader.nextInt();
        }
      }

      // Set up the array.
      int[][] rentingCosts = new int[n][n];
      for (int row = 0; row < n && fileReader.hasNext(); ++row) {
        for (int col = row + 1; col < n && fileReader.hasNext(); ++col) {
          // Has int, store into array.
          if (fileReader.hasNextInt()) {
            rentingCosts[row][col] = fileReader.nextInt();
          } else {  // Doesn't have int, eat up input.
            fileReader.next();
          }
        }
      }

      // Finished reading from file, close Scanner.
      fileReader.close();

      return rentingCosts;
    } catch (FileNotFoundException e) {
      // Something went wrong.
      System.out.println("Unable to extract from data file: " + path + ".");
      System.exit(1);
    }

    // Something went wrong; didn't go through try/catch.
    return null;
  }

  
  /**
   * The start of the program.
   *
   * @param args - the command-line arguments (expected data file path).
   */
  public static void main(String[] args) {
    int[][] rentingCosts = extractFromFile(args[0]);

    // Testing extractFromFile().
    for (int[] i : rentingCosts) {
      for (int j : i) {
        System.out.print(j + " ");
      }
      System.out.println();
    }
  }
}
