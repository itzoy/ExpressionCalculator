import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.Stack;

import org.omg.CORBA.DynAnyPackage.InvalidValue;


public class Algorithms {
	public static String CreatePostfixNotationFromInfix(String infix) throws InvalidValue{
		Deque<Object> queue = new ArrayDeque<Object>();
		Stack<Character> stack = new Stack<Character>();
		
		int length = infix.length();
		for(int i = 0; i < length; i++){
			char currentChar = infix.charAt(i);
			if(currentChar >= '0' && currentChar <= '9'){
				queue.push(currentChar);
			}
			else{
				if(currentChar == '('){
					stack.push(currentChar);
				}
				else if(currentChar == ')'){
					char stackChar;
					do
					{
						stackChar = stack.pop();
						if(stackChar != '('){
							queue.push(stackChar);
			 			}
					}while(stackChar != '(');
				}
				else if(currentChar == '+' || currentChar == '-' ||currentChar == '*' ||currentChar == '/'){
					int operatorPriority = OperatorPriority.Priority(currentChar);
					while(stack.empty() == false && OperatorPriority.Priority(stack.peek()) > operatorPriority)
					{
						queue.push(stack.pop());
					}
					stack.push(currentChar);
				}
				else {
					throw new InvalidValue("Invalid symbol !!!");
				}
			}
		}
		
		if(stack.empty() == false){
			while(stack.empty() == false){
				char currentChar = stack.pop();
				if(currentChar == '('){
					throw new InputMismatchException("Parenthesis not valid !!!");
					}
				queue.push(currentChar);
			}
		}
		
		return queue.toString();
	}
	
	public static ExpressionTree CreateExpressionTreeFromPostfix(String postfix) throws InvalidAlgorithmParameterException{
		int length = postfix.length();
		Stack<Node> stack = new Stack<Node>();
		int currentNumber = 0;
		char currentChar;
		boolean numberIsSet = false;
		for(int i = 0; i < length; i++){
			currentChar = postfix.charAt(i);
			if(currentChar >= '0' && currentChar <= '9'){
				currentNumber *= 10;
				currentNumber += currentChar - '0';
				numberIsSet = true;
			}
			else
			{
				if(numberIsSet){
					stack.push(new Node(currentNumber));
					currentNumber = 0;
					numberIsSet = false;
				}
				Operation operation;
				switch (currentChar) {
				case '+':
					operation = Operation.ADDITION;
					break;
				case '-':
					operation = Operation.SUBTRACTION;
					break;
				case '*':
					operation = Operation.MULTIPLICATION;
					break;
				case '/':
					operation = Operation.DIVISION;
					break;
				case ' ':
					continue;
				default:
					throw new InvalidParameterException();
				}
				
				Node firstNode = stack.pop();
				Node secondNode = stack.pop();
				stack.push(new Node(operation, firstNode, secondNode));
			}
		}
		
		Node root = stack.pop();
		if(stack.empty() == false){
			throw new InvalidAlgorithmParameterException("Stack should be empty !!!");
		}
		return new ExpressionTree(root);
	}
}
