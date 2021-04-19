import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Stack;


//======================= Iterative DFS Implementation ==============================
//Time complexity: O(V+E)
public class DFS_Iterative{
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


//Helper method to add edges in the graph, takes graph and two vertices as arguments
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


}

