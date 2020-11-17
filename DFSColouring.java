import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;



//In this problem we are asked to solve the two colourability problem using a DFS or rather custom 
//implementation of the DFS algorithm


/*There are two implementations of the DFS Algorithm: The iterative and the recursive
For the purpose of solving this problem I will use the recursive but I will do a simple
example implementaion of the iterative version aswell for practice purposes*/


//=================== Two-colourability using DFS =============================

//Here we define the main problem class
public class DFSColouring{
        public boolean[] colour; //colour array
        public boolean[] visited; //an array to keep track of visited vertices
        public HashMap<Integer, List<Integer>> graph; //our graph
        public int startVetex; //a start vertex from our graph

        public DFSColouring(HashMap<Integer, List<Integer>> g, int start){
                this.graph = g;
                this.startVetex = start;
                this.colour = new boolean[g.size()+1];
                this.visited = new boolean[g.size()+1];
        }


//Boolean method to check if the given graph is two colourable
public boolean isTwoColourable(){
        int nbrOfVisistedVertices = dfsColour(startVetex, true);
        return nbrOfVisistedVertices == graph.size();

}


//The following method is a modified implementation of a recursive DFS two check for two colourability

//The main thing to notice is that check for two colourability is the same as checking if a graph is 
//bipartite. Hence we will check if we could distinguish two distinct sets in the graph such that any
//edge that belongs to this graph does not connect vertices from the same set.

//This method goes through all the vertices in the adjacency list
//although hashmap access time is constant, we still visit every vertex and loop through
//all of its adjacent vertices. This amounts to a time complexity of O(V+E).
public int dfsColour(int v, boolean vertexColour){
        //init a counter to keep track of the number of vertices visited thus far
        int count = 1;
        //We set the colour of the current vertex in the colour array
        colour[v] = vertexColour;
        
        //We mark the vertex as visted
        visited[v] = true;
        //The colour of the adjacent vertices should be different from that of the current vertex
        //for the graph to be two-colourable, so we define a variable to help us check that
        boolean adjColour = !vertexColour;
        //We get the adjacency list of the vertex v from the graph
        List<Integer> v_adj = graph.get(v);
        //Now we can go through all of the adjacent edges and check for colourability
        for(int e : v_adj){
                //If vertex has not been visited before, then colour it and mark it visited
                if(!visited[e]){
                        colour[e] = adjColour;
                        visited[e] = true;
                }
                //If it was visited, we simply compare its colour to the current vertex v
                else{
                        if(colour[e] == vertexColour) 
                                 return -1;
                        else
                                continue;
                }
                //The recursiveCounter gets us the count value from the recursive call
                //Essentially, if any of the subgraphs is not two-colourable, the count is -1 and we return
                //if all are two-colourable, we add the vertex count to the total count.
                int recursiveCount = dfsColour(e, adjColour);
                if(recursiveCount == -1)
                        return -1;
                count += recursiveCount;
        }

        //Finally we return the total count so we could check if the number of vertices returned is equal to 
        //the number of vertices in the graph.
        return count;
}

// ============== Pseudocode of Coloring ==========================
/*
DFSColouring(int v, colour):
        initialize counter to 1
        set vertex colour to that specified in argument
        mark vertex as visited
        AdjacentVertexColour <- !(colour)
        
        for each edge e in the adjacency list of v:
                
                check if e is not visited:
                        visit e
                        colour e <- AdjacentVertexColour
                
                else
                        check if e's colur is the same as v:
                                return -1
                        else
                                continue

                countSum <- DFSColouring(e, AdjacentVertexColour)
                
                check if countSum is -1:
                        return -1

                increment counter by countSum

        return counter
*/


public void SolveTwoCol(){
        //Method runs at O(1)
        if(isTwoColourable())
        System.out.println("Graph is two colourable");

else
        System.out.println("Graph is not two colourable");


}


//Helper method to add edge between two vertices in the given graph.
//The graph implementation is a bit different here in that the Hashmap has vertex
//keys as its keySet() and a Lists of vertex keys in its adjacency lists instead
//of lists of vertices. This is only a variant implementation to facilitate work.
public static void addEdge(HashMap<Integer, List<Integer>> graph, int u, int v){
        
        if(graph.get(u) == null)
                graph.put(u, new ArrayList<Integer>());
        
        if(graph.get(v) == null)
                graph.put(v, new ArrayList<Integer>());
            
        graph.get(u).add(v);
        graph.get(v).add(u);

    }




    //Example run on simple graph.
public static void main(String[] args) {

HashMap<Integer, List<Integer>> graph = new HashMap<>();

addEdge(graph, 1, 3);
addEdge(graph, 3, 5);
addEdge(graph, 2, 5);
addEdge(graph, 2, 4);

System.out.println("The number of vertices in the graph: " + graph.size()); 
System.out.printf("The adjacency lists for all the vertices are as follows: ");
System.out.println(graph.values());

DFSColouring TwoColCheck = new DFSColouring(graph, 1);

TwoColCheck.SolveTwoCol();


//Let's add one edge between same-coloured vertices to break the property

addEdge(graph, 4, 5);
System.out.println("The number of vertices in the graph: " + graph.size()); 
System.out.printf("The adjacency lists for all the vertices are as follows: ");
System.out.println(graph.values());

TwoColCheck.SolveTwoCol();



}



}


