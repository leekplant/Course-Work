//Samuel Tran
//CSCE 314-502
//UIN: 825006292
// This file gives access to the underlying datafile and stores the data in the Workout class.
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Scanner;

public class FileAccess {
  public static boolean loadPrimes(Primes primes, String filename) {
	  if (!filename.contains(".txt")){
		  return false;
	  }
	  primes.clearPrimes();
	  BigInteger prime = null;
	  try {
		  File file = new File(Config.DATAPATH + filename);
		  Scanner sc = new Scanner(file);
		  while(sc.hasNext()) {
			  prime = sc.nextBigInteger();
			  primes.addPrime(prime);
		  }
		  sc.close();
	  }	catch (IOException e) {
		  e.printStackTrace();
		  return false;
	  }
	  
	  return true;
  }
  
  public static boolean loadCrosses(Primes primes, String filename) {
	  primes.clearCrosses();
	  try {
		  if (!filename.contains(".txt")){
			  return false;
		  }
		  File file = new File(Config.DATAPATH + filename);
		  Scanner sc = new Scanner(file);
		  String str = "";
		  while(sc.hasNextLine()) {			//Reads in Cross file and splits Big Integer separated by a comma
			  str = sc.nextLine();
			  String[] crossLine = str.split(",");
			  BigInteger num1 = new BigInteger(crossLine[0]);
			  BigInteger num2 = new BigInteger(crossLine[1]);
			  Pair<BigInteger> crossPair = new Pair<BigInteger>(num1, num2);
			  primes.addCross(crossPair);
		  }
		  sc.close();
	  }	catch (IOException e) {
		  e.printStackTrace();
		  return false;
	  }
	  
    return true;
	}
  
  public static boolean savePrimes(Primes primes, String filename)
  {
	  try {
		  File outFile = new File (Config.DATAPATH + filename);
		  FileWriter fWriter = new FileWriter(outFile);
		  PrintWriter pWriter = new PrintWriter(fWriter);
		  for(BigInteger primeOut : primes.iteratePrimes()) {
			  pWriter.println(primeOut);
		  }
		  pWriter.close();
		  
	  } catch (IOException e) {
		  e.printStackTrace();
		  return false;
	  }
  	return true;
  }
  
  public static boolean saveCrosses(Primes primes, String filename)
  {
	  try {
		  File outFile = new File (Config.DATAPATH + filename);
		  FileWriter fWriter = new FileWriter(outFile);
		  PrintWriter pWriter = new PrintWriter(fWriter);
		  for(Pair<BigInteger> crossOut : primes.iterateCrosses()) {
			  pWriter.println(crossOut.left() + "," + crossOut.right());
		  }
		  pWriter.close();
		  
	  } catch (IOException e) {
		  e.printStackTrace();
		  return false;
	  }
  	return true;
  }
}