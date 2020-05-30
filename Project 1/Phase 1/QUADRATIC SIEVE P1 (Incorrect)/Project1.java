//Samuel Tran UIN:825006292
public class Project1 {
	public static void main(String[] args) 
	{
		// Instantiate Primes Class
		PrimeOperations testOne = new PrimeOperations();
		
		// Generate Primes and test.
		testOne.generatePrimes(21);
		testOne.printPrimes();
		
		// Generate and test Twin Primes
		PrimeOperations testTwo = new PrimeOperations();
		testTwo.generatePrimes(100);
		testTwo.generateTwinPrimes();
		testTwo.printTwins();
		
		// Generate and test Hexagonal crosses
		PrimeOperations testThree = new PrimeOperations();
		testThree.generatePrimes(2000);
		testThree.generateTwinPrimes();
		testThree.generateHexPrimes();
		testThree.printHexes();
		
		// Instantiate Primes Class
		PrimeOperations testOne2 = new PrimeOperations();
				
		// Generate Primes and test.
		testOne2.generatePrimes(71);
		testOne2.printPrimes();
				
		// Generate and test Twin Primes
		PrimeOperations testTwo2 = new PrimeOperations();
		testTwo2.generatePrimes(523);
		testTwo2.generateTwinPrimes();
		testTwo2.printTwins();
		
		// Generate and test Hexagonal crosses
		PrimeOperations testThree2 = new PrimeOperations();
		testThree2.generatePrimes(12541);
		testThree2.generateTwinPrimes();
		testThree2.generateHexPrimes();
		testThree2.printHexes();
	}
}
