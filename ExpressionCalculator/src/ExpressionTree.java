public class ExpressionTree implements Runnable {
	private Node root;
	
	private int Calculate(Node currentNode){
		if(currentNode instanceof DataNode){
			return((DataNode)currentNode).GetData();
		}
		else{
			OperationNode node = (OperationNode)currentNode;
			int left = this.Calculate(node.GetLeftNode());
			int right = this.Calculate(node.GetRightNode());
			switch(node.GetOperation()){
			case ADDITION: 
				return left + right;
			case SUBTRACTION:
				return left - right;
			case MULTIPLICATION:
				return left*right;
			case DIVISION:
				return left/right;
			default:
				throw new UnsupportedOperationException();
			}
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
	
	public int Calculate(){
		return this.Calculate(this.root);
	}

	@Override
	public void run() {
				
	}
	
	
	
	
}
