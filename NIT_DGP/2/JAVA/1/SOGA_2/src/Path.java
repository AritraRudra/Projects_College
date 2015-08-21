//-------------------------------------------------------------
//Finding paths in graphs
//CS 501 
//Zdravko Markov
//https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=8&sqi=2&ved=0CFwQFjAH&url=http%3A%2F%2Fwww.cs.ccsu.edu%2F~markov%2Fccsu_courses%2FCS501%2FPath.java&ei=4lmdU6q4NM2OuATIjYHADA&usg=AFQjCNHtPebx3I5t5CVaTPzXlQAxvQG5iQ&bvm=bv.68911936,d.c2E&cad=rja
//-------------------------------------------------------------

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;

public class Path
{
	int /*i, j,*/ noOfNodes;
	int[][] adjMat = new int[noOfNodes][noOfNodes];
	//static int[][] graph = new int[noOfNodes][noOfNodes];
	//Graph adjacency matrix (unweighted graph)
	/*
static private int[][] graph = { {0,1,0,1,0},  // 0->1  0->3
                                 {0,0,1,1,0},  // 1->2, 1->3
                                 {0,0,0,0,1},  // 2->4
                                 {0,0,1,0,1},  // 3->2, 3->4
                                 {0,1,0,0,0}   // 4->1 
                               };
/*                                  
//Graph adjacency matrix (weighted graph)
static private int[][] graph = { {0,50, 0,80, 0},  // 0->1  0->3
                                 {0, 0,60,90, 0},  // 1->2, 1->3
                                 {0, 0, 0, 0,40},  // 2->4
                                 {0, 0,20, 0,70},  // 3->2, 3->4
                                 {0,50, 0, 0, 0}   // 4->1 
                               };
	 */
	//--------------------------------------------------------------                                  
	//public static void main (String[] args)
	
	public Path(int[][] adjMat, int noOfNodes ){
		this.adjMat = adjMat;
		this.noOfNodes = noOfNodes;
	}
	
	public int[] initilize_Get(String KEY) {
		/*
		try {
			//Read the network information and store in adjacency matrix
			String netInfFile=new String("Network_Data.txt");
			Scanner netInfData = new Scanner(new FileInputStream(netInfFile));
			for (int i = 0; i < noOfNodes; i++){
				for (int j = 0; j < noOfNodes; j++){
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
		 */
		
		ArrayList<Integer> Start = new ArrayList<Integer>();
		Start.add(0);    // The start node
		ArrayList<ArrayList> Queue = new ArrayList();
		Queue.add(Start); // inserted in the initial queue of paths as [[Start]]
		//Use one search at a time (comment the others)
		ArrayList Path = new ArrayList<>();
		//int[] newGenes = new int [noOfNodes];
		if (KEY.equals("DFS"))		
			Path = depth_first(Queue,31);   // Depth first search
		else if (KEY.equals("BFS"))
			Path = breadth_first(Queue,31); // Breadth first search
		//     ArrayList Path = uni_cost(Queue,4); // Uniform cost search
		//System.out.println(Path);
		//newGenes = getChrom(Path);
		return getChrom(Path);
		
		//     System.out.println(path_cost(Path));
	}
	
	private int[] getChrom(ArrayList path) {
		int i, len = path.size();
		int[] rev = new int[len];
		//int[] correct = new int[len];
		int[] newGenes = new int[this.noOfNodes];
		
		for ( i = 0 ; i<this.noOfNodes ; i++){
			newGenes[i] = 0;
		}
		
		for ( i = 0 ; i<len ; i++){
			rev[i] = (int) path.get(i);
			//correct[(len-1) - i] = rev[i];
			newGenes[rev[i]] = 1;
			//System.out.println(temp1[i]);
		}
		
		return newGenes;
	}

	//--------------------------------------------------------------
	//Adds to the queue paths extending the first path in the queue       
	public ArrayList<ArrayList> extend (ArrayList<Integer> Path)
	{
		ArrayList<ArrayList> NewPaths = new ArrayList();
		for (int i=0;i<adjMat.length;i++)
			if (adjMat[Path.get(0)][i]>0 && !Path.contains(i))
			{
				ArrayList Path1 = (ArrayList)Path.clone();
				Path1.add(0,i);
				NewPaths.add(Path1);
			}
		return NewPaths;
	}
	//--------------------------------------------------------------
	//Appends y to x and returns the result in a new ArrayList z 
	public ArrayList append (ArrayList x, ArrayList y) 
	{
		ArrayList z = (ArrayList)x.clone();;
		for (int i=0;i<y.size();i++) z.add(y.get(i));
		return z;
	}
	//--------------------------------------------------------------
	//Depth first search for a path from Start to Goal. 
	//The Start node must be in the initial queue of paths as [[Start]]   
	public ArrayList<ArrayList> depth_first(ArrayList<ArrayList> Queue, int Goal) 
	{
		if (Queue.size()==0) return Queue;         // path not found
		if ((Integer)Queue.get(0).get(0) == Goal)  // if Goal is the first node in the first path
			return Queue.get(0);                   // return path
		else // replace the first path with all its extensions and put them in front of the queue            
		{
			ArrayList<ArrayList> NewPaths = extend(Queue.get(0));
			Queue.remove(0); 
			return depth_first(append(NewPaths,Queue),Goal);
		}
	}
	//--------------------------------------------------------------
	//Breadth first search for a path from Start to Goal. 
	//The Start node must be in the initial queue of paths as [[Start]]   
	public ArrayList<ArrayList> breadth_first(ArrayList<ArrayList> Queue, int Goal) 
	{
		if (Queue.size()==0) return Queue;          // path not found
		if ((Integer)Queue.get(0).get(0) == Goal)   // if Goal is the first node in the first path
			return Queue.get(0);                           // return path
		else // replace the first path with all its extensions and put them at the end of the queue
		{ 
			ArrayList<ArrayList> NewPaths = extend(Queue.get(0));
			Queue.remove(0);
			return breadth_first(append(Queue,NewPaths),Goal);
		}
	}
	//--------------------------------------------------------------
	public int path_cost (ArrayList<Integer> Path)
	{
		int cost = 0;
		for (int i=0;i<Path.size()-1;i++)
			cost = cost + adjMat[Path.get(i+1)][Path.get(i)];
		return cost;
	}
	//--------------------------------------------------------------    
	//Uniform cost search for a path from Start to Goal. 
	//The Start node must be in the initial queue of paths as [[Start]]   
	public ArrayList<ArrayList> uni_cost(ArrayList<ArrayList> Queue, int Goal) 
	{
		if (Queue.size()==0) return Queue;          // path not found
		if ((Integer)Queue.get(0).get(0) == Goal)   // if Goal is the first node in the first path
			return Queue.get(0);                           // return path
		else // replace the first path with all its extensions and put them at the end of the queue
		{ 
			ArrayList<ArrayList> NewPaths = extend(Queue.get(0));
			Queue.remove(0);
			ArrayList<ArrayList> Queue1 = append(Queue,NewPaths);
			sort(Queue1);   // Put the path with the lowest cost first in the queue
			return uni_cost(Queue1,Goal);
		}
	}
	//--------------------------------------------------------------
	public  void sort(ArrayList<ArrayList> Queue)
	{
		int out, in;
		for(out=Queue.size()-1; out>=1; out--)
			for(in=0; in<out; in++)
				if( path_cost(Queue.get(in)) > path_cost(Queue.get(in+1)) )
					swap(Queue, in, in+1);
	}
	//--------------------------------------------------------------
	private  void swap(ArrayList<ArrayList> a, int one, int two)
	{
		ArrayList<Integer> temp = a.get(one);
		a.set(one,a.get(two));
		a.set(two,temp);
	}

}


/*
public class Graph{


	 ------------------------------------------
	      Data structure used to represent a graph
	      ------------------------------------------ 
	int[][]  adjMatrix;
	int rootNode = 0;
	int NNodes;

	boolean[] visited;

	//int indexOfNode;
	int route[];
	boolean routeFound = false;
	int[] chromosome;

	 -------------------------------
	      Construct a graph of N nodes
	      ------------------------------- 
	Graph(int N)
	{
		NNodes = N;
		adjMatrix = new int[N][N];
		visited = new boolean[N];
		chromosome = new int[N];
		route = new int[N];
		for(int i = 0 ; i < N; i++)
			route[i] = 0;
	}

	Graph(int[][] mat)
	{
		int i, j;

		NNodes = mat.length;

		adjMatrix = new int[NNodes][NNodes];
		visited = new boolean[NNodes];
		chromosome = new int[NNodes];
		route = new int[NNodes];
		for(i = 0 ; i < NNodes; i++)
			route[i] = 0;

		for ( i=0; i < NNodes; i++)
			for ( j=0; j < NNodes; j++)
				adjMatrix[i][j] = mat[i][j];
	}

	Graph(int[][] mat, int noOfNodes, int startNode){
		int i = 0;
		NNodes = noOfNodes;
		adjMatrix = new int[NNodes][NNodes];
		visited = new boolean[NNodes];
		chromosome = new int[NNodes];
		rootNode = startNode;
		adjMatrix = mat;
		route = new int[noOfNodes];
		for( i = 0 ; i < noOfNodes; i++)
			route[i] = -1;
	}

	public void dfs()
	{
		int i = rootNode,j;
		visited[i] = true;

		printNode(i);
		//setGeneForDFS(indexOfNode);
		addNodeToRoute(i, "DFS");

		for ( j = 0; j < NNodes; j++ )
		{
			if ( adjMatrix[i][j] > 0 && !visited[j] )
				dfs(j);
		}
	}

	public void dfs(int i)
	{
		int j;
		visited[i] = true;

		printNode(i);
		addNodeToRoute(i, "DFS");

		for ( j = 0; j < NNodes; j++ )
		{
			if ( adjMatrix[i][j] > 0 && !visited[j] )
				dfs(j);
		}
	}

	public void bfs()
	{
		// BFS uses Queue data structure

		Queue<Integer> q = new LinkedList<Integer>();

		q.add(rootNode);
		visited[rootNode] = true;

		printNode(rootNode);
		addNodeToRoute(rootNode,"BFS");

		while( !q.isEmpty() )
		{
			int n, child;

			n = (q.peek()).intValue();

			child = getUnvisitedChildNode(n);

			if ( child != -1 )
			{
				visited[child] = true;

				printNode(child);
				addNodeToRoute(child,"BFS");

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

	public void clearVisited()
	{
		int i;

		for (i = 0; i < visited.length; i++)
			visited[i] = false;
	}

	private void addNodeToRoute(int nodeNo, String keyword) {
		int i = 0;
		while(!this.routeFound && this.route[i] != -1){
			if((this.route[i] == this.NNodes-1) && (!this.routeFound)){
				this.routeFound= true;
				if(keyword.equals("BFS"))
					setGene(this.route,i);
				else
					setGene(this.route,i);
			}
			i++;
		}
		this.route[i] = nodeNo;
	}




	private void setGeneForBFS(int[] foundRoute, int loc){
		int i = 0;
		for( i = 0; i < NNodes; i++)
			this.chromosome[i] = Byte.parseByte("0");
		for( i = 0; i < loc; i++){
			this.chromosome[foundRoute[i]] = Byte.parseByte("1");
		}
	}

	private void setGeneForDFS(int[] foundRoute, int loc){
		int i = 0;
		for( i = 0; i < NNodes; i++)
			this.chromosome[i] = Byte.parseByte("0");
		for( i = 0; i < loc; i++){
			this.chromosome[foundRoute[i]] = Byte.parseByte("1");
		}
	}

	public byte[] getChromosome() {
		return chromosome;		
	}


	private void setGene(int[] foundRoute, int loc) {
		int i = 0;
		System.out.println("In Graph.setGene : ");
		for( i = 0; i < NNodes; i++)
			this.chromosome[i] = 0;
		for( i = 0; i < loc; i++){
			this.chromosome[foundRoute[i]] = 1;
			System.out.print(chromosome[i]);
		}
		System.out.println();
	}

	public int[] getChromosome() {
		return chromosome;		
	}

	public void printNode(int n)
	{
		//System.out.println(n);
	}
}*/