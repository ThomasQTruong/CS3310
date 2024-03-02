import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * <p>
 * Thomas Truong
 * Bronco ID: 014426906
 * CS 3310, Spring 2024
 * Programming Assignment 1
 *
 * Graph.java
 * Simple implementation for a Graph data structure.
 *
 * Copyright (c) 2024, Thomas Truong.
 * </p>
 */
public class Graph {
  private Map<Integer, List<Integer>> adjacencyList;
  private int sizeOfGraph;

  /**
   * Default Constructor: initialize a hashmap as the adjacency list.
   */
  public Graph() {
    // Initialize list.
    adjacencyList = new HashMap<Integer, List<Integer>>();
    sizeOfGraph = 0;
  }


  /**
   * Constructor: create a graph with a specified amount of verticies. 
   *
   * @param amount - the amount of verticies to create.
   */
  public Graph(int amount) {
    // Initialize list.
    adjacencyList = new HashMap<Integer, List<Integer>>();

    // Create the specified amount of verticies.
    for (int i = 1; i <= amount; ++i) {
      adjacencyList.put(i, new LinkedList<Integer>());
    }

    sizeOfGraph = amount;
  }


  /**
   * Adds a vertex to the graph.
   *
   * @param value - the value of the vertex.
   * @return boolean - whether the operation was successful or not.
   */
  public boolean addVertex(int value) {
    // If the vertex already exists.
    if (adjacencyList.containsKey(value)) {
      return false;
    }

    // Vertex is unique, create.
    adjacencyList.put(value, new LinkedList<Integer>());

    ++sizeOfGraph;

    return true;
  }


  /**
   * Adds an edge which connects two verticies together.
   *
   * @param vertex1 - the first vertex.
   * @param vertex2 - the second vertex.
   * @return boolean - whether the operation was successful or not.
   */
  public boolean addEdge(int vertex1, int vertex2) {
    // If the verticies do not exist, return false.
    if (adjacencyList.containsKey(vertex1) == false) {
      return false;
    }
    if (adjacencyList.containsKey(vertex2) == false) {
      return false;
    }

    // Since both exist, check if linked already.
    if (adjacencyList.get(vertex1).contains(vertex2)) {
      return false;
    }

    // Link the two verticies.
    adjacencyList.get(vertex1).add(vertex2);
    adjacencyList.get(vertex2).add(vertex1);

    return true;
  }


  /**
   * Uses the BFS algorithm to visit and print all of the connected components.
   *
   * @param graphNumber - the ID number of the graph.
   */
  public void BFS(int graphNumber) {
    // Holds the visited verticies.
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    String result = "";  // The result to print at the end.
    int connectedComponents = 0;  // Amount of connected components.
    
    // For every vertex in the adjacency list.
    for (int i = 1; i < adjacencyList.size(); ++i) {

      // Has not visited the vertex.
      if (!visited.contains(i)) {
        result += "{";
        queue.add(i);
        visited.add(i);

        // Is the first vertex (dont add space).
        boolean start = true;

        // Has a vertex to go to still.
        while (!queue.isEmpty()) {
          // Get vertex from queue.
          int currentVertex = queue.poll();

          // Print based on start or not (no space for start).
          if (start) {
            result += currentVertex;
            start = false;
          } else {
            result += " " + currentVertex;
          }

          // Get the neighbors.
          List<Integer> neighbors = adjacencyList.get(currentVertex);

          // Visit the neighbors.
          for (int neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
              visited.add(neighbor);
              queue.add(neighbor);
            }
          }
        }
        result += "} ";
        ++connectedComponents;
      }
    }

    // Print out the results.
    System.out.println("Graph " + graphNumber + ": ");
    System.out.println(connectedComponents + " connected components: " + result);
  }


  public int size() {
    return sizeOfGraph;
  }
}
