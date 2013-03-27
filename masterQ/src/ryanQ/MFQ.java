package ryanQ;
import java.io.*;
import java.util.Scanner;
/**
 * Takes in the information from the Job and CPU classes and outputs it to a file.
 * @author Ryan Wagner
 *
 */
public class MFQ{
	private PrintWriter write;
	Scanner scan = new Scanner(new File("mfq.txt"));
	Job job = new Job(scan);
	private CPU cpu = new CPU();
	private int jobNum = 0;
	/**
	 * Empty constructor for MFQ class.
	 * @throws IOException	 uses the Scanner class, which throws an IOException.
	 */
	public MFQ()throws IOException{

	}
	/**
	 * MFQ constructor which takes in the PrintWriter from the Driver.
	 * @param writer PrintWriter.
	 * @throws IOException	uses the Scanner class, which throws an IOException.
	 */
	public MFQ(PrintWriter writer)throws IOException{
		this.write = writer;
	}
	/**
	 * Gets the jobs from the Job class.
	 * @throws IOException	uses the Scanner class, which throws an IOException.
	 */
	public void getJobs()throws IOException{
		job.setJobs();
	}
	/**
	 * Outputs the header to System and PrintWriter.
	 */
	public void outputHeader(){
		System.out.println("ACTION\t\tPID\tSYSTIME\tWORKTIME\tTIMER\tLLQ:");
		write.println("A\t\t\tP|\tS|\tW|\t\tT|\tL\nC\t\t\tI|\tY|\tO|\t\tI|\tL\nT\t" +
				"\t\tD|\tS|\tR|\t\tM|\tQ\nI\t\t\t\tT|\tK|\t\tE|\t:\nO\t\t\t\tI|\tT|\t" +
				"\tR|\nN\t\t\t\tM|\tI|\n\t\t\t\tE|\tM|\n\t\t\t\t\tE|\n=============" +
				"=====================");
	}
	/**
	 * Runs the simulation.
	 */
	public void runSimulation(){
		if(!job.jobQue().isEmpty()){
			cpu.notEmpty(job.jobQue(), write);
			runSimulation();
		}else{
			if(cpu.busy()){
				cpu.quesEmpty(job.jobQue(), write);
				runSimulation();
			}
		}

	}
	/**
	 * Outputs the stats from the simulation.
	 */
	public void outStats(){
		jobNum = cpu.jobNum();
		System.out.println("Total Jobs:\t" + String.format("%.2f", jobNum/1.0));
		System.out.println("Total Run Time:\t" + String.format("%.2f", cpu.totalTime()/1.0));
		System.out.println("Average Response Time:\t" + String.format("%.2f", cpu.responseTime()/(jobNum/1.0)));
		System.out.println("Average Waiting Time:\t" + String.format("%.2f", cpu.inactiveTime()/(jobNum/1.0)));
		System.out.println("Average Turnaround:\t" + String.format("%.2f", cpu.waitTime()/(jobNum/1.0)));
		System.out.println("Average Throughput:\t" + String.format("%.2f", cpu.totalTime()/(jobNum/1.0)));
		System.out.println("Total idle:\t" + String.format("%.2f", (cpu.totalTime() - cpu.averageRun()/1.0)));
		write.println("Total Jobs:\t" + String.format("%.2f", jobNum/1.0));
		write.println("Total Run Time:\t" + String.format("%.2f", cpu.totalTime()/1.0));
		write.println("Average Response Time:\t" + String.format("%.2f", cpu.responseTime()/(jobNum/1.0)));
		write.println("Average Waiting Time:\t" + String.format("%.2f", cpu.inactiveTime()/(jobNum/1.0)));
		write.println("Average Turnaround:\t" + String.format("%.2f", cpu.waitTime()/(jobNum/1.0)));
		write.println("Average Throughput:\t" + String.format("%.2f", cpu.totalTime()/(jobNum/1.0)));
		write.println("Total idle:\t" + String.format("%.2f", (cpu.totalTime() - cpu.averageRun()/1.0)));
	}
}
