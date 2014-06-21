import static org.junit.Assert.*;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Queue;

import org.apfloat.Apfloat;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.DynAnyPackage.InvalidValue;


public class ExpressionTreeCalculations {
	
	ExpressionTree tree;
	Node plus1;
	Node plus2;
	Node mul1;
	Node root;
	
	@Before
	public void InitializeExpressionTree(){
		Node node1 = new Node(new Apfloat(1, Constants.PRECISION_OF_APFLOAT));
		Node node2 = new Node(new Apfloat(2, Constants.PRECISION_OF_APFLOAT));
		Node node3 = new Node(new Apfloat(3, Constants.PRECISION_OF_APFLOAT));
		Node node4 = new Node(new Apfloat(4, Constants.PRECISION_OF_APFLOAT));
		Node node5 = new Node(new Apfloat(5, Constants.PRECISION_OF_APFLOAT));
		
		plus1 = new Node(Operation.ADDITION, node1, node2);
		plus2 = new Node(Operation.ADDITION, node4, node5);
		
		mul1 = new Node(Operation.MULTIPLICATION, node3, plus2);
		root = new Node(Operation.MULTIPLICATION, plus1, mul1);
		
		tree = new ExpressionTree(root);
	}
	
	
	@Test
	public void CalculateExpressionTree() throws InvalidAlgorithmParameterException, IOException, InterruptedException {
		Apfloat expectedResult = new Apfloat(81);
		
		Apfloat result = tree.Calculate(1, null);
		
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void GetAppropriateNodesFromExpressionTree() throws InvalidAlgorithmParameterException, IOException, InterruptedException {
		
		int numberOfThreads = 2;
		
		Queue<Node> nodes = tree.GetNodes(numberOfThreads);
		
		assertTrue(nodes.contains(plus1));
		assertTrue(nodes.contains(mul1));
	}
	
	@Test
	public void GetAppropriateNodesFromExpressionTree_threeThreads() throws InvalidAlgorithmParameterException, IOException, InterruptedException {
		
		int numberOfThreads = 3;
		
		Queue<Node> nodes = tree.GetNodes(numberOfThreads);
		
		assertTrue(nodes.contains(plus1));
		assertTrue(nodes.contains(mul1));
		
	}
	
	@Test
	public void Test() throws InvalidValue, IOException, InterruptedException, InvalidAlgorithmParameterException{
		String input = "5 + ((1 + 2) * 4) - 3";
		String postfix = Algorithms.CreatePostfixNotationFromInfix(input);
		
		ExpressionTree currentTree = Algorithms.CreateExpressionTreeFromPostfix(postfix);
		Apfloat result = currentTree.Calculate(1, null);
		
		Apfloat expectedResult = new Apfloat(14, Constants.PRECISION_OF_APFLOAT);
		
		assertEquals(expectedResult, result);
		
	}
	
	@Test
	public void TestWithTwoThreadsAndResultAsString() throws InvalidValue, IOException, InterruptedException, InvalidAlgorithmParameterException{
		String input = "356 + 42 * 2 / ( 11 - 5 )";
		String postfix = Algorithms.CreatePostfixNotationFromInfix(input);
		
		ExpressionTree currentTree = Algorithms.CreateExpressionTreeFromPostfix(postfix);
		Apfloat result = currentTree.Calculate(2, null);
		
		String expectedResult = "370"; 
		
		assertEquals(expectedResult, result.toString(true));
		
	}
	
}
