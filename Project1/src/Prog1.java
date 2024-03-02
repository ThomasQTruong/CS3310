import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <p>
 * Thomas Truong
 * Bronco ID: 014426906
 * CS 3310, Spring 2024
 * Programming Assignment 1
 * 
 * Prog1.java
 * Driver class to test out graph data structures and 
 * BFS algorithm for Graph.java.
 *
 * Copyright (c) 2024, Thomas Truong.
 * </p>
 */
public class Prog1 {
  private static ArrayList<Graph> graphList = new ArrayList<Graph>();

  /**
   * Converts data from a file into graph(s).
   * Prints and exits if file does not exist.
   *
   * @param fileName - the name of the file.
   */
  public static void fileToGraphs(String fileName) {
    try {
      File inputFile = new File(fileName);
      Scanner fileReader = new Scanner(inputFile);

      // For every line, create graph from input.
      while (fileReader.hasNextLine()) {
        String data = fileReader.nextLine();
        graphList.add(inputLineToGraph(data));
      }

      fileReader.close();
    } catch (FileNotFoundException e) {
      // File was unreadable.
      System.out.println("Invalid file: " + fileName + ".");
      System.exit(1);
    }
  }


  /**
   * Creates a graph from the input line.
   *
   * @param inputLine - the line from the input file.
   * @return Graph - the converted graph.
   */
  public static Graph inputLineToGraph(String inputLine) {
    // Get rid of spaces and ): "7 (1,2) (3,4)" => "7(1,2(3,4".
    String parsed = inputLine.replace(" ", "");
    parsed = parsed.replace(")", "");

    // "7(1,2(3,4" => {"7", "1,2", "3,4"}.
    String[] values = parsed.split("\\(");

    // Create graph with the inputted amount of verticies.
    Graph graph = new Graph(Integer.valueOf(values[0]));

    // Add every edge.
    for (int i = 1; i < values.length; ++i) {
      // Extract verticies from the values.
      String[] verticies = values[i].split(",");

      graph.addEdge(Integer.valueOf(verticies[0]), Integer.valueOf(verticies[1]));
    }

    return graph;
  }


  /**
   * The start of the program. 
   *
   * @param args - command-line arguments (file name).
   */
  public static void main(String[] args) {
    // Convert the input file into a graph.
    fileToGraphs(args[0]);

    // For every graph, apply BFS algorithm.
    for (int i = 0; i < graphList.size(); ++i) {
      graphList.get(i).BFS(i + 1);
    }
  }
}

