import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;


public class Utilities {
	static String readFile(String path, Charset encoding) throws IOException {
		  byte[] encoded = Files.readAllBytes(Paths.get(path));
		  return new String(encoded, encoding);
	}
	
	public Deque<Object> CreatePostfixNotationFromInfix(String infix) throws UnsupportedOperationException{
		Deque<Object> queue = new ArrayDeque<Object>();
		Stack<Character> stack = new Stack<Character>();
		
		int currentNumber = 0;
		boolean numberIsSet = false;
		int length = infix.length();
		for(int i = 0; i < length; i++){
			char currentChar = infix.charAt(i);
			if(currentChar >= '0' && currentChar <= '9'){
				currentNumber *= 10;
				currentNumber += currentNumber - '0';
				numberIsSet = true;
			}
			else if(numberIsSet){
				queue.push(currentNumber);
				currentNumber = 0;
				numberIsSet = false;
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
			}
		}
		
		return queue;
	}
	
	public ExpressionTree CreateExpressionTreeFromPostfix(String postfix) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
}
