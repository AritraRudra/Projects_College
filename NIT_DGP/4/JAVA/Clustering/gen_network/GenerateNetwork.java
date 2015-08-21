package gen_network;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class GenerateNetwork {
	public static void main(String[] args) {

		int i;
		int L = 100; // maximum range and distance of axes;
		int noOfNodes = 100,minInt = 1, maxInt = L;
		String filePath = "D://Java//Project//Mine//4//Clustering//";

		int[] netXLoc= new Generate_Random_Ints(minInt, maxInt, noOfNodes).getRandomNos();
		int[] netYLoc= new Generate_Random_Ints(minInt, maxInt, noOfNodes).getRandomNos();
		
		BufferedWriter writer = null;
		try {
			//create a temporary file
			File fileName;
			//fileName = new File(filePath + "Network_Data_" + L + "_" + noOfNodes+ ".txt");	//L_NoofNodes
			fileName = new File(filePath + "Network_Data.txt");//_" + L + "_" + noOfNodes+ ".txt");	//L_NoofNodes
			writer = new BufferedWriter(new FileWriter(fileName));
			
			for( i = 0; i < noOfNodes; i++)
				writer.write(Integer.toString(netXLoc[i]) + " ");
			writer.newLine();
			for( i = 0; i < noOfNodes; i++)
				writer.write(Integer.toString(netYLoc[i]) + " ");
			
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {}
		}
	}
}