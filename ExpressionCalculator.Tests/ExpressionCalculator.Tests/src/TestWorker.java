import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;

import org.junit.Test;
import org.omg.CORBA.DynAnyPackage.InvalidValue;

public class TestWorker {
	
	@Test
	public void TestWorker_FromFile() throws InvalidValue, InvalidAlgorithmParameterException, IOException, InterruptedException {
		String path = "/home/maria/Desktop/input.txt";
		OutputStream os = null;
		int numberOfThreads = 1;
		String expectedResult = "14";
		
		CalculationInfo result = Worker.CalculateFromFile(path, os, numberOfThreads);
		assertEquals(expectedResult, result.getResult());
		
	}
}
