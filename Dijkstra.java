import java.util.*;




//========================Assignment 4 Problem 2=======================//
/*Here we present an implementation of Dijkstra's Algorithm */
public class Dijkstra{


  // Helper class to define edges between nodes  
  public static class Edge {
    double cost;
    int from, to;

    public Edge(int from, int to, double cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }

  // Node class to track the nodes to visit while running Dijkstra's
  public static class Node {
    char label;
    int id;
    double value;

    public Node(int id, double value) {
      this.id = id;
      this.value = value;
    }
  }
  //Note that n is the size of the graph in this case.
  private int n;
  private double[] dist;
  private int[] prev;
  //Perfered using lists of edges rather than integer keys.
  private List<List<Edge>> graph;

  //Let's define a custom comparator to maintain the priority queue.
  //we check the values of the nodes and compare them to maintain order in PQ
  private Comparator<Node> comparator =
      new Comparator<Node>() {
        public int compare(Node node1, Node node2) {
          if (Math.abs(node1.value - node2.value) < 1e-8) 
                return 0;
          else{
                if(node1.value - node2.value > 0)
                    return 1;
                else
                    return -1;
          }
        }
      };


  
  // Helper method to init empty graph
  private void generateGraph() {
    graph = new ArrayList<>(n);
    for (int i = 0; i < n; i++) 
        graph.add(new ArrayList<>());
  }

  public Dijkstra(int n) {
    this.n = n;
    generateGraph();
  }


  public Dijkstra(int n, Comparator<Node> comparator) {
    this(n);
    this.comparator = comparator;
  }


  //Helper function to add new edge to the graph 
  public void addEdge(char from, char to, int cost) {
      int fromCast = (int) from - 97;
      int toCast = (int) to - 97;
    if(cost >= 0)
            graph.get(from).add(new Edge(fromCast, toCast, cost));

    else
        throw new IllegalArgumentException("Negative edge cost for Dijkstra");
    }
    
 
  public boolean ValidNode(int current){
      if(current < 0 || current >= n)
        return false;
    
        return true;
  }


  public ArrayList<Integer> tracePath(int start, int end) {

    if (!ValidNode(start) || !ValidNode(end))
     throw new IllegalArgumentException("Invalid current");

    //we get the distance from our Dijkstra Algo 
    double dist = dijkstra(start, end);
    
    ArrayList<Integer> path = new ArrayList<>();

    if (dist == Double.POSITIVE_INFINITY) 
        return path;

    for (Integer at = end; at != null; at = prev[at]) 
        path.add(at);

    Collections.reverse(path);

    return path;
  }


  // Implementation of Dijkstra's algorithm for a directed graph to get
  //the shortest path between two nodes
  public double dijkstra(int start, int end) {
    // Maintain an array of the minimum distance to each current
    dist = new double[n];
    // Init all distances to infinity
    Arrays.fill(dist, Double.POSITIVE_INFINITY);
    dist[start] = 0;

    // We keep a priority queue of the best candidate current to visit next using our comparator
    PriorityQueue<Node> pq = new PriorityQueue<>(2 * n, comparator);
    pq.offer(new Node(start, 0));

    // Array used to track which nodes have already been visited.
    boolean[] visited = new boolean[n];
    prev = new int[n];

    while (!pq.isEmpty()) {

      Node current = pq.poll();
      //mark current node as visisted
      visited[current.id] = true;

      // We can skip this node because it already has 
      //a shorter distance than its cost
      if (dist[current.id] < current.value) 
            continue;

      List<Edge> edges = graph.get(current.id);

      for (int i = 0; i < edges.size(); i++) {
        Edge edge = edges.get(i);

        //We cannot get a shorter path by revisiting the same node
        if (visited[edge.to]) 
            continue;

        // Relax the edge by adding the cost to the previous distance value
        double newDist = dist[edge.from] + edge.cost;
        if (newDist < dist[edge.to]) {
          prev[edge.to] = edge.from;
          dist[edge.to] = newDist;
          pq.offer(new Node(edge.to, dist[edge.to]));
        }
      }
      
      //If we reach the end, we can return the shortest distance
        if (current.id == end) 
            return dist[end];
    }
    // The end Node in here is unreachable. Either independent component OR not 
    //reachbale by the directed edges.
    return Double.POSITIVE_INFINITY;
  }

  public static void main(String[] args) {
      /**TBD:
       * Add first and third graphs and init start at A and end at furthest point
       * G2 is not solvable with Dijkstra (neg weight)
       * Write printTrace function to print along with calling tracePath
       * 
       * 
       */
  }


}