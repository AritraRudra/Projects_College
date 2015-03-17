package meanNbr;

import java.util.Random;

public class Generate_Random_Ints {
	
	int minInt, maxInt,noOfInts;	
	
	public Generate_Random_Ints(int minInt, int maxInt,int noOfInts) {		
		this.noOfInts = noOfInts;
		this.minInt = minInt;
		this.maxInt = maxInt;
	}
		
	int[] getRandomNos(){
		int[] arr=new int [noOfInts];
		Random r = new Random();
		for(int i=0; i<noOfInts; i++){
			//arr[i] = r.nextInt(noOfInts);
			arr[i]=r.nextInt(maxInt-minInt+1) + minInt;
			//System.out.print(arr[i]+"  ");
		}		
		return(arr);
		//int i1 = r.nextInt(maxInt - minInt + 1) + minInt;
	}
}
