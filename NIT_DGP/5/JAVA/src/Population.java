import java.util.Arrays;

public class Population {
	private int i=0, populationSize;//, noOfNodes;//size=individuals.length;
	private int[][] adjMat;
	private double[][] S,costMatDelay, costMatEnrg;
	public Individual[] individuals, sortedIndiv;	//in descending order


	/*
	 * Constructors
	 */
	// Create a population
	public Population(int populationSize){
		this.individuals = new Individual[populationSize];
		this.populationSize = populationSize;
	}

	public Population(int populationSize, boolean initialize, int[][] adjMat, double[][] costMatDelay, double[][] costMatEnrg, double[][] S,int noOfNodes, int goalNode) {
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
			Miguel Camelo, Member, IEEE,, Carlos Oma˜na, Harold Castro
			Population Initialization : Create a few randomly and others by random DFS and BFS
		 */
		if (initialize) {
			double dist;
			int a = 0;
			boolean bfsDone = false, dfsDone = false;
			int[] chrome=null;// = {0,5,6,8,11,15,14,19,12,32,21,28,30,33,37,44,46,26,17,23,9,31,27,34,18,3,1,16,13,29,36,24,47,63}; //null ;
			// Loop and create individuals
			for (i = 0; i < this.populationSize; i++) {
				Individual newIndividual = new Individual();
				newIndividual.setNetworkProp(this.adjMat, this.costMatDelay, this.costMatEnrg, this.S);
				
				if(i<(2*((this.populationSize)/3)))		// 66 %
					newIndividual.generateChromosome("RANDOM",noOfNodes, goalNode);
				
				else if(i<(9*(this.populationSize/10)))		//(90-66)% = 24%
					newIndividual.generateChromosome("HEURISTIC",noOfNodes, goalNode);
				
				//create rest by BFS or DFS
				/*
				else if(i%2 == 0){		// 5%
					chrome = new int[]{0, 33, 17, 37, 24, 59, 63};
					newIndividual.setChromosome(chrome);
					/*if(!bfsDone){
						bfsDone = true;
						//set 8 use -1 from each element
						chrome = new int[]{0,9,42,8,3,63};
						/*
						newIndividual.generateChromosome("BFS",noOfNodes, goalNode);
						chrome = newIndividual.getChromosome();
						newIndividual.printChromosome();
						
					}else
						newIndividual.setChromosome(chrome);
						*/
				//}
				else{		//5%
					chrome = new int[]{0, 25, 33, 5, 17, 30, 10, 3, 13, 11, 12, 28, 23, 15, 14, 19, 20, 21, 29, 38, 47, 43, 24, 37, 40, 45, 56, 59, 63};
					newIndividual.setChromosome(chrome);
					/*
					if(!dfsDone){
						//System.out.println("Population.Population()");						
						dfsDone = true;
						//set 8 use -1 from each element
						chrome = new int[]{0, 9, 7, 1, 8, 2, 3, 11, 22, 37, 20, 6, 44, 35, 30, 14, 12, 10, 13, 17, 23, 36, 41, 5, 19, 15, 16, 26, 52, 60, 62, 63};
						/*
						newIndividual.generateChromosome("DFS",noOfNodes, goalNode);
						chrome = newIndividual.getChromosome();
						newIndividual.printChromosome();
						
					}else
						newIndividual.setChromosome(chrome);
					*/
				}
				
				/*
				dist = (((double)i)/this.populationSize)*100;
				if( ( ( ((int)dist) % 1) == 0) && (a != (int)dist)){
					a = (int)dist;
					System.out.println(a + "% Done ... ");
				}
				*/
				setIndividual(i, newIndividual);
				//newIndividual.printChromosome();
			}
		}
	}


	//Getters and Setters
	/*
	public int getNoOfNodes() {
		return noOfNodes;
	}

	public void setNoOfNodes(int noOfNodes) {
		this.noOfNodes = noOfNodes;
	}
	*/
	
	public int[][] getAdjMat() {
		return adjMat;
	}

	public void setAdjMat(int[][] adjMat) {
		this.adjMat = adjMat;
	}

	public double[][] getCostMatDelay() {
		return costMatDelay;
	}

	public void setCostMatDelay(double[][] costMatDelay) {
		this.costMatDelay = costMatDelay;
	}

	public double[][] getCostMatEnrg() {
		return costMatEnrg;
	}

	public void setCostMatEnrg(double[][] costMatEnrg) {
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

	public void setNetworkPropInPop( int[][] adjMat, double[][] costMatDelay, double[][] costMatEnrg, double[][] S) {
		this.adjMat = adjMat;
		this.costMatDelay = costMatDelay;
		this.costMatEnrg = costMatEnrg;
		this.S = S;
	}

	public void sortByFitness(){
		//sortedIndiv = new Individual[populationSize];
		//sortedIndiv = individuals;
		IndivWithFitness indivWithFitness[] = new IndivWithFitness[this.populationSize];
		for (i = 0; i < this.populationSize; i++) {
			indivWithFitness[i] = new IndivWithFitness();
			indivWithFitness[i].setIndex(this.individuals[i].getIndex());
			indivWithFitness[i].setIndivdual(this.individuals[i]);
			//indivWithFitness[i].setFitness(this.individuals[i].getFitness());
			indivWithFitness[i].setFitness(this.individuals[i].getFitness(this.adjMat,this.costMatDelay, this.costMatEnrg, this.S));
		}

		
		Arrays.sort(indivWithFitness,IndivWithFitness.fitnessComparator);
		//Arrays.sort(indivWithFitness,null);
		//Arrays.sort(indivWithFitness);

		sortedIndiv = new Individual[this.populationSize];
		for (i = 0; i < this.populationSize; i++) {	//if sorted in increasing order then reverse this loop
			//sortedNodes[i]=asortedNodes[trk[i].getIndex()];
			sortedIndiv[i] = new Individual();
			//setIndividual(i, indivWithFitness[i].getIndivdual());
			sortedIndiv[i].setIndex(i);
			sortedIndiv[i] = indivWithFitness[i].getIndivdual();
			//System.out.println(sortedIndiv[i].getFitness());	//for viewing individual fitness values in non-decreasing order
			//sortedIndiv[i].printChromosome();
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
	
	public double getDelayOfFittest() {
		//sortByFitness(populationSize);
		return (sortedIndiv[0].getDelayCost());
		//return ( sortedIndiv[0].getFitness(this.adjMat,this.costMatDelay, this.costMatEnrg, this.S));
	}
	
	public double getEnergyOfFittest() {
		//sortByFitness(populationSize);
		return (sortedIndiv[0].getEnrgCost());
		//return ( sortedIndiv[0].getFitness(this.adjMat,this.costMatDelay, this.costMatEnrg, this.S));
	}

	public Individual getBySortedFitness(int rank) {
		return (sortedIndiv[rank]);
	}

	public double getAvgFitnessOfPop(){
		double avg, sum = 0.0;
		for (i = 0; i < this.populationSize; i++) {
			sum += this.individuals[i].getFitness(); 
		}
		avg = (sum/this.populationSize);
		return(avg);
	}
	
	public double getAvgDelayOfPop(){
		double avg, sum = 0.0;
		for (i = 0; i < this.populationSize; i++) {
			sum += this.individuals[i].getDelayCost(); 
		}
		avg = (sum/this.populationSize);
		return(avg);
	}
	
	public double getAvgEnergyOfPop(){
		double avg, sum = 0.0;
		for (i = 0; i < this.populationSize; i++) {
			sum += this.individuals[i].getEnrgCost(); 
		}
		avg = (sum/this.populationSize);
		return(avg);
	}
	
	/*//returns chromosome length
	public int getIndividualLength(){
		return (sortedIndiv[0].getChromosomeLength());
	}
	 */
}
