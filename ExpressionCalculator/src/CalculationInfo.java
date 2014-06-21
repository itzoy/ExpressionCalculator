
public class CalculationInfo {
	private long calculatingTime;
	private String result;
	
	public CalculationInfo(long calculationTime, String result){
		this.calculatingTime = calculationTime;
		this.result = result;
	}
	
	public long getCalculatingTime() {
		return calculatingTime;
	}
	public void setCalculatingTime(long calculatingTime) {
		this.calculatingTime = calculatingTime;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
