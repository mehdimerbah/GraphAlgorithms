import java.util.*;



public class BFS{
    private static int n;
    private static Integer[] prev;
    private static HashMap<Integer, List<Edge>> graph;

    static class Edge {
        int from, to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;

        }

    }

    public BFS(HashMap<Integer, List<Edge>> g) {
        if (g == null)
            throw new IllegalArgumentException("Graph can not be null");
        n = g.size();
        graph = g;
      }



public static int bfs(int start){
    prev = new Integer[n];
    boolean[] visited = new boolean [n];

    Deque<Integer> queue = new ArrayDeque<>();


    queue.offer(start);
    visited[start] = true;

    while(!queue.isEmpty()){
        int current = queue.poll();
        List<Edge> edges = graph.get(current);

        for(Edge e : edges){
            visited[e.to] = true;
            prev[e.to] = current;
            queue.offer(e.to);
        }

    }

    return 1;
}

public static void addEdge(HashMap<Integer, List<Edge>> graph, int u, int v){
    //get list of edges
    List<Edge> UAdj_list = graph.get(u);
    List<Edge> VAdj_list = graph.get(v);
    
    if(UAdj_list == null){
    UAdj_list = new ArrayList<Edge>();
    graph.put(u, UAdj_list);
    }
    
    if(VAdj_list == null){
    VAdj_list = new ArrayList<Edge>();
    graph.put(u, VAdj_list);
    }
    
    UAdj_list.add(new Edge(u, v));
    VAdj_list.add(new Edge(v, u));
    
    }


public static void main(String[] args) {
    HashMap<Integer, List<Edge>> graph = new HashMap<>();
    addEdge(graph, 9, 8);
    addEdge(graph, 9, 10);
    addEdge(graph, 10, 1);
    addEdge(graph, 1, 8);
    addEdge(graph, 8, 12);
    addEdge(graph, 12, 2);
    addEdge(graph, 2, 3);
    addEdge(graph, 3, 8);
    addEdge(graph, 7, 11);
    addEdge(graph, 7, 0);
    addEdge(graph, 11, 0);
    addEdge(graph, 0, 9);
    System.out.println(graph.values());
    bfs(0);
    

    

    
}




}
