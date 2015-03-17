public class Individual {

	//final int defaultGeneLength = 32;	//No of nodes
	private int  index, defaultGeneLength = 32;	//No of nodes
	private double fitness = 0;
	//private byte[] genes = new byte[defaultGeneLength];	//Chromosome
	private int[] genes;// = new int[defaultGeneLength];
	public int[][] adjMat, costMatDelay, costMatEnrg;
	public double[][] S;


	public Individual(){
		this.genes = new int[defaultGeneLength];
		this.fitness = 0.0;
	}

	// Create a random individual
	public void generateChromosome(String keyword) {
		int i, pos, newGene;
		//Graph graph = new Graph(adjMat, defaultGeneLength, 0);
		//For random generation
		if(keyword.equals("RANDOM")){
			//if (Math.random() <=0.5) {
				for (i = 0; i < this.defaultGeneLength; i++) {
					newGene =  (int) Math.round(Math.random());
					genes[i] = newGene;
				}
			}
		/*else{
				//}
				//if(keyword.equals("RANDOM")){
				/*
					Random rand = new Random();
					pos = rand.nextInt(defaultGeneLength -1);	 //[0-31]
				 */
				/*newGene = 0;
				pos = (int) Math.round(Math.random()*(defaultGeneLength-1));
				for (i = 0; i < this.defaultGeneLength; i++) {
					if(adjMat[pos][i] == 1)
						newGene = 1;
					genes[i] = newGene;
				}
				genes[pos] = 1;
			}
		}
		*/
		//For BFS generation
		else if(keyword.equals("BFS")){
			Path path = new Path(adjMat, defaultGeneLength);
			genes = path.initilize_Get("BFS");
		}
		//For DFS generation
		else {
			Path path = new Path(adjMat, defaultGeneLength);
			genes = path.initilize_Get("DFS");
		}

		/*
		for (i = 0; i < size(); i++) {
			byte gene = (byte) Math.round(Math.random());
			genes[i] = gene;
		}
		 */	
	}

	//Only setters
	// Use this if you want to create individuals with different gene lengths
	public void setDefaultGeneLength(int length) {
		this.defaultGeneLength = length;
	}
	public int getLength(){
		return (this.defaultGeneLength);
	}

	public void setAdjMat(int[][] adjMat) {
		this.adjMat = adjMat;
	}

	public void setCostMatDelay(int[][] costMatDelay) {
		this.costMatDelay = costMatDelay;
	}

	public void setCostMatEnrg(int[][] costMatEnrg) {
		this.costMatEnrg = costMatEnrg;
	}

	public void setS(double[][] S) {
		this.S = S;
	}

	public void setNetworkProp(int[][] adjMat, int[][] costMatDelay, int[][] costMatEnrg, double[][] S) {
		this.adjMat = adjMat;
		this.costMatDelay = costMatDelay;
		this.costMatEnrg = costMatEnrg;
		this.S = S;
	}

	//Both Getters and Setters
	public int getIndex() {
		return (this.index);
	}

	public void setIndex(int index) {
		this.index = index;
	}



	/*
	public byte getGene(int index) {
		return (this,genes[index]);
	}

	public void setGene(int index, byte value) {
		this.genes[index] = value;
		this.fitness = 0;
	}
	 */

	public int getGene(int index) {
		return (this.genes[index]);
	}

	public void setGene(int index, int value) {
		this.genes[index] = value;
		this.fitness = 0.0;
	}

	/*
	    public byte[] getChromosome() {
	        return genes;
	    }

	    public void setChromosome(byte[] chr) {
		this.genes = chr;
		this.fitness = 0;
	}
	 */

	public int[] getChromosome() {
		return (this.genes);
	}

	public void setChromosome(int[] chr) {
		this.genes = chr;
		this.fitness = 0.0;
	}

	//End of Getters and Setters

	public double getFitness() {
		if (this.fitness == 0.0) {
			this.fitness = calculateFitness();
			return (this.fitness);
		}else 
			return (this.fitness);
	}

	/*private double calculateFitness() {		//cost = fitness , i.e., higher cost is higher fitness value but we sort fitness values in non descending order 
		int i,a,b, countOf1s, len = (this.defaultGeneLength -1);
		double myFitness = 0.0, cost_w = 0.0, s;
		int[] chrom = this.genes, indexOf1s, temp = new int[len +1];	//Including first and last 1
		if (chrom[0] != 1 || chrom[len] != 1) {
			myFitness = 200000;
			return (myFitness);
		}else{
			countOf1s = 0;
			for( i = 0; i < (len +1) ; i++){		// Nope : to check up to 30th index, excluding the last one
				temp[i] = -1;
				if(chrom[i] == 1){
					temp[i] = i;
					countOf1s++;
				}
				System.out.print(this.genes[i]);
				System.out.print(chrom[i]);
			}
			System.out.println();
			indexOf1s = new int[countOf1s];
			countOf1s = 0;
			for( i = 0; i < (len +1) ; i++){
				if(temp[i] != -1){
					indexOf1s[countOf1s] = temp[i];
					System.out.println(indexOf1s[countOf1s]);
					countOf1s++;
				}
			}

			for( i = 0; i < (countOf1s-1) ; i++){		//looks OK
				a = indexOf1s[i];
				b = indexOf1s[i+1];
				//if(adjMat[a][b] == 1){		//When there is exists an edge between a and b
					s = 0.8;
					cost_w = s*costMatDelay[a][b]	+ (1.0 - s)*costMatEnrg[a][b];
					//cost_w = S[a][b]*costMatDelay[a][b]	+ ( 1-S[a][b] )*costMatEnrg[a][b];

				cost_w = S[indexOf1s[i]][indexOf1s[i+1]]*costMatDelay[indexOf1s[i]][indexOf1s[i+1]]
						+ ( 1-S[indexOf1s[i]][indexOf1s[i+1]] )*costMatEnrg[indexOf1s[i]][indexOf1s[i+1]];

					myFitness += cost_w;
				//}else				//When there is no edge between a and b
					//myFitness += 100000;
			}
			//System.out.println(myFitness);
			return (myFitness);
		}
	}*/

	private double calculateFitness() {		//cost = fitness , i.e., higher cost is higher fitness value but we sort fitness values in non descending order 
		int i,a,b, countOf1s, len = (this.defaultGeneLength -1);
		double myFitness = 0.0, cost_w = 500000.0, s;
		int[] indexOf1s, temp = new int[len +1];	//Including first and last 1
		if (this.genes[0] != 1 || this.genes[len] != 1) {
			myFitness = 100000;
			return (myFitness);
		}else{
			countOf1s = 0;
			for( i = 0; i < (len +1) ; i++){		// Nope : to check up to 30th index, excluding the last one
				temp[i] = -1;
				if(this.genes[i] == 1){
					temp[i] = i;
					countOf1s++;
				}
				//System.out.print(this.genes[i]);
				//System.out.print(chorm[i]);
			}
			//System.out.println();
			indexOf1s = new int[countOf1s];
			countOf1s = 0;
			for( i = 0; i < (len +1) ; i++){
				if(temp[i] != -1){
					indexOf1s[countOf1s] = temp[i];
					//System.out.println(indexOf1s[countOf1s]);
					countOf1s++;
				}
			}

			for( i = 0; i < (countOf1s -1) ; i++){		//looks OK	(countOf1s -1) 
				a = indexOf1s[i];
				b = indexOf1s[i+1];
				if(adjMat[a][b] == 1){		//When there is exists an edge between a and b
					s = 0.8;
					cost_w = s*costMatDelay[a][b] + (1.0 - s)*costMatEnrg[a][b];
					//cost_w = S[a][b]*costMatDelay[a][b]	+ ( 1-S[a][b] )*costMatEnrg[a][b];
					/*
				cost_w = S[indexOf1s[i]][indexOf1s[i+1]]*costMatDelay[indexOf1s[i]][indexOf1s[i+1]]
						+ ( 1-S[indexOf1s[i]][indexOf1s[i+1]] )*costMatEnrg[indexOf1s[i]][indexOf1s[i+1]];
					 */
					myFitness += cost_w;
				}
				else{				//When there is no edge between a and b
					s = 0.8;
					cost_w = s*costMatDelay[a][b] + (1.0 - s)*costMatEnrg[a][b];
					myFitness += cost_w;
					//myFitness += 25000.0;
					//break;
				}
			}
			//System.out.println(myFitness);
			return (myFitness);
		}
	}


	/*
		//byte chr[] = individual.getChromosome();
		if((individual.getGene(0) != Byte.parseByte("1")) || (individual.getGene(len -1) != Byte.parseByte("1"))){		//check if first and last bit are 1
			fitness = 32000.0;
			return (int) (fitness);
		}else{
			for(int i = 0; i < (len-1) ; i++){		// to check up to 30th index, excluding the last one
				indexOfNext1 = individual.getIndexOfNext1(i);
				//System.out.println(indexOfNext1 + " ");
				if((indexOfNext1 != -1) && ( adjMat[i][indexOfNext1] == 1)){
					cost_w = S[i][indexOfNext1]*costMatDelay[i][indexOfNext1] + ( 1-S[i][indexOfNext1] )*costMatEnrg[i][indexOfNext1];
					fitness+= cost_w;
					//System.out.println(fitness + " ");
				}
				/*else{
    			fitness = 9999;
    		}
			}
	 */



	/*
	public int getIndexOfNext1(int currIndex){
		int gene1 = 1;
		for(int i = currIndex+1 ; i < size() ; i++){
			if(this.genes[i] == byte1)
				return (i);
		}
		return (-1);
	}
	 */

	@Override
	public String toString() {
		int i;
		String geneString = "";
		for (i = 0; i < this.defaultGeneLength ; i++) {
			geneString += getGene(i);
		}
		return geneString;
	}


	//Comparator
	/*
    public int compare(Individual o1, Individual o2) {
		return (int) (o1.getFitness() - o2.getFitness());	//for reverse order
	}
	 */

	/*
    @Override
	public int compare(Individual o1, Individual o2) {
		return (int) (o1.getFitness() - o2.getFitness());
	}



	public static Comparator<Individual> fitnessComparator = new Comparator<Individual>() {
		//static Comparator<Tracking> NbrNoComparator = new Comparator<Tracking>() {
		@Override
		public int compare(Individual t1, Individual t2) {
			return (int) (t1.getFitness() - t2.getFitness());
		}
	};
	 */

}
