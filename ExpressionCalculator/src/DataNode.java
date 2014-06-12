
public class DataNode extends Node {
	private int data;
	
	public DataNode(int data){
		this.SetData(data);
	}
	
	public int GetData(){
		return this.data;
	}
	
	public void SetData(int value){
		this.data = value;
	}
}
