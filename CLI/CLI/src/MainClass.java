import java.io.OutputStream;


public class MainClass {

	public static void main(String[] args) throws Exception {
		

		if (args.length > 9) {
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
			case "-p":
				Constants.PRECISION_OF_APFLOAT = Integer.parseInt(args[i + 1]);
				break;

			default:
				break;
			}
		}
		
		if (inputFileName == "") {
			throw new Exception("Must have input file!");
		}
		
		if (t == 0) {
			throw new Exception("Tasks must be at least 1!");
		}
		
		OutputStream os = (q)? null : System.out;
		
		CalculationInfo result = Worker.CalculateFromFile(inputFileName, os, t);
		
		System.out.println("Execution time: " + result.getCalculatingTime() + " ms");
		
		Utilities.WriteToFile(outputFileName, result.getResult());
		
		
	}

}
