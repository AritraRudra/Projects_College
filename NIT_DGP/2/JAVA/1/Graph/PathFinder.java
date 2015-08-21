import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Random;

public class PathFinder{
	public static void main (String[] args){
		int i, j, k, pos, newGene, noOfNodes = 32, noOfNbrs = 0;
		int currNode, nextNodePos, nextNode;
		int[] tempArr1 = new int[160];
		int[] tempPath = new int[160];
		int[][] adjMat = new int[noOfNodes][noOfNodes];
		int delThisNode;
		boolean reachedLastNode = false;
		Random rand = new Random();

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


		//Finds the nbrs of the starting node , i.e., first node
		currNode = 0;
		tempPath[0] = 0;
		for (i = 0; i < noOfNodes ; i++) {
			if(adjMat[currNode][i] == 1){
				tempArr1[noOfNbrs] = i;
				noOfNbrs++;
			}
		}

		nextNodePos = rand.nextInt(noOfNbrs);		// returns a random number between 0 and indiv.length() (31)
		nextNode = tempArr1[nextNodePos];
		tempPath[1] = nextNode;
		pos = 2;
		while(!reachedLastNode){
			if(nextNode == 31){
				reachedLastNode = true;
				//TODO 
				break;
			}
			//else{
			currNode = nextNode;
			noOfNbrs = 0;
			for (i = 0; i < noOfNodes ; i++) {
				if(adjMat[currNode][i] == 1){
					tempArr1[noOfNbrs] = i;
					noOfNbrs++;
				}
			}
			nextNodePos = rand.nextInt(noOfNbrs);
			nextNode = tempArr1[nextNodePos];

			//loop identification and removal i.e., if nextNode was encountered and added previously
			for (i = 0; i < pos ; i++) {
				if(tempPath[i] == nextNode){
					//remove all after this point
					delThisNode = tempPath[i+1];	//don't consider this node during random choosing as it may lead to the formation of loop(s) 
					for ( j = i+1; j < pos; j++) {
						tempPath[j] = -1;
					}
					noOfNbrs = 0;
					for (k = 0; k < noOfNodes ; k++) {
						if((adjMat[nextNode][k] == 1) && (k != delThisNode)){
							tempArr1[noOfNbrs] = k;
							noOfNbrs++;
						}
					}
					nextNodePos = rand.nextInt(noOfNbrs);
					nextNode = tempArr1[nextNodePos];
					pos = i+1;
					break;	//??!
				}
			}
			tempPath[pos] = nextNode;				
			pos++;
		}
		for (i = 0; i < pos ; i++) 
			System.out.print(tempPath[i]+1 + " ");

	}
}