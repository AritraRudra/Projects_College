import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Dijkstra{
	static int INT_MAX = 9999999;
	public static void main(String[] args) {
		double actEnrg = 0, actDelay = 0, costDelay = 0, costEnrg = 0, dist = 0;
		int i, j, noOfNodes = 32;

		int adjMat[][] = new int [noOfNodes][noOfNodes];
		/*
		double costMatDelay[][] = new double [noOfNodes][noOfNodes];
		int costMatEnrg[][] = new int [noOfNodes][noOfNodes];
		double actMatDelay[][] = new double [noOfNodes][noOfNodes];
		double actMatEnrg[][] = new double [noOfNodes][noOfNodes];
		double distMat[][] = new double [noOfNodes][noOfNodes];
		 */
		double costMatDelay[][] = new double [noOfNodes][noOfNodes];
		double costMatEnrg[][] = new double [noOfNodes][noOfNodes];
		double actMatDelay[][] = new double [noOfNodes][noOfNodes];
		double actMatEnrg[][] = new double [noOfNodes][noOfNodes];
		double distMat[][] = new double [noOfNodes][noOfNodes];
		double tempMat[][] = new double [noOfNodes][noOfNodes];

		// Read the required data from text files
		try {
			//Read the network information and store in adjacency matrix
			String netInfFile=new String("Network_Data.txt");
			Scanner netInfData = new Scanner(new FileInputStream(netInfFile));

			String costEnergyFile=new String("Energy_Cost_Data.txt");
			Scanner costEnergyData = new Scanner(new FileInputStream(costEnergyFile));

			String costDelayFile=new String("Delay_Cost_Data.txt");
			Scanner costDelayData = new Scanner(new FileInputStream(costDelayFile));

			String actEnergyFile=new String("Energy_Actual_Data.txt");
			Scanner actEnergyData = new Scanner(new FileInputStream(actEnergyFile));

			String actDelayFile=new String("Delay_Actual_Data.txt");
			Scanner actDelayData = new Scanner(new FileInputStream(actDelayFile));

			String distFile=new String("Distances_Between_Nodes.txt");
			Scanner distData = new Scanner(new FileInputStream(distFile));


			//netInfData.nextLine();	//Ignores the first line
			for (i = 0; i < noOfNodes; i++){
				for (j = 0; j < noOfNodes; j++){
					adjMat[i][j] = netInfData.nextInt();
					//System.out.print(adjMat[i][j] + " ");
				}
				//System.out.println();
				netInfData.nextLine();
			}

			//costEnergyData.nextLine();	//Ignores the first line
			for (i = 0; i < noOfNodes; i++){
				for (j = 0; j < noOfNodes; j++){
					costMatEnrg[i][j] = costEnergyData.nextDouble();
				}
				costEnergyData.nextLine();
				//System.out.println(N_i + ", " + N_j + ", " + E_i + "," + D_i);
			}

			//costDelayData.nextLine();	//Ignores the first line
			for (i = 0; i < noOfNodes; i++){
				for (j = 0; j < noOfNodes; j++){
					costMatDelay[i][j] = costDelayData.nextDouble();
					//System.out.print(costMatDelay[i][j] + " ");
				}
				//System.out.println();
				costDelayData.nextLine();
				//System.out.println(N_i + ", " + N_j + ", " + E_i + "," + D_i);
			}

			//actEnergyData.nextLine();	//Ignores the first line
			for (i = 0; i < noOfNodes; i++){
				for (j = 0; j < noOfNodes; j++){
					actMatEnrg[i][j] = actEnergyData.nextDouble();
				}
				actEnergyData.nextLine();
				//System.out.println(N_i + ", " + N_j + ", " + E_i + "," + D_i);
			}

			//actDelayData.nextLine();	//Ignores the first line
			for (i = 0; i < noOfNodes; i++){
				for (j = 0; j < noOfNodes; j++){
					actMatDelay[i][j] = actDelayData.nextDouble();
				}
				actDelayData.nextLine();
				//System.out.println(N_i + ", " + N_j + ", " + E_i + "," + D_i);
			}

			for (i = 0; i < noOfNodes; i++){
				for (j = 0; j < noOfNodes; j++){
					distMat[i][j] = distData.nextDouble();
				}
				distData.nextLine();
				//System.out.println(N_i + ", " + N_j + ", " + E_i + "," + D_i);
			}

			netInfData.close();
			costEnergyData.close();
			costDelayData.close();
			actEnergyData.close();
			actDelayData.close();
			distData.close();
		}catch (FileNotFoundException expFile) {
			System.out.println("Files containing Network Information are not found. "); 
			expFile.printStackTrace();
		}catch(InputMismatchException expIn){
			System.out.println("Files containing Network Information have different format/type. "); 
			expIn.printStackTrace();
		}


		dist = dijkstras(costMatEnrg, 0);
		System.out.println("Distance between node 1 and node 32 is : " + dist);

		/*
		//dijkstra(adjMat, 0);
		dijkstra(costMatDelay, 0);
		dijkstra(costMatEnrg, 0);
		dijkstra(actMatDelay, 0);
		dijkstra(actMatEnrg, 0);
		dijkstra(distMat, 0);
		*/
		
		printResults(dijkstras(costMatDelay, 0),dijkstras(costMatEnrg, 0),dijkstras(actMatDelay, 0),dijkstras(actMatEnrg, 0),dijkstras(distMat, 0));
		printResults(dijkstra(costMatDelay, 0),dijkstra(costMatEnrg, 0),dijkstra(actMatDelay, 0),dijkstra(actMatEnrg, 0),dijkstra(distMat, 0));


		for (i = 0; i < noOfNodes; i++){
			for (j = 0; j < noOfNodes; j++){
				tempMat[i][j] = costMatDelay[i][j] + costMatEnrg[i][j];
			}
		}
		dist = dijkstras(tempMat, 0);
		System.out.println("Total cost : " + dist + " Rupees");
		//System.out.println("Total cost : " + dijkstra(tempMat, 0) + " Rupees");

	/*	for (i = 0; i < noOfNodes; i++){
			for (j = 0; j < noOfNodes; j++){
				tempMat[i][j] = actMatDelay[i][j] + actMatEnrg[i][j];
			}
		}
		dist = dijkstras(tempMat, 0);
		System.out.println("Total (D+E) : " + dist + " no Unit");
		System.out.println("Total (D+E) : " + dijkstra(tempMat, 0) + " no Unit");
		
		/*
		//next two blocks are different
		//http://www.geeksforgeeks.org/dynamic-programming-set-6-min-cost-path/
		dist = minCost(costMatDelay, noOfNodes, noOfNodes);
		System.out.println("Uuu###: " + dist);
		*/
		
	}
	
	/* Returns cost of minimum cost path from (0,0) to (m, n) in mat[R][C]*/
	/*static double minCost(double[][] cost, int m, int n)	{
		int noOfNodes = 32;
	   if (n < 0 || m < 0)
	      return INT_MAX;
	   else if (m == 0 && n == 0)
	      return cost[m][n];
	   else
	      return (cost[m][n] + min( minCost(cost, m-1, n-1), minCost(cost, m-1, n), minCost(cost, m, n-1) ));
	}
	 
	/* A utility function that returns minimum of 3 integers */
	/*static double min(double x, double y, double z)
	{
	   if (x < y)
	      return (x < z)? x : z;
	   else
	      return (y < z)? y : z;
	}
	*/
	
	
	

	static void printResults(double totalCostDelay,double totalCostEnrg,double totalActDelay,double totalActEnrg,double totalDist){
		System.out.println("Dijkstra's method between node 1 and node 32 : ");
		System.out.println("Cost for Delay : " + totalCostDelay + " Rupees");
		System.out.println("Cost for Energy : " + totalCostEnrg + " Rupees");
		System.out.println("Actual Delay : " + totalActDelay + " sec.");
		System.out.println("Actual Energy : " + totalActEnrg + " KJ");
		System.out.println("Total Distance : " + totalDist + " K.M.");
	}

	//http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
	// Funtion that implements Dijkstra's single source shortest path algorithm
	// for a graph represented using adjacency matrix representation
	public static double dijkstra(double[][] graph, int src){
		int i, u, v, count, V = 32;
		double[] dist = new double[V];   // The output array.  dist[i] will hold the shortest
		// distance from src to i

		boolean[] sptSet = new boolean[V]; // sptSet[i] will true if vertex i is included in shortest
		// path tree or shortest distance from src to i is finalized

		// Initialize all distances as INFINITE and stpSet[] as false
		for ( i = 0; i < V; i++){
			dist[i] = (graph[src][i] == 0 ? INT_MAX:graph[src][i]);
			sptSet[i] = false;
		}

		// Distance of source vertex from itself is always 0
		dist[src] = 0.0;

		// Find shortest path for all vertices
		for (count = 0; count < (V-1); count++)
		{
			// Pick the minimum distance vertex from the set of vertices not
			// yet processed. u is always equal to src in first iteration.
			u = minDistance(dist, sptSet);

			// Mark the picked vertex as processed
			sptSet[u] = true;

			// Update dist value of the adjacent vertices of the picked vertex.
			for (v = 0; v < V; v++)

				// Update dist[v] only if is not in sptSet, there is an edge from 
				// u to v, and total weight of path from src to  v through u is 
				// smaller than current value of dist[v]
				//if (!sptSet[v] && (graph[u][v] != 0) && (dist[u] != INT_MAX) && (dist[u]+graph[u][v] < dist[v]))		//### MIND HERE 0 or 99999
				if (!sptSet[v] && ((graph[u][v] !=99999) && (graph[u][v] !=0.0)) && (dist[u] != INT_MAX) && (dist[u]+graph[u][v] < dist[v]))
					dist[v] = dist[u] + graph[u][v];
		}

		// print the constructed distance array
		//printSolution(dist, V);
		return (dist[(V - 1)]);
	}

	// A utility function to find the vertex with minimum distance value, from
	// the set of vertices not yet included in shortest path tree
	static int minDistance(double[] dist, boolean sptSet[])
	{
		int v, V = 32, min_index = -1;
		double min = INT_MAX;

		for ( v = 0; v < V; v++){
			if (sptSet[v] == false && dist[v] <= min){		//if (sptSet[v] == false && dist[v] < min)
				min = dist[v];
				min_index = v;
			}
		}
		return min_index;
	}
	static void printSolution(double[] dist, int n){
		//System.out.println("Total from Source 1 to Destination 32 ");
		System.out.println(dist[(n - 1)]);
		/*
   System.out.println("Vertex Distance from Source");
   for (int i = 0; i < n; i++)
	System.out.println(i + "\t\t" + dist[i]);
		 */
	}


	/*
	public static void dijkstra(int[][] graph, int src){
     int i, u, v, count, V = 32;
	 int[] dist = new int[V];   // The output array.  dist[i] will hold the shortest
                      // distance from src to i

     boolean[] sptSet = new boolean[V]; // sptSet[i] will true if vertex i is included in shortest
                     // path tree or shortest distance from src to i is finalized

     // Initialize all distances as INFINITE and stpSet[] as false
     for ( i = 0; i < V; i++){
       dist[i] = (graph[src][i] == 0 ? INT_MAX:graph[src][i]);
	   sptSet[i] = false;
	}

     // Distance of source vertex from itself is always 0
     dist[src] = 0;

     // Find shortest path for all vertices
     for (count = 0; count < (V-1); count++)
     {
       // Pick the minimum distance vertex from the set of vertices not
       // yet processed. u is always equal to src in first iteration.
       u = minDistance(dist, sptSet);

       // Mark the picked vertex as processed
       sptSet[u] = true;

       // Update dist value of the adjacent vertices of the picked vertex.
       for (v = 0; v < V; v++)

         // Update dist[v] only if is not in sptSet, there is an edge from 
         // u to v, and total weight of path from src to  v through u is 
         // smaller than current value of dist[v]
         if (!sptSet[v] && (graph[u][v] !=0) && (dist[u] != INT_MAX) && (dist[u]+graph[u][v] < dist[v]))
            dist[v] = dist[u] + graph[u][v];
     }

     // print the constructed distance array
     printSolution(dist, V);
}

// A utility function to find the vertex with minimum distance value, from
// the set of vertices not yet included in shortest path tree
static int minDistance(int dist[], boolean sptSet[])
{
   int min = INT_MAX, v, V = 32, min_index = -1;

   for ( v = 0; v < V; v++){
      if (sptSet[v] == false && dist[v] <= min){		//if (sptSet[v] == false && dist[v] < min)
         min = dist[v];
		 min_index = v;
		}
	}
   return min_index;
}
static void printSolution(int[] dist, int n){
   System.out.println("Vertex Distance from Source");
   for (int i = 0; i < n; i++)
	System.out.println(i + "\t\t" + dist[i]);
	}
	 */


	private static double dijkstras(double[][] g, int start) // Added a start point.
	{
		// Dijkstra's Algorithm
		int i, j, length = 32, currentNode;
		double[] best = new double[length];
		boolean[] visited = new boolean[length];
		double min, max = 10000000.0; // Infinity equivalent.
		int[] route = new int[length];
		for ( i = 0; i < length; i++)
		{
			best[i] = max;
			visited[i] = false;
		}

		best[start] = start; // Changed the 0 to variable start.

		for( i = 0; i < length; i++)
		{
			min = max;
			currentNode = 0;
			for ( j = 0; j < length; j++)
			{
				if (!visited[j] && best[j] <= min)
				{
					currentNode = j;
					min = best[j];
				}
			}
			visited[currentNode] = true;
			for ( j = 0; j < length; j++)
			{
				if ((g[currentNode][j] < max) && ((best[currentNode] +   g[currentNode][j]) < best[j]))
				{
					best[j] = best[currentNode] + g[currentNode][j];
				}
			}
		}
		
		/*
		for( i = 0; i < length; i++){
			if(visited[i]){
				route[i] = i;
				System.out.print("-" + (i+1));
			}
		}
		*/
		return best[(length - 1)];
	}

}