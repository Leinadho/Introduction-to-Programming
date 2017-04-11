import java.util.Arrays;
import java.util.Scanner;
public class sieveOfEratosthenes {

		public static void main(String[] args)
		{
			Scanner input = new Scanner(System.in);
			System.out.println("enter a positive integer: ");
			int N;
			while(!input.hasNextInt())
			{
				System.out.println("that ain't a number!");
				input.next();
			}
			N= input. nextInt();
			
			while(N<2)
			{
				System.out.println("invalid input, please enter a positive integer greater than 2:");
				N=input.nextInt();
			}
			System.out.println("\n"+nonCrossedOutSubseqToString(sieve(N)));
		}
		

		
		public static int[] createSequence(int N)
		{
														//createSequence simply makes an array of 1's of length N 
			if(N<2)System.out.println("N must be greater than 1");
			int[] sequence;		
			sequence = new int[N];
			for(int i=0;i<N;i++)
			{
				sequence[i]=1;							//initialise sequence to [1,1,1,...,1]
			}	
			return sequence;
		}
	
		public static int[] crossOutHigherMultiples(int[] sequence)

		{
															//This function converts each element in the array
			sequence[0]=0;									//which has a non-prime index (ie:index+1) 
			for(int i=1;i<=Math.sqrt(sequence.length);i++)	//from 1 to 0
			{
				
				if(sequence[i-1]==1)
				{
					for(int x= (i*i);x<=sequence.length;x+=i)
					{
						sequence[x-1]=0;
					}
				}
			}
			return sequence;
		}
		

		public static int[] sieve(int N){
			return crossOutHigherMultiples(createSequence(N));
		}
		
		public static String nonCrossedOutSubseqToString(int[] sequence)
		{
			int primeCount=0;								//The following code takes the array of 0's and 1's
			for(int i=0;i<sequence.length;i++)				//and adds the indexes of all 1's to a new array
			{												//These indexes (or indexes+1) will be the prime numbers
				if(sequence[i]==1)primeCount++;	
			}
			
			int[] subSeq = new int[primeCount];
			int count = 0;
			for(int i=0;i<sequence.length;i++)
			{
				if(sequence[i]==1)
				{
					subSeq[count]=i+1;
					count++;
				}
			}
			String subString = Arrays.toString(subSeq);		//The new array is then converted to a string
			return subString.substring(1, subString.length()-1);
		}
		
}
