import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;

import org.omg.CORBA.DynAnyPackage.InvalidValue;


public class Worker {
	public static String CalculateFromFile(String Path) throws IOException, InvalidValue, InvalidAlgorithmParameterException
	{
		String expression = Utilities.ReadFile(Path, Charset.forName("utf-8"));
		
		return CalculateFromExpression(expression);
	}
	
	public static String CalculateFromExpression(String expression) throws InvalidValue, InvalidAlgorithmParameterException
	{
		String postfix = Algorithms.CreatePostfixNotationFromInfix(expression);
		ExpressionTree tree = Algorithms.CreateExpressionTreeFromPostfix(postfix);
		
		Integer result = tree.Calculate();
		
		return result.toString();
		
	}
}