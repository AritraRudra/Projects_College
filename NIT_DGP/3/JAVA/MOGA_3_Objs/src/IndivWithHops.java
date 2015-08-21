import java.util.Comparator;

public class IndivWithHops implements Comparator<IndivWithHops>{
//public class IndivWithFitness implements Comparator<double>{
	int index;
	int hops;
	Individual individual;
	
	public IndivWithHops(){
		individual = new Individual();
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getHops() {
		return this.hops;
	}

	public void setHops(int hops) {
		this.hops = hops;
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
    
	@Override
	public int compare(IndivWithHops o1, IndivWithHops o2) {
		return (int) (o1.getHops() - o2.getHops());
		//return (int) (-(o1.getFitness() - o2.getFitness()));
	}
	
	public static Comparator<IndivWithHops> hopsComparator = new Comparator<IndivWithHops>() {
		//@Override
		public int compare(IndivWithHops t1, IndivWithHops t2) {
			return (int) (t1.getHops() - t2.getHops());
			//return (int) (-(t1.getFitness() - t2.getFitness()));
		}
	};
}
