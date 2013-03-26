package ryanQ;
import java.io.*;
/**
 * 
 * @author Ryan Wagner
 *
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
