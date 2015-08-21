import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Random;
import java.util.Scanner;

public class StartHere {
	public static void main(String[] args) {


		/*
		int generation_n = 512;	// Number of generations
		int popuSize = 512;		// Population size
		double xover_rate = 0.8;	// Crossover rate
		double mutate_rate = 0.01;	// Mutation rate
		//int bit_n = 8;			// Bit number for each input variable
		 */

		int i,j;
		String routeStr, route;
		int noOfNodes = 32;
		int popuSize = 512;	//(int) Math.pow(2, 20);	//2^31;	// Population size
		//int generationCount = 128;	// Number of generations

		/* GA parameters */
		//private static final double crossoverRate = 0.8;
		final double crossoverRate = 0.8;
		final double mutationRate = 0.08;
		final int tournamentSize = 5;
		final boolean elitism = true;	//save best two across generations

		int adjMat[][] = new int [noOfNodes][noOfNodes];
		int costMatDelay[][] = new int [noOfNodes][noOfNodes];
		int costMatEnrg[][] = new int [noOfNodes][noOfNodes];
		int actMatDelay[][] = new int [noOfNodes][noOfNodes];
		int actMatEnrg[][] = new int [noOfNodes][noOfNodes];
		double S[][] = new double [noOfNodes][noOfNodes];	//Proposed tuning parameter  can be set to local values for each node pair

		// first check to see if the program was run with the command line argument
		/*if(args.length < 1) {
	        System.out.println("Error, usage: java ClassName inputfile");
		System.exit(1);
	    }*/

		try {
			//Read the network information and store in adjacency matrix
			String netInfFile=new String("Network_Data.txt");
			Scanner netInfData = new Scanner(new FileInputStream(netInfFile));


			String costEnergyFile=new String("Energy_Cost_Data.txt");
			Scanner costEnergyData = new Scanner(new FileInputStream(costEnergyFile));

			String costDelayFile=new String("Delay_Cost_Data.txt");
			Scanner costDelayData = new Scanner(new FileInputStream(costDelayFile));

			/*String actEnergyFile=new String("Energy_Actual_Data.txt");
		Scanner actEnergyData = new Scanner(new FileInputStream(actEnergyFile));

		String actDelayFile=new String("Delay_Actual_Data.txt");
		Scanner actDelayData = new Scanner(new FileInputStream(actDelayFile));
			 */

			String tuningInfFile=new String("Tuning_Parameter.txt");
			Scanner tuningData = new Scanner(new FileInputStream(tuningInfFile));


			//netInfData.nextLine();	//Ignores the first line
			for (i = 0; i < noOfNodes; i++){
				for (j = 0; j < noOfNodes; j++){
					adjMat[i][j] = netInfData.nextInt();
					//System.out.print(adjMat[i][j] + " ");
				}
				//System.out.println();
				netInfData.nextLine();
			}

			//costEnergyData.nextLine();	//Ignores the first line
			for (i = 0; i < noOfNodes; i++){
				for (j = 0; j < noOfNodes; j++){
					costMatEnrg[i][j] = costEnergyData.nextInt();
				}
				costEnergyData.nextLine();
				//System.out.println(N_i + ", " + N_j + ", " + E_i + "," + D_i);
			}

			//costDelayData.nextLine();	//Ignores the first line
			for (i = 0; i < noOfNodes; i++){
				for (j = 0; j < noOfNodes; j++){
					costMatDelay[i][j] = costDelayData.nextInt();
					//System.out.print(costMatDelay[i][j] + " ");
				}
				//System.out.println();
				costDelayData.nextLine();
				//System.out.println(N_i + ", " + N_j + ", " + E_i + "," + D_i);
			}

			/*
		actEnergyData.nextLine();	//Ignores the first line
		for (i = 0; i < popuSize; i++){
			for (j = 0; j < popuSize; j++){
				actMatEnrg[i][j] = actEnergyData.nextInt();
			}
			actEnergyData.nextLine();
			//System.out.println(N_i + ", " + N_j + ", " + E_i + "," + D_i);
		}

		actDelayData.nextLine();	//Ignores the first line
		for (i = 0; i < popuSize; i++){
			for (j = 0; j < popuSize; j++){
				actMatDelay[i][j] = actDelayData.nextInt();
			}
			actDelayData.nextLine();
			//System.out.println(N_i + ", " + N_j + ", " + E_i + "," + D_i);
		}
			 */

			//tuningData.nextLine();	//Ignores the first line
			for (i = 0; i < noOfNodes; i++){
				for (j = 0; j < noOfNodes; j++){
					S[i][j] = tuningData.nextDouble();
				}
				tuningData.nextLine();
				//System.out.println(N_i + ", " + N_j + ", " + E_i + "," + D_i);
			}

			netInfData.close();
			costEnergyData.close();
			costDelayData.close();
			/*
		actEnergyData.close();
		actDelayData.close();
			 */
			tuningData.close();
		} catch (FileNotFoundException e) {
			System.out.println("Files containing Network Information are not found. "); 
			e.printStackTrace();
		}



		// Create an initial population
		Population myPop = new Population(popuSize, true, adjMat,costMatDelay, costMatEnrg, S);
		myPop.setNetworkPropInPop(adjMat, costMatDelay, costMatEnrg, S);
		/*
		myPop.setAdjMat(adjMat);
		myPop.setCostMatDelay(costMatDelay);
		myPop.setCostMatEnrg(costMatEnrg);
		myPop.setS(S);
		 */

		// Set a candidate solution
		//FitnessCalc.setSolution("1111000000000000000000000000000000000000000000000000000000001111");

		// Evolve our population until we reach an optimum solution

		//while (myPop.getFittest().getFitness() < FitnessCalc.getMaxFitness()) {
		for( i = 0; i < 10; i++){
			//generationCount++;
			//System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
			//System.out.println("Gen No : " + i);
			myPop = evolvePopulation(myPop);
			//System.out.println("Route : " + myPop.getFittest().getChromosome());
		}
		System.out.println("Generation no : " + i);		//initial popu -> 0th, next is 1st gen.
		myPop.sortByFitness();
		System.out.println("Route chromosome: " + myPop.getFittest().toString());
		System.out.println("Fitness : " + myPop.getFittest().getFitness());

		route = myPop.getFittest().toString();
		routeStr = "1";
		for( i = 1; i < noOfNodes; i++){
			if(route.substring(i, i+1).equals("1")){
				routeStr += "-"+ (i+1);
			}
		}
		System.out.println("Route : " + routeStr);

	}




	//Algorithm
	public static Population evolvePopulation(Population pop) {
		int i, elitismOffset;	//no of elite chromosomes

		/* GA parameters */
		final double crossoverRate = 0.8;
		/*final*/ double mutationRate = 0.08;
		final boolean elitism = true;	//save best two across generations

		Population newPopulation = new Population(pop.getPopSize(), false);
		newPopulation.setNetworkPropInPop(pop.getAdjMat(),pop.getCostMatDelay(), pop.getCostMatEnrg(), pop.getS());

		pop.sortByFitness();		//First use of sort
		// Crossover population
		if (elitism) {
			elitismOffset = 128;
			//System.out.println(" hulla");
		} else {
			elitismOffset = 0;
		}

		// Keep our best individual
		if (elitism) {
			//pop.sortByFitness();		//First use of sort
			//pop.sortByFitness(pop.getPopSize());		//First use of sort
			//newPopulation.setIndividual(0, pop.getFittest());
			//newPopulation.setIndividual(1, pop.getSecondFittest());
			for( i = 0; i < elitismOffset; i++){	//No of elite chromosomes for next gen
				//System.out.println("In StartHere.evolve.ifElitism :i : "+ i + " " + pop.getBySortedFitness(i).getFitness());
				newPopulation.setIndividual(i, pop.getBySortedFitness(i));
				/*System.out.println(pop.getBySortedFitness(i).fitness + " #1");
				System.out.println(" hulla");*/
				//System.out.println("Fitness : "+ pop.getBySortedFitness(i).getFitness());
				//System.out.println( "String : " + pop.getBySortedFitness(i).getChromosome().toString());
			}			
		}



		// Loop over the population size and create new individuals with
		// crossover, select rest (except elites) and crossover in them
		for ( i = elitismOffset; i < pop.getPopSize(); i+=2) {
			//for ( i = 0; i < pop.getPopSize(); i+=2) {
			//			Individual indiv1 = tournamentSelection(pop);
			//			Individual indiv2 = tournamentSelection(pop);
			Individual indiv1 = pop.getIndividual(i - elitismOffset);
			Individual indiv2 =  pop.getIndividual(i+1 - elitismOffset);
			if (Math.random() <= crossoverRate) {
				//System.out.println("Before Cross 1: ");
				Individual newIndiv1 = crossover(indiv1, indiv2, true);			//false for first child i.e.,  [part of parent1][part of parent2]
				Individual newIndiv2 = crossover(indiv1, indiv2, false);		//true for first child i.e.,  [part of parent2][part of parent1]
				newPopulation.setIndividual(i, newIndiv1);
				newPopulation.setIndividual(i+1, newIndiv2);
				//System.out.println("After Cross 1: ");
			}else{
				newPopulation.setIndividual(i, indiv1);
				newPopulation.setIndividual(i+1, indiv2);
			}
			//System.out.println("After Cross 2: ");
		}

		// MUTATION (elites are not subject to this.)	mutate in the new generation
		mutationRate = 0.1;
		for ( i = elitismOffset; i < newPopulation.getPopSize(); i++) {
			//for ( i = 0; i < newPopulation.getPopSize(); i++) {
			if (Math.random() <= mutationRate)
				newPopulation.setIndividual(i, mutate(newPopulation.getIndividual(i)));
		}

		return newPopulation;
	}


	// Crossover individuals
	private static Individual crossover(Individual indiv1, Individual indiv2,boolean parent_1) {
		int i, j, firstCommonPnt, size = indiv1.getLength();
		int[] newGenes = new int [size];
		boolean crossoverDone = false, skipOnce = true;
		Individual newIndiv = new Individual();
		//System.out.println("In Cross : " + indiv1.getFitness() + " " + indiv2.getFitness());
		// Loop through genes
		i = 1; 		//to avoid source point exchange
		while((!crossoverDone) &&  (i < size)) {		////to avoid destination point exchange
			// Crossover		check if correct, adou ki crossover / genetic material exchange hochhe	Update : Yup hochhe!!!
			//System.out.println("In Cross while : "+i );
			if(parent_1)
				newGenes[i] = indiv1.getGene(i);
			else
				newGenes[i] = indiv2.getGene(i);

			if(indiv1.getGene(i)==indiv2.getGene(i)){
				if(skipOnce){
					skipOnce = false;
					i++;
					continue;
				}					
				firstCommonPnt = i;
				for( j = firstCommonPnt; j < size ; j++){
					if(parent_1)
						newGenes[j] = indiv2.getGene(j);
					else
						newGenes[j] = indiv1.getGene(j);
				}
				crossoverDone = true;
			}
			i++;
		}
		/*
		for ( i = 1; i < (size-1); i++) {		//to avoid source and destination point exchange
			// Crossover		check if correct, adou ki crossover / genetic material exchange hochhe	Update : Yup hochhe!!!
			if(parent_1)
				newGene[i] = indiv1.getGene(i);
			else
				newGene[i] = indiv2.getGene(i);

			if(indiv1.getGene(i)==indiv2.getGene(i)){
				firstCommonPnt = i;
				for( j = firstCommonPnt; j < size ; j++){
					if(parent_1)
						newGene[j] = indiv2.getGene(j);
					else
						newGene[j] = indiv1.getGene(j);
				}
				break;
			}
		}
		 */
		newIndiv.setChromosome(newGenes);
		return newIndiv;
	}

	/*// Mutate an individual
		private static void mutate(Individual indiv) {
			// Loop through genes
			for (int i = 1; i < indiv.size()-1; i++) {		//to avoid source and destination mutation
				if (Math.random() <= mutationRate) {
					// Create random gene
					byte gene = (byte) Math.round(Math.random());
					indiv.setGene(i, gene);
				}
			}
		}
	 */

	// Mutate an individual
	private static Individual mutate(Individual indivToMutate) {
		// Select any gene at random and mutate/flip it
		int originalGene, mutatedGene, randInt;
		Random rand = new Random();
		randInt = rand.nextInt(indivToMutate.getLength() -1) + 1;		// returns a random number between 0 and indiv.length() (31)	+1 to avoid the first node i.e., 1-31
		originalGene = indivToMutate.getGene(randInt);
		if(originalGene == 0)
			mutatedGene = 1;
		else
			mutatedGene = 0;
		indivToMutate.setGene(randInt, mutatedGene);
		return (indivToMutate);
	}

	// Select individuals for crossover
	private static Individual tournamentSelection(Population pop) {
		int i, randomId, tournamentSize = 5;
		Individual fittest;
		// Create a tournament population
		Population tournament = new Population(tournamentSize, false);
		tournament.setNetworkPropInPop(pop.getAdjMat(),pop.getCostMatDelay(), pop.getCostMatEnrg(), pop.getS());
		// For each place in the tournament get a random individual
		for (i = 0; i < tournamentSize; i++) {
			randomId = (int) (Math.random()*pop.getPopSize());
			tournament.setIndividual(i, pop.getIndividual(randomId));
		}
		//Sort by fitness
		tournament.sortByFitness();
		//tournament.sortByFitness(tournamentSize);
		// Get the fittest
		fittest = tournament.getFittest();
		return fittest;
	}
}