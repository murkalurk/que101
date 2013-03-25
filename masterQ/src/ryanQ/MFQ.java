package ryanQ;
import java.io.*;
import java.util.Scanner;
public class MFQ{
	private PrintWriter writer;
	Scanner scan = new Scanner(new File("input.txt"));
	Job job = new Job(scan);
	CPU cpu = new CPU();
	public MFQ()throws IOException{
		
	}
	public MFQ(PrintWriter writer)throws IOException{
		this.writer = writer;
	}
	public void getJobs()throws IOException{
		job.setJobs();
	}
	public void outputHeader(){
		System.out.println("ACTION\t\tPID\tSYSTIME\tWORKTIME\tTIMER\tLLQ:");
		writer.println("A\t\t\tP\tS\tW\t\tT\tL\nC\t\t\tI\tY\tO\t\tI\tL\nT\t\t\tD\tS\tR\t\tM\tQ\nI\t\t\t\tT\tK\t\tE\t:\nO\t\t\t\tI\tT\t\tR\nN\t\t\t\tM\tI\n\t\t\t\tE\tM\n\t\t\t\t\tE");
	}
	public void runSimulation(){
		cpu.simulation(job.jobQue());
	}
	public void outStats(){
		while (!cpu.queReturn().isEmpty()){
			writer.println(cpu.queReturn().query());
			System.out.println(cpu.queReturn().remove());
		}
	}
}
