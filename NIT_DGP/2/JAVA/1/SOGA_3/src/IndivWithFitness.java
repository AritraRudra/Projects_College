import java.util.Comparator;

public class IndivWithFitness implements Comparator<IndivWithFitness>{
//public class IndivWithFitness implements Comparator<double>{
	int index;
	double fitness;
	Individual individual;
	
	public IndivWithFitness(){
		individual = new Individual();
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getFitness() {
		return this.fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

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
	public int compare(IndivWithFitness o1, IndivWithFitness o2) {
		return (int) (o1.getFitness() - o2.getFitness());
		//return (int) (-(o1.getFitness() - o2.getFitness()));
	}



	public static Comparator<IndivWithFitness> fitnessComparator = new Comparator<IndivWithFitness>() {
		//static Comparator<Tracking> NbrNoComparator = new Comparator<Tracking>() {
		@Override
		public int compare(IndivWithFitness t1, IndivWithFitness t2) {
			return (int) (t1.getFitness() - t2.getFitness());
			//return (int) (-(t1.getFitness() - t2.getFitness()));
		}
	};
	
}
