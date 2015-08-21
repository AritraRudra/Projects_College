import java.util.Comparator;

public class IndivWithDelay implements Comparator<IndivWithDelay>{
//public class IndivWithFitness implements Comparator<double>{
	int index;
	double delay;
	Individual individual;
	
	public IndivWithDelay(){
		individual = new Individual();
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getDelay() {
		return this.delay;
	}

	public void setDelay(double delay) {
		this.delay = delay;
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
	public int compare(IndivWithDelay o1, IndivWithDelay o2) {
		return (int) (o1.getDelay() - o2.getDelay());
		//return (int) (-(o1.getFitness() - o2.getFitness()));
	}



	public static Comparator<IndivWithDelay> delayComparator = new Comparator<IndivWithDelay>() {
		//@Override
		public int compare(IndivWithDelay t1, IndivWithDelay t2) {
			return (int) (t1.getDelay() - t2.getDelay());
			//return (int) (-(t1.getFitness() - t2.getFitness()));
		}
	};
	
}
