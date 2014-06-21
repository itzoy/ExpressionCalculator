import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;

import org.apfloat.Apfloat;


public class ExpressionTree{
	private Node root;
	
	private long calculationStart;
	
	private long calculationEnd;
	
	public static Apfloat Calculate(Node currentNode){
		Operation operation = currentNode.GetOperation();
		if(operation == null){
			return currentNode.GetData();
		}
		else{
			Apfloat left = Calculate(currentNode.GetLeftNode());
			Apfloat right = Calculate(currentNode.GetRightNode());
			switch(currentNode.GetOperation()){
			case ADDITION: 
				currentNode.SetData(left.add(right));
				break;
			case SUBTRACTION:
				currentNode.SetData(left.subtract(right));
				break;
			case MULTIPLICATION:
				currentNode.SetData(left.multiply(right));
				break;
			case DIVISION:
				currentNode.SetData(left.divide(right));
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
	
	public long getCalculationStart() {
		return calculationStart;
	}

	public void setCalculationStart(long calculationStart) {
		this.calculationStart = calculationStart;
	}

	public long getCalculationEnd() {
		return calculationEnd;
	}

	public void setCalculationEnd(long calculationEnd) {
		this.calculationEnd = calculationEnd;
	}
	
	protected Queue<Node> GetNodes(int number){
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
				result.add(leftNode);
				result.add(rightNode);
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
	
	public Apfloat Calculate(int numberOfThreads, OutputStream os) throws IOException, InterruptedException
	{
		this.setCalculationStart(Calendar.getInstance().getTimeInMillis());
		
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
			threads[i].join();
			long timeExecution = calculations[i].getEndTime() - calculations[i].getStartTime();
			String outputMessage = String.format("Thread %s worked %d ms", threads[i].getName(), timeExecution);
			if(os != null){
				os.write(outputMessage.getBytes(Charset.forName("UTF-8")));
				os.flush();
			}
		}
		
		Apfloat result = ExpressionTree.Calculate(this.root);
		
		this.setCalculationEnd(Calendar.getInstance().getTimeInMillis());
		
		return result;
	}
	
  @Override
	    public boolean equals(Object obj) {
	        if (obj == this) {
	            return true;
	        }
	        if (obj == null || obj.getClass() != this.getClass()) {
	            return false;
	        }
	        
	        ExpressionTree other = (ExpressionTree) obj;
	        
	        return this.root.equals(other.root);
  	}
}
