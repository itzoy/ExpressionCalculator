import java.io.OutputStream;
import java.util.Calendar;


public class CalculateNode implements Runnable {

	private Node node;
	private OutputStream os;
	private long startTime;
	private long endTime;
	
	public CalculateNode(Node node, OutputStream os){
		this.setNode(node);
		this.setOs(os);
	}
	
	@Override
	public void run() {
		this.setStartTime(Calendar.getInstance().getTimeInMillis());
		ExpressionTree.Calculate(this.getNode());
		this.setEndTime(Calendar.getInstance().getTimeInMillis());
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public OutputStream getOs() {
		return os;
	}

	public void setOs(OutputStream os) {
		this.os = os;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

}
