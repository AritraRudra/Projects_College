//http://stackoverflow.com/questions/18009158/i-want-to-write-a-prims-algorithm-in-java

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

class Prims{
	public static void main(String[] args){
		int i, j, noOfNodes = 32;
		int adjMat[][] = new int [noOfNodes][noOfNodes];
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
		Graph g=new Graph(noOfNodes, costMatDelay);
		g.algo();
	}
}

class Graph{
	int noOfNodes,e;
	double weight[][];//=new double[noOfNodes][noOfNodes];
	//int visited[]=new int [noOfNodes];
	boolean[] visited;//=new boolean[noOfNodes];
	double d[];//=new double[noOfNodes];
	int p[];//=new int[noOfNodes];

	public Graph(int noOfNodes, double[][] wtMat) {
		this.noOfNodes = noOfNodes;
		this.weight = wtMat;
		visited = new boolean[noOfNodes];
		d = new double[noOfNodes];
		p = new int[noOfNodes];
	}

	void creategraph(){
		int i;//,j,a,b,w;

		/*
		for ( i=0;i< noOfNodes;i++)		// 1 theke kno ?!!
			for( j=0;j< noOfNodes;j++)
				weight[i][j]=0;
		 */

		for (i=0;i<noOfNodes;i++)
		{
			visited[i]= false;
			p[i]=0;
			d[i]=Integer.MAX_VALUE;
		}

		/*
		for ( i=1;i<=e;i++)
		{
			System.out.print("\nEnter edge a,b and weight w :");
			a=Integer.parseInt(in.readLine());
			b=Integer.parseInt(in.readLine());
			w=Integer.parseInt(in.readLine());
			weight[a][b]=weight[b][a]=w;
		}
		 */

	}

	void algo (){
		creategraph();
		int current,total, i;
		double mincost;
		//current=1;d[current]=0;
		current=0;
		d[current]=0.0;
		total=1;
		visited[current] = true;
		while(total!=noOfNodes){
			for (i=0;i<noOfNodes;i++){  
				if((weight[current][i] != 99999) && (weight[current][i] != 0)){
					if(visited[i] == false){
						if(d[i]>weight[current][i])	{
							d[i]=weight[current][i];
							p[i]=current;
						}
					}
				}
			}
			mincost = Integer.MAX_VALUE;
			for (i=0;i<noOfNodes;i++){
				if(visited[i]==false){
					if(d[i]<mincost){
						mincost=d[i];
						current=i;
					}
				}
			}
			visited[current]=true;
			total++;
		}

		mincost = 0.0;		
		for(i=0;i<noOfNodes;i++)
			mincost = mincost + d[i];

		System.out.println("Minimum cost = " + mincost);
		System.out.print("Minimum Spanning tree is : " + (p[0] + 1) );

		for(i=1;i<noOfNodes;i++)
			System.out.print("-" + (p[i] + 1));	//"vertex" +i+"is connected to"+p[i]);
	}
}
