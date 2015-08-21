import java.util.Scanner;
class Panagram{
	public static void main(String []args)throws java.lang.Exception{
		int t,i,flag,len,index;
		char ch;
		boolean ans, alphas[]=new boolean[26];
		String str;
		Scanner sc = new Scanner(System.in);
		t=sc.nextInt();
		sc.nextLine();
		while(t-->0){
			flag=0;
			str=sc.nextLine();
			System.out.println("READ "+str);
			for(i=0;i<26;i++)
				alphas[i]=false;
			i=0;
			len=str.length();
			while((i<len)&&(flag!=26)){
				ch=str.charAt(i);
				index=-1;
				if((ch>=65)&&(ch<=90))
					index=(int)(ch-'A');
				if((ch>=97)&&(ch<=122))
					index=(int)(ch-'a');
				if((index!=-1)&&(alphas[index]==false)){
					alphas[index]=true;
					flag++;
				}
				i++;
				
			}
			if(flag==26)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
		sc.close();
	}
}