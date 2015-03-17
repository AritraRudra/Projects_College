package meanNbr;

import java.util.Comparator;

public class Tracking implements Comparator<Tracking>{
	int index,nodeNo,noOfNbrs;
	boolean checked=false;

	int getIndex() {
		return index;
	}
	void setIndex(int index) {
		this.index = index;
	}
	int getNodeNo() {
		return nodeNo;
	}
	void setNodeNo(int nodeNo) {
		this.nodeNo = nodeNo;
	}
	int getNoOfNbrs() {
		return noOfNbrs;
	}
	void setNoOfNbrs(int noOfNbrs) {
		this.noOfNbrs = noOfNbrs;
	}
	boolean isChecked() {
		return checked;
	}
	void setChecked(boolean checked) {
		this.checked = checked;
	}		

	public int compareTo(Tracking t1) {
		return (this.noOfNbrs- t1.noOfNbrs);
	}

	@Override
	public int compare(Tracking o1, Tracking o2) {
		return (int) (o1.getNoOfNbrs() - o2.getNoOfNbrs());
	}



	public static Comparator<Tracking> NbrNoComparator = new Comparator<Tracking>() {
		//static Comparator<Tracking> NbrNoComparator = new Comparator<Tracking>() {
		@Override
		public int compare(Tracking t1, Tracking t2) {
			return (int) (t1.getNoOfNbrs() - t2.getNoOfNbrs());
		}
	};
}