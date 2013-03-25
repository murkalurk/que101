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
		writer.println("enQue\tjobID\tworkTime");
	}
	public void runSimulation(){
		cpu.simulation(job.jobQue());
	}
	public void outStats(){
		if(!(job.jobQue()).isEmpty()){
		System.out.println((job.jobQue()).remove());
		outStats();}
	}
}
