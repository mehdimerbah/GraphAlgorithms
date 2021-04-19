import java.util.*;


public class DFS {
	//We first define a helper class for us to set up the graph with 
	//vertices and edges connecting them.
public static class Edge{
	int from, to;
	
	public Edge(int f, int t) {
		this.from = f;
		this.to = t;
	}
	
}


//note that the graph is defined as a map to minimize search time for nodes
//The graph is a mapping of every vertex to its adjacency list
//start argument is starting node and n is the number of vertices
public static int dfs(HashMap<Integer, List<Edge>> graph, int start, int n) {
    //initialize a counter to help us check if DFS was valid
    int c = 0;
	//initialize boolean array to keep track of visited nodes
    boolean[] visited = new boolean[n];
    //init stack
	Stack<Integer> s = new Stack<Integer>();
    //push start node into stack
    s.push(start);
	visited[start] = true;
    /*
    while our stack is not empty we pop the top most element 
    mark it as visited 
    increment node counter by 1
    and get its adjacency list
    */
	while(!s.isEmpty()) {
		int k = s.pop();
		c++;
		List<Edge> k_adj = graph.get(k);
		//if our vertex has adjacent vertices we work through them
		if(k_adj != null) {
		//now, for all elements in its adjacency list we check if they had been visited
			for(Edge e : k_adj) {
                //if the adjacent vertex has not been visited yet, push it into the stack and mark it visited
				if(!visited[e.to]) {
					s.push(e.to);
					visited[e.to] = true;
				}
					
			}
			
		}
		
	
	
	}

	return c;

}



public static void addEdge(HashMap<Integer, List<Edge>> graph, int from, int to){
    List<Edge> edges_list = graph.get(from);
    if(edges_list == null){
        edges_list = new ArrayList<Edge>();
        graph.put(from, edges_list);
    }
    edges_list.add(new Edge(from, to));
}





public static void main(String[] args) {
HashMap<Integer, List<Edge>> graph = new HashMap<>();

addEdge(graph, 0, 1);
addEdge(graph, 0, 2);
addEdge(graph, 1, 2);
addEdge(graph, 1, 3);
addEdge(graph, 2, 3);


if(dfs(graph,0,4) == 4)
	System.out.println("DFS Worked!");
else
	System.out.println("DFS Messed up!");



}




}
