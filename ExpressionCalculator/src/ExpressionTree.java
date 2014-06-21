import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class ExpressionTree implements {
	private Node root;
	
	private void Calculate(Node currentNode){
		Operation operation = currentNode.GetOperation();
		if(operation == null){
			currentNode.GetData();
		}
		else{
			this.Calculate(currentNode.GetLeftNode());
			this.Calculate(currentNode.GetRightNode());
			int left = currentNode.GetLeftNode().GetData();
			int right = currentNode.GetRightNode().GetData();
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
	
	public int Calculate(int numberOfThreads, OutputStream os)
	{
		this.GetNodes(numberOfThreads);
	}
	
}
