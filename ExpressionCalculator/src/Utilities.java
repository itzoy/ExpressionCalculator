import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;


public class Utilities {
	static String readFile(String path, Charset encoding) throws IOException {
		  byte[] encoded = Files.readAllBytes(Paths.get(path));
		  return new String(encoded, encoding);
	}
	
	public String CreatePostfixNotationFromInfix(String infix) throws UnsupportedOperationException{
		Deque<Object> queue = new ArrayDeque<Object>();
		Deque<Operation> stack = new ArrayDeque<Operation>();
		
	}
	
	public ExpressionTree CreateExpressionTreeFromPostfix(String postfix) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
}
