import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
  public static int[][] extractFromFile(final String path) {
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
   * Gets the cheapest costs matrix and predecessors for each path of the given matrix.
   *
   * @param rentingCosts - the given costs matrix.
   * @param predecessors - the matrix that will store the preceeding ports (gets modified).
   * @return int[][] - the cheapest costs for each path matrix.
   */
  public static int[][] getCheapestCosts(final int[][] rentingCosts, int[][] predecessors) {
    int n = rentingCosts.length;
    int[][] cheapestCosts = new int[n][n];

    // Initialize cheapCosts and predecessors arrays.
    for (int row = 0; row < n; ++row) {
      for (int col = 0; col < n; ++col) {
        // Copy over rentingCosts into cheapCosts.
        cheapestCosts[row][col] = rentingCosts[row][col];

        // If it is the upper half of the triangle.
        if (row < col) {
          predecessors[row][col] = row;
        } else {  // Bottom half/main diagonal.
          predecessors[row][col] = -1;
        }
      }
    }

    // Floyd algorithm.
    for (int k = 0; k < n; ++k) {
      for (int row = 0; row < n; ++row) {
        for (int col = 0; col < n; ++col) {
          int minNumber = Math.min(cheapestCosts[row][col], 
                                cheapestCosts[row][k] + cheapestCosts[k][col]);

          // Different minimum, replace it and put into predecessor.
          if (cheapestCosts[row][col] != minNumber) {
            cheapestCosts[row][col] = minNumber;
            predecessors[row][col] = predecessors[k][col];
          }
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
  public static void print2dArray(final int[][] array) {
    for (int[] row : array) {
      for (int col : row) {
        System.out.print(col + " ");
      }
      System.out.println();
    }
  }


  /**
   * Prints the shortest path from the starting port to the ending port.
   *
   * @param predecessors - the matrix of preceeding ports.
   * @param start - the starting port.
   * @param end - the ending port.
   */
  public static void printShortestPath(final int[][] predecessors, final int start, final int end) {
    List<Integer> path = new ArrayList<Integer>();
    int current = end;

    // Obtain the paths.
    while (current != start) {
      path.add(current);
      current = predecessors[start][current];
    }
    path.add(start);
    Collections.reverse(path);

    // Print the paths.
    System.out.print("Shortest path from port " + start + " to port " + end + ": ");
    for (int i = 0; i < path.size(); i++) {
      System.out.print(path.get(i));
      if (i < path.size() - 1) {
        System.out.print(" -> ");
      }
    }
    System.out.println();
  }

  
  /**
   * The start of the program.
   *
   * @param args - the command-line arguments (expected data file path).
   */
  public static void main(String[] args) {
    // Obtain data from the file and print.
    System.out.println("===== Renting Costs =====");
    int[][] rentingCosts = extractFromFile(args[0]);
    print2dArray(rentingCosts);
    System.out.println();

    // Obtain cheapest costs matrix and predecessors and print.
    System.out.println("===== Cheapest Costs =====");
    int n = rentingCosts.length;
    int[][] predecessors = new int[n][n];
    int[][] cheapestCosts = getCheapestCosts(rentingCosts, predecessors);
    print2dArray(cheapestCosts);
    System.out.println("-------------------------");
    System.out.println("Port 0 to port " + (n - 1) + ": $" + cheapestCosts[0][n - 1] + ".");
    System.out.println();
    System.out.println("===== Cheapest Path =====");
    printShortestPath(predecessors, 0, n - 1);
  }
}
