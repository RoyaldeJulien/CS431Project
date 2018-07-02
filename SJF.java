import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//shortest job first
public class SJF extends FCFS implements CPUScheduler {
	@Override
	public void executeProcesses(List<Process> jobs) {
		List<Process> jobscp = new ArrayList<>(jobs);
		Collections.sort(jobscp);
		super.executeProcesses(jobscp);
	}
}
