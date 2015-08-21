import java.util.ArrayList;
import java.util.Arrays;

public class Population {
	private int i=0, populationSize, appendPosition = 0;//size=individuals.length;
	private int[][] adjMat;
	private double[][] trnsCstMat;
	public Individual[] individuals, sortedIndiv;	//, sortedIndivByHops, sortedIndivByTrnsCst;//	//in descending order

	//ArrayList<Individual[]> fronts = new ArrayList<Individual[]>();		//2D array can be used instead of arraylist
	ArrayList<Individual> currFront = new ArrayList<Individual>();
	ArrayList<Individual> nextFront = new ArrayList<Individual>();		//Not the front after, i.e., not the second after first, used as temp
	ArrayList<ArrayList<Individual>> fronts = new ArrayList<ArrayList<Individual>>(); 

	/*
	 * Constructors
	 */

	// Create a population
	/*
	public Population(int populationSize){
		this.individuals = new Individual[populationSize];
		this.populationSize = populationSize;
	}
	 */

	public Population(int populationSize, boolean addStepByStep){
		this.individuals = new Individual[populationSize];
		if(addStepByStep)
			this.populationSize = 0;
		else
			this.populationSize = populationSize;
	}

	public Population(int populationSize, boolean initialize, int[][] adjMat, double[][] trnsCstMat,int noOfNodes, int goalNode) {
		this.populationSize = populationSize;
		this.setNetworkPropInPop(adjMat,trnsCstMat);
		individuals = new Individual[populationSize];
		for ( i = 0; i < this.populationSize; i++) {
			individuals[i] = new Individual();
			individuals[i].setNetworkProp(adjMat, trnsCstMat);
			//individuals[i].setNetworkProp(this.adjMat, this.actMatDelay, this.actMatEnrg, this.S);
		}

		// Initialize population
		/*	QoS Routing Algorithm Based on Multi-Objective Optimization for Wireless Mesh Networks
			Miguel Camelo, Member, IEEE,, Carlos Oma˜na, Harold Castro
			Population Initialization : Create a few randomly and others by random DFS and BFS
		 */
		if (initialize) {
			int a = 0;
			double dist;
			boolean bfsDone = false, dfsDone = false;
			int[] chrome = null;// = {0,5,6,8,11,15,14,19,12,32,21,28,30,33,37,44,46,26,17,23,9,31,27,34,18,3,1,16,13,29,36,24,47,63}; //null ;
			// Loop and create individuals
			for (i = 0; i < this.populationSize; i++) {
				Individual newIndividual = new Individual();
				newIndividual.setNetworkProp(this.adjMat, this.trnsCstMat);
				/*
				if(i<(2*((this.populationSize)/3)))		// 66 %
					newIndividual.generateChromosome("RANDOM",noOfNodes, goalNode);
				
				else*/ if(i<(9*(this.populationSize/10)))		//(90-66)% = 24%
					newIndividual.generateChromosome("HEURISTIC",noOfNodes, goalNode);
				
				//create rest by BFS or DFS
				else if(i%2 == 0){		// 5%
					if(!bfsDone){
						bfsDone = true;
						//chrome = new int[]{0,5,6,8,11,15,14,19,12,32,21,28,30,33,37,44,46,26,17,23,9,31,27,34,18,3,1,16,13,29,36,24,47,63};
						newIndividual.generateChromosome("BFS",noOfNodes, goalNode);
						chrome = newIndividual.getChromosome();
						//newIndividual.printChromosome();
					}else
						newIndividual.setChromosome(chrome);
				}
				else{		//5%
					if(!dfsDone){
						dfsDone = true;
						chrome = new int[]{0,16,17,1,32,2,30,38,6,24,55,18,52,57,14,33,19, 45,59};
						// 1    17    18     2    33     3    31    39     7    25    56    19    53    58    15    34    20    46    60
						//newIndividual.generateChromosome("DFS",noOfNodes, goalNode);
						newIndividual.setChromosome(chrome);
					}else
						newIndividual.setChromosome(chrome);
				}
				/*
				if(i<(9*(this.populationSize/10)))		//(90-66)% = 24%
					newIndividual.generateChromosome("HEURISTIC",noOfNodes, goalNode);
				
				//create rest by BFS or DFS
				else if(i%2 == 0)		// 5%
					newIndividual.generateChromosome("BFS",noOfNodes, goalNode);
				
				else		//5%
					newIndividual.generateChromosome("DFS",noOfNodes, goalNode);
				*/
				
				dist = (((float)i)/this.populationSize)*100;
				if( ( ( ((int)dist) % 1) == 0) && (a != (int)dist)){
					a = (int)dist;
					System.out.println(a + "% Done ... ");
				}
				
				//newIndividual.printChromosome();
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

	public double[][] getTrnsCstMat() {
		return trnsCstMat;
	}

	public void setTrnsCstMat(double[][] trnsCstMat) {
		this.trnsCstMat = trnsCstMat;
	}

	// Get population size
	public int getPopSize() {
		return (this.populationSize);
	}

	public void setPopSize(int newSize) {
		this.populationSize = newSize;
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

	public Individual[] getIndividuals() {
		return individuals;
	}

	public void setIndividuals(Individual[] individualsOfA, Individual[] individualsOfB, int sizeOfA, int sizeOfB) {
		//System.out.println("In Popu.setIndiv : " + " " + indiv);
		int i, totalSize = (sizeOfA + sizeOfB);
		for (i = 0; i < sizeOfA; i++) {
			this.individuals[i] = new Individual();
			this.individuals[i] = individualsOfA[i];
			this.individuals[i].setIndex(i);
		}
		for (i = sizeOfA; i < totalSize; i++) {
			this.individuals[i] = new Individual();
			this.individuals[i] = individualsOfB[(i - sizeOfA)];
			this.individuals[i].setIndex(i);
		}
	}

	//End of getters and setters

	public void setNetworkPropInPop( int[][] adjMat, double[][] trnsCstMat) {
		this.adjMat = adjMat;		
		this.trnsCstMat = trnsCstMat;
	}

	public ArrayList<Individual> getFrontByIndex(int index) {
		return (this.fronts.get(index));
	}

	public void setFront(int index, ArrayList<Individual> frontToadd) {
		this.fronts.set(index, frontToadd);
	}

	public void addFront(ArrayList<Individual> frontToadd) {
		int i, noOfElm = frontToadd.size();
		Individual indivToAdd = new Individual();
		Individual[] indivsFromFront = frontToadd.toArray(new Individual[noOfElm]);
		this.fronts.add(frontToadd);
		this.populationSize += noOfElm;
		for( i=0; i< noOfElm ; i++){
			indivToAdd = indivsFromFront[i];	//new Individual();
			this.appendIndividual(indivToAdd); 
		}
	}

	private void appendIndividual(Individual indivToAdd) {
		this.individuals[this.appendPosition] = indivToAdd;
		this.individuals[this.appendPosition].setIndex(this.appendPosition);
		this.appendPosition++;
	}

	/* Puts nextSize no. of individuals
	 * specified by tempArrList
	 * into to the front denoted by frontNo
	 */
	//public void putIndivIntoLastFront(int frontNo, ArrayList<Individual> tempArrList, int nextSize) {
	public void putIndivIntoLastFront(ArrayList<Individual> tempArrList, int nextSize) {
		int i;
		ArrayList<Individual> frontToAdd = new ArrayList<Individual>();
		for( i = 0; i<nextSize ; i++){
			frontToAdd.add(tempArrList.get(i));
			this.appendIndividual(tempArrList.get(i));
		}

		this.fronts.add(frontToAdd);
		this.populationSize += nextSize; // frontToAdd.size();
	}

	/*
	public void putIndivIntoFrontOneByOne(Individual individual) {

	}
	 */

	public Individual[] sortByHops(Individual[] indivsToSort){
		//sortedIndiv = new Individual[populationSize];
		//sortedIndiv = individuals;
		int sortSize = indivsToSort.length;
		Individual[] sortedIndivByHops;
		IndivWithHops indivWithHops[] = new IndivWithHops[sortSize];
		for (i = 0; i < sortSize; i++) {
			indivWithHops[i] = new IndivWithHops();
			indivWithHops[i].setIndex(indivsToSort[i].getIndex());
			indivWithHops[i].setIndivdual(indivsToSort[i]);
			//indivWithFitness[i].setFitness(this.individuals[i].getFitness());
			indivWithHops[i].setHops(indivsToSort[i].getHops());
			//indivWithDelay[i].setEnergy(this.individuals[i].getEnergy(this.adjMat,this.actMatDelay, this.actMatEnrg, this.S));
		}


		Arrays.sort(indivWithHops,IndivWithHops.hopsComparator);
		//Arrays.sort(indivWithFitness,null);
		//Arrays.sort(indivWithFitness);

		sortedIndivByHops = new Individual[sortSize];
		for (i = 0; i < sortSize; i++) {	//if sorted in increasing order then reverse this loop
			//sortedNodes[i]=asortedNodes[trk[i].getIndex()];
			sortedIndivByHops[i] = new Individual();
			//setIndividual(i, indivWithFitness[i].getIndivdual());
			sortedIndivByHops[i].setIndex(i);
			sortedIndivByHops[i] = indivWithHops[i].getIndivdual();
			//sortedIndivByHops[i].setHops(indivWithDelay[i].getHops());
			//System.out.println("Hops : " + sortedIndivByHops[i].getHops());	//for viewing individual fitness values in non-decreasing order
		}
		return sortedIndivByHops;
	}

	public Individual[] sortByTrnsCst(Individual[] indivsToSort){
		//sortedIndiv = new Individual[populationSize];
		//sortedIndiv = individuals;
		int sortSize = indivsToSort.length;
		Individual[] sortedIndivByTrnsCst;
		IndivWithTrnsCst indivWithTrnsCst[] = new IndivWithTrnsCst[sortSize];
		for (i = 0; i < sortSize; i++) {
			indivWithTrnsCst[i] = new IndivWithTrnsCst();
			indivWithTrnsCst[i].setIndex(indivsToSort[i].getIndex());
			indivWithTrnsCst[i].setIndivdual(indivsToSort[i]);
			//indivWithFitness[i].setFitness(this.individuals[i].getFitness());
			//indivWithEnergy[i].setDelay(this.individuals[i].getDelay(this.adjMat,this.actMatDelay, this.actMatEnrg, this.S));
			indivWithTrnsCst[i].setTrnsCst(indivsToSort[i].getTrnsCst(this.adjMat, this.trnsCstMat));
		}


		Arrays.sort(indivWithTrnsCst,IndivWithTrnsCst.trnsCstComparator);
		//Arrays.sort(indivWithFitness,null);
		//Arrays.sort(indivWithFitness);

		sortedIndivByTrnsCst = new Individual[sortSize];
		for (i = 0; i < sortSize; i++) {	//if sorted in increasing order then reverse this loop
			//sortedNodes[i]=asortedNodes[trk[i].getIndex()];
			sortedIndivByTrnsCst[i] = new Individual();
			//setIndividual(i, indivWithFitness[i].getIndivdual());
			sortedIndivByTrnsCst[i].setIndex(i);
			sortedIndivByTrnsCst[i] = indivWithTrnsCst[i].getIndivdual();
			sortedIndivByTrnsCst[i].setTrnsCst(indivWithTrnsCst[i].getTrnsCst());
			//System.out.println("Energy : " + sortedIndivByTrnsCst[i].getEnergy());	//for viewing individual fitness values in non-decreasing order
		}
		return sortedIndivByTrnsCst;
	}
	
	
	public Individual getFittest() {
		return (sortedIndiv[0]);
	}

	/*
	public Individual getSecondFittest() {
		return (sortedIndiv[1]);
	}

	public double getFitnessOfFittest() {
		//sortByFitness(populationSize);
		return (sortedIndiv[0].getFitness());
		//return ( sortedIndiv[0].getFitness(this.adjMat,this.actMatDelay, this.actMatEnrg, this.S));
	}
	 */

	public Individual getBySortedFitness(int rank) {
		return (sortedIndiv[rank]);
	}


	//returns chromosome length
	/*public int getIndividualLength(){
		return (sortedIndiv[0].getChromosomeLength());
	}
	 */

	//Non Dominated Sorting, returns a list of the non-dominated fronts
	//public ArrayList<ArrayList<Individual>> sortByDominance(Population popu) {
	//public ArrayList<ArrayList<Individual>> sortByDominance() {
	public void sortByDominance() {
		int i, j, popSize = this.populationSize, currLevel,noOfDominatedIndivs;
		Individual[] dominatedIndivs;//, tempIndiv;

		for (i = 0; i < popSize; i++) {
			for (j = 0; j < popSize; j++) {
				if(j == i)		// to skip comparing with itself
					continue;
				if(dominates(this.individuals[i],this.individuals[j]) == -1 ){		// if i dominates j
					//this.individuals[i].addToMyDominanceList(j);
					//System.out.println("Population.sortByDominance() 1 ");
					this.individuals[i].addToMyDominanceList(this.individuals[j]);
				}
				if (dominates(this.individuals[i],this.individuals[j]) == +1) {		// if j dominates i
					//System.out.println("Population.sortByDominance() 2 ");
					this.individuals[i].increaseDominatedCount();
				}
				/*else{	
					// if none dominates the other
					// do nothing
				}*/
			}
			if(this.individuals[i].getDominatesMe() == 0){		//if no solution dominates i then i is a member of  the first front
				this.individuals[i].setFitnessLevel(0);
				this.currFront.add(this.individuals[i]);
			}
		}
		//this.fronts.add(0, this.currFront);		// TODO check seems OK
		this.fronts.add(this.currFront);


		currLevel = 0;
		while (this.currFront.size() != 0){
			this.nextFront = new ArrayList<Individual>();	//null;
			for (i = 0; i < this.currFront.size(); i++) {
				noOfDominatedIndivs = this.currFront.get(i).getMyDominanceList().size();
				//tempIndiv = new Individual[noOfDominatedIndivs];	//to avoid : The local variable tempIndiv may not have been initialized
				//dominatedIndivs = this.currFront.get(i).getMyDominanceList().toArray(tempIndiv);		//Obtain the set Sp		nullpointer exception ?!!
				dominatedIndivs = this.currFront.get(i).getMyDominanceList().toArray(new Individual[noOfDominatedIndivs]);
				for (j = 0; j < noOfDominatedIndivs; j++) {
					dominatedIndivs[j].decreaseDominatedCount();
					if(dominatedIndivs[j].getDominatesMe() == 0){
						//Add to list H, i.e., nextfront
						dominatedIndivs[j].setFitnessLevel((currLevel +1));
						this.nextFront.add(dominatedIndivs[j]);
					}
				}
			}
			currLevel++;
			//this.fronts.add(currLevel, this.nextFront);
			this.fronts.add(this.nextFront);
			this.currFront = this.nextFront;
		}
		//return this.fronts;
	}

	/* Returns -1 if indiv1 dominates indiv2,
	 * 0 if none dominates the other and
	 * +1 if indiv1 is dominated by indiv2
	 */
	/*
	private byte dominates(Individual indiv1, Individual indiv2, boolean delaySensetive) {
		double S = 0.1, extendedParam;
		double delayAct1 = indiv1.getDelay(this.adjMat,this.actMatDelay, this.trnsCstMat, this.S);
		double delayAct2 = indiv2.getDelay(this.adjMat,this.actMatDelay, this.trnsCstMat, this.S);
		double energyAct1 = indiv1.getEnergy();//this.adjMat,this.actMatDelay, this.actMatEnrg, this.S);
		double energyAct2 = indiv2.getEnergy();//this.adjMat,this.actMatDelay, this.actMatEnrg, this.S);

		if(delaySensetive){
			//if delay of indiv1 is less than delay of indiv2
			//but energy of indiv1 is more than energy of indiv2
			//apply our extended algo and decide
			if((delayAct1 < delayAct2) && (energyAct1 > energyAct2)){
				extendedParam = (1 + S)*energyAct2;		//energyAct2 + energyAct2*S;
				if(energyAct1 > extendedParam)
					return (0);
				else	// if energyAct1 <= extendedParam then indiv1 dominates indiv2
					return (-1);
			}
			else if((delayAct1 > delayAct2) && (energyAct1 < energyAct2)){
				extendedParam = (1 + S)*energyAct1;		//energyAct1 + energyAct1*S;
				if(energyAct2 > extendedParam)
					return (0);
				else	// if energyAct2 <= extendedParam then indiv1 is dominated by indiv2
					return (+1);
			}
			else if((delayAct1 < delayAct2) && (energyAct1 <= energyAct2))
				return (-1);
			else if((delayAct1 > delayAct2) && (energyAct1 >= energyAct2))
				return (+1);
			else if ((delayAct1 == delayAct2) && (energyAct1 < energyAct2))
				return (-1);
			else if((delayAct1 == delayAct2) && (energyAct1 > energyAct2))
				return (+1);
			else	//when all are equal i.e., (delayAct1 == delayAct2) && (energyAct1 == energyAct2)
				return (0);
			//// can also be done by switch case
		}
		else{	//for energy aware
			//if delay of indiv1 is less than delay of indiv2
			//but energy of indiv1 is more than energy of indiv2
			//apply our extended algo and decide
			if((energyAct1 > energyAct2) && (delayAct1 < delayAct2)){
				extendedParam = (1 + S)*delayAct1;
				if(delayAct2 > extendedParam)
					return (0);
				else	// if delayAct2 <= extendedParam then indiv1 is dominated by indiv2
					return (+1);
			}
			else if((energyAct1 < energyAct2) && (delayAct1 > delayAct2)){
				extendedParam = (1 + S)*delayAct2;
				if(delayAct1 > extendedParam)
					return (0);
				else	// if delayAct1 <= extendedParam then indiv1 dominates indiv2
					return (-1);
			}
			else if((energyAct1 < energyAct2) && (delayAct1 <= delayAct2))
				return (-1);
			else if((energyAct1 > energyAct2) && (delayAct1 >= delayAct2))
				return (+1);
			else if ((energyAct1 == energyAct2) && (delayAct1 < delayAct2))
				return (-1);
			else if((energyAct1 == energyAct2) && (delayAct1 > delayAct2))
				return (+1);
			else	//when all are equal i.e., (delayAct1 == delayAct2) && (energyAct1 == energyAct2)
				return (0);
			//// can also be done by switch case
		}
	}
	/*/	
	private byte dominates(Individual indiv1, Individual indiv2) {
		double trnsCst1 = indiv1.getTrnsCst(this.adjMat,this.trnsCstMat);
		double trnsCst2 = indiv2.getTrnsCst(this.adjMat,this.trnsCstMat);
		int hopL1 = indiv1.getHops();
		int hopL2 = indiv2.getHops();
		
		if((hopL1 < hopL2) && (trnsCst1 > trnsCst2))		
			return (0);
		else if((hopL1 < hopL2) && (trnsCst1 <= trnsCst2))
			return (-1);
		else if((hopL1 > hopL2) && (trnsCst1 >= trnsCst2))
			return (+1);
		else if ((hopL1 == hopL2) && (trnsCst1 < trnsCst2))
			return (-1);
		else if((hopL1 == hopL2) && (trnsCst1 > trnsCst2))
			return (+1);
		else 
			return(0);	
	}
	
	
	/*
	public void assignCrowdingDist(ArrayList<Individual> frontToAssignDist){
		int i, noOfElm = frontToAssignDist.size();
		Individual[] sortedByDelay, sortedByEnergy;
		Individual[] indivsInFront = frontToAssignDist.toArray(new Individual[noOfElm]);
		for(i = 1 ; i< (noOfElm -1); i++)
			indivsInFront[i].setCrowdingDist(0);

		// For objective 1
		sortedByDelay = this.sortByDelay(indivsInFront);
		sortedByDelay[0].setCrowdingDist(999999999.9);
		sortedByDelay[noOfElm].setCrowdingDist(999999999.9);
		for(i = 1 ; i< (noOfElm -1); i++)
			sortedByDelay[i].setCrowdingDist((sortedByDelay[i].getCrowdingDist() + (sortedByDelay[(i+1)].getCrowdingDist() - sortedByDelay[(i-1)].getCrowdingDist())));

		// For objective 2
		sortedByEnergy = this.sortByEnergy(indivsInFront);
		sortedByEnergy[0].setCrowdingDist(999999999.9);
		sortedByEnergy[noOfElm].setCrowdingDist(999999999.9);
		for(i = 1 ; i< (noOfElm -1); i++)
			sortedByEnergy[i].setCrowdingDist((sortedByEnergy[i].getCrowdingDist() + (sortedByEnergy[(i+1)].getCrowdingDist() - sortedByEnergy[(i-1)].getCrowdingDist())));

	}
	 */
 
	// Assign crowding distance to the elements in each front
	public ArrayList<Individual> assignCrowdingDist(ArrayList<Individual> frontToAssignDist){
		//System.out.println("size " + frontToAssignDist.size());
		int i, noOfElm = frontToAssignDist.size();
		//System.out.println("Population.assignCrowdingDist() noOfElm " + noOfElm);
		Individual[] sortedByHops, sortedByTrnsCst;
		Individual[] indivsInFront = frontToAssignDist.toArray(new Individual[noOfElm]);
		ArrayList<Individual> assignedFront = new ArrayList<Individual>();
		//for(i = 1 ; i< (noOfElm -1); i++)
		for(i = 0 ; i< noOfElm; i++)
			indivsInFront[i].setCrowdingDist(0);

		// For objective 1
		//System.out.println("Population.assignCrowdingDist() popu size 1 " + this.populationSize);
		sortedByHops = this.sortByHops(indivsInFront);
		sortedByHops[0].setCrowdingDist(Double.MAX_VALUE);
		sortedByHops[(noOfElm - 1)].setCrowdingDist(Double.MAX_VALUE);
		for(i = 1 ; i< (noOfElm -1); i++)
			sortedByHops[i].setCrowdingDist((sortedByHops[i].getCrowdingDist() + (sortedByHops[(i+1)].getHops() - sortedByHops[(i-1)].getHops())));

		// For objective 2
		//sortedByEnergy = this.sortByEnergy(indivsInFront);
		sortedByTrnsCst = this.sortByTrnsCst(sortedByHops);
		sortedByTrnsCst[0].setCrowdingDist(999999999.9);
		sortedByTrnsCst[(noOfElm - 1)].setCrowdingDist(999999999.9);
		for(i = 1 ; i< (noOfElm -1); i++)
			sortedByTrnsCst[i].setCrowdingDist((sortedByTrnsCst[i].getCrowdingDist() + (sortedByTrnsCst[(i+1)].getTrnsCst() - sortedByTrnsCst[(i-1)].getTrnsCst())));
		//System.out.println("Population.assignCrowdingDist() popu size 2 " + this.populationSize);
		for(i = 0; i< noOfElm; i++)
			assignedFront.add(sortedByTrnsCst[i]);
		//System.out.println("Population.assignCrowdingDist() popu size 3 " + this.populationSize);
		return (assignedFront);			
	}

	//Crowded Comparison operator sorting (>>)	in descending order of rank
	public void sortByCrowdedComparisonOperator() {
		//sortedIndiv = new Individual[populationSize];
		//sortedIndiv = individuals;
		IndivForCrowdedComparison indivForCrowdedComparison[] = new IndivForCrowdedComparison[this.populationSize];
		//System.out.println("Population.sortByCrowdedComparisonOperator() popu size " + this.populationSize);
		for (i = 0; i < this.populationSize; i++) {
			indivForCrowdedComparison[i] = new IndivForCrowdedComparison();
			//System.out.println("Population.sortByCrowdedComparisonOperator() val of i " + i );
			indivForCrowdedComparison[i].setIndex(this.individuals[i].getIndex());
			indivForCrowdedComparison[i].setIndivdual(this.individuals[i]);
			//indivWithFitness[i].setFitness(this.individuals[i].getFitness());
			indivForCrowdedComparison[i].setFitnessLevel(this.individuals[i].getFitnessLevel());
			indivForCrowdedComparison[i].setCrowdingDist(this.individuals[i].getCrowdingDist());	//this.adjMat,this.actMatDelay, this.actMatEnrg, this.S));
		}


		Arrays.sort(indivForCrowdedComparison,IndivForCrowdedComparison.crowdedComparator);
		//Arrays.sort(indivWithFitness,null);
		//Arrays.sort(indivWithFitness);

		this.sortedIndiv = new Individual[this.populationSize];
		for (i = 0; i < this.populationSize; i++) {	//if sorted in increasing order then reverse this loop
			this.sortedIndiv[i] = new Individual();
			//setIndividual(i, indivWithFitness[i].getIndivdual());
			this.sortedIndiv[i].setIndex(i);
			this.sortedIndiv[i] = indivForCrowdedComparison[i].getIndivdual();
			this.sortedIndiv[i].setCrowdingDist(indivForCrowdedComparison[i].getCrowdingDist());
			this.sortedIndiv[i].setFitnessLevel(indivForCrowdedComparison[i].getFitnessLevel());
			////System.out.println(sortedIndiv[i].getFitness());	//for viewing individual fitness values in non-decreasing order
		}		
	}
	
	//http://www.ltcconline.net/greenl/courses/201/descstat/mean.htm
	//http://www.mathsisfun.com/data/mean-frequency-table.html
	//http://www.ltcconline.net/greenl/courses/201/descstat/meansdgrouped.htm
	
	//sd is sqrt of sum of (values-mean) squared divided by n - 1 
	void calMeanSDOfFrstFrnt(ArrayList<Individual> frstFrnt){
		int i,noOfElms = frstFrnt.size();
		double totHops = 0,totTrnsCst = 0, meanHops, meanTrnsCst, var, totVarHops = 0, totVarTrnsCst = 0, sdHops, sdTrnsCst;

		// calculate the mean
		for(i = 0; i<noOfElms; i++){
			totHops += frstFrnt.get(i).getHops();
			totTrnsCst += frstFrnt.get(i).getTrnsCst();
		}
		meanHops = totHops/noOfElms;
		meanTrnsCst = totTrnsCst/noOfElms;
		System.out.println("Mean Hops : " + meanHops);
		System.out.println("Mean TrnsCst : " + meanTrnsCst);
		// calculate the sum of squares 
		for(i = 0; i<noOfElms; i++){
			var = frstFrnt.get(i).getHops() - meanHops;
			totVarHops += (var*var);
			var = frstFrnt.get(i).getTrnsCst() - meanTrnsCst;
			totVarTrnsCst += (var*var);
		}
		// Change ( noOfElm - 1 ) to noOfElm if you have complete data instead of a sample.
		sdHops = Math.sqrt(totVarHops/noOfElms);
		sdTrnsCst = Math.sqrt(totVarTrnsCst/noOfElms);
		System.out.println("SD Hops : " + sdHops);
		System.out.println("SD TrnsCst : " + sdTrnsCst);
	}
}


/*
http://www.cleveralgorithms.com/nature-inspired/evolution/nsga.html

def dominates(p1, p2)
  p1[:objectives].each_index do |i|
    return false if p1[:objectives][i] > p2[:objectives][i]
  end
  return true
end

def fast_nondominated_sort(pop)
fronts = Array.new(1){[]}
pop.each do |p1|
  p1[:dom_count], p1[:dom_set] = 0, []
  pop.each do |p2|
    if dominates(p1, p2)
      p1[:dom_set] << p2
    elsif dominates(p2, p1)
      p1[:dom_count] += 1
    end
  end
  if p1[:dom_count] == 0
    p1[:rank] = 0
    fronts.first << p1
  end
end
curr = 0
begin
  next_front = []
  fronts[curr].each do |p1|
    p1[:dom_set].each do |p2|
      p2[:dom_count] -= 1
      if p2[:dom_count] == 0
        p2[:rank] = (curr+1)
        next_front << p2
      end
    end
  end
  curr += 1
  fronts << next_front if !next_front.empty?
end while curr < fronts.size
return fronts
end

def calculate_crowding_distance(pop)
pop.each {|p| p[:dist] = 0.0}
num_obs = pop.first[:objectives].size
num_obs.times do |i|
  min = pop.min{|x,y| x[:objectives][i]<=>y[:objectives][i]}
  max = pop.max{|x,y| x[:objectives][i]<=>y[:objectives][i]}
  rge = max[:objectives][i] - min[:objectives][i]
  pop.first[:dist], pop.last[:dist] = 1.0/0.0, 1.0/0.0
  next if rge == 0.0
  (1...(pop.size-1)).each do |j|
    pop[j][:dist]+=(pop[j+1][:objectives][i]-pop[j-1][:objectives][i])/rge
  end
end
end

def crowded_comparison_operator(x,y)
return y[:dist]<=>x[:dist] if x[:rank] == y[:rank]
return x[:rank]<=>y[:rank]
end

def better(x,y)
if !x[:dist].nil? and x[:rank] == y[:rank]
  return (x[:dist]>y[:dist]) ? x : y
end
return (x[:rank]<y[:rank]) ? x : y
end
 */