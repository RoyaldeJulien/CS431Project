
public class Process implements Comparable<Process> {
	
	private String processName;
	private int remainingTime;
	
	public String getProcessName() { return processName; }
	public int getRemainingTime()  { return remainingTime; }
	
	public Process(String name, int timeToComplete) {
		this.processName = name;
		if (timeToComplete <= 0) {
			throw new RuntimeException();
		}
		this.remainingTime = timeToComplete;
	}
	
	public void step() {
		if (this.isDone()) {
			throw new RuntimeException();
		}
		remainingTime--;
	}
	
	public void runToCompletion() {
		while(!this.isDone()) {
			this.step();
		}
	}
	
	public boolean isDone() {
		return remainingTime == 0;
	}
	
	@Override
	public int compareTo(Process other) {
		return this.remainingTime - other.remainingTime;
	}
}
