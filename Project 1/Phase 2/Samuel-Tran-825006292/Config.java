//Samuel Tran
//CSCE 314-502
//UIN: 825006292
// This class is for all our configuration data. By putting it all in one place, we can easily make changes to the program without having to hunt down where in the code a constant is defined.

public class Config {
  public final static String DATAPATH = "C:\\Users\\samue\\Documents\\TAMU Classes\\Fall 2019\\CSCE 314 502\\Project 1\\Phase 2\\Fall2019-314-master(Phase2)\\data\\"; // If you don't know what the static keyword does, you better go look it up now.
  // Prime file: One prime per line.
  public final static String PRIMEFILENAME = "primes.txt";
  // Cross file: Two primes per line, separated by a comma.
  public final static String CROSSFILENAME = "crosses.txt";
  public final static String APPLICATIONNAME = "Hexagon Cross For Less";
}