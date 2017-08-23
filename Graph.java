class Graph{
    private final int MAX_VERTS = 20;
    private final int INFINITY = 1000000;
    private Vertex vertexList[];
    private int adjMat[][];
    private int nVerts;
    private int nTree;
    private DistPar sPath[];
    private int currentVert;
    private int startToCurrent;
    private int shortestDistance;
    int [] shortPath;
    
    public Graph(){
        shortestDistance = 10000;
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        nTree = 0;
        shortPath = new int[20];
        for(int j=0;j<MAX_VERTS;j++)
            for(int k = 0; k<MAX_VERTS;k++)
                adjMat[j][k] = INFINITY;
        sPath = new DistPar[MAX_VERTS];
    }
    
    public void addVertex(char lab){
        vertexList[nVerts++] = new Vertex(lab);
    }
    
    public void addEdge(int start, int end, int weight){
        adjMat[start][end] = weight;
        adjMat[end][start] = weight;
    }
    
     public void pathVertex(int start, int end){
        int startTree = start;
        vertexList[startTree].isInTree = true;
        nTree = 1;
        
        for(int j = 0; j<nVerts; j++){
            int tempDist = adjMat[startTree][j];
            sPath[j] = new DistPar(startTree, tempDist);
        }
      
        while(nTree < end){
            int indexMin = getMin();
            int minDist = sPath[indexMin].distance;
            
            if(minDist == INFINITY){
           //     System.out.print("THERE ARE UNREACHABLE VERTICES");
                break;
            }
            
            else{
                currentVert = indexMin;
                startToCurrent = sPath[indexMin].distance;
            }
            
            vertexList[currentVert].isInTree = true;
            nTree++;
         
            adjust_sPath();
         
        }
        System.out.print(vertexList[sPath[start].parentVert].label);
        System.out.print(sPath[end].distance);
        char parent = vertexList[sPath[end].parentVert].label;
        System.out.print("(" + parent + ")");
        System.out.println();
        displayPaths();
        nTree = 0;
        for(int j=0; j<nVerts; j++)
            vertexList[j].isInTree = false;
    }
     
    public void path(){
        int startTree = 0;
        vertexList[startTree].isInTree = true;
        nTree = 1;
        
        for(int j = 0; j<nVerts; j++){
            int tempDist = adjMat[startTree][j];
            sPath[j] = new DistPar(startTree, tempDist);
        }
        
        while(nTree < nVerts){
            int indexMin = getMin();
            int minDist = sPath[indexMin].distance;
            
            if(minDist == INFINITY){
                System.out.print("THERE ARE UNREACHABLE VERTICES");
                break;
            }
            
            else{
                currentVert = indexMin;
                startToCurrent = sPath[indexMin].distance;
            }
            
            vertexList[currentVert].isInTree = true;
            nTree++;
            adjust_sPath();
        }
        
        displayPaths();
        nTree = 0;
        for(int j=0; j<nVerts; j++)
            vertexList[j].isInTree = false;
    }
    
    public int getMin(){
        
        int minDist = INFINITY;
        int indexMin = 0;
        
        for(int j = 0;j<nVerts;j++){
            if(!vertexList[j].isInTree && sPath[j].distance < minDist)
            {
                minDist = sPath[j].distance;
                indexMin = j;
            }
        }
        return indexMin;
    }
    
    public void adjust_sPath(){
        int column = 1;
        while(column < nVerts){
            if(vertexList[column].isInTree)
            {
                column++;
                continue;
            }
            int currentToFringe = adjMat[currentVert][column];
            int startToFringe = startToCurrent + currentToFringe;
            int sPathDist = sPath[column].distance;
            
            if(startToFringe < sPathDist)
            {
                sPath[column].parentVert = currentVert;
                sPath[column].distance = startToFringe;
            }
            column++;
        }
    }
    
    public void Floyd(){
        displayAdjMat();
        int total;
        for(int x = 0;x<nVerts;x++){
            for(int y = 0;y<nVerts;y++){
                if (adjMat[x][y] != INFINITY){
                    for(int z = 0;z<nVerts;z++){
                        if(adjMat[z][x] != INFINITY){
                             total = adjMat[x][y] + adjMat[z][x];
                             if (total < adjMat[y][z])
                                 adjMat[z][y] = total;
                             displayAdjMat();
                        }
                    }
                        
                }
            }
        }
    }
    
    public void displayPaths(){
        for(int j = 0; j< nVerts; j++)
        {
            System.out.print(vertexList[j].label + "=");
            if(sPath[j].distance == INFINITY)
                System.out.print("inf");
            else
                System.out.print(sPath[j].distance);
            char parent = vertexList[sPath[j].parentVert].label;
            System.out.print("(" + parent + ")");
        }
        System.out.println("");
    }
    
    public void displayAdjMat(){
        for(int i = 0;i<nVerts;i++){
            for(int j = 0; j<nVerts;j++)
                System.out.print(adjMat[i][j]+" ");
            System.out.println();
        }
        System.out.println();
    }
    
    public void travelingSalesman(){
        int [] chArray = new int [nVerts-1];
        int total = 0;
        char [] finalRoute = new char[nVerts+1];
      
        for(int j = 1;j<nVerts;j++){
            chArray[j-1] = j;
        }
        int poss = 1;
        for(int n = 1;n<nVerts;n++)
            poss *= n;
        String [] strArray = new String[poss];
        doAnagrams(chArray, chArray.length, poss);
        System.out.println(shortestDistance);
        System.out.print(vertexList[0].label + " ");
        for(int k = 0;k<chArray.length;k++)
            System.out.print(vertexList[shortPath[k]].label + " ");
        System.out.println(vertexList[0].label);
    }
    
    public void doAnagrams(int [] chArray, int newSize, int poss){
        int total =0;
   
        if(newSize == 1)
            return;
        for(int j = 0; j<newSize;j++){
            doAnagrams(chArray, newSize-1, poss);
            if (newSize==2){
                total = adjMat[0][chArray[0]];
                for(int m = 1;m<nVerts-1;m++){
                    total += adjMat[chArray[m]][chArray[m-1]];               
                }
               total += adjMat[0][chArray[chArray.length-1]];
               if (total < shortestDistance)
               {
                   shortestDistance = total;
                   for(int i = 0;i<chArray.length;i++)
                       shortPath[i] = chArray[i];
               }
            }
            rotate(chArray, newSize);
            total=0;
        }
 
      
    }
    public int [] rotate(int [] arr, int size){
        int j;
        int position = arr.length - size;
        int temp = arr[position];
        for(j=position+1;j<arr.length;j++)
            arr[j-1] = arr[j];
        arr[j-1] = temp;
        return arr;
    }
    

}