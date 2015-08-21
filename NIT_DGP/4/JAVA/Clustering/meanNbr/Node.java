package meanNbr;

//import java.util.Arrays;

public class Node {
	int nodeNo,xLoc,yLoc,nbrs[],noOfNbrs;
	boolean checked=false,isSingle;
	int index,myHead=-1;

	int getIndex() {
		return index;
	}
	void setIndex(int index) {
		this.index = index;
	}
	public int getNodeNo() {
		return nodeNo;
	}
	public void setNodeNo(int nodeNo) {
		this.nodeNo = nodeNo;
	}
	public int getxLoc() {
		return xLoc;
	}
	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}
	public int getyLoc() {
		return yLoc;
	}
	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}
	public int[] getNbrs() {
		return nbrs;
	}
	public void setNbrs(int[] nbrs) {
		this.nbrs = nbrs;
	}
	public int getNoOfNbrs() {
		return noOfNbrs;
	}
	public void setNoOfNbrs(int noOfNbrs) {
		this.noOfNbrs = noOfNbrs;
	}
	int getMyHead() {
		return myHead;
	}
	void setMyHead(int myHead) {
		this.myHead = myHead;
	}

	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isSingle() {
		return isSingle;
	}
	public void setSingle(boolean isSingle) {
		this.isSingle = isSingle;
	}	


	public Node(){
		super();
	}
	public Node(int nodeNo, int xLoc, int yLoc, int[] nbrs, int noOfNbrs,boolean checked, boolean isSingle) {
		//super();		
		this.nodeNo = nodeNo;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.nbrs = nbrs;
		this.noOfNbrs = noOfNbrs;
		this.checked = checked;
		this.isSingle = isSingle;
	}
	public Node(int index,int nodeNo, int xLoc, int yLoc, int[] nbrs, int noOfNbrs,boolean checked, boolean isSingle) {
		//super();
		this.index=index;
		this.nodeNo = nodeNo;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.nbrs = nbrs;
		this.noOfNbrs = noOfNbrs;
		this.checked = checked;
		this.isSingle = isSingle;
	}
	public Node(Node node){
		this.index=node.getIndex();
		this.nodeNo = node.getNodeNo();
		this.xLoc = node.getxLoc();
		this.yLoc = node.getyLoc();
		this.nbrs = node.getNbrs();
		this.noOfNbrs = node.getNoOfNbrs();
		this.checked = node.isChecked();
		this.isSingle = node.isSingle();
	}

	public String toString(){
		String str=new String();
		str=this.index+" "+this.nodeNo+" "+this.noOfNbrs+" "+this.checked;
		return str;		
	}

	public void removeNbr(int nbr) {		
		if(nbr==this.nodeNo){
			//System.out.print("##");
			return;
		}
		else{
			/*for(int a=0;a<this.noOfNbrs;a++)
				System.out.print(this.nbrs[a]);
			System.out.println("after ");*/
			this.noOfNbrs=(this.noOfNbrs-1);
			int i=0,newNbrs[]=new int[this.noOfNbrs];		
			while(nbr!=this.nbrs[i]){
				newNbrs[i]=this.nbrs[i];
				i++;
			}
			for(int j=i;j<this.noOfNbrs;j++)
				newNbrs[j]=this.nbrs[j+1];
			this.nbrs=newNbrs;
			/*for(int a=0;a<this.noOfNbrs;a++)
				System.out.print(this.nbrs[a]);
			System.out.println();*/
		}
	}
}