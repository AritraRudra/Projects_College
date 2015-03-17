/*
http://www.hackerearth.com/october-clash/algorithm/chemical-reaction/

Chemical Reaction
Max. Score 100
Ani and his Favourite Chemistry Teacher Lissa were performing an Experiment in the Chemistry Lab. Experiment involves a N step Chemical Reaction. An N step Chemical Reaction requires N different reactants from the periodic table . (Do you know about Periodic Table? No , still you can try solving this problem). N elements are stacked in the bottom up manner with their reacting times. Look at the given figure.

enter image description here

Lissa is very good at performing experiment (offcourse as she is a chemistry teacher). So, she is doing the actual job alone. Ani is there only to provide her a helping hand. After every step, Lissa ordered Ani to put kth element from the stack (counting start from bottom) into the ongoing chemical reaction and record the expected time taken by the chemical reaction to be accomplished.

Expected Time of a Chemical reaction is defined as the maximum of reacting time of all the reactants present in the chemical reaction at that instant of time.

Considering a 6 step Chemical reaction with the same set of reactants given above. Let the order of elements given by Lissa to Ani follows this list.

Note that the list contains N-1 elements only.

2 2 1 2 2

Step 1: Ani puts the second element from the bottom i.e titanium into the chemical reaction and records the expected time as 799 .

New stack configuration ::

enter image description here

Step 2: Ani puts the second element from the bottom i.e barium into the chemical reaction and records the expected time as 799.

New stack configuration ::

enter image description here

Step 3: Ani puts the first element from the bottom i.e zinc into the chemical reaction and records the expected time as 999.

New stack configuration ::

enter image description here

Step 4: Ani puts the second element from the bottom i.e potassium into the chemical reaction and records the expected time as 999.

New stack configuration ::

enter image description here

Step 5: Ani puts the second element from the bottom i.e sodium into the chemical reaction and records the expected time as 999.

New stack configuration ::

enter image description here

As there is only one element left on the stack in the end. Ani puts that element into the reaction without asking his teacher (He is over-smart actually ). While doing this, he dropped some chemical on the record taken by him. This made Miss Lissa very angry and she decided to punish him. Ani does not want to be punished by his favourite teacher. So, can you save him from being punished ?. Can you generate same record for him.

Input:

First line of input contains a single integer T denoting the number of Experiments to be performed. Next 4*T lines contains description of each experiment. Each experiment's description consists of 4 lines.

First line of description contains a single integer N denoting the order of reaction (number of reactants) . Next line of description contains N space separated strings i.e names of reactants. Next line of description contains N integers denoting the reacting time of each element. Next line of description contains N-1 integers denoting the ordered list of elements given by Lissa to Ani.

Output:

For each Experiment, Output consists of N lines where ith line contains one string (name of Element added in the ith step) and expected time of the Chemical Reaction after ith step.

Constraints:

1 <= T <=10

1 <= N <= 5*105

Element names composed of only lower case letters

1 <=|Reactant's name| <= 10

0 <= Reacting time <= 109

sum of all N over all the test cases is 5*109

NOTE:

Prefer to use Printf / Scanf instead of cin / cout (very large input set).

Sample Input (Plaintext Link)
1
6 
zinc titanium barium lithium potassium sodium
999 799 600 140 7 100
2 2 1 2 2
Sample Output (Plaintext Link)
titanium 799
barium 799
zinc 999
potassium 999
sodium 999
lithium 999 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Element{
    String name;
	int r_time;
}
class ChemReaction{	
	public static void main(String[] args)throws Exception{
		int t,n,i,max,rt;
		int arr[];
		String str=new String();
		Element elm;
		ArrayList<Element> elmns= new ArrayList<Element>();
		InputStream is = null;
		BufferedReader in = null;
		PrintStream out = new PrintStream(System.out);
		StringTokenizer tok;
		is = System.in;
		in = new BufferedReader(new InputStreamReader(is));
		try{
			str=in.readLine();			
			t=Integer.parseInt(str);
			while(t-->0){
				str=in.readLine().trim();
				n=Integer.parseInt(str);
				str=in.readLine();
				tok=new StringTokenizer(str, " ");
				for(i=0;i<n;i++){					
					elm=new Element();
					elm.name=tok.nextToken();
					elm.r_time=0;
					elmns.add(elm);
				}
				str=in.readLine();
				tok=new StringTokenizer(str);
				for(i=0;i<n;i++){
					rt=Integer.parseInt(tok.nextToken());
					elm=new Element();
					elm.r_time=rt;
					elm.name=elmns.get(i).name;
					elmns.set(i,elm);
				}
				max=-1;
				n--;
				arr=new int[n];
				str=in.readLine();
				tok=new StringTokenizer(str);
				for(i=0;i<n;i++)
					arr[i]=Integer.parseInt(tok.nextToken())-1;				
				for(i=0;i<n;i++){
					elm=elmns.get(arr[i]);
					rt=elm.r_time;
					if(rt>max)
						max=rt;
					out.println(elm.name+" "+max);
					elmns.remove(arr[i]);
				}
				str=elmns.get(0).name;
				rt=elmns.get(0).r_time;
				if(rt>max)
					max=rt;
				out.println(str+" "+max);
				elmns.removeAll(elmns);
			}
		}
		catch (IOException ioe){}
		finally{
			try{
				in.close();
				out.close();
			}catch (IOException ioe){}
		}
	}
}