package com.dsa.toolkit.datastructures;

import java.util.*;

/**
 * A graph implementation using adjacency list representation.
 * Supports both directed and undirected graphs.
 * Uses HashMap<Integer, List<Integer>> for efficient vertex and edge operations.
 * 
 * Time Complexities:
 * - addVertex(): O(1)
 * - addEdge(): O(1) for directed, O(1) for undirected
 * - removeVertex(): O(V + E) where V is vertices, E is edges
 * - removeEdge(): O(degree of vertex)
 * - hasEdge(): O(degree of vertex)
 * - getNeighbors(): O(1)
 * 
 * Space Complexity: O(V + E)
 * 
 * @author DSA Toolkit
 * @version 1.0
 */
public class Graph {

    private HashMap<Integer, List<Integer>> adjacencyList;
    private boolean isDirected;
    private int vertexCount;
    private int edgeCount;

    /**
     * Constructs an empty undirected graph
     */
    public Graph() {
        this(false);
    }

    /**
     * Constructs an empty graph
     * @param isDirected true for directed graph, false for undirected
     */
    public Graph(boolean isDirected) {
        this.adjacencyList = new HashMap<>();
        this.isDirected = isDirected;
        this.vertexCount = 0;
        this.edgeCount = 0;
    }

    /**
     * Adds a vertex to the graph
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param vertex the vertex to add
     * @return true if vertex was added, false if it already exists
     */
    public boolean addVertex(int vertex) {
        if (adjacencyList.containsKey(vertex)) {
            return false;
        }
        adjacencyList.put(vertex, new ArrayList<>());
        vertexCount++;
        return true;
    }

    /**
     * Adds an edge between two vertices
     * Automatically creates vertices if they don't exist
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param u source vertex
     * @param v destination vertex
     * @param isDirected true for directed edge, false for undirected
     * @return true if edge was added successfully
     */
    public boolean addEdge(int u, int v, boolean isDirected) {
        // Add vertices if they don't exist
        addVertex(u);
        addVertex(v);

        // Check if edge already exists to avoid duplicates
        if (adjacencyList.get(u).contains(v)) {
            return false;
        }

        // Add edge from u to v
        adjacencyList.get(u).add(v);

        // If undirected, add edge from v to u as well
        if (!isDirected && u != v) { // Avoid duplicate self-loops
            adjacencyList.get(v).add(u);
        }

        edgeCount++;
        return true;
    }

    /**
     * Adds an edge using the graph's default directedness
     * 
     * @param u source vertex
     * @param v destination vertex
     * @return true if edge was added successfully
     */
    public boolean addEdge(int u, int v) {
        return addEdge(u, v, this.isDirected);
    }

    /**
     * Removes a vertex and all its associated edges
     * 
     * Time Complexity: O(V + E)
     * Space Complexity: O(1)
     * 
     * @param vertex the vertex to remove
     * @return true if vertex was removed, false if it didn't exist
     */
    public boolean removeVertex(int vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            return false;
        }

        // Remove all edges pointing to this vertex
        for (int v : adjacencyList.keySet()) {
            List<Integer> neighbors = adjacencyList.get(v);
            if (neighbors.contains(vertex)) {
                neighbors.remove(Integer.valueOf(vertex));
                edgeCount--;
            }
        }

        // Remove edges from this vertex and update edge count
        edgeCount -= adjacencyList.get(vertex).size();

        // Remove the vertex
        adjacencyList.remove(vertex);
        vertexCount--;
        return true;
    }

    /**
     * Removes an edge between two vertices
     * 
     * Time Complexity: O(degree of vertex)
     * Space Complexity: O(1)
     * 
     * @param u source vertex
     * @param v destination vertex
     * @return true if edge was removed, false if it didn't exist
     */
    public boolean removeEdge(int u, int v) {
        if (!adjacencyList.containsKey(u) || !adjacencyList.containsKey(v)) {
            return false;
        }

        boolean removed = false;

        // Remove edge from u to v
        if (adjacencyList.get(u).remove(Integer.valueOf(v))) {
            removed = true;
            edgeCount--;
        }

        // If undirected and not a self-loop, remove edge from v to u
        if (!isDirected && u != v) {
            adjacencyList.get(v).remove(Integer.valueOf(u));
        }

        return removed;
    }

    /**
     * Checks if there's an edge between two vertices
     * 
     * Time Complexity: O(degree of vertex)
     * Space Complexity: O(1)
     * 
     * @param u source vertex
     * @param v destination vertex
     * @return true if edge exists, false otherwise
     */
    public boolean hasEdge(int u, int v) {
        if (!adjacencyList.containsKey(u)) {
            return false;
        }
        return adjacencyList.get(u).contains(v);
    }

    /**
     * Checks if a vertex exists in the graph
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param vertex the vertex to check
     * @return true if vertex exists, false otherwise
     */
    public boolean hasVertex(int vertex) {
        return adjacencyList.containsKey(vertex);
    }

    /**
     * Gets the neighbors of a vertex
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(degree of vertex)
     * 
     * @param vertex the vertex to get neighbors for
     * @return list of neighboring vertices, or empty list if vertex doesn't exist
     */
    public List<Integer> getNeighbors(int vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    /**
     * Gets the degree of a vertex (number of edges)
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param vertex the vertex to get degree for
     * @return degree of the vertex, or -1 if vertex doesn't exist
     */
    public int getDegree(int vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            return -1;
        }
        return adjacencyList.get(vertex).size();
    }

    /**
     * Gets all vertices in the graph
     * 
     * Time Complexity: O(V)
     * Space Complexity: O(V)
     * 
     * @return set of all vertices
     */
    public Set<Integer> getVertices() {
        return new HashSet<>(adjacencyList.keySet());
    }

    /**
     * Returns the number of vertices in the graph
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return number of vertices
     */
    public int getVertexCount() {
        return vertexCount;
    }

    /**
     * Returns the number of edges in the graph
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return number of edges
     */
    public int getEdgeCount() {
        return edgeCount;
    }

    /**
     * Checks if the graph is directed
     * 
     * @return true if directed, false if undirected
     */
    public boolean isDirected() {
        return isDirected;
    }

    /**
     * Checks if the graph is empty (no vertices)
     * 
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return vertexCount == 0;
    }

    /**
     * Clears all vertices and edges from the graph
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void clear() {
        adjacencyList.clear();
        vertexCount = 0;
        edgeCount = 0;
    }

    /**
     * Prints the graph structure in a readable format
     * Shows each vertex and its neighbors
     */
    public void printGraph() {
        if (isEmpty()) {
            System.out.println("Graph is empty");
            return;
        }

        System.out.println("=== Graph Structure ===");
        System.out.println("Type: " + (isDirected ? "Directed" : "Undirected"));
        System.out.println("Vertices: " + vertexCount + ", Edges: " + edgeCount);
        System.out.println();

        // Sort vertices for consistent output
        List<Integer> sortedVertices = new ArrayList<>(adjacencyList.keySet());
        Collections.sort(sortedVertices);

        for (int vertex : sortedVertices) {
            List<Integer> neighbors = adjacencyList.get(vertex);
            System.out.print("Vertex " + vertex + ": ");

            if (neighbors.isEmpty()) {
                System.out.println("(no neighbors)");
            } else {
                // Sort neighbors for consistent output
                List<Integer> sortedNeighbors = new ArrayList<>(neighbors);
                Collections.sort(sortedNeighbors);

                System.out.print("[");
                for (int i = 0; i < sortedNeighbors.size(); i++) {
                    System.out.print(sortedNeighbors.get(i));
                    if (i < sortedNeighbors.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");
            }
        }
        System.out.println("========================");
    }

    /**
     * Returns a visual representation of the graph
     * 
     * @return string representation of the graph
     */
    public String toVisualString() {
        if (isEmpty()) {
            return "Empty Graph";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Graph (").append(isDirected ? "Directed" : "Undirected").append("):\n");

        List<Integer> sortedVertices = new ArrayList<>(adjacencyList.keySet());
        Collections.sort(sortedVertices);

        for (int vertex : sortedVertices) {
            List<Integer> neighbors = adjacencyList.get(vertex);
            sb.append(vertex).append(" --> ");

            if (neighbors.isEmpty()) {
                sb.append("∅");
            } else {
                List<Integer> sortedNeighbors = new ArrayList<>(neighbors);
                Collections.sort(sortedNeighbors);
                sb.append("{");
                for (int i = 0; i < sortedNeighbors.size(); i++) {
                    sb.append(sortedNeighbors.get(i));
                    if (i < sortedNeighbors.size() - 1) {
                        sb.append(", ");
                    }
                }
                sb.append("}");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Returns the adjacency list representation
     * 
     * @return string representation of adjacency list
     */
    @Override
    public String toString() {
        return "Graph{" +
                "vertices=" + vertexCount +
                ", edges=" + edgeCount +
                ", directed=" + isDirected +
                ", adjacencyList=" + adjacencyList +
                '}';
    }

    /**
     * Performs Breadth-First Search starting from a given vertex
     * 
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     * 
     * @param startVertex the vertex to start BFS from
     * @return list of vertices in BFS order
     */
    public List<Integer> bfs(int startVertex) {
        if (!hasVertex(startVertex)) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);

            // Sort neighbors for consistent traversal order
            List<Integer> neighbors = new ArrayList<>(getNeighbors(current));
            Collections.sort(neighbors);

            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return result;
    }

    /**
     * Performs Depth-First Search starting from a given vertex
     * 
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     * 
     * @param startVertex the vertex to start DFS from
     * @return list of vertices in DFS order
     */
    public List<Integer> dfs(int startVertex) {
        if (!hasVertex(startVertex)) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfsHelper(startVertex, visited, result);
        return result;
    }

    /**
     * Helper method for DFS recursion
     */
    private void dfsHelper(int vertex, Set<Integer> visited, List<Integer> result) {
        visited.add(vertex);
        result.add(vertex);

        // Sort neighbors for consistent traversal order
        List<Integer> neighbors = new ArrayList<>(getNeighbors(vertex));
        Collections.sort(neighbors);

        for (int neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited, result);
            }
        }
    }

    /**
     * Demonstration and testing of Graph implementation
     */
    public static void main(String[] args) {
        System.out.println("=== Graph Implementation Demo ===\n");

        // Test undirected graph
        System.out.println("--- Creating Undirected Graph ---");
        Graph undirectedGraph = new Graph(false);

        System.out.println("Created empty undirected graph");
        System.out.println("Is empty: " + undirectedGraph.isEmpty());
        System.out.println("Vertex count: " + undirectedGraph.getVertexCount());
        System.out.println("Edge count: " + undirectedGraph.getEdgeCount());

        // Add edges (vertices will be created automatically)
        System.out.println("\n--- Adding Edges to Undirected Graph ---");
        int[][] undirectedEdges = { { 1, 2 }, { 1, 3 }, { 2, 4 }, { 3, 4 }, { 4, 5 } };

        for (int[] edge : undirectedEdges) {
            boolean added = undirectedGraph.addEdge(edge[0], edge[1], false);
            System.out.println("Added edge " + edge[0] + " - " + edge[1] +
                    ": " + added);
        }

        System.out.println("\nUndirected Graph Structure:");
        undirectedGraph.printGraph();

        System.out.println("\nVisual representation:");
        System.out.println(undirectedGraph.toVisualString());

        // Test directed graph
        System.out.println("\n--- Creating Directed Graph ---");
        Graph directedGraph = new Graph(true);

        // Add vertices first
        System.out.println("Adding vertices: 0, 1, 2, 3, 4");
        for (int i = 0; i <= 4; i++) {
            directedGraph.addVertex(i);
        }

        // Add directed edges
        System.out.println("\n--- Adding Edges to Directed Graph ---");
        int[][] directedEdges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 1 }, { 2, 4 }, { 3, 4 } };

        for (int[] edge : directedEdges) {
            boolean added = directedGraph.addEdge(edge[0], edge[1], true);
            System.out.println("Added directed edge " + edge[0] + " → " + edge[1] +
                    ": " + added);
        }

        System.out.println("\nDirected Graph Structure:");
        directedGraph.printGraph();

        // Test graph operations
        System.out.println("\n--- Testing Graph Operations ---");
        System.out.println("Undirected Graph Tests:");
        System.out.println("Has vertex 3: " + undirectedGraph.hasVertex(3));
        System.out.println("Has vertex 99: " + undirectedGraph.hasVertex(99));
        System.out.println("Has edge 1-2: " + undirectedGraph.hasEdge(1, 2));
        System.out.println("Has edge 2-1: " + undirectedGraph.hasEdge(2, 1)); // Should be true for undirected
        System.out.println("Has edge 1-5: " + undirectedGraph.hasEdge(1, 5));

        System.out.println("\nDirected Graph Tests:");
        System.out.println("Has edge 0→1: " + directedGraph.hasEdge(0, 1));
        System.out.println("Has edge 1→0: " + directedGraph.hasEdge(1, 0)); // Should be false for directed
        System.out.println("Has edge 2→1: " + directedGraph.hasEdge(2, 1));

        // Test neighbors and degrees
        System.out.println("\n--- Testing Neighbors and Degrees ---");
        System.out.println("Undirected Graph:");
        for (int vertex : undirectedGraph.getVertices()) {
            List<Integer> neighbors = undirectedGraph.getNeighbors(vertex);
            int degree = undirectedGraph.getDegree(vertex);
            System.out.println("Vertex " + vertex + ": neighbors=" + neighbors +
                    ", degree=" + degree);
        }

        System.out.println("\nDirected Graph:");
        for (int vertex : directedGraph.getVertices()) {
            List<Integer> neighbors = directedGraph.getNeighbors(vertex);
            int degree = directedGraph.getDegree(vertex);
            System.out.println("Vertex " + vertex + ": out-neighbors=" + neighbors +
                    ", out-degree=" + degree);
        }

        // Test BFS and DFS
        System.out.println("\n--- Testing Graph Traversal ---");
        System.out.println("Undirected Graph BFS from vertex 1: " +
                undirectedGraph.bfs(1));
        System.out.println("Undirected Graph DFS from vertex 1: " +
                undirectedGraph.dfs(1));

        System.out.println("\nDirected Graph BFS from vertex 0: " +
                directedGraph.bfs(0));
        System.out.println("Directed Graph DFS from vertex 0: " +
                directedGraph.dfs(0));

        // Test edge removal
        System.out.println("\n--- Testing Edge Removal ---");
        System.out.println("Removing edge 1-3 from undirected graph:");
        boolean removed = undirectedGraph.removeEdge(1, 3);
        System.out.println("Edge removed: " + removed);
        undirectedGraph.printGraph();

        // Test vertex removal
        System.out.println("\n--- Testing Vertex Removal ---");
        System.out.println("Removing vertex 2 from directed graph:");
        removed = directedGraph.removeVertex(2);
        System.out.println("Vertex removed: " + removed);
        directedGraph.printGraph();

        // Test self-loops
        System.out.println("\n--- Testing Self-Loops ---");
        Graph selfLoopGraph = new Graph(true);
        selfLoopGraph.addEdge(1, 1, true);
        selfLoopGraph.addEdge(1, 2, true);
        selfLoopGraph.addEdge(2, 2, true);
        System.out.println("Graph with self-loops:");
        selfLoopGraph.printGraph();

        // Real-world example: Social Network
        System.out.println("\n--- Real-World Example: Social Network ---");
        Graph socialNetwork = new Graph(false); // Undirected for friendships

        // People represented as integers: 1=Alice, 2=Bob, 3=Charlie, 4=Diana, 5=Eve
        String[] people = { "", "Alice", "Bob", "Charlie", "Diana", "Eve" };

        System.out.println("👥 Building Social Network:");
        System.out.println("People: Alice(1), Bob(2), Charlie(3), Diana(4), Eve(5)");

        // Friendships (undirected relationships)
        int[][] friendships = { { 1, 2 }, { 1, 3 }, { 2, 4 }, { 3, 4 }, { 3, 5 }, { 4, 5 } };

        for (int[] friendship : friendships) {
            socialNetwork.addEdge(friendship[0], friendship[1], false);
            System.out.println("👫 " + people[friendship[0]] + " and " +
                    people[friendship[1]] + " are now friends");
        }

        System.out.println("\n🌐 Social Network Structure:");
        System.out.print("Network connections:\n");
        for (int person = 1; person <= 5; person++) {
            List<Integer> friends = socialNetwork.getNeighbors(person);
            System.out.print("👤 " + people[person] + " is friends with: ");
            for (int i = 0; i < friends.size(); i++) {
                System.out.print(people[friends.get(i)]);
                if (i < friends.size() - 1)
                    System.out.print(", ");
            }
            System.out.println(" (" + friends.size() + " friends)");
        }

        System.out.println("\n🔍 Finding connections from Alice:");
        List<Integer> connections = socialNetwork.bfs(1);
        System.out.print("People Alice can reach: ");
        for (int i = 0; i < connections.size(); i++) {
            System.out.print(people[connections.get(i)]);
            if (i < connections.size() - 1)
                System.out.print(" → ");
        }
        System.out.println();

        // Performance test
        System.out.println("\n--- Performance Test ---");
        Graph largeGraph = new Graph(false);
        long startTime = System.nanoTime();

        // Create a graph with 1000 vertices and random edges
        for (int i = 0; i < 1000; i++) {
            largeGraph.addVertex(i);
        }

        // Add 5000 random edges
        Random random = new Random(42); // Fixed seed for reproducible results
        for (int i = 0; i < 5000; i++) {
            int u = random.nextInt(1000);
            int v = random.nextInt(1000);
            largeGraph.addEdge(u, v, false);
        }

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0;

        System.out.printf("Created graph with 1000 vertices and ~5000 edges in %.2f ms%n", duration);
        System.out.println("Final stats - Vertices: " + largeGraph.getVertexCount() +
                ", Edges: " + largeGraph.getEdgeCount());

        // Web/Internet example
        System.out.println("\n--- Real-World Example: Web Pages ---");
        Graph webGraph = new Graph(true); // Directed for web links

        // Web pages: 1=HomePage, 2=AboutPage, 3=ContactPage, 4=BlogPage, 5=ProductPage
        String[] pages = { "", "HomePage", "AboutPage", "ContactPage", "BlogPage", "ProductPage" };

        System.out.println("🌐 Building Web Link Structure:");

        // Web links (directed relationships)
        int[][] webLinks = {
                { 1, 2 }, { 1, 3 }, { 1, 4 }, { 1, 5 }, // HomePage links to all others
                { 2, 3 }, { 2, 1 }, // AboutPage links to Contact and Home
                { 4, 1 }, { 4, 5 }, // BlogPage links to Home and Products
                { 5, 1 }, { 5, 3 } // ProductPage links to Home and Contact
        };

        for (int[] link : webLinks) {
            webGraph.addEdge(link[0], link[1], true);
            System.out.println("🔗 " + pages[link[0]] + " → " + pages[link[1]]);
        }

        System.out.println("\n📊 Web Link Analysis:");
        for (int page = 1; page <= 5; page++) {
            List<Integer> outLinks = webGraph.getNeighbors(page);
            System.out.println("📄 " + pages[page] + " links to " + outLinks.size() +
                    " pages: " + outLinks.stream()
                            .map(p -> pages[p])
                            .reduce((a, b) -> a + ", " + b)
                            .orElse("none"));
        }

        System.out.println("\n🕷️ Web crawling from HomePage:");
        List<Integer> crawlOrder = webGraph.bfs(1);
        System.out.print("Pages reachable: ");
        for (int i = 0; i < crawlOrder.size(); i++) {
            System.out.print(pages[crawlOrder.get(i)]);
            if (i < crawlOrder.size() - 1)
                System.out.print(" → ");
        }
        System.out.println();

        System.out.println("\n=== Graph Demo Complete! ===");
    }
}