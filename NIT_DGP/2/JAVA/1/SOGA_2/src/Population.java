import java.util.Arrays;

public class Population {
	
	private int i=0, populationSize;//size=individuals.length;
	private int[][] adjMat,costMatDelay, costMatEnrg;
	private double[][] S;
	public Individual[] individuals, sortedIndiv;	//in descending order
	

	/*
	 * Constructors
	 */
	// Create a population
	public Population(int populationSize, boolean initialize){
		this.individuals = new Individual[populationSize];
		this.populationSize = populationSize;
	}
	
	public Population(int populationSize, boolean initialize, int[][] adjMat, int[][] costMatDelay, int[][] costMatEnrg, double[][] S) {
		this.populationSize = populationSize;
		individuals = new Individual[populationSize];
		this.setNetworkPropInPop(adjMat, costMatDelay, costMatEnrg, S);
		for ( i = 0; i < populationSize; i++) {
			individuals[i] = new Individual();
			individuals[i].setNetworkProp(adjMat, costMatDelay, costMatEnrg, S);
			//individuals[i].setNetworkProp(this.adjMat, this.costMatDelay, this.costMatEnrg, this.S);
		}
		
		// Initialize population
		/*	QoS Routing Algorithm Based on Multi-Objective Optimization for Wireless Mesh Networks
			Miguel Camelo, Member, IEEE,, Carlos Oma�na, Harold Castro
			Population Initialization : Create a few randomly and others by random DFS and BFS
		 */
		if (initialize) {
			// Loop and create individuals
			for (i = 0; i < this.populationSize; i++) {
				Individual newIndividual = new Individual();
				newIndividual.setNetworkProp(this.adjMat, this.costMatDelay, this.costMatEnrg, this.S);
				//create first 128 random chromosomes
				if(i<128)
					newIndividual.generateChromosome("RANDOM");
					//System.out.println(newIndividual.getGene(5));
				//create rest by BFS or DFS
				else if(i%2 == 0)
					newIndividual.generateChromosome("BFS");
				else
					newIndividual.generateChromosome("DFS");
				
				setIndividual(i, newIndividual);
			}
		}
	}
	
	
	//Getters and Setters

	public int[][] getAdjMat() {
		return adjMat;
	}

	public void setAdjMat(int[][] adjMat) {
		this.adjMat = adjMat;
	}

	public int[][] getCostMatDelay() {
		return costMatDelay;
	}

	public void setCostMatDelay(int[][] costMatDelay) {
		this.costMatDelay = costMatDelay;
	}

	public int[][] getCostMatEnrg() {
		return costMatEnrg;
	}

	public void setCostMatEnrg(int[][] costMatEnrg) {
		this.costMatEnrg = costMatEnrg;
	}

	public double[][] getS() {
		return S;
	}

	public void setS(double[][] S) {
		this.S = S;
	}

	// Get population size
	public int getPopSize() {
		return (this.populationSize);
	}
	
	public Individual getIndividual(int index) {
		return individuals[index];
	}
	
	public void setIndividual(int index, Individual indiv) {
		//System.out.println("In Popu.setIndiv : " + " " + indiv);
		this.individuals[index] = new Individual();
		this.individuals[index] = indiv;
		this.individuals[index].setIndex(index);
	}
	
	//End of getters and setters
	
	public void setNetworkPropInPop( int[][] adjMat, int[][] costMatDelay, int[][] costMatEnrg, double[][] S) {
		this.adjMat = adjMat;
		this.costMatDelay = costMatDelay;
		this.costMatEnrg = costMatEnrg;
		this.S = S;
	}
	
	/*
	public void sortByFitness(int populationSize){
		//sortedIndiv = new Individual[populationSize];
		//sortedIndiv = individuals;
		IndivWithFitness indivWithFitness[] = new IndivWithFitness[populationSize];
		for (i = 0; i < populationSize; i++) {
			indivWithFitness[i] = new IndivWithFitness();
			indivWithFitness[i].setIndex(individuals[i].getIndex());
			indivWithFitness[i].setIndivdual(individuals[i]);
			indivWithFitness[i].setFitness(individuals[i].getFitness(this.adjMat,this.costMatDelay, this.costMatEnrg, this.S));			
		}
		
		Arrays.sort(indivWithFitness,IndivWithFitness.fitnessComparator);
		
		sortedIndiv = new Individual[populationSize];
		for (i = 0; i < populationSize; i++) {	//if sorted in increasing order then reverse this loop
			//sortedNodes[i]=asortedNodes[trk[i].getIndex()];
			sortedIndiv[i] = new Individual();
			//setIndividual(i, indivWithFitness[i].getIndivdual());
			sortedIndiv[i].setIndex(i);
			sortedIndiv[i] = indivWithFitness[i].getIndivdual();
			System.out.println(sortedIndiv[i].getFitness());
		}
	}
	*/
	
	public void sortByFitness(){
		//sortedIndiv = new Individual[populationSize];
		//sortedIndiv = individuals;
		IndivWithFitness indivWithFitness[] = new IndivWithFitness[populationSize];
		for (i = 0; i < populationSize; i++) {
			indivWithFitness[i] = new IndivWithFitness();
			indivWithFitness[i].setIndex(individuals[i].getIndex());
			indivWithFitness[i].setIndivdual(individuals[i]);
			indivWithFitness[i].setFitness(individuals[i].getFitness());
			//indivWithFitness[i].setFitness(individuals[i].getFitness(this.adjMat,this.costMatDelay, this.costMatEnrg, this.S));			
		}
		
		//TODO
		Arrays.sort(indivWithFitness,IndivWithFitness.fitnessComparator);
		//Arrays.sort(indivWithFitness,null);
		//Arrays.sort(indivWithFitness);
		
		sortedIndiv = new Individual[populationSize];
		for (i = 0; i < populationSize; i++) {	//if sorted in increasing order then reverse this loop
			//sortedNodes[i]=asortedNodes[trk[i].getIndex()];
			sortedIndiv[i] = new Individual();
			//setIndividual(i, indivWithFitness[i].getIndivdual());
			sortedIndiv[i].setIndex(i);
			sortedIndiv[i] = indivWithFitness[i].getIndivdual();
			//System.out.println(sortedIndiv[i].getFitness());
		}
	}
	
	public Individual getFittest() {
		//Individual fittest = sortedIndiv[0];
		//return (fittest);
		return (sortedIndiv[0]);
	}


	public Individual getSecondFittest() {
		return (sortedIndiv[1]);
	}
	
	public double getFitnessOfFittest() {
		//sortByFitness(populationSize);
		return (sortedIndiv[0].getFitness());
		//return ( sortedIndiv[0].getFitness(this.adjMat,this.costMatDelay, this.costMatEnrg, this.S));
	}
	
	public Individual getBySortedFitness(int rank) {
		return (sortedIndiv[rank]);
	}
	
	public int getIndividualLength(){		//returns chromosome length
		return (sortedIndiv[0].getLength());
	}
	

}
