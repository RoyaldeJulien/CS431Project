import java.util.HashMap;
import java.util.Map;

//same as a process, but records information about itself as it runs
//such as when it finished and at what clock values it was running
public class RecordedProcess extends Process {
	//shared clock between all recorded processes
	//so they can record the times when they ran.
	private static int clock = 0;
	//record when the process finishes
	private int timeFinished;
	//maps clock ticks in which the process ran to true
	private Map<Integer, Boolean> whenRun;
	
	public RecordedProcess(String name, int timeToComplete) {
		super(name, timeToComplete);
		whenRun = new HashMap<>();
	}
	
	@Override
	public void step() {
		super.step();
		whenRun.put(clock, true);
		if (this.isDone()) {
			timeFinished = clock + 1;
		}
		clock++;
	}
	
	public int GetTimeFinished() {
		if (!this.isDone()) {
			throw new RuntimeException(this.getProcessName() + "is not finished running");
		}
		else return timeFinished;
	}
	
	public boolean ranAt(int time) {
		return whenRun.containsKey(time);
	}
	
	public static void resetClock() {
		clock = 0;
	}
}
