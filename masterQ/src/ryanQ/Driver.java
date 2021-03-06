package ryanQ;

import java.io.*;
/**
 * Driver for the MFQ lab.
 * @author Ryan Wagner
 * @author Student ID: 007222159
 * @version 1.0.0
 *
 */
public class Driver {
	public static void main(String[] args) throws IOException, InterruptedException{
		PrintWriter pw = new PrintWriter(
				new FileWriter("csis.txt"));
		MFQ mfq = new MFQ(pw);
		mfq.getJobs();
		mfq.outputHeader();
		mfq.runSimulation();
		mfq.outStats();
		pw.close();
	}
}