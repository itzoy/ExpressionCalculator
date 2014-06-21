import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.Queue;


public class ExpressionTree{
	private Node root;
	
	public static int Calculate(Node currentNode){
		Operation operation = currentNode.GetOperation();
		if(operation == null){
			return currentNode.GetData();
		}
		else{
			int left = Calculate(currentNode.GetLeftNode());
			int right = Calculate(currentNode.GetRightNode());
			switch(currentNode.GetOperation()){
			case ADDITION: 
				currentNode.SetData(left + right);
				break;
			case SUBTRACTION:
				currentNode.SetData(left - right);
				break;
			case MULTIPLICATION:
				currentNode.SetData(left*right);
				break;
			case DIVISION:
				currentNode.SetData(left/right);
				break;
			default:
				throw new UnsupportedOperationException();
			}
			
			currentNode.SetOperation(null);
			return currentNode.GetData();
		}
	}
	
	public ExpressionTree(Node root){
		this.SetRoot(root);
	}
	
	public Node GetRoot(){
		return this.root;
	}
	public void SetRoot(Node value){
		this.root = value;
	}
	
	public Queue<Node> GetNodes(int number){
		int repeats = 0;
		Queue<Node> result = new LinkedList<Node>();
		result.add(this.GetRoot());
		number--;
		
		while(number > 0 && repeats < result.size()){
			Node frontNode = result.poll();
			Node leftNode = frontNode.GetLeftNode();
			Node rightNode = frontNode.GetRightNode();
			if(leftNode != null && leftNode.GetOperation() != null 
					&& rightNode != null && rightNode.GetOperation() != null){
				result.add(frontNode.GetLeftNode());
				result.add(frontNode.GetRightNode());
				number --;
				repeats = 0;
			}
			else{
				result.add(frontNode);
				repeats ++;
			}
		}
		
		return result;
	}
	
	public int Calculate(int numberOfThreads, OutputStream os) throws IOException
	{
		Queue<Node> nodes = this.GetNodes(numberOfThreads);
		Thread threads[] = new Thread[nodes.size()];
		CalculateNode calculations[] = new CalculateNode[nodes.size()];
		int index = 0;
		while(nodes.isEmpty() == false)
		{
			Node currentNode = nodes.poll();
			calculations[index] = new CalculateNode(currentNode, os);
			Thread currentThread = new Thread(calculations[index]);
			threads[index] = currentThread;
			currentThread.start();
			index++;
		}
		
		for(int i = 0; i < threads.length; i++){
			try {
				threads[i].join();
				long timeExecution = calculations[i].getEndTime() - calculations[i].getStartTime();
				String outputMessage = String.format("Thread %s worked %d ms", threads[i].getName(), timeExecution);
				os.write(outputMessage.getBytes(Charset.forName("UTF-8")));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		int result = ExpressionTree.Calculate(this.root);
		
		return result;
	}
	
}
