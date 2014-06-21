import static org.junit.Assert.*;

import java.security.InvalidAlgorithmParameterException;

import org.apfloat.Apfloat;
import org.junit.Test;


public class CreateExpressionTree {

	@Test
	public void CreateExpressionTreeFromPostfix() throws InvalidAlgorithmParameterException {
		String input = "1 2 + 3 4 5 + * *";
		Node node1 = new Node(new Apfloat(1, Constants.PRECISION_OF_APFLOAT));
		Node node2 = new Node(new Apfloat(2, Constants.PRECISION_OF_APFLOAT));
		Node node3 = new Node(new Apfloat(3, Constants.PRECISION_OF_APFLOAT));
		Node node4 = new Node(new Apfloat(4, Constants.PRECISION_OF_APFLOAT));
		Node node5 = new Node(new Apfloat(5, Constants.PRECISION_OF_APFLOAT));
		
		Node plus1 = new Node(Operation.ADDITION, node1, node2);
		Node plus2 = new Node(Operation.ADDITION, node4, node5);
		
		Node mul1 = new Node(Operation.MULTIPLICATION, node3, plus2);
		Node root = new Node(Operation.MULTIPLICATION, plus1, mul1);
		
		ExpressionTree tree = new ExpressionTree(root);
		
		ExpressionTree result = Algorithms.CreateExpressionTreeFromPostfix(input);
		
		assertEquals(tree, result);
	}

}
