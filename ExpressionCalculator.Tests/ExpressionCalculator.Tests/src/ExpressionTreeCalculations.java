import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;

import org.apfloat.Apfloat;
import org.junit.Test;


public class ExpressionTreeCalculations {
	@Test
	public void CalculateExpressionTree() throws InvalidAlgorithmParameterException, IOException, InterruptedException {
		Node node1 = new Node(new Apfloat(1, Constants.PRECISION_OF_APFLOAT));
		Node node2 = new Node(new Apfloat(2, Constants.PRECISION_OF_APFLOAT));
		Node node3 = new Node(new Apfloat(3, Constants.PRECISION_OF_APFLOAT));
		Node node4 = new Node(new Apfloat(4, Constants.PRECISION_OF_APFLOAT));
		Node node5 = new Node(new Apfloat(5, Constants.PRECISION_OF_APFLOAT));
		
		Node plus1 = new Node(Operation.ADDITION, node1, node2);
		Node plus2 = new Node(Operation.ADDITION, node4, node5);
		
		Node mul1 = new Node(Operation.MULTIPLICATION, node3, plus2);
		Node root = new Node(Operation.MULTIPLICATION, plus1, mul1);
		
		Apfloat expectedResult = new Apfloat(81);
		
		ExpressionTree tree = new ExpressionTree(root);
		
		Apfloat result = tree.Calculate(1, null);
		
		assertEquals(expectedResult, result);
	}
}
