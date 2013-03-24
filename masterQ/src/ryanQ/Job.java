package ryanQ;

import java.io.*;
import java.util.Scanner;

public class Job {
	private Scanner scan;
	private String s;
	ObjectQueue que = new ObjectQueue();
	public Job(Scanner scan)throws IOException{
		this.scan = scan;
	}
	public Job(){	
	}
	public void setJobs(){
		if(scan.hasNextLine()){
		s = scan.nextLine();
		que.insert(s);
		setJobs();}
	}
	public ObjectQueue jobQue(){
		return que;
	}
}
