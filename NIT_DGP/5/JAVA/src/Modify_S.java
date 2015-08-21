import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Modify_S {
	public static void main(String[] args) {
		int t,i,j,r,c,noOfNodes=64;
		boolean flag=false;
		double s=0.5,S[][]=new double[noOfNodes][noOfNodes];

		String dataFilesPath = "D://College//Project//5//Data//Set_8//";
		String resultFilePath = "D://College//Project//5//Results//Set_8//";
		String tuningInfFile=new String(dataFilesPath + "Tuning_Parameter.txt");		
		Scanner in = new Scanner(System.in);
		Scanner tuningData=null;
		BufferedWriter writer = null;
		File fileName=new File(resultFilePath + "S_Mod.txt");		

		try {
			tuningData = new Scanner(new FileInputStream(tuningInfFile));

			for (i = 0; i < noOfNodes; i++){
				for (j = 0; j < noOfNodes; j++){
					S[i][j] = tuningData.nextDouble();
				}
				tuningData.nextLine();
				//System.out.println(N_i + ", " + N_j + ", " + E_i + "," + D_i);
			}
			tuningData.close();


		}catch (FileNotFoundException expFile) {
			System.out.println("Files containing Network Information are not found. "); 
			expFile.printStackTrace();
		}catch(InputMismatchException expIn){
			System.out.println("Files containing Network Information have different format/type. "); 
			expIn.printStackTrace();
		}

		t=in.nextInt();		//number of nodes to change
		if(t<65){
			flag=true;
			while(t-->0){
				r=in.nextInt();		// row 
				c=in.nextInt();		// column
				s=in.nextDouble();		// new value of s
				S[r-1][c-1]=s;		// node number -1 because starts from 0
			}
		}
		if(!flag)
			s=in.nextDouble();

		in.close();

		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			if(flag){
				for (i = 0; i < noOfNodes; i++){
					for (j = 0; j < noOfNodes; j++){
						writer.write(Double.toString(S[i][j]) + " ");
					}
					writer.write("\n");
				}
			}
			else{
				for (i = 0; i < noOfNodes; i++){
					for (j = 0; j < noOfNodes; j++){
						S[i][j]=s;
						writer.write(Double.toString(S[i][j]) + " ");
					}
					writer.write("\n");
				}
			}
			writer.flush();
			try {
				// Close the writer regardless of what happens...
				writer.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}