import org.apfloat.Apfloat;

public class Node {
	
	private Operation operation;
	private Node leftNode;
	private Node rightNode;
	private Apfloat data;
	
	public Node(Operation operation, Node leftNode, Node rightNode){
		this.SetOperation(operation);
		this.SetLeftNode(leftNode);
		this.SetRightNode(rightNode);
	}
	
	public Node(Apfloat data){
		this.SetData(data);
		this.SetOperation(null);
		this.SetLeftNode(null);
		this.SetRightNode(null);
	}
	
	public Apfloat GetData(){
		return this.data;
	}
	
	public void SetData(Apfloat value){
		this.data = value;
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
