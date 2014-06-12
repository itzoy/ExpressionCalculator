
public class OperationNode extends Node {
	
	private Operation operation;
	private Node leftNode;
	private Node rightNode;
	
	public OperationNode(Operation operation){
		this.SetOperation(operation);
		this.leftNode = null;
		this.rightNode = null;
	}
	
	public Operation GetOperation(){
		return this.operation;
	}
	public void SetOperation(Operation value){
		this.operation = value;
	}
	
	public Node GetLeftNode(){
		return this.leftNode;
	}
	public void SetLeftNode(Node value){
		this.leftNode = value;
	}
	
	public Node GetRightNode(){
		return this.rightNode;
	}
	public void SetRightNode(Node value){
		this.rightNode = value;
	}
	
	
}
