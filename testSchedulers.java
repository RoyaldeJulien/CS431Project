import java.util.ArrayList;
import java.util.List;

public class testSchedulers {
	public static void main(String[] args) {
		CPUScheduler schedule = new RR(4);
		
		List<Process> jobs = new ArrayList<>();
		jobs.add(new RecordedProcess("job1", 3));
		jobs.add(new RecordedProcess("job2", 3));
		jobs.add(new RecordedProcess("job3", 4));
		jobs.add(new RecordedProcess("job4", 6));
		jobs.add(new RecordedProcess("job5", 9));
		jobs.add(new RecordedProcess("job6", 2));
		jobs.add(new RecordedProcess("job7", 1));
		jobs.add(new RecordedProcess("job8", 7));
		jobs.add(new RecordedProcess("job9", 4));
		
		schedule.executeProcesses(jobs);
		
		@SuppressWarnings("unchecked")
		List<RecordedProcess> jobsRecords = (List<RecordedProcess>)(List<?>) jobs;
		
		StringBuilder output = new StringBuilder();
		List<Integer> whenRan = new ArrayList<Integer>();
		
		for (RecordedProcess p : jobsRecords) {
			output.append(p.getProcessName() + "(" + p.GetTimeFinished() + ")" + "\t|");
			whenRan = p.GetWhenRun();
			for (int i = 0; i <= p.GetTimeFinished(); i++) {
				if (whenRan.contains(i)) 
					output.append('R');
				else 
					output.append('-');
			}
			output.append('\n');
			
		}
		
		System.out.println(output.toString());
		
		
	}
}
