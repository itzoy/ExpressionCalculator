public class ExpressionTree {
	private Node root;
	
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
		throw new UnsupportedOperationException();
	}
	
}
