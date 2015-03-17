import java.util.Comparator;


public class IndivForCrowdedComparison implements Comparator<IndivForCrowdedComparison>{
	int index, fitnessLevel;
	double crowdingDist;
	Individual individual;

	public IndivForCrowdedComparison(){
		individual = new Individual();
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getCrowdingDist() {
		return (this.crowdingDist);
	}

	public void setCrowdingDist(double crowdingDist) {
		this.crowdingDist = crowdingDist;
	}

	public int getFitnessLevel() {
		return (this.fitnessLevel);
	}

	public void setFitnessLevel(int fitnessLevel) {
		this.fitnessLevel = fitnessLevel;
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


	/*Parameters:
	o1 the first object to be compared.
	o2 the second object to be compared.
	Returns:
	a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
	if o1 < o2 => -1
	if o1 > o2 => +1
	if o1 == o2 => 0
	 */
	//@Override
	public int compare(IndivForCrowdedComparison o1, IndivForCrowdedComparison o2) {
		if(o1.getFitnessLevel() > o2.getFitnessLevel())
			return (+1);
		else if(o1.getFitnessLevel() < o2.getFitnessLevel())
			return (-1);
		else{	//when the ranks are equal
			if(o1.getCrowdingDist() > o2.getCrowdingDist())
				return (-1);
			else if(o1.getCrowdingDist() < o2.getCrowdingDist())
				return (+1);
			else
				return (0);
		}

		//return (int) (o1.getDelay() - o2.getDelay());
		//return (int) (-(o1.getFitness() - o2.getFitness()));
	}



	public static Comparator<IndivForCrowdedComparison> crowdedComparator = new Comparator<IndivForCrowdedComparison>() {
		//static Comparator<Tracking> NbrNoComparator = new Comparator<Tracking>() {
		@Override
		public int compare(IndivForCrowdedComparison o1, IndivForCrowdedComparison o2) {
			if(o1.getFitnessLevel() > o2.getFitnessLevel())
				return (+1);
			else if(o1.getFitnessLevel() < o2.getFitnessLevel())
				return (-1);
			else{	//when the ranks are equal
				if(o1.getCrowdingDist() > o2.getCrowdingDist())
					return (-1);
				else if(o1.getCrowdingDist() < o2.getCrowdingDist())
					return (+1);
				else
					return (0);
				//return (int) (t1.getDelay() - t2.getDelay());
				//return (int) (-(t1.getFitness() - t2.getFitness()));
			}
		}
	};
}
