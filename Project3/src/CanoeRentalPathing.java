import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
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
  public static int MAX_VALUE = 99;

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

            // Update MAX_VALUE if it isn't the actual max.
            if (rentingCosts[row][col] > MAX_VALUE) {
              MAX_VALUE = rentingCosts[row][col];
            }
          } else {  // Doesn't have int, eat up input.
            fileReader.next();
          }
        }
      }

      // Fill the under triangle with a high value (acts as infinity).
      for (int row = 1; row < n; ++row) {
        for (int col = 0; col < row; ++col) {
          rentingCosts[row][col] = MAX_VALUE;
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
   * Gets the cheapest costs for each path matrix of the given matrix.
   *
   * @param rentingCosts - the given costs matrix.
   * @return int[][] - the cheapest costs for each path matrix.
   */
  public static int[][] getCheapestCosts(int[][] rentingCosts) {
    int n = rentingCosts.length;
    int[][] cheapestCosts = new int[n][n];

    // Copy over array.
    for (int row = 0; row < n; ++row) {
      for (int col = 0; col < n; ++col) {
        cheapestCosts[row][col] = rentingCosts[row][col];
      }
    }

    // Floyd algorithm.
    for (int k = 0; k < n; ++k) {
      for (int row = 0; row < n; ++row) {
        for (int col = 0; col < n; ++col) {
          cheapestCosts[row][col] = Math.min(cheapestCosts[row][col],
                      cheapestCosts[row][k] + cheapestCosts[k][col]);
        }
      }
    }

    return cheapestCosts;
  }


  /**
   * Prints out a 2D array.
   *
   * @param array - the 2D array to print.
   */
  public static void print2dArray(int[][] array) {
    for (int[] row : array) {
      for (int col : row) {
        System.out.print(col + " ");
      }
      System.out.println();
    }
  }

  
  /**
   * The start of the program.
   *
   * @param args - the command-line arguments (expected data file path).
   */
  public static void main(String[] args) {
    int[][] rentingCosts = extractFromFile(args[0]);
    print2dArray(rentingCosts);

    System.out.println("=============================");

    int[][] cheapestCosts = getCheapestCosts(rentingCosts);
    print2dArray(cheapestCosts);
  }
}
