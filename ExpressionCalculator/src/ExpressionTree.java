import java.util.ArrayList;
import java.util.List;


public class ExpressionTree {
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
	
	public List<Node> GetNodes(int number)
	{
		List<Node> result = new ArrayList<Node>();
		
		return result;
	}
	
	public int Calculate()
	{
		throw new UnsupportedOperationException();
	}
	
}
