class PathApp{
    public static void main(String[] args)
    {
    Graph theGraph = new Graph();
    theGraph.addVertex('A');
    theGraph.addVertex('C');
    theGraph.addVertex('B');
    theGraph.addVertex('D');
    theGraph.addVertex('E');
    
    theGraph.addEdge(0, 1, 50);
    theGraph.addEdge(0, 3, 80);
    theGraph.addEdge(1, 2, 60);
    theGraph.addEdge(2, 4, 40);
    theGraph.addEdge(3, 2, 20);
    theGraph.addEdge(3, 4, 70);
    theGraph.addEdge(4, 1, 50);
    
    System.out.println("Shortest paths");
    theGraph.path();
    System.out.println();
    theGraph.pathVertex(0,2);
    
    Graph theGraph2 = new Graph();
    
    theGraph2.addVertex('A');
    theGraph2.addVertex('B');
    theGraph2.addVertex('C');
    theGraph2.addVertex('D');
    
    theGraph2.addEdge(1,0,70);
    theGraph2.addEdge(1,3,10);
    theGraph2.addEdge(2,0,30);
    theGraph2.addEdge(3,2,20);
    
  //  theGraph2.Floyd();
    
    Graph theGraph3 = new Graph();
    
    theGraph3.addVertex('A');
    theGraph3.addVertex('B');
    theGraph3.addVertex('C');
    theGraph3.addVertex('D');
    theGraph3.addVertex('E');
    
    theGraph3.addEdge(0,1,91);
    theGraph3.addEdge(0,2,62);
    theGraph3.addEdge(0,3,55);
    theGraph3.addEdge(1,4,31);
    theGraph3.addEdge(1,2,44);
    theGraph3.addEdge(2,3,52);
    theGraph3.addEdge(2,4,45);
    theGraph.addEdge(3,4,83);
    theGraph2.travelingSalesman();
    theGraph3.travelingSalesman();
    }
}