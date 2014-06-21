import java.math.BigInteger;


public class MainClass {

	public static void main(String[] args) throws Exception {
		

		if (args.length > 7) {
			System.out.println("Too many arguments!");
			System.exit(1);
		}
		
		String inputFileName = "";
		String outputFileName = "result.txt";
		int t = 0;
		boolean q = false;
		
		for(int i = 0; i < args.length; i++){
			switch (args[i]) {
			case "-f":
				inputFileName = args[i + 1];
				break;
			case "-o":
				outputFileName = args[i + 1];
				break;
			case "-t":
				t = Integer.parseInt(args[i + 1]);
				break;
			case "-q":
				q = true;
				break;

			default:
				break;
			}
		}
		
		if (inputFileName == "") throw new Exception("Must have input file!");
		if (t == 0) throw new Exception("Tasks must be at least 1!");
		
		String result = Worker.CalculateFromFile(inputFileName, System.out, t);
		
		
		
	}

}