import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

  /**
   * Default Constructor: initialize a hashmap as the adjacency list.
   */
  public Graph() {
    // Initialize list.
    adjacencyList = new HashMap<Integer, List<Integer>>();
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
}
