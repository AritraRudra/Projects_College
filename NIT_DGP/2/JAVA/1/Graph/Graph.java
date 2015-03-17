import java.util.*;

public class Graph{
	
	
	/* ------------------------------------------
	      Data structure used to represent a graph
	      ------------------------------------------ */
	int[][]  adjMatrix;
	int rootNode = 0;
	int NNodes;

	boolean[] visited;
	
	//int indexOfNode;
	int route[];
	boolean routeFound = false;
	int[] chromosome;

	/* -------------------------------
	      Construct a graph of N nodes
	      ------------------------------- */
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
		//addNodeToRoute(i, "DFS");
		
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
		//addNodeToRoute(i, "DFS");
		
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
		//addNodeToRoute(rootNode,"BFS");

		while( !q.isEmpty() )
		{
			int n, child;

			n = (q.peek()).intValue();

			child = getUnvisitedChildNode(n);

			if ( child != -1 )
			{
				visited[child] = true;

				printNode(child);
				//addNodeToRoute(child,"BFS");

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

	/*
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
	*/
	
	
	/*
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
	*/
	
	/*
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
	*/
	
	public void printNode(int n)
	{
		System.out.print((n+1) + " ");
	}
}