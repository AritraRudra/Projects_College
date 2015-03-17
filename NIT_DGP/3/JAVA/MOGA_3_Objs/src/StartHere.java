import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/*
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
*/


public class StartHere {
	static final int noOfNodes = 60;
	static final int sinkNode = 59;	//use index of the target/destination node
	static final int popuSize = 256;	//(int) Math.pow(2, 20);	//2^31;	// Population size
	static final int generationCount = 10;	// Number of generations
	//static final boolean delaySensitive = true;// Set if delay sensitive or energy aware network i.e., toggle

	/* GA parameters */
	static final double crossoverRate = 0.75;
	static final double mutationRate = 0.15;
	static final  boolean elitism = true;	//save best two across generations

	public static void main(String[] args) {
		int i,j, a = 0, b, size, frontNo, nextSize;
		int[] route;
		double trnsCst = 0, dist = 0;
		String dataFilesPath = "D://College//Project//3//UD//Data//Data_Set_2//";
		
		/*
		int noOfNodes = 320;
		//int sinkNode = 319;	//use index of the target/destination node
		int popuSize = 512;	//(int) Math.pow(2, 20);	//2^31;	// Population size
		//int generationCount = 128;	// Number of generations
		boolean delaySensitive;
		 */
		Population p, q, r;
		ArrayList<Individual> tempArrList = new ArrayList<Individual>();

		/* GA parameters 
		//private static final double crossoverRate = 0.8;
		final double crossoverRate = 0.8;
		final double mutationRate = 0.08;
		final int tournamentSize = 5;
		final boolean elitism = true;	//save best two across generations
		*/

		int adjMat[][] = new int [noOfNodes][noOfNodes];
		double trnsCstMat[][] = new double [noOfNodes][noOfNodes];
		double distMat[][] = new double [noOfNodes][noOfNodes];
		
		/* For graph */
		String filePath = "D://Matlab//Project//3//Data_Graphs//";
		double uniqueHops, uniqueTrnsCst;
		double[] tempFrontHops, tempFrontTrnsCst, frontTrnsCst, frontHops;

		// Read the required data from text files
		try {
			//Read the network information and store in adjacency matrix
			String netInfFile=new String(dataFilesPath + "Network_Data.txt");
			Scanner netInfData = new Scanner(new FileInputStream(netInfFile));

			String trnsCstFile=new String(dataFilesPath + "Transmission_Cost_Data.txt");
			Scanner costTrnsData = new Scanner(new FileInputStream(trnsCstFile));

			/*String distFile=new String(dataFilesPath + "Distances_Between_Nodes.txt");
			Scanner distData = new Scanner(new FileInputStream(distFile));*/

			//netInfData.nextLine();	//Ignores the first line
			for (i = 0; i < noOfNodes; i++){
				for (j = 0; j < noOfNodes; j++){
					adjMat[i][j] = netInfData.nextInt();
					//System.out.print(adjMat[i][j] + " ");
				}
				//System.out.println();
				netInfData.nextLine();
			}
			
			for (i = 0; i < noOfNodes; i++){
				for (j = 0; j < noOfNodes; j++){
					trnsCstMat[i][j] = costTrnsData.nextDouble();
					//System.out.print(trnsCstMat[i][j] + " ");
				}
				//System.out.println();
				costTrnsData.nextLine();
				//System.out.println(N_i + ", " + N_j + ", " + E_i + "," + D_i);
			}

			/*for (i = 0; i < noOfNodes; i++){
				for (j = 0; j < noOfNodes; j++){
					distMat[i][j] = distData.nextDouble();
				}
				distData.nextLine();
				//System.out.println(N_i + ", " + N_j + ", " + E_i + "," + D_i);
			}*/

			netInfData.close();
			costTrnsData.close();
			//distData.close();
		}catch (FileNotFoundException expFile) {
			System.out.println("Files containing Network Information are not found. "); 
			expFile.printStackTrace();
		}catch(InputMismatchException expIn){
			System.out.println("Files containing Network Information have different format/type. "); 
			expIn.printStackTrace();
		}

		// Create an initial population of size N
		p = new Population(popuSize, true, adjMat, trnsCstMat, noOfNodes, sinkNode);
		/*
		BufferedWriter writer = null;
		try {
			//create a temporary file
			File fileName;
			fileName = new File(filePath + "Scatter_Data.txt");			
			writer = new BufferedWriter(new FileWriter(fileName));
			
			for( i = 0; i < popuSize; i++)
				writer.write(Double.toString(p.getIndividual(i).getTrnsCst()) + " ");			
			writer.newLine();
			
			for( i = 0; i < popuSize; i++)
				writer.write(Integer.toString(p.getIndividual(i).getHops()) + " ");			
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {}
		}
		/*
		System.out.println("Going to Sleep");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		*/

		//Create a child population on same size N
		//q = new Population(popuSize);
		
		//System.out.println("StartHere.main() 1 popu size " + p.getPopSize());
		p.sortByDominance();
		//System.out.println("StartHere.main() 2 popu size " + p.getPopSize());
		size = p.fronts.size() - 1;//TODO why this -1 can not be removed, it should be removed
		System.out.println("No of Fronts at initial stage " + size);
		while(size > 0){
			//System.out.println("Front no. at initial stage " + size);
			size--;
			//System.out.println("Size of front " + size + " is " +p.getFrontByIndex(size).size() );
			//System.out.println("StartHere.main() 3 popu size " + p.getPopSize());
			tempArrList = p.getFrontByIndex(size);
			//System.out.println("StartHere.main() 4 popu size " + p.getPopSize());
			p.assignCrowdingDist(tempArrList);
			//System.out.println("StartHere.main() 5 popu size " + p.getPopSize());
			p.setFront(size, tempArrList);
			//System.out.println("StartHere.main() 6 popu size " + p.getPopSize());
			////p.setFront(p.assignCrowdingDist(p.getFrontByIndex(size)));//getFrontByIndex(size)));
		}
		//System.out.println("StartHere.main() 7 popu size " + p.getPopSize());
		p.sortByCrowdedComparisonOperator();
		//System.out.println("StartHere.main() 8 popu size " + p.getPopSize());
		q = evolvePopulation(p);
		//System.out.println("StartHere.main() 9 popu size " + q.getPopSize());

		/*
		//Combine them into r
		r = combinePopu(p, q);

		r.sortByDominance();
		 */

		//The MAIN LOOP

		i = 0;
		while(i < generationCount){	//no of desired generations.
			r = combinePopu(p, q);			
			r.sortByDominance();	//Creates the fronts in r, true for delaySensetive, false for energy aware
			size = q.getPopSize();
			p = new Population(size, true);
			p.setNetworkPropInPop(adjMat,trnsCstMat);
			//System.out.println("StartHere.main() 11 popu size " + p.getPopSize());
			frontNo = 0;
			while(p.getPopSize() != size){		//while population is not filled	!= or <=
				//System.out.println("Front no. " + frontNo);
				tempArrList = r.getFrontByIndex(frontNo);		//gets front from r
				if(tempArrList.size() == 0)
					break;
				r.assignCrowdingDist(tempArrList);		//assigns crowding dist
				nextSize = (p.getPopSize() + tempArrList.size());
				//System.out.println("StartHere.main() 12 popu size " + p.getPopSize());
				if( nextSize >= size){
					nextSize = size - p.getPopSize();
					p.putIndivIntoLastFront(tempArrList, nextSize);	//The last front is included in the population based on the individuals with least crowding distance NOPE
					//p.putIndivIntoLastFront(frontNo, tempArrList, nextSize);
					/*for(j = 0; j<tempArrList.size(); j++){
						p.putIndivIntoFrontOneByOne(tempArrList.get(j));
					}*/
				}else
					p.addFront(tempArrList);
				frontNo++;
				//System.out.println("StartHere.main() 13 popu size " + p.getPopSize());
			}
			//p.getIndividual(0).printChromosome();
			p.sortByCrowdedComparisonOperator();
			q = evolvePopulation(p);
			
			dist = (((float)i)/generationCount)*100;
			if( ( ( ((int)dist) % 1) == 0) && (a != (int)dist)){
				a = (int)dist;
				System.out.println(a + "% Done ... ");
			}
			
			i++;
		}
		System.out.println("Complete");

		//System.out.println("StartHere.main() 21 popu size " + q.getPopSize());
		q.sortByDominance();
		size = q.fronts.size() -1;
		System.out.println("No of Fronts at final stage " + size);
		while(size > 0){
			size--;
			q.setFront(size, (q.assignCrowdingDist(q.getFrontByIndex(size))));
		}
		q.sortByCrowdedComparisonOperator();
		//System.out.println("StartHere.main() 22 popu size " + q.getIndividuals().length);
		//System.out.println("StartHere.main() 23 popu size " + q.getPopSize());
		
		/*System.out.println("Elements of first front ");
		for( i = 0; i < q.getFrontByIndex(0).size() ; i++)
			q.getFrontByIndex(0).get(i).printChromosome();
		 */

		System.out.println("Using MOO : ");
		System.out.println("No of elements in the final first front : " + q.getFrontByIndex(0).size());
		System.out.println("Generation no : " + generationCount);		//initial popu -> 0th, next is 1st gen.
		System.out.println("Poopulation size : " + q.getPopSize());
		System.out.println("Route chromosome : " );
		q.getFittest().printChromosome();
		
		/*for graph */
		size = q.getFrontByIndex(0).size();
		uniqueHops  = q.getFittest().getHops();
		uniqueTrnsCst = q.getFittest().getTrnsCst();
		tempFrontHops = new double[size];
		tempFrontTrnsCst = new double[size];
		a = 0;
		for( i = 0; i < size ; i++){
			//q.getFrontByIndex(0).get(i).printChromosome();
			if((uniqueHops != q.getFrontByIndex(0).get(i).getHops()) && (uniqueTrnsCst != q.getFrontByIndex(0).get(i).getTrnsCst())){
			uniqueHops = q.getFrontByIndex(0).get(i).getHops();
			tempFrontHops[a] = uniqueHops;
			uniqueTrnsCst = q.getFrontByIndex(0).get(i).getTrnsCst();
			tempFrontTrnsCst[a] = uniqueTrnsCst;
			q.getFrontByIndex(0).get(i).printChromosome();
			//System.out.print("Level : " + q.getFrontByIndex(0).get(i).getFitnessLevel());
			a++;
			}
		}
		//System.out.print("Level : " + q.getFrontByIndex(1).get(1).getFitnessLevel());
		//System.out.println();
		
		i = 0;
		//frontHops = frontTrnsCst = new double[a];
		frontHops = new double[a];
		frontTrnsCst = new double[a];
		while(i < a){
			frontHops[i] = tempFrontHops[i];
			frontTrnsCst[i] = tempFrontTrnsCst[i];
			i++;
		}

		route = q.getFittest().getChromosome();
		for( i = 0; i < (route.length - 1) ; i++){
			a = route[i];
			b = route[i+1];
			//routeCost = s*trnsCstMat[a][b] + (1.0 - s)*costMatEnrg[a][b];
			trnsCst += trnsCstMat[a][b];
			dist += distMat[a][b];
		}

		System.out.print("Transmission Cost : " );
		System.out.println(trnsCst);
		System.out.println("No of Hops : " +  (route.length-1));
		System.out.print("Total Length of this Route : " );
		System.out.println(dist);
		
		/*
		for(i = 0; i< popuSize; i++){
			System.out.println(q.getIndividual(i).getDelay());
			/*if(q.getIndividual(i).getDelay() == 70.001)
				System.out.println(q.getIndividual(i).getEnergy());
		}		
		/*for(i = 0; i< popuSize; i++)
			System.out.println(q.getIndividual(i).getEnergy());
		*/
		
		/* write to file */
		/*BufferedWriter writer = null;
		try {
			//create a temporary file
			File fileName;			
			fileName = new File(filePath + "Front.txt");
			//fileName = new File(filePath + "Temp.txt");

			// This will output the full path where the file will be written to...
			//System.out.println(logFile.getCanonicalPath());

			writer = new BufferedWriter(new FileWriter(fileName));
			for( i = 0; i < frontHops.length; i++)
				writer.write(Double.toString(frontHops[i]) + " ");
			
			writer.newLine();
			
			for( i = 0; i < frontTrnsCst.length; i++)
				writer.write(Double.toString(frontTrnsCst[i]) + " ");
			
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {}
		}
		// */
		
		// for mean and standard deviation
		q.calMeanSDOfFrstFrnt(q.getFrontByIndex(0));
	}


	private static Population combinePopu(Population a, Population b) {
		Population combinedPopu = new Population((a.getPopSize() + b.getPopSize()), false);
		combinedPopu.setNetworkPropInPop(a.getAdjMat(), a.getTrnsCstMat());
		combinedPopu.setIndividuals(a.getIndividuals(), b.getIndividuals(), a.getPopSize(), b.getPopSize());
		return combinedPopu;
	}


	private static Population evolvePopulation(Population pop) {		
		int i = 0, elitismOffset;	//no of elite chromosomes

		/* GA parameters 
		final double crossoverRate = 0.8;
		final double mutationRate = 0.1;
		final boolean elitism = true;	//save best two across generations
		 */

		Population newPopulation = new Population(pop.getPopSize(), false);
		//Population newPopulation = new Population(pop.getPopSize(), true);
		newPopulation.setNetworkPropInPop(pop.getAdjMat(),pop.getTrnsCstMat());

		//pop.sortByFitness();		//First use of sort
		//pop.sortByDominance(true);	//	## ###### DELAY SENSITIVE
		// Crossover population
		if (elitism) {
			elitismOffset = (pop.getPopSize()/4);	// 1/4 of the total popu (512/4)
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
				/*
			while(newPopulation.getPopSize() <= elitismOffset){
				newPopulation.setFront(pop.getFrontByIndex(i));
				i++;			
			}
				 */
			}
		}



		// Loop over the population size and create new individuals with
		// crossover, select rest (except elites) and crossover in them
		for ( i = elitismOffset; i < pop.getPopSize(); i+=2) {
			//for ( i = newPopulation.getPopSize(); i < pop.getPopSize(); i+=2) {
			//for ( i = 0; i < pop.getPopSize(); i+=2) {
			Individual indiv1 = tournamentSelection(pop);
			Individual indiv2 = tournamentSelection(pop);
			////Individual indiv1 = pop.getIndividual(i - elitismOffset);
			////Individual indiv2 =  pop.getIndividual(i+1 - elitismOffset);
			///Individual indiv1 = pop.getBySortedFitness(i);
			///Individual indiv2 =  pop.getBySortedFitness(i+1);
			if (Math.random() <= crossoverRate) {
				//System.out.println("Before Cross 1: ");
				Individual newIndiv1 = crossover(indiv1, indiv2, true);			//false for first child i.e.,  [part of parent1][part of parent2]
				Individual newIndiv2 = crossover(indiv1, indiv2, false);		//true for first child i.e.,  [part of parent2][part of parent1]
				/*	for synchronization if overlap problem occurs
				Individual newIndiv1 = new Individual();// = crossover(indiv1, indiv2, true);			//false for first child i.e.,  [part of parent1][part of parent2]
				Individual newIndiv2 = new Individual();// = crossover(indiv1, indiv2, false);		//true for first child i.e.,  [part of parent2][part of parent1]
				synchronized (newIndiv1) {
					newIndiv1 = crossover(indiv1, indiv2, true);
				}
				synchronized (newIndiv2) {
					newIndiv2 = crossover(indiv1, indiv2, false);
				}
				 */
				newPopulation.setIndividual(i, newIndiv1);
				newPopulation.setIndividual(i+1, newIndiv2);
				//newPopulation.setPopSize((newPopulation.getPopSize() + 2));
				//System.out.println("After Cross 1: ");
			}else{
				newPopulation.setIndividual(i, indiv1);
				newPopulation.setIndividual(i+1, indiv2);
				//newPopulation.setPopSize((newPopulation.getPopSize() + 2));
			}
			//System.out.println("After Cross 2: ");
		}
		///////newPopulation.setPopSize(pop.getPopSize());

		// MUTATION (elites are not subject to this.)	mutate in the new generation
		//mutationRate = 0.1;
		for ( i = elitismOffset; i < newPopulation.getPopSize(); i++) {
			//for ( i = 0; i < newPopulation.getPopSize(); i++) {
			if (Math.random() <= mutationRate)
				newPopulation.setIndividual(i, mutate(newPopulation.getIndividual(i), newPopulation.getAdjMat()));
		}

		return newPopulation;
	}


	// Crossover individuals
	private static Individual crossover(Individual indiv1, Individual indiv2,boolean parent_1) {
		int i, j, count=0, crossSite = -1, lenNew, len1 = indiv1.getChromosome().length, len2 = indiv2.getChromosome().length;
		int[] tempArr1, tempArr2, tempArrRearr1, tempArrRearr2, newGenes;
		int[] chrm1 = indiv1.getChromosome();
		int[] chrm2 = indiv2.getChromosome();
		Random randInt = new Random();
		Individual newIndiv = new Individual();

		tempArr1 = new int[len1>len2?len1:len2];
		tempArr2 = new int[tempArr1.length];
		for(i = 0; i<tempArr1.length; i++){
			tempArr1[i] = -1;
			tempArr2[i] = -1;
		}

		for(i = 1; i<(len1-1); i++){		//to avoid first and last points
			for(j = 1; j<(len2-1); j++){
				if(chrm1[i] == chrm2[j]){
					tempArr1[i] = i;
					tempArr2[i] = j;
					//System.out.println("If Equal : " + tempArr1[i] + " " + tempArr2[i]);
					count++;
				}
			}
		}
		//potentialCrossoverSites = new int[count];
		/*
		count = 0;
		for(i = 0; i<tempArr.length; i++){
			if(tempArr[i] != -1){
				potentialCrossoverSites[count] = tempArr[i];
				count++;
			}
		}
		 */
		if(count != 0){
			crossSite = randInt.nextInt(count);	//+1 is used to consider the last occurrence too
			//System.out.println(" Site : " + crossSite);
			tempArrRearr1 = new int[count];
			tempArrRearr2 = new int[count];

			count = 0;
			for(i = 0; i<tempArr1.length; i++){
				if(tempArr1[i] != -1){
					tempArrRearr1[count] = tempArr1[i];
					tempArrRearr2[count] = tempArr2[i];
					count++;
				}
				/*
				if(tempArr1[i] != -1){
					tempArrRearr1[count1] = tempArr1[i];
					count1++;
				}
				if(tempArr1[i] != -1){
					tempArrRearr2[count2] = tempArr2[i];
					count2++;
				}
				 */
			}

			/*
			System.out.println("Before Crossover : Parent is 1 - " + parent_1 );
			System.out.println("1 : ");
			indiv1.printChromosome();
			System.out.println("2 : ");
			indiv2.printChromosome();
			 */
			//extra = (tempArrRearr1[crossSite] - tempArrRearr2[crossSite]);
			count = 0;
			if(parent_1){
				//System.out.println("CS1 : " + tempArrRearr1[crossSite]);
				//lenNew = crossSite + (len2 - crossSite);
				lenNew = tempArrRearr1[crossSite] + Math.abs(len2 - tempArrRearr2[crossSite]);
				//System.out.println("NewLen : " + lenNew);
				//lenNew = crossSite + (len2 - tempArrRearr2[crossSite]);
				newGenes = new int[lenNew];
				for(i = 0; i<tempArrRearr1[crossSite] ; i++){
					//newGenes[count] = indiv1.getGene(i);
					newGenes[count] = chrm1[i];
					count++;
				}
				//for(i = (tempArrRearr1[crossSite] + extra); i< len2 ; i++){
				for(i = tempArrRearr2[crossSite]; i< len2 ; i++){
					//newGenes[count] = indiv2.getGene(i);
					newGenes[count] = chrm2[i];
					count++;
				}
				//System.out.println("CS1.1 : " + tempArrRearr1[crossSite]);
			}
			else{
				//lenNew = crossSite + (len1 - crossSite);
				//System.out.println("CS2 : " + tempArrRearr2[crossSite]);
				lenNew = tempArrRearr2[crossSite] + Math.abs(len1 - tempArrRearr1[crossSite]);
				//System.out.println("NewLen : " + lenNew);
				//lenNew = crossSite + (len1 - tempArrRearr1[crossSite]);
				newGenes = new int[lenNew];
				for(i = 0; i<tempArrRearr2[crossSite] ; i++){
					//newGenes[i] = indiv2.getGene(i);
					newGenes[count] = chrm2[i];
					count++;
				}
				for(i = tempArrRearr1[crossSite]; i< len1 ; i++){
					//newGenes[i] = indiv1.getGene(i);
					newGenes[count] = chrm1[i];
					count++;
				}
			}
			/*
			System.out.println("Before Crossover : Parent is 1 - " + parent_1 );
			System.out.println("1 : ");
			indiv1.printChromosome();
			System.out.println("2 : ");
			indiv2.printChromosome();
			 */
			//System.out.println("After Crossover");
		}else{
			if(parent_1)
				newGenes = chrm1;
			else
				newGenes = chrm2;
		}

		/*
		System.out.println("Before Crossover : Parent is 1 - " + parent_1 );
		System.out.println("1 : ");
		indiv1.printChromosome();
		System.out.println("2 : ");
		indiv2.printChromosome();
		System.out.println("After Crossover");
		 */

		newIndiv.setChromosome(newGenes);
		//newIndiv.printChromosome();

		return(newIndiv);
	}


	// Mutate an individual
	private static Individual mutate(Individual indivToMutate, int[][] adjMat) {
		// Select any gene at random and mutate/flip it
		int i, mutatedGene, randInt, newLen, count = 0;
		int[] mutatedChromosome, tempCh1, tempCh2;
		Random rand = new Random();
		//System.out.println("Mutation Chrom len : " + indivToMutate.getChromosomeLength());
		if(indivToMutate.getChromosomeLength()<=2)
			//only s->d, i.e., directly connected (from random initialization) that is (1->32)
			return(indivToMutate);
		else
			randInt = rand.nextInt(indivToMutate.getChromosomeLength() - 2) + 1;		// -2 to avoid the first and last node i.e., 0-30 and	+1 to avoid the first node i.e., 1-30
		mutatedGene = indivToMutate.getGene(randInt);
		tempCh1 = new int[randInt];
		for( i = 0; i < (randInt-1); i++)
			tempCh1[i] = indivToMutate.getGene(i);

		indivToMutate.setAdjMat(adjMat);
		tempCh2 = indivToMutate.getRouteFromNode(mutatedGene, adjMat.length, sinkNode);
		newLen = (randInt) + tempCh2.length;
		mutatedChromosome = new int[newLen];
		for( i = 0; i < randInt; i++)
			mutatedChromosome[i] = indivToMutate.getGene(i);//tempCh1[i];
		for( i = randInt; i < newLen; i++){
			mutatedChromosome[i] = tempCh2[count];
			count++;
		}

		////System.out.println("Before Mutation, mutation node " + mutatedGene);
		/////indivToMutate.printChromosome();
		indivToMutate.setChromosome(mutatedChromosome);
		indivToMutate.removeLoops();
		////System.out.println("After Mutation");
		////indivToMutate.printChromosome();
		return (indivToMutate);
	}

	// Select individuals for crossover
	private static Individual tournamentSelection(Population pop) {
		int i, randomId, tournamentSize = 5;
		Individual fittest;
		// Create a tournament population
		Population tournament = new Population(tournamentSize, false);
		tournament.setNetworkPropInPop(pop.getAdjMat(),pop.getTrnsCstMat());
		// For each place in the tournament get a random individual
		for (i = 0; i < tournamentSize; i++) {
			randomId = (int) (Math.random()*pop.getPopSize());
			tournament.setIndividual(i, pop.getIndividual(randomId));
		}
		//Sort by fitness
		//tournament.sortByDominance(true);
		tournament.sortByCrowdedComparisonOperator();
		//tournament.sortByFitness(tournamentSize);
		// Get the fittest
		fittest = tournament.getFittest();
		return fittest;
	}
}