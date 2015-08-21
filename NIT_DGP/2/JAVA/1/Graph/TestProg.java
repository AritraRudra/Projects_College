import java.util.Stack;
import java.util.Queue;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;

public class TestProg
{

   public static void main(String[] args)
   {
   int i, j, noOfNodes = 32;
   int[][] adjMat = new int[noOfNodes][noOfNodes];
//                        0  1  2  3  4  5  6  7  8
// ===================================================
/*
      int[][] conn = {  { 0, 1, 0, 1, 0, 0, 0, 0, 1 },  // 0
			{ 1, 0, 0, 0, 0, 0, 0, 1, 0 },  // 1
			{ 0, 0, 0, 1, 0, 1, 0, 1, 0 },  // 2
			{ 1, 0, 1, 0, 1, 0, 0, 0, 0 },  // 3
			{ 0, 0, 0, 1, 0, 0, 0, 0, 1 },  // 4
			{ 0, 0, 1, 0, 0, 0, 1, 0, 0 },  // 5
			{ 0, 0, 0, 0, 0, 1, 0, 0, 0 },  // 6
			{ 0, 1, 1, 0, 0, 0, 0, 0, 0 },  // 7
			{ 1, 0, 0, 0, 1, 0, 0, 0, 0 } };// 8
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

      Graph G = new Graph(adjMat);
      G.bfs();
	  
	  System.out.println();
	  G.clearVisited();
      G.dfs(0);

   }
}