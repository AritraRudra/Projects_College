import java.util.Stack;
import java.util.Queue;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;


public class DFS {

    Stack<Integer> st;
      int vFirst;

      int[][] adjMatrix;		//Used in DFS()
      int[] isVisited = new int[32];		//change from 7 to 32

    /**
     * @param args
     */
    public static void main(String[] args) {
	int i, j, noOfNodes = 32;
	int[][] adjMat = new int[noOfNodes][noOfNodes];
        /*
		int[][] adjMat = { {0, 1, 1, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0 ,0},
                {0, 0, 1, 1, 1, 0, 0}  };
		*/
	try {
		//Read the network information and store in adjacency matrix
		String netInfFile=new String("Network_Data.txt");
		Scanner netInfData = new Scanner(new FileInputStream(netInfFile));
		for (i = 0; i < noOfNodes; i++){
			for (j = 0; j < noOfNodes; j++){
				adjMat[i][j] = netInfData.nextInt();
				//System.out.print(adjMat[i][j] + " ");
			}
			//System.out.println();
			netInfData.nextLine();
		}
		netInfData.close();
		} catch (FileNotFoundException e) {
			System.out.println("Files containing Network Information are not found. "); 
			e.printStackTrace();
		}

      new DFS(adjMat);
	  //new BFS(adjMat);

    }

    public DFS(int[][] Matrix) {

         this.adjMatrix = Matrix;
         st = new Stack<Integer>();
         int i;
         //int[] node = {0, 1, 2, 3, 4, 5, 6};		//7 nodes hence 6
		 //int[] node = new int[31];		//32 nodes hence 31
         int firstNode = 0;//node[0];
         depthFirst(firstNode, 32);
		 //this.adjMatrix = Matrix;
		//breadthFirst(0);
		 }

          public void depthFirst(int vFirst,int n)
          {
          int v,i;

          st.push(vFirst);

          while(!st.isEmpty())
          {
              v = st.pop();
              if(isVisited[v]==0)
              {
                  System.out.print("\n"+(v+1));
                  isVisited[v]=1;
              }
              for ( i=0;i<n;i++)
              {
                  if((adjMatrix[v][i] == 1) && (isVisited[i] == 0))
                  {
                      st.push(v);
                      isVisited[i]=1;
                      System.out.print(" " + (i+1));
                      v = i;
                  }
              }
          }
}
}

/*
public class Graph1
{
   /* ------------------------------------------
      Data structure used to represent a graph
      ------------------------------------------ */
 /*  int[][]  adjMatrix;
   int      rootNode = 0;
   int      NNodes;

   boolean[] visited; 

   /* -------------------------------
      Construct a graph of N nodes
      ------------------------------- */
  /* Graph1(int N)
   {
      NNodes = N;
      adjMatrix = new int[N][N];
      visited = new boolean[N];
   }

   Graph1(int[][] mat)
   {
      int i, j;

      NNodes = mat.length;

      adjMatrix = new int[NNodes][NNodes];
      visited = new boolean[NNodes];


      for ( i=0; i < NNodes; i++)
         for ( j=0; j < NNodes; j++)
            adjMatrix[i][j] = mat[i][j];
   }


   public void bfs()
   {
      // BFS uses Queue data structure

      Queue<Integer> q = new LinkedList<Integer>();

      q.add(rootNode);
      visited[rootNode] = true;

      printNode(rootNode);

      while( !q.isEmpty() )
      {
         int n, child;

         n = (q.peek()).intValue();

         child = getUnvisitedChildNode(n);

         if ( child != -1 )
         {
            visited[child] = true;

            printNode(child);

            q.add(child);
         }
         else
         {
            q.remove();
         }
      }

      clearVisited();      //Clear visited property of nodes
   }


   int getUnvisitedChildNode(int n)
   {
      int j;

      for ( j = 0; j < NNodes; j++ )
      {
	 if ( adjMatrix[n][j] > 0 )
         {
	    if ( ! visited[j] )
               return(j);
         }
      }

      return(-1);
   }

   void clearVisited()
   {
      int i;

      for (i = 0; i < visited.length; i++)
         visited[i] = false;
   }

   void printNode(int n)
   {
      System.out.println(n);
   }
}
*/












/*
public void breadthFirst(int startName){
    int current, item, startindex = 0;
    boolean finished = false;
    Queue myQueue = new Queue();
    System.out.println(startName);

   for (int i = 0; i < 32;i++)
      visited[i] = false;
   while (Vertex[startindex] != startName)
   // search array from subscript 0
     startindex++;
   current = startindex;
   visited[startindex] = true;             // OK, we've been there!
     do {
        for (int column= 0; column < 32; column++) {
          // enqueue its neighbours
          if (adjMatrix[current][column] != 0 && !visited[column])
                myQueue.enqueue(column);
          }
        item = myQueue.dequeue();      // remove head of queue
        if (item == -1)                // if empty, dequeue returns -1
          finished = true;
        else {
          System.out.println(Vertex[item]);  // write out the name
          visited[item] = true;             // register it as visited
          current = item;
        }
       } while (!finished);
  }
//Ok till here

 /*public BFS(int[][] Matrix) {
this.adjMatrix = Matrix;
breadthFirst(0);
*/
/*public void breadthFirst(int startName){
    int current, item, startindex = 0;
    boolean finished = false;
    Queue myQueue = new Queue();
    System.out.println(startName);

   for (int i = 0; i < 32 i++)
      visited[i] = false;
   while (Vertex[startindex] != startName)
   // search array from subscript 0
     startindex++;
   current = startindex;
   visited[startindex] = true;             // OK, we've been there!
     do {
        for (int column= 0; column < 32; column++) {
          // enqueue its neighbours
          if (adjMatrix[current][column] != 0 && !visited[column])
                myQueue.enqueue(column);
          }
        item = myQueue.dequeue();      // remove head of queue
        if (item == -1)                // if empty, dequeue returns -1
          finished = true;
        else {
          System.out.println(Vertex[item]);  // write out the name
          visited[item] = true;             // register it as visited
          current = item;
        }
       } while (!finished);
  }
*/




