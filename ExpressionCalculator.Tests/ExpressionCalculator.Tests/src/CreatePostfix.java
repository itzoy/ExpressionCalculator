import static org.junit.Assert.*;

import org.junit.Test;
import org.omg.CORBA.DynAnyPackage.InvalidValue;


public class CreatePostfix {

	@Test
	public void CreatePostfix_Test1() throws InvalidValue {
		String input = "5 + ((1 + 2) * 4) - 3";
		String expectedResult = "5 1 2 + 4 × + 3 -";
		
		String result = Algorithms.CreatePostfixNotationFromInfix(input);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void CreatePostfix_Test2() throws InvalidValue {
		String input = "3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3";
		String expectedResult = "3 4 2 * 1 5 - 2 3 ^ ^ / +";
		
		String result = Algorithms.CreatePostfixNotationFromInfix(input);
		assertEquals(expectedResult, result);
	}
	
}
