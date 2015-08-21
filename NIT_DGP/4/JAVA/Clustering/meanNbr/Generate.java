package meanNbr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Generate{

	int R = 59,L=100; // maximum range and distance of axes;
	int noOfNodes = 100,minInt = 1, maxInt = L;

	//int[] netXLoc= new Generate_Random_Ints(minInt, maxInt, noOfNodes).getRandomNos();
	//int[] netYLoc= new Generate_Random_Ints(minInt, maxInt, noOfNodes).getRandomNos();	
	int[] netXLoc = new int[noOfNodes];
	int[] netYLoc = new int[noOfNodes];
	int adj_matrix[][]=new int [noOfNodes][noOfNodes];
	int temp_Nbrs[]=new int[noOfNodes];
	int currNbrs[][]=new int [noOfNodes][];

	Node nodes[]=new Node [noOfNodes]; 	//global nodes
	Node originalNodes[]=new Node [noOfNodes];

	public Generate(){
		//int[] netXLoc= new Generate_Random_Ints(minInt, maxInt, noOfNodes).getRandomNos();
		//int[] netYLoc= new Generate_Random_Ints(minInt, maxInt, noOfNodes).getRandomNos();
		int i,j,k;		
		try {
			//String locFilePath = "D://Java//Project//Mine//4//Clustering//Network_Data_" + L + "_" + noOfNodes+ ".txt";
			String locFilePath = "D://Java//Project//Mine//4//Clustering//Network_Data.txt";//_" + L + "_" + noOfNodes+ ".txt";
			Scanner netLocData = new Scanner(new FileInputStream(locFilePath));
			for(i = 0; i<noOfNodes; i++)
				netXLoc[i] = netLocData.nextInt();
			for(i = 0; i<noOfNodes; i++)
				netYLoc[i] = netLocData.nextInt();
			
			netLocData.close();
		}catch (FileNotFoundException expFile) {
			System.out.println("Files containing Network Information are not found. "); 
			expFile.printStackTrace();
		}catch(InputMismatchException expIn){
			System.out.println("Files containing Network Information have different format/type. "); 
			expIn.printStackTrace();
		}			
			
			
		int count=0;
		for(i = 0;i<noOfNodes;i++){
			for(j = 0;j<noOfNodes;j++){
				adj_matrix[i][j]=0;
				int distance = (int)Math.sqrt(Math.pow((netXLoc[i]-netXLoc[j]),2) + Math.pow((netYLoc[i]-netYLoc[j]),2));		
				if(distance <= R){
					adj_matrix[i][j]=1;
					temp_Nbrs[count]=j+1;//pos(i).getNodeNo();
					count++;
				}
			}
			/*int[] newArray = new int[count];
		    System.arraycopy( temp_Nbrs, 0, newArray, 0, count);*/
			int temp_Nbrs1[]=new int[count];
			for(k=0;k<count;k++){
				if(temp_Nbrs[k]==0)
					break;
				temp_Nbrs1[k]=temp_Nbrs[k];
			}
			currNbrs[i]=temp_Nbrs1;//newArray;			
			count=0;
		}

		for( i=0;i<noOfNodes;i++){
			nodes[i]=new Node();
			nodes[i].setIndex(i);
			nodes[i].setNodeNo(i+1);
			nodes[i].setxLoc(netXLoc[i]);
			nodes[i].setyLoc(netYLoc[i]);
			nodes[i].setNbrs(currNbrs[i]);
			nodes[i].setNoOfNbrs((currNbrs[i]).length);
			nodes[i].setChecked(false);
			if(nodes[i].getNoOfNbrs()==1)
				nodes[i].setSingle(true);

			originalNodes[i]=new Node();
			originalNodes[i]=nodes[i];
		}
	}
	
	
	public Node[] getHeads(){
		int size=0,temp[]=new int[noOfNodes];		
		for(int i=0;i<noOfNodes;i++){
			if(nodes[i].getNoOfNbrs()==1){
				temp[size]=nodes[i].getNodeNo();
				size++;
			}
		}
		int ekla[]=new int[size];
		for(int i=0;i<size;i++){
			ekla[i]=temp[i];
		}		
		Node finalHeads[]=noLR();
		System.out.println("No of Orphan Nodes : "+size+"\nThey are : ");
		for(int i=0;i<size;i++)
			System.out.print(ekla[i]+" ");
		System.out.println();
		return finalHeads;
	}


	private Node[] noLR() {
		boolean notChecked=true;
		int i,size,count=0,unCheckedIndices[],temp[]=new int [noOfNodes];

		Node tempNodes[]=nodes;
		Node sortedNodes[]=sortNodes(tempNodes);
		Node firstHead=getHead(sortedNodes);
		//show();
		reArrangeNbrsOfHead(firstHead.getIndex());
		//show();

		Node head,finalHeads[],otherHeads[]=new Node[noOfNodes];//getHeads(sortedNodes);
		Node tempnds[]=new Node[noOfNodes];
		Node sortedNds[];
		for(i=0;i<noOfNodes;i++)
			otherHeads[i]=new Node();
		//Node head,finalHeads[];//=new Node[noOfNodes];

		while(notChecked){
			size=0;
			for(i=0;i<noOfNodes;i++){
				/*if(nodes[i].isChecked())
					continue;
				else{
					temp[size]=nodes[i].getIndex();
					size++;
				}*/
				if(!(nodes[i].isChecked())){
					//temp[size]=nodes[i].getIndex();
					tempnds[size]=new Node();//#$
					tempnds[size]=nodes[i];//#$
					size++;
				}
			}

			if(size>0){
				/*unCheckedIndices=new int [size];			
				for(i=0;i<size;i++)
					unCheckedIndices[i]=temp[i];


				for(i=0;i<size;i++)
					tempNodes[i]=nodes[unCheckedIndices[i]];	//selecting remaining nodes
				 */
				//#$
				Node currNodes[]=new Node[size];
				for(i=0;i<size;i++){
					currNodes[i]=new Node();
					currNodes[i]=tempnds[i];
				}					
				//show(currNodes);
				sortedNds=sortNodes(currNodes);
				//show(sortedNds);
				head=getHead(sortedNds);
				reArrangeNbrsOfHead(head.getIndex());
				otherHeads[count]=head;
				count++;
			}else if(size==0)
				notChecked=false;
		}
		finalHeads=new Node[count];
		finalHeads[0]=new Node();
		finalHeads[0]= firstHead;
		for(i=1;i<count;i++){
			finalHeads[i]=new Node();
			finalHeads[i]= otherHeads[i];
		}
		return finalHeads;
	}	

	private Node[] sortNodes(Node[] asortedNodes) {		
		int len=asortedNodes.length;
		System.out.print("Len->"+len);
		//System.out.print(length+" #4");
		//int nodeNos[]= new int [length];
		//int arrToSort[]= new int [len];
		Node sortedNodes[]=new Node[len];
		Tracking trk[]=new Tracking[len];
		//Tracking track=new Tracking();

		for(int i=0;i<len;i++){
			trk[i]=new Tracking();
			trk[i].setIndex(i);
			trk[i].setNodeNo(asortedNodes[i].getNodeNo());
			trk[i].setNoOfNbrs(asortedNodes[i].getNoOfNbrs());
			//System.out.print(trk[i].getNoOfNbrs()+"   # ");
			//arrToSort[i]=asortedNodes[i].getNoOfNbrs();
		}
		Arrays.sort(trk,Tracking.NbrNoComparator);
		/*for(int i=0;i<length;i++){
			System.out.print(trk[i].getNodeNo()+" #3 ");
		}*/

		for(int i=0;i<len;i++){
			sortedNodes[i]=new Node();
			//sortedNodes[i]=asortedNodes[((trk[i].getNodeNo())-1)];	//as the node no starts from 1,not 0 (1->L instead of 0->L-1) in arr indexing
			//sortedNodes[i]=nodes[((trk[i].getNodeNo())-1)];	//OK ??!
			sortedNodes[i]=asortedNodes[trk[i].getIndex()];
		}

		for(int i=0;i<len;i++){		//Good for exception viewing
			System.out.print(sortedNodes[i].getNodeNo());
			System.out.print(sortedNodes[i].getNoOfNbrs()+" ");
		}
		System.out.println();
		return (sortedNodes);
	}
	
	private void reArrangeNbrsOfHead(int index){
		nodes[index].setChecked(true);

		int pos,nbr,nbrsCount=nodes[index].getNoOfNbrs();
		int nbrs[]=nodes[index].getNbrs();
		for(int i=0;i<nbrsCount;i++){
			nbr=nbrs[i];
			//nodes[(nbr-1)].setChecked(true); OK too
			pos=getNodePos(nbr);
			nodes[pos].setChecked(true);			
		}

		int size=0,id;
		for(int i=0;i<nbrsCount;i++){	// removing node from nbrs of head's nbrs
			nbr=nbrs[i];
			pos=getNodePos(nbr);
			//size=nodes[(nbr-1)].getNoOfNbrs();
			size=nodes[pos].getNoOfNbrs();
			int tempNbrs[]=new int[size];
			//tempNbrs=nodes[(nbr-1)].getNbrs();
			tempNbrs=nodes[pos].getNbrs();
			for(int k=0;k<size;k++){
				//nodes[((tempNbrs[k])-1)].removeNbr(nbr);
				id=getNodePos(tempNbrs[k]);
				nodes[id].removeNbr(nbr);
			}
			//nodes[nbr].arrange(arr);
		}
	}

	private int getNodePos(int nbr) {	//Returns the index
		int position=0;
		for(int i=0;i<noOfNodes;i++){
			if(nodes[i].getNodeNo()==nbr){
				//position=i;
				position=nodes[i].getIndex();
				break;
			}
		}
		//System.out.print(position+"+"+nbr);
		return position;
	}

	private Node getHead(Node[] sortedNodes){
		int mean,j,noOfElem=sortedNodes.length,index;
		double sum=0.0,div=0.0,res=0.0;
		for(int i=0;i<noOfElem;i++){
			//System.out.println(sortedNodes[i].noOfNbrs+"  ");
			sum=sum+sortedNodes[i].getNoOfNbrs();
			div++;
		}
		res=sum/div;
		mean=(int)Math.ceil(res);//ceil(sum/div);
		System.out.println("Mean->"+mean);		

		/*while(sortedNodes[j].getNoOfNbrs()!=mean)		//	7+87+8##Len->611 31 41 53 93 103 
			j++;*/										//	Mean->2
														//Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 6	
														//Can be handled by catching this exception and mean=mean-1
		try {
			j=0;
			while(sortedNodes[j].getNoOfNbrs()!=mean) 
				j++;
		} catch (ArrayIndexOutOfBoundsException arrex1) {
			j=0;
			mean=mean-1;
			try {
				while(sortedNodes[j].getNoOfNbrs()!=mean) 
					j++;
			} catch (ArrayIndexOutOfBoundsException arrex2) {
				j=0;
				mean=mean+2;
				while(sortedNodes[j].getNoOfNbrs()!=mean) 
					j++;
			}			
		}

		index=sortedNodes[j].getIndex();
		//Head head=new Head(sortedNodes[j]);
		Node head=new Node(nodes[index]);

		return head;
	}

	private void show(){
		for(int i=0;i<noOfNodes;i++){
			System.out.println(nodes[i].toString());
		}
	}

	private void show(Node paramNodes[]){
		int len=paramNodes.length;
		for(int i=0;i<len;i++){
			System.out.println(paramNodes[i].toString());
		}
	}


}
