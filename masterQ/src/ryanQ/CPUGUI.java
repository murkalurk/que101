package ryanQ;
import java.io.*;
/**
 * GUI Extra Credit for MFQ Lab.
 * @author Ryan Wagner
 * @author Student 007222159
 */
public class CPUGUI{
	/**
	 * Empty constructor.
	 * @throws IOException
	 */
	public CPUGUI() throws IOException{	
	}
	/**
	 * Main method for the GUI.
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args)throws IOException, InterruptedException{
		InheritGUI inherit = new InheritGUI();
		inherit.jFrame();
	}
}
