import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatContext;
import org.omg.CORBA.DynAnyPackage.InvalidValue;


public class Worker {
	public static CalculationInfo CalculateFromFile(String Path, OutputStream os, int numberOfThreads) throws IOException, InvalidValue, InvalidAlgorithmParameterException, InterruptedException
	{
		String expression = Utilities.ReadFile(Path, Charset.forName("utf-8"));
		
		return CalculateFromExpression(expression, null, numberOfThreads);
	}
	
	public static CalculationInfo CalculateFromExpression(String expression, OutputStream os, int numberOfThreads) throws InvalidValue, InvalidAlgorithmParameterException, IOException, InterruptedException
	{
		String postfix = Algorithms.CreatePostfixNotationFromInfix(expression);
		ExpressionTree tree = Algorithms.CreateExpressionTreeFromPostfix(postfix);
		
		ApfloatContext.getGlobalContext().setNumberOfProcessors(Constants.NUMBER_OF_PROCESSORS_FOR_APFLOAT);
		
		Apfloat result = tree.Calculate(numberOfThreads, os);
		
		long calculationTime = tree.getCalculationEnd() - tree.getCalculationStart();
		String resultInfo = result.toString();
		
		CalculationInfo resultInformation = new CalculationInfo(calculationTime, resultInfo);
		
		return resultInformation;
		
	}
}
