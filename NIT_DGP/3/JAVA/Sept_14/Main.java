import java.util.Scanner;
import java.util.ArrayList;
import java.math.BigInteger;
public class Main{
	public static void main(String[] args) throws java.lang.Exception{
		int t,j,k,listLen,divLen;
		BigInteger n,i,temp;
		BigInteger init_n,loopChk;
		BigInteger power,num_factors;
		ArrayList<BigInteger> arrList,divisors;
		Scanner sc = new Scanner(System.in);
		t=sc.nextInt();
		while(t-->0){
			n=sc.nextBigInteger();
			arrList=new ArrayList<BigInteger>();

			//http://stackoverflow.com/questions/2844703/algorithm-to-find-the-factors-of-a-given-number-shortest-method
			init_n=n;
			num_factors = BigInteger.ONE;
			//temp=BigInteger.ONE;
			temp=init_n;
			
			loopChk=BigInteger.valueOf(4L);
			for (i = BigInteger.valueOf(2L); (i.multiply(i).compareTo(init_n)) <=0 ; i=i.add(BigInteger.ONE)){ // for each number i up until the square root of the given number
				power = BigInteger.ZERO; // suppose the power i appears at is 0
				while ((n.remainder(i).compareTo(BigInteger.ZERO)) == 0){ // while we can divide n by i
					n=n.divide(i);
					power=power.add(BigInteger.ONE);
					/*if(temp.remainder(i).compareTo(BigInteger.ZERO)==0){
						temp=init_n;
						arrList.add(i);
					}*/
					arrList.add(i);
					//arrList.add(temp.multiply(power));
					//n = n / i // divide it, thus ensuring we'll only check prime factors
			        //++power // increase the power i appears at
				}
				num_factors = num_factors.multiply((power.add(BigInteger.ONE))); // apply the formula
				//loopChk=i.multiply(i);
			}
			if(n.compareTo(BigInteger.ONE)>0)
				num_factors.multiply(BigInteger.valueOf(2L));
				//num_factors=num_factors.multiply(BigInteger.valueOf(2L));

			/*
			if (n > 1) // will happen for example for 14 = 2 * 7
			{
			    num_factors = num_factors * 2 // n is prime, and its power can only be 1, so multiply the number of factors by 2
			}
			 */
			//end


			
			
			//2nd method slower but shows all the factors
			/*int divisorsCount = 1;
			int i;
			for(i = 2; i * i < n; ++i)
			{
			    if(n % i == 0)
			    {
			        ++divisorsCount;
			    }
			}
			divisorsCount *= 2;
			if(i * i == n)
			{
			    ++divisorsCount;
			}*/
			//end
			// Implementation of 2nd method
			/*
			for (i = BigInteger.valueOf(2L); (i.multiply(i).compareTo(init_n)) < 0 ; i=i.add(BigInteger.ONE)){
				if((n.remainder(i).compareTo(BigInteger.ZERO)) == 0){
					arrList.add(i);
					num_factors=num_factors.add(BigInteger.ONE);
				}
			}
			num_factors=num_factors.multiply(BigInteger.valueOf(2L));
			if(i.multiply(i).compareTo(init_n)==0)
				num_factors=num_factors.add(BigInteger.ONE);
			*/
			//End
			
			System.out.println(num_factors);
			System.out.println(arrList);
			temp=BigInteger.ONE;
			divisors=new ArrayList<BigInteger>();
			listLen=arrList.size();
			//divisors.add(BigInteger.ONE);
			for(j=0;j<listLen;j++){
													//for (i = BigInteger.ZERO; (i.compareTo(num_factors)) < 0 ; i=i.add(BigInteger.ONE)){
				temp=arrList.get(j);
				if(!(divisors.contains(temp))){
					//System.out.println(temp);
					divisors.add(temp);
				}
			}
			divLen=divisors.size();
			for(j=divLen;j<num_factors.intValue();j++){
			//for(j=0;j<num_factors.intValue();j++){
				//temp=arrList.get(j%listLen);
				for(k=(j%listLen);k<listLen;k++){
					/*temp=arrList.get(j%listLen);
					temp=temp.multiply(arrList.get(k));*/
					temp=arrList.get(j%listLen);
					temp=temp.multiply(arrList.get(k));
					if(!(divisors.contains(temp))){
						divisors.add(temp);
						//System.out.println(temp);
					}
				}
			}
			
			divLen=divisors.size();
			for(j=0;j<divLen;j++){
				power=divisors.get(j);
				for(k=j;k<divLen;k++){
					temp=divisors.get(k);
					temp=temp.multiply(power);
					if((temp.compareTo(init_n)<=0)&&(!divisors.contains(temp))){
						divisors.add(temp);
						//System.out.println(temp);
					}
				}
			}
			
			System.out.println();
			divLen=divisors.size();
			//System.out.println(divisors);
			for(j=0;j<divLen;j++){
				//temp=temp.multiply(divisors.get(j));
				temp=divisors.get(j);
				power=init_n;
				if(power.remainder(temp).compareTo(BigInteger.ZERO)==0)
					System.out.println(temp);
			}
		}
	}





	/*
	private static void printPrimeFactors(int num){
		int prime = 2;
		while(num>1){
			if(num%prime == 0){
				printf("%d ",prime);
				num = num/prime;
			}
			else
				prime++;
		}
	}
	 */
}