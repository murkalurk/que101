package ryanQ;

public class CPU{
	private int cpuClock = 0,
				i;
	private boolean busy = false;
	private boolean newJob = false;
	private int jobClock = 0;
	private int workTimer = 0;
	private int qClock = 0;
	private int llq = 0;
	private String[] st;
	private String s;
	ObjectQueue cpu = new ObjectQueue();
	ObjectQueue q1 = new ObjectQueue();
	ObjectQueue q2 = new ObjectQueue();
	ObjectQueue q3 = new ObjectQueue();
	ObjectQueue q4 = new ObjectQueue();
	public CPU(){
	}
	public void simulation(ObjectQueue que){
		if(!que.isEmpty()){
			initQue(que);	// Ticks the clock and sets s to que.query(), st to s.split(" ") and i to 1 or 0.
							// Also sets enQue(), jobID(), and workTime() by way of st.
			if(enQue() == cpuClock){
				inNew(que);	// Inserts the new job into q1 and newJob to true, also inserts a value 0 for jobClock.
			}else;
			if(!busy)
				qChecker();	// Checks the queues for highest available Job and inserts it into the CPU.
			else{
				setValues();//Sets values for s, llq, qClock, jobClock, and workTimer.
				cpuFill();
				if(jobClock == 0){
					cpuClear();
					System.out.println("REMOVED\t\t" + jobID() + "\t" + cpuClock + "\t" + jobClock + "\t\t" + workTimer + "\t" + cpu.isEmpty());
					busy = false;
					qChecker();
				}
				else{
					if(qClock == 0 || newJob){
						llq++;
						checkLLQ();
						qChecker();
						newJob = false;
					}else;
				}
			}
		simulation(que);
		}
		}
	public void cpuClear(){
		cpu.remove();cpu.remove();cpu.remove();cpu.remove();cpu.remove();
	}
	public void cpuFill(){
		qClock--;	//Decrement qClock.
		jobClock--;	//Decrement jobClock.
		workTimer++;//Increment workTimer.
		cpu.insert(s);
		cpu.insert(llq);
		cpu.insert(qClock);
		cpu.insert(jobClock);
		cpu.insert(workTimer);
	}
	public void setValues(){
		s = (String)cpu.remove();
		st = s.split(" ");
		i = 0;
		llq = (Integer)cpu.remove();
		qClock = (Integer)cpu.remove();
		jobClock = (Integer)cpu.remove();
		workTimer = (Integer)cpu.remove();
	}
	public void checkLLQ(){
		if(llq == 2){
			q2.insert(cpu.remove());//insert job String into Q2
			cpu.remove();//remove llq as it is a declared variable within the CPU.insert
			cpu.remove();//remove qClock as it is stated wsithin the CPU.insert
			q2.insert(cpu.remove());//Insert job time countdown into Q2
			q2.insert(cpu.remove());//insert work timer into Q2
		}
		else if(llq == 3){
			q3.insert(cpu.remove());//insert job String into Q2
			cpu.remove();//remove llq as it is a declared variable within the CPU.insert
			cpu.remove();//remove qClock as it is stated wsithin the CPU.insert
			q3.insert(cpu.remove());//Insert job time countdown into Q2
			q3.insert(cpu.remove());//insert work timer into Q2
		}
		else if(llq == 4){
			q4.insert(cpu.remove());//insert job String into Q2
			cpu.remove();//remove llq as it is a declared variable within the CPU.insert
			cpu.remove();//remove qClock as it is stated wsithin the CPU.insert
			q4.insert(cpu.remove());//Insert job time countdown into Q2
			q4.insert(cpu.remove());//insert work timer into Q2
		}
	}
	public void qChecker(){
		if(!q1.isEmpty()){
			getWorkTime(q1);
			cpu.insert(q1.remove());
			cpu.insert(1);
			cpu.insert(2);
			cpu.insert(workTime());
			cpu.insert(q1.remove());
			busy = true;
		}
		else if(q1.isEmpty() && !q2.isEmpty()){
			getWorkTime(q2);
			cpu.insert(q2.remove());
			cpu.insert(2);
			cpu.insert(4);
			cpu.insert(q2.remove());
			cpu.insert(q2.remove());
			busy = true;
		}
		else if(q1.isEmpty() && q2.isEmpty() && !q3.isEmpty()){
			getWorkTime(q3);
			cpu.insert(q3.remove());
			cpu.insert(3);
			cpu.insert(8);
			cpu.insert(q3.remove());
			cpu.insert(q3.remove());
			busy = true;
		}
		else if(q1.isEmpty() && q2.isEmpty() && q3.isEmpty() && !q4.isEmpty()){
			getWorkTime(q4);
			cpu.insert(q4.remove());
			cpu.insert(3);
			cpu.insert(16);
			cpu.insert(q4.remove());
			cpu.insert(q4.remove());
			busy = true;
		}
		else
			busy = false;
		newJob = false;
	}
	public void initQue(ObjectQueue que){
		cpuClock++;
		s = (String)que.query();
		st = s.split("\\s+");
		i = stringCut();
	}

	public void inNew(ObjectQueue que){
		q1.insert(que.remove());
		q1.insert(0);
		newJob = true;
		workTimer = 0;
		System.out.println("ADDED\t\t" + jobID() + "\t" + cpuClock + "\t" + workTime() + "\t\t" + workTimer + "\t" + cpu.isEmpty());
	}
	public void getWorkTime(ObjectQueue qNum){
		s = (String)qNum.query();
		st = s.split("\\s+");
		i = stringCut();
	}
	public int stringCut(){
		return s.startsWith(" ")? 1 : 0;
	}
	public int enQue(){
		return Integer.parseInt(String.valueOf(st[i]));	
	}
	public int jobID(){
		return Integer.parseInt(String.valueOf(st[i+1]));
	}
	public int workTime(){
		return Integer.parseInt(String.valueOf(st[i+2]));
	}
	public String printJob(){
		return enQue() + "\t" + jobID() + "\t" + workTime();
	}
}
