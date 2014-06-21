import java.math.BigInteger;


public class MainClass {

	public static void main(String[] args) {
		

		if (args.length > 7) {
			System.out.println("Too many arguments!");
			System.exit(1);
		}
		
		String inputFileName;
		String outputFileName = "result.txt";
		int t;
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
		
		String result = Worker.CalculateFromFile(inputFileName, System.out);
		
		int thread_count = new Integer(args[1]);
		
		int chunk_size = el_count / thread_count;

		long a[] = new long[el_count];
		BigInteger res[] = new BigInteger[thread_count];
	
		Thread tr[] = new Thread[thread_count];
		
		for(int i = 0; i < thread_count; i++) {

			SumRunnable r = new SumRunnable(a, res, chunk_size, i, thread_count);
			Thread t = new Thread(r);
			tr[i] = t;
			t.start();
		
		}
		
		BigInteger sum = BigInteger.valueOf(0);
		// BigInteger sum = BigInteger.valueOf(1);
		for(int i = 0; i < thread_count; i++) {
			
			try {
				
				tr[i].join();
				sum = sum.add(res[i]);
				// sum = sum.multiply(res[i]);
			} catch (InterruptedException e) {
				
			}
			
		}
		
		System.out.println("result: " + sum);
		
	}

}