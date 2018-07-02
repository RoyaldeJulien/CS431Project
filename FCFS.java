import java.util.List;

//first-come first-serve
public class FCFS implements CPUScheduler {
	@Override
	public void executeProcesses(List<Process> jobs) {
		for (Process p : jobs) {
			p.runToCompletion();
		}
	}
}
