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
	
	@Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        
        Node other = (Node) obj; 
        
        if(this.operation != other.operation){
        	return false;
        }
        
        if((this.data == null) != (other.data == null)){
        	return false;
        }
        
        if(this.data != null && other.data != null && this.data.equals(other.data) == false){
        	return false;
        }
        
        boolean leftEquals = false;
        if(this.leftNode == null && other.leftNode == null){
        	leftEquals = true;
        }
        else if(this.leftNode != null && other.leftNode != null){
        	leftEquals = this.leftNode.equals(other.leftNode);
        }
        
        boolean rightEquals = false;
        if(this.rightNode == null && other.rightNode == null){
        	rightEquals = true;
        }
        else if(this.rightNode != null && other.rightNode != null){
        	rightEquals = this.rightNode.equals(other.rightNode);
        }
        
        return (leftEquals && rightEquals);
	}
}
