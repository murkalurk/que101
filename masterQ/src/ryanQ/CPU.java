package ryanQ;
/**
 * Executes the Jobs collected by the Job class to output into MFQ.
 * @author Ryan Wagner
 */
public class CPU{
	private int cpuClock = 0,
				i;
	private boolean busy = false;
	private boolean newJob = false;
	private int jobClock = 0;
	private int qClock = 0;
	private int llq = 0;
	private int busyTime = 0;
	private String[] st;
	private String s;
	private int waitTime = 0;
	private int inactiveTime = 0;
	private int responseTime = 0;
	ObjectQueue cpu = new ObjectQueue();
	ObjectQueue q1 = new ObjectQueue();
	ObjectQueue q2 = new ObjectQueue();
	ObjectQueue q3 = new ObjectQueue();
	ObjectQueue q4 = new ObjectQueue();
	ObjectQueue q5 = new ObjectQueue();
	/**
	 * Empty constructor for CPU class.
	 */
	public CPU(){
	}
	/**
	 * Runs when the Job list has been emptied and empties the remaining Queues.
	 * Checks if the job is completed.
	 * If not, checks if there is remaining Quantum time.
	 * If not, moves into a lower level Queue.
	 * @param que Takes in the Queue containing all of the jobs.
	 */
	public void quesEmpty(ObjectQueue que){
		if(busy){
			busyTime++;
			cpuClock++;
			setValues();
			cpuFill();
			if(jobClock == 0)
				jobOver();
			else{
				if(qClock == 0)
					llqMove();
			}
		}
	}
	/**
	 * Runs when the Job list has not been emptied and simulates everything until it is.
	 * Checks if there is a new Job to add.
	 * Also, checks to see if the system already has a job on the Queue.
	 * @param que Takes in the Queue containing all of the jobs.
	 */
	public void notEmpty(ObjectQueue que){
		initQue(que);
		if(enQue() == cpuClock){
			responseTime = responseTime + enQue();
			inNew(que);
		}else;
		if(!busy)
			qChecker();
		else
			simBusy();
	}
	/**
	 * Runs when the Simulation's busy flag is set to true.
	 * Checks if the Job is over.
	 * If not, checks if the Quantum is over or if a New job has come to the system.
	 * If so, moves the Job to the next Lower Level Queue.
	 */
	public void simBusy(){
		busyTime++;
		setValues();
		cpuFill();
		if(jobClock == 0)
			jobOver();
		else{
			if(qClock == 0 || newJob){
				llqMove();
				newJob = false;
			}else;
		}
	}
	/**
	 * Runs when the job has completed.
	 * Clears the CPU so that there will be no conflicts when the next job is loaded.
	 * Sets the CPU busy flag to false.
	 */
	public void jobOver(){
		cpuClear();
		q5.insert("REMOVED\t\t" + jobID() + "\t" + cpuClock + "\t" + jobClock + "\t\t" + (cpuClock - enQue()) + "\t" + llq);
		waitTime = waitTime + (cpuClock - enQue());
		inactiveTime = inactiveTime + (cpuClock - enQue() - workTime());
		busy = false;
		qChecker();
	}
	public void overLine(){
		System.out.println("REMOVED\t\t" + jobID() + "\t" + cpuClock + "\t" + jobClock + "\t\t" + (cpuClock - enQue()) + "\t" + llq);
	}
	public int cpuTime(){
		return cpuClock;}
	/**
	 * Returns total time the jobs have spent inactive.
	 * @return inactiveTime
	 */
	public int inactiveTime(){
		return inactiveTime;
	}
	/**
	 * Returns the system response time.
	 * @return responseTime
	 */
	public int responseTime(){
		return responseTime;
	}
	/**
	 * Returns the system total time of all jobs in the system.
	 * @return waitTime
	 */
	public int waitTime(){
		return waitTime;
	}
	/**
	 * Returns the final cpu Time.
	 * @return cpuClock
	 */
	public int totalTime(){
		return cpuClock;
	}
	/**
	 * Returns the total time spent busy.
	 * @return busyTime
	 */
	public int averageRun(){
		return busyTime;
	}
	/**
	 * Returns true if the System is busy.
	 * @return true or false
	 */
	public boolean busy(){
		return busy;
	}
	/**
	 * Moves the Job to the next lower level Queue.
	 */
	public void llqMove(){
		if(llq != 4)
			llq++;else;
		checkLLQ();
		qChecker();
	}
	/**
	 * Removes all information on the CPU.
	 */
	public void cpuClear(){
		cpu.remove();cpu.remove();cpu.remove();cpu.remove();
	}
	/**
	 * If the CPU is busy, decrements the job and Quantum clocks and returns their values behind the Job info.
	 */
	public void cpuFill(){
		qClock--;
		jobClock--;
		cpu.insert(s);
		cpu.insert(llq);
		cpu.insert(qClock);
		cpu.insert(jobClock);
	}
	/**
	 * Sets the current values attached with the active Job.
	 */
	public void setValues(){
		s = (String)cpu.remove();
		st = s.split(" ");
		i = 0;
		intCheck();
		llq = (Integer)cpu.remove();
		qClock = (Integer)cpu.remove();
		jobClock = (Integer)cpu.remove();
	}
	/**
	 * Checks the value for LLQ and inserts the current job into it.
	 */
	public void checkLLQ(){
		if(llq == 2){
			q2.insert(cpu.remove());
			cpu.remove();
			cpu.remove();
			q2.insert(cpu.remove());
		}
		else if(llq == 3){
			q3.insert(cpu.remove());
			cpu.remove();
			cpu.remove();
			q3.insert(cpu.remove());
		}
		else if(llq == 4){
			q4.insert(cpu.remove());
			cpu.remove();
			cpu.remove();
			q4.insert(cpu.remove());
		}
	}
	/**
	 * Inserts Job from highest nonempty Queue.
	 */
	public void qChecker(){
		if(!q1.isEmpty()){
			getWorkTime(q1);
			cpu.insert(q1.remove());
			cpu.insert(1);
			cpu.insert(2);
			cpu.insert(workTime());
			busy = true;
		}
		else if(!q2.isEmpty()){
			getWorkTime(q2);
			cpu.insert(q2.remove());
			cpu.insert(2);
			cpu.insert(4);
			cpu.insert(q2.remove());
			busy = true;
		}
		else if(!q3.isEmpty()){
			getWorkTime(q3);
			cpu.insert(q3.remove());
			cpu.insert(3);
			cpu.insert(8);
			cpu.insert(q3.remove());
			busy = true;
		}
		else if(!q4.isEmpty()){
			getWorkTime(q4);
			cpu.insert(q4.remove());
			cpu.insert(4);
			cpu.insert(16);
			cpu.insert(q4.remove());
			busy = true;
		}
		else
			busy = false;
		newJob = false;
	}
	/**
	 * Initializes the Queue and ticks the clock.
	 * @param que Takes in the Queue containing all of the jobs. 
	 */
	public void initQue(ObjectQueue que){
		cpuClock++;
		s = (String)que.query();
		st = s.split("\\s+");
		i = stringCut();
		intCheck();
	}
	/**
	 * Sets values for use in the GUI.
	 */
	public void setVals(){
		if(!cpu.isEmpty())
			s = (String)cpu.query();
		else
			s = "0 0 0";
		st = s.split("\\s+");
		i = stringCut();
	}
	/**
	 * Holds the value for the Lowest Level Queue for the GUI.
	 * @return Lowest Level Queue
	 */
	public int llq(){
		return llq;
	}
	/**
	 * Holds the value of the Quantum for the GUI.
	 * @return Quantum Timer
	 */
	public int qClock(){
		return qClock;
	}
	/**
	 * Checks to make sure the value being read into job is a valid integer.
	 * If not, throws an error message and exits.
	 */
	public void intCheck(){
		try{
			Integer.parseInt(st[i]);
			Integer.parseInt(st[i+1]);
			Integer.parseInt(st[i+2]);
		}catch(Exception e){
			System.out.println("Illegal input in Job Queue");
			System.exit(1);
		}
	}
	/**
	 * Inserts a new Job into Q1.
	 * @param que Takes in the Queue containing all of the jobs.
	 */
	public void inNew(ObjectQueue que){
		q1.insert(que.remove());
		newJob = true;
		q5.insert("ADDED\t\t" + jobID() + "\t" + cpuClock + "\t" + workTime());
	}
	/**
	 * Gets the work time of the Job currently on the highest level Queue.
	 * @param qNum Sets the qNum to the number of the current Queue.
	 */
	public void getWorkTime(ObjectQueue qNum){
		s = (String)qNum.query();
		st = s.split("\\s+");
		i = stringCut();
	}
	/**
	 * Sets a value for i dependent on whether or not the Job string contains any leading spaces.
	 * @return 1 or 0.
	 */
	public int stringCut(){
		return s.startsWith(" ")? 1 : 0;
	}
	/**
	 * Returns the Queue in which all of the returned information is stored.
	 * @return q5
	 */
	public ObjectQueue queReturn(){
		return q5;
	}
	/**
	 * Returns the time in which the Job is added into the System.
	 * @return st[i]
	 */
	public int enQue(){
			return Integer.parseInt(String.valueOf(st[i]));
	}
	/**
	 * Returns the Job ID.
	 * @return st[i+1]
	 */
	public int jobID(){
			return Integer.parseInt(String.valueOf(st[i+1]));
	}
	/**
	 * Returns the amount of time which it will take to work through.
	 * @return st[i+2]
	 */
	public int workTime(){
		return Integer.parseInt(String.valueOf(st[i+2]));
	}
	}
