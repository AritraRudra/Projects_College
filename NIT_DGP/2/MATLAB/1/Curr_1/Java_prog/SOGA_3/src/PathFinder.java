
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Random;


public class PathFinder{
	public static void main (String[] args){
		/*
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
		 */


		//for testing method: getRoute 
		//also apply remove loops
		int[] route = getRoute(9);
		for (int i = 0; i < route.length ; i++) 
			System.out.print(route[i]+1 + " ");
		
		

		//for testing method: reAdjustRoute
		/*boolean loopMightExist = true;
		int[] route;
		//int[] toBeAdjusted = {1,2,5,7,15,17,7,9,12,19,32};	//OK
		int[] toBeAdjusted = {1,2,5,7,15,17,7,9,12,19,15,18,20,32};		//Not OK
		//1-2-5-17-15-7-18-6-9-12-21-23-32
		//1-3-4-6-7-9-12-19-15-18-5-19-20-25-32
		 * 1-2-5-17-15-18-5-19-20-25-32
		 * 1-3-4-6-7-9-12-19-15-7-18-6-9-12-21-23-32	<- **
		//while(loopMightExist){
		route = reAdjustRoute(toBeAdjusted);
		//}
		for (int i = 0; i < route.length ; i++) 
			System.out.print(route[i] + " ");

		 */

		/*
		//for testing method: removeLoops
		boolean loopMightExist = true;
		int[] route;
		//int[] toBeAdjusted = {1,2,5,7,15,17,7,9,12,19,32};	//OK by both
		//int[] toBeAdjusted = {1,2,5,7,15,17,7,9,12,19,15,18,20,32};		// OK by removeLoops
		//int[] toBeAdjusted = {1,3,4,6,7,9,12,19,15,18,12,20,21,23,20,32};	// OK by removeLoops
		int[] toBeAdjusted = {1, 22 ,29, 2, 30, 18, 20, 27, 8, 7, 28, 3, 21, 10, 32, 10, 32, 10, 32};
		//1-2-5-17-15-7-18-6-9-12-21-23-32
		//1-3-4-6-7-9-12-19-15-18-5-19-20-25-32
		//1-2-5-17-15-18-5-19-20-25-32
		//1-3-4-6-7-9-12-19-15-7-18-6-9-12-21-23-32	<- **
		
		route = removeLoops(toBeAdjusted);
		//}
		for (int i = 0; i < route.length ; i++) 
			System.out.print(route[i] + " ");
		 */

	}

	public static int[] getRoute(int currNode) {
		int i, j, k, pos, noOfNodes = 32, noOfNbrs = 0;
		int nextNodePos, nextNode, route[];
		int[] tempArr1 = new int[noOfNodes];
		int[] tempPath = new int[noOfNodes];
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

		tempPath[0] = currNode;
		for (i = 0; i < noOfNodes ; i++) {
			if(adjMat[currNode][i] == 1){
				tempArr1[noOfNbrs] = i;
				noOfNbrs++;
			}
		}		
		nextNodePos = rand.nextInt(noOfNbrs);
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

		route = new int[pos];
		for (i = 0; i < pos ; i++) 
			route[i] = tempPath[i];

		return (route);
	}

	public static int[] reAdjustRoute(int[] oldRoute) {
		int i, j, k, lenOld = (oldRoute.length ), lenNew, index;
		//boolean loopMightExist = true;
		int[] tempRoute = new int[lenOld];
		int[] adjustedRoute;

		for (k = 0; k < lenOld ; k++)
			tempRoute[k] = -1;

		/*//loop identification and removal from 4.pdf by searching in forward direction
		for ( i = 0; i < lenOld; i++) {
			for ( j = i+1; j < lenOld; j++) {
				//Finds a loop
				if(oldRoute[j] == oldRoute[i]){
					//Eliminate the loop
					//removeLoop(oldRoute, i, j);
					//remove every node between i and j
					index = j+1;
					for (k = i+1; k <= j ; k++) {
						oldRoute[k]  = oldRoute[index];
						index++;
					}
				}
			}
		}
		 */



		for (i = 0, j=0; i < ( lenOld - j) ; i++, j++) {
			//Find a loop
			if(oldRoute[i] == oldRoute[lenOld-1 - j]){
				//Eliminate the loop
				for (k = 0; k < (i - 1) ; k++) {
					tempRoute[k] = oldRoute[k];
				}
				for (k = (lenOld - j+1); k < lenOld ; k++) {
					tempRoute[k] = oldRoute[k];
				}

			}
		}

		lenNew = 0;
		for (k = 0; k < lenOld ; k++){
			if(tempRoute[k] != -1)
				lenNew++;
		}
		adjustedRoute = new int [lenNew];
		index = 0;
		for (k = 0; k < lenOld ; k++){
			if(tempRoute[k] != -1){
				adjustedRoute[index] = tempRoute[k];
				index++;
			}
		}
		return adjustedRoute;
	}

	public static int[] removeLoops(int[] genes) {
		int i, j, k, lenOld = (genes.length ), lenTemp, index;
		int[] tempRoute = new int[lenOld];
		int[] adjustedRoute;
		int[] oldRoute = genes;
		boolean loopMightExist = true;

		/*
		for (k = 0; k < lenOld ; k++)
			tempRoute[k] = -1;
		 */

		index = tempRoute.length;
		tempRoute = oldRoute;

		while(loopMightExist){
			//loop identification and removal from 4.pdf by searching in forward direction
			lenOld = index;
			oldRoute = tempRoute;
			for ( i = 0; i < lenOld; i++) {
				for ( j = i+1; j < lenOld; j++) {
					//Finds a loop
					if(oldRoute[j] == oldRoute[i]){
						//Eliminate the loop
						//removeLoop(oldRoute, i, j);
						//remove every node between i and j
						index = i+1;
						for (k = j+1; k <lenOld ; k++) {
							oldRoute[index]  = oldRoute[k];
							index++;
						}
						for (k = 0; k <index ; k++) {
							tempRoute[k] = oldRoute[k];
						}
						System.out.println(index);
					}
				}
			}
			if(i == index)
				loopMightExist= false;
		}
		
		adjustedRoute = new int[index];
		for ( i = 0; i < index; i++)
			adjustedRoute[i] = tempRoute[i];

		return adjustedRoute;
		//return tempRoute; //adjustedRoute;
	}
}
