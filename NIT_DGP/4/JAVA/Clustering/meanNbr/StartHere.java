package meanNbr;
public class StartHere {

	public static void main(String[] args) {
		Generate network=new Generate();
		Node heads[]=network.getHeads();
		int noOfHeads=heads.length;
		System.out.println("No of Heads : "+noOfHeads);
		for(int i=0;i<noOfHeads;i++)
			System.out.print(heads[i].getNodeNo()+"  ");
	}
}
