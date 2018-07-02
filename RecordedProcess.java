import java.util.ArrayList;
import java.util.List;

//same as a process, but records information about itself as it runs
//such as when it finished and at what clock values it was running
public class RecordedProcess extends Process {
	//shared clock between all recorded processes
	//so they can record the times when they ran.
	private static int clock = 0;
	//record when the process finishes
	private int timeFinished;
	//list of clock ticks in which the process was running
	private List<Integer> whenRun;
	
	public RecordedProcess(String name, int timeToComplete) {
		super(name, timeToComplete);
		whenRun = new ArrayList<Integer>();
	}
	
	@Override
	public void step() {
		super.step();
		whenRun.add(clock);
		if (this.isDone()) {
			timeFinished = clock;
		}
		clock++;
	}
	
	public int GetTimeFinished() {
		if (!this.isDone()) {
			throw new RuntimeException(this.getProcessName() + "is not finished running");
		}
		else return timeFinished;
	}
	
	public List<Integer> GetWhenRun() {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < whenRun.size(); i++) {
			result.add(whenRun.get(i).intValue());
		}
		return result;
	}
}
