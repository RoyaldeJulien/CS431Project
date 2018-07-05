import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestSchedulers {
	
	public static void main(String[] args) throws FileNotFoundException {
		List<RecordedProcess> jobs = parseInputFile("job.txt");
		
		System.out.println("First-Come First-Serve:\n" + test(new FCFS(), jobs));
		
		System.out.println("Shortest Job First:\n" + test(new SJF(), jobs));
		
		System.out.println("Round-Robin w/Q=2:\n" + test(new RR(2), jobs));
		
		System.out.println("Round-Robin w/Q=5:\n" + test(new RR(5), jobs));

	}
	
	public static List<RecordedProcess> parseInputFile(String filename) throws FileNotFoundException {
		//open file
		Scanner input = new Scanner(new File(filename));
		
		//create list to hold jobs
		List<RecordedProcess> result = new ArrayList<>();
		
		//parse file for job info
		while (input.hasNextLine()) {
			result.add(new RecordedProcess(input.nextLine(),Integer.parseInt(input.nextLine())));
		}
		
		//close and return
		input.close();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static String test(CPUScheduler schedule, List<RecordedProcess> jobs) {
		//create a list of copies of all elements in jobs
		List<RecordedProcess> jobsCP = new ArrayList<>();
		for(RecordedProcess p : jobs) {
			jobsCP.add(new RecordedProcess(p.getProcessName(),p.getRemainingTime()));
		}
		
		//reset the recording clock and execute
		RecordedProcess.resetClock();
		schedule.executeProcesses((List<Process>)(List<?>)jobsCP);
		
		//return formatted output
		return formatOutput(jobsCP);
	}
	
	public static String formatOutput(List<RecordedProcess> jobs) {
		StringBuilder result = new StringBuilder();
		int padlength = 9;
		int divisionlength = 5;
		char runningchar = 'R';
		char waitingchar = '-';
		char gridlinechar = '+';
		
		//create table column labels
		int maxtime = jobs.get(0).GetTimeFinished();
		for (RecordedProcess p : jobs) {
			maxtime = (p.GetTimeFinished() > maxtime) ? p.GetTimeFinished():maxtime;
		}
		result.append(padRight("job(TF)", padlength) + '|');
		for (int i = 0; i < maxtime; i += divisionlength) {
			result.append(padRight(Integer.toString(i), divisionlength));
		}
		result.append('\n');
		
		//create rows
		for (RecordedProcess p : jobs) {
			//create the label for that row
			String rowLabel = p.getProcessName() + "(" + p.GetTimeFinished()+")";
			result.append(padRight(rowLabel, padlength) + "|");
			//create the rest of the row
			for (int i = 0; i < p.GetTimeFinished(); i++) {
				if (p.ranAt(i)) {
					result.append(runningchar);
				}
				else if ((i % divisionlength) == 0 ) {
					result.append(gridlinechar);
				}
				else {
					result.append(waitingchar);
				}
			}
			result.append('\n');
		}
		
		//create table addendum
		result.append("*TF = Time finished*\n");
		double avgtime = 0;
		for (RecordedProcess p : jobs) {
			avgtime += p.GetTimeFinished();
		}
		avgtime /= (double)jobs.size();
		result.append("Average Completion Time: " + avgtime + "\n");
		
		//return table
		return result.toString();
	}
	
	public static String padRight(String s, int length) {
		return String.format("%1$-"+ length  +"s", s);
	}
}
