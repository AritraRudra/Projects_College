import java.util.ArrayList;
/*
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;
 */

public class Path
{
	int /*i, j,*/ noOfNodes;
	int[][] adjMat;// = new int[noOfNodes][noOfNodes];

	public Path(int[][] adjMat, int noOfNodes ){
		//adjMat = new int[noOfNodes][noOfNodes];
		this.adjMat = adjMat;
		this.noOfNodes = noOfNodes;
	}

	public int[] initilize_Get(String KEY, int goalNode) {
		/*
		try {
			//Read the network information and store in adjacency matrix
			String netInfFile=new String("Network_Data.txt");
			Scanner netInfData = new Scanner(new FileInputStream(netInfFile));
			for (int i = 0; i < noOfNodes; i++){
				for (int j = 0; j < noOfNodes; j++){
					adjMat[i][j] = netInfData.nextInt();
					//System.out.print(adjMat[i][j] + " ");
				}
				//System.out.println();
				netInfData.nextLine();
			}
			netInfData.close();
		} catch (FileNotFoundException e) {
			System.out.println("Files containing Network Information are not found. "); 
			e.printStackTrace();
		}
		 */

		ArrayList<Integer> Start = new ArrayList<Integer>();
		Start.add(0);    // The start node
		ArrayList<ArrayList> Queue = new ArrayList();
		Queue.add(Start); // inserted in the initial queue of paths as [[Start]]
		//Use one search at a time (comment the others)
		ArrayList Path = new ArrayList<>();
		//int[] newGenes = new int [noOfNodes];
		if (KEY.equals("DFS"))
			Path = depth_first(Queue,goalNode);   // Depth first search
		else if (KEY.equals("BFS"))
			Path = breadth_first(Queue,goalNode); // Breadth first search
		//     ArrayList Path = uni_cost(Queue,4); // Uniform cost search
		//System.out.println(Path);
		//newGenes = getChrom(Path);
		return getChrom(Path);

		//     System.out.println(path_cost(Path));
	}

	private int[] getChrom(ArrayList path) {
		int i, len = path.size();
		//int[] rev = new int[len];
		//int[] correct = new int[len];
		int[] newGenes = new int[len];

		/*
		/for binary chromosome
		for ( i = 0 ; i<this.noOfNodes ; i++){
			newGenes[i] = -1;
		}

		for ( i = 0 ; i<len ; i++){
			rev[i] = (int) path.get(i);
			//correct[(len-1) - i] = rev[i];
			newGenes[rev[i]] = 1;
			//System.out.println(temp1[i]);
		}
		 */

		//for non-binary chromosome
		for ( i = 0 ; i<len ; i++)
			newGenes[i] = (int) path.get((len-1) -i);

		return newGenes;
	}

	//--------------------------------------------------------------
	//Adds to the queue paths extending the first path in the queue       
	public ArrayList<ArrayList> extend (ArrayList<Integer> Path)
	{
		ArrayList<ArrayList> NewPaths = new ArrayList();
		for (int i=0;i<adjMat.length;i++)
			if (adjMat[Path.get(0)][i]>0 && !Path.contains(i))
			{
				ArrayList Path1 = (ArrayList)Path.clone();
				Path1.add(0,i);
				NewPaths.add(Path1);
			}
		return NewPaths;
	}
	//--------------------------------------------------------------
	//Appends y to x and returns the result in a new ArrayList z 
	public ArrayList append (ArrayList x, ArrayList y) 
	{
		ArrayList z = (ArrayList)x.clone();;
		for (int i=0;i<y.size();i++) z.add(y.get(i));
		return z;
	}
	//--------------------------------------------------------------
	//Depth first search for a path from Start to Goal. 
	//The Start node must be in the initial queue of paths as [[Start]]   
	public ArrayList<ArrayList> depth_first(ArrayList<ArrayList> Queue, int Goal) 
	{
		if (Queue.size()==0) return Queue;         // path not found
		if ((Integer)Queue.get(0).get(0) == Goal)  // if Goal is the first node in the first path
			return Queue.get(0);                   // return path
		else // replace the first path with all its extensions and put them in front of the queue            
		{
			ArrayList<ArrayList> NewPaths = extend(Queue.get(0));
			Queue.remove(0); 
			return depth_first(append(NewPaths,Queue),Goal);
		}
	}
	//--------------------------------------------------------------
	//Breadth first search for a path from Start to Goal. 
	//The Start node must be in the initial queue of paths as [[Start]]   
	public ArrayList<ArrayList> breadth_first(ArrayList<ArrayList> Queue, int Goal) 
	{
		if (Queue.size()==0) return Queue;          // path not found
		if ((Integer)Queue.get(0).get(0) == Goal)   // if Goal is the first node in the first path
			return Queue.get(0);                           // return path
		else // replace the first path with all its extensions and put them at the end of the queue
		{ 
			ArrayList<ArrayList> NewPaths = extend(Queue.get(0));
			Queue.remove(0);
			return breadth_first(append(Queue,NewPaths),Goal);
		}
	}
	//--------------------------------------------------------------
	public int path_cost (ArrayList<Integer> Path)
	{
		int cost = 0;
		for (int i=0;i<Path.size()-1;i++)
			cost = cost + adjMat[Path.get(i+1)][Path.get(i)];
		return cost;
	}
	//--------------------------------------------------------------    
	//Uniform cost search for a path from Start to Goal. 
	//The Start node must be in the initial queue of paths as [[Start]]   
	public ArrayList<ArrayList> uni_cost(ArrayList<ArrayList> Queue, int Goal) 
	{
		if (Queue.size()==0) return Queue;          // path not found
		if ((Integer)Queue.get(0).get(0) == Goal)   // if Goal is the first node in the first path
			return Queue.get(0);                           // return path
		else // replace the first path with all its extensions and put them at the end of the queue
		{ 
			ArrayList<ArrayList> NewPaths = extend(Queue.get(0));
			Queue.remove(0);
			ArrayList<ArrayList> Queue1 = append(Queue,NewPaths);
			sort(Queue1);   // Put the path with the lowest cost first in the queue
			return uni_cost(Queue1,Goal);
		}
	}
	//--------------------------------------------------------------
	public  void sort(ArrayList<ArrayList> Queue)
	{
		int out, in;
		for(out=Queue.size()-1; out>=1; out--)
			for(in=0; in<out; in++)
				if( path_cost(Queue.get(in)) > path_cost(Queue.get(in+1)) )
					swap(Queue, in, in+1);
	}
	//--------------------------------------------------------------
	private  void swap(ArrayList<ArrayList> a, int one, int two)
	{
		ArrayList<Integer> temp = a.get(one);
		a.set(one,a.get(two));
		a.set(two,temp);
	}

}