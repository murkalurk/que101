package ryanQ;

import java.io.*;
import java.util.Scanner;
/**
 * Scans in the Jobs from MFQ.txt and puts them in a Queue.
 * @author Ryan Wagner
 *
 */
public class Job {
	private Scanner scan;
	private String s;
	ObjectQueue que = new ObjectQueue();
	public Job(Scanner scan)throws IOException{
		this.scan = scan;
	}
	/**
	 * Empty constructor for the Job class.
	 */
	public Job(){	
	}
	/**
	 * Scans the jobs from Scanner and puts them in a Queue.
	 */
	public void setJobs(){
		if(scan.hasNextLine()){
		s = scan.nextLine();
		que.insert(s);
		setJobs();}
	}
	/**
	 * creates a reference point for MFQ to take the contents of the Queue.
	 * @return que
	 */
	public ObjectQueue jobQue(){
		return que;
	}
}
