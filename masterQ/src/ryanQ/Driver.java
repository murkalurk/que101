package ryanQ;

import java.io.*;
public class Driver {
	public static void main(String[] args) throws IOException{
		PrintWriter pw = new PrintWriter(
				new FileWriter("csis.txt"));
		MFQ mfq = new MFQ(pw);
		mfq.getJobs();
		mfq.outputHeader();
		mfq.runSimulation();
		mfq.outStats();
		pw.close();}}