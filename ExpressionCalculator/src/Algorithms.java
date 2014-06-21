import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.util.InputMismatchException;
import java.util.Stack;

import org.apfloat.Apfloat;
import org.omg.CORBA.DynAnyPackage.InvalidValue;


public class Algorithms {
	private static void PushSymbol(Character ch, StringBuilder queue){
		if(ch == '+' || ch == '-' || ch == '*' || ch == '/'){
			if(queue.length() > 0 && queue.charAt(queue.length() - 1) != ' '){
				queue.append(' ');
			}
			queue.append(ch);
		}
		else if(ch == ' '){
			if(queue.length() > 0 && queue.charAt(queue.length() - 1) != ' '){
				queue.append(ch);
			}
		}
		else{
			queue.append(ch);
		}
	}
	
	public static String CreatePostfixNotationFromInfix(String infix) throws InvalidValue{
		StringBuilder queue = new StringBuilder();
		Stack<Character> stack = new Stack<Character>();
		
		int length = infix.length();
		for(int i = 0; i < length; i++){
			char currentChar = infix.charAt(i);
			if((currentChar >= '0' && currentChar <= '9') || currentChar == ' '){
				PushSymbol(currentChar, queue);
			}
			else{
				PushSymbol(' ', queue);
				if(currentChar == '('){
					stack.push(currentChar);
				}
				else if(currentChar == ')'){
					char stackChar;
					do
					{
						stackChar = stack.pop();
						if(stackChar != '('){
							PushSymbol(stackChar, queue);
			 			}
					}while(stackChar != '(');
				}
				else if(currentChar == '+' || currentChar == '-' ||currentChar == '*' ||currentChar == '/'){
					int operatorPriority = OperatorPriority.Priority(currentChar);
					while(stack.empty() == false && OperatorPriority.Priority(stack.peek()) >= operatorPriority)
					{
						if (stack.peek() != '('){
							PushSymbol(stack.pop(), queue);
						}
						else {
							break;
						}
					}
					stack.push(currentChar);
				}
				else {
					throw new InvalidValue("Invalid symbol !!!" + currentChar);
				}
			}
		}
		
		if(stack.empty() == false){
			while(stack.empty() == false){
				char currentChar = stack.pop();
				if(currentChar == '('){
					throw new InputMismatchException("Parenthesis not valid !!!");
					}
				PushSymbol(currentChar, queue);
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
					stack.push(new Node(new Apfloat(currentNumber, Constants.PRECISION_OF_APFLOAT)));
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
				
				Node rightNode = stack.pop();
				Node leftNode = stack.pop();
				stack.push(new Node(operation, leftNode, rightNode));
			}
		}
		
		Node root = stack.pop();
		if(stack.empty() == false){
			throw new InvalidAlgorithmParameterException("Stack should be empty !!!");
		}
		return new ExpressionTree(root);
	}
}
