import java.util.Comparator;

public class IndivWithEnergy implements Comparator<IndivWithEnergy>{
//public class IndivWithFitness implements Comparator<double>{
	int index;
	double energy;
	Individual individual;
	
	public IndivWithEnergy(){
		individual = new Individual();
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public double getEnergy() {
		return this.energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
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
	public int compare(IndivWithEnergy o1, IndivWithEnergy o2) {
		return (int) (o1.getEnergy() - o2.getEnergy());
		//return (int) (-(o1.getFitness() - o2.getFitness()));
	}



	public static Comparator<IndivWithEnergy> energyComparator = new Comparator<IndivWithEnergy>() {
		//static Comparator<Tracking> NbrNoComparator = new Comparator<Tracking>() {
		@Override
		public int compare(IndivWithEnergy t1, IndivWithEnergy t2) {
			return (int) (t1.getEnergy() - t2.getEnergy());
			//return (int) (-(t1.getFitness() - t2.getFitness()));
		}
	};
	
}
