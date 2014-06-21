import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.omg.CORBA.DynAnyPackage.InvalidValue;

public class TestWorker {
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Test
	public void TestWorker_FromFile() throws InvalidValue, InvalidAlgorithmParameterException, IOException, InterruptedException {
		File createdFile = folder.newFile("input.txt");
		String path = createdFile.getAbsolutePath();
		String expression = "5 + ((1 + 2) * 4) - 3";
		Utilities.WriteToFile(path, expression);
		OutputStream os = null;
		int numberOfThreads = 1;
		String expectedResult = "14";
		
		CalculationInfo result = Worker.CalculateFromFile(path, os, numberOfThreads);
		assertEquals(expectedResult, result.getResult());
	}
	
	@Test
	public void TestWorker_FromExpression() throws InvalidValue, InvalidAlgorithmParameterException, IOException, InterruptedException{
		String expression = "356 + 42 * 2 / ( 11 - 5 )";
		OutputStream os = null;
		int numberOfThreads = 1;
		String expectedResult = "370";
		
		CalculationInfo result = Worker.CalculateFromExpression(expression, os, numberOfThreads);
		assertEquals(expectedResult, result.getResult());
	}
}
