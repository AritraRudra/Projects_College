import java.util.Comparator;

public class IndivWithTrnsCst implements Comparator<IndivWithTrnsCst>{
//public class IndivWithFitness implements Comparator<double>{
	int index;
	double trnsCst;
	Individual individual;
	
	public IndivWithTrnsCst(){
		individual = new Individual();
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public double getTrnsCst() {
		return this.trnsCst;
	}

	public void setTrnsCst(double trnsCst) {
		this.trnsCst = trnsCst;
	}
	/*
	public double getFitness() {
		return this.fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	*/

	public Individual getIndivdual() {
		return this.individual;
	}

	public void setIndivdual(Individual indivdual) {
		this.individual = indivdual;
	}
	
	
	/*
    public int compare(Individual o1, Individual o2) {
		return (int) (-(o1.getFitness() - o2.getFitness()));	//for reverse order
	}
    */
    
	//@Override
	public int compare(IndivWithTrnsCst o1, IndivWithTrnsCst o2) {
		return (int) (o1.getTrnsCst() - o2.getTrnsCst());
		//return (int) (-(o1.getFitness() - o2.getFitness()));
	}

	public static Comparator<IndivWithTrnsCst> trnsCstComparator = new Comparator<IndivWithTrnsCst>() {
		//static Comparator<Tracking> NbrNoComparator = new Comparator<Tracking>() {
		@Override
		public int compare(IndivWithTrnsCst t1, IndivWithTrnsCst t2) {
			return (int) (t1.getTrnsCst() - t2.getTrnsCst());
			//return (int) (-(t1.getFitness() - t2.getFitness()));
		}
	};	
}