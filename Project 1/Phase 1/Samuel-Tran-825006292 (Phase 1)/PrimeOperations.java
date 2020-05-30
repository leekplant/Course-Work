//Samuel Tran UIN:825006292
/*
 * Resources: “3.7.2 The Quadratic Sieve.” Introduction to Mathematical Cryptography, by JEFFREY HOFFSTEIN, SPRINGER-VERLAG NEW YORK, 2016, pp. 155–162.
 *  
 */
import java.util.ArrayList; 
import java.math.BigInteger;
/*
 *  Desc: This class generates primes, twin primes, and hexagon crosses using BigInteger data types.
 */
public class PrimeOperations {
	
	// Pair class implementation.
	private class Pair<T> {
		private T value1;
		private T value2;
		
		private Pair(T value1, T value2) { 
			this.value1 = value1;
			this.value2 = value2;
		}
		
		public T getValue1() { return value1; }
		
		public T getValue2() { return value2; }
		
		//public void setValue1() { this.value1 = value1; }
		
		//public void setValue2() { this.value2 = value2; }
	}
	
	// Member variables for containing out lists of integers, twin primes, hexagon crosses, and the pairs of twin primes that make up the hex crosses.
	public ArrayList<BigInteger> list = new ArrayList<BigInteger>();
	public ArrayList<Pair<BigInteger>> pairList = new ArrayList<Pair<BigInteger>>(); 
	public ArrayList<Pair<Pair<BigInteger>>> hexList = new ArrayList<Pair<Pair<BigInteger>>>();
	
	// Add a prime to the prime list if and only iff it is not already in the list. (ignore duplicates)
	public void addPrime(BigInteger x)
	{
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i) != x) { list.add(x); }
			else { return; }
		}
	}
	
	// Output the prime list. Each prime should be on a separate line and the total number of primes should be on the following line.
	public void printPrimes()
	{
		int numPrimes = 0;
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			numPrimes++;
		}
		System.out.println("Total Primes: " + numPrimes);
	}
		
	// Output the twin prime list. Each twin prime should be on a separate line with a comma separating them, and the total number of twin primes should be on the following line.
	public void printTwins()
	{
		int numTwins = 0;
		for(int i = 0; i < pairList.size(); i++) {
			System.out.println(pairList.get(i).getValue1() + ", " + pairList.get(i).getValue2());
			numTwins++;
		}
		System.out.println("Total Twins: " + numTwins);
	}
		
	// Output the hexagon cross list. Each should be on a separate line listing the two twin primes and the corresponding hexagon cross, with the total number on the following line.
	public void printHexes()
	{
		int numHex = 0;
		for(int i = 0; i < hexList.size(); i++) {
			BigInteger L1 = hexList.get(i).getValue1().getValue1();
			BigInteger R1 = hexList.get(i).getValue1().getValue2();
			
			BigInteger L2 = hexList.get(i).getValue2().getValue1();
			BigInteger R2 = hexList.get(i).getValue2().getValue2();
			
			BigInteger add1 = L1.add(R1);
			BigInteger add2 = L2.add(R2);
			
			BigInteger mid1 = add1.divide(BigInteger.valueOf(2));
			BigInteger mid2 = add2.divide(BigInteger.valueOf(2));
			System.out.println("Prime Pairs: " + L1 + ", " + R1 + " and " + L2 + ", " + R2 + " seperated by " + mid1 + ", " + mid2);
			numHex++;
		}
		System.out.println("Total Hexes: " + numHex);
	}
		
	// Generate and store a list of primes.
	public void generatePrimes(int count)
	{
		int iter = 0;
		for (int i = 2; iter < count; i++) {	//Starting at prime = 2, we determine which integers are prime and add it to prime list
			boolean isPrime = true;
			for (int j = 2; j <= i/2; j++) {
				if (i % j == 0) {
					isPrime = false;
				}
			}
			
			if(isPrime == true) {
				list.add(BigInteger.valueOf(i));
				iter++;
			}
		}
	}
	
	// Generate and store a list of twin primes.
	public void generateTwinPrimes()
	{
		for (int i = 0; i < list.size() - 1; i++) {
			BigInteger val1 = list.get(i);
			BigInteger val2 = list.get(i+1);			
			BigInteger diff = val2.subtract(val1);
			if(diff.equals(BigInteger.valueOf(2))) {	//If the difference of two primes is equal to 2 then they are twin primes
				Pair<BigInteger> p = new Pair<BigInteger>(val1, val2);
				pairList.add(p);
			}
		}
	}
	
	// Generate and store the hexagon crosses, along with the two twin primes that generate the hexagon cross.
	public void generateHexPrimes()
	{
		for(int i = 0; i < pairList.size() - 1; i++) {
			BigInteger val1 = pairList.get(i).getValue1();
			BigInteger val2 = pairList.get(i).getValue2();
			BigInteger mid1 =  val1.add(val2);
			for(int j = 1; j < pairList.size(); j++) {
				BigInteger val3 = pairList.get(j).getValue1();
				BigInteger val4 = pairList.get(j).getValue2();
				BigInteger add1 = val3.add(val4);
				BigInteger mid2 = add1.divide(BigInteger.valueOf(2));
				if(mid1.equals(mid2)) {
					Pair<Pair<BigInteger>> h = new Pair<Pair<BigInteger>>(pairList.get(i), pairList.get(j));
					hexList.add(h);
				}
			}
		}
	}
}
