import java.util.ArrayList;
import java.util.List;

public class RR implements CPUScheduler {
	private int quantum;
	
	public RR(int val) {
		if (val <= 0) throw new RuntimeException();
		else quantum = val;
	}
	
	@Override
	public void executeProcesses(List<Process> jobs) {
		List<Process> jobscp = new ArrayList<>(jobs);
		
		while(!jobscp.isEmpty()) {
			Process currentProcess = jobscp.remove(0);
			for (int i = 0; i < quantum && !currentProcess.isDone(); i++) {
				currentProcess.step();
			}
			if (!currentProcess.isDone()) {
				jobscp.add(currentProcess);
			}
		}
	}
}
