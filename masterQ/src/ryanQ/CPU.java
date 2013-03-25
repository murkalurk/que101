package ryanQ;

public class CPU{
	private int cpuClock = 0,
				i;
	private boolean busy = false;
	private boolean newJob = false;
	private int jobClock = 0;
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
			notEmpty(que);
		}else{
			quesEmpty(que);
		}
	}
	public void quesEmpty(ObjectQueue que){
		if(busy){
			cpuClock++;
			setValues();
			cpuFill();
			if(jobClock == 0){
				jobOver();
			}
			else{
				if(qClock == 0){
					llqMove();
				}
			}
		simulation(que);
		}
	}
	public void notEmpty(ObjectQueue que){
		initQue(que);
		if(enQue() == cpuClock){
			inNew(que);
		}else;
		if(!busy)
			qChecker();
		else{
			simBusy();
		}
	simulation(que);
	}
	public void simBusy(){
		setValues();
		cpuFill();
		if(jobClock == 0){
			jobOver();
		}
		else{
			if(qClock == 0 || newJob){
				llqMove();
				newJob = false;
			}else;
		}
	}
	public void jobOver(){
		cpuClear();
		System.out.println("REMOVED\t\t" + jobID() + "\t" + cpuClock + "\t" + jobClock + "\t\t" + (cpuClock - enQue()) + "\t" + llq);
		busy = false;
		qChecker();
	}
	public void llqMove(){
		if(llq != 4)
			llq++;else;
		checkLLQ();
		qChecker();
	}
	public void cpuClear(){
		cpu.remove();cpu.remove();cpu.remove();cpu.remove();
	}
	public void cpuFill(){
		qClock--;
		jobClock--;
		cpu.insert(s);
		cpu.insert(llq);
		cpu.insert(qClock);
		cpu.insert(jobClock);
	}
	public void setValues(){
		s = (String)cpu.remove();
		st = s.split(" ");
		i = 0;
		llq = (Integer)cpu.remove();
		qClock = (Integer)cpu.remove();
		jobClock = (Integer)cpu.remove();
	}
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
	public void initQue(ObjectQueue que){
		cpuClock++;
		s = (String)que.query();
		st = s.split("\\s+");
		i = stringCut();
	}

	public void inNew(ObjectQueue que){
		q1.insert(que.remove());
		newJob = true;
		System.out.println("ADDED\t\t" + jobID() + "\t" + cpuClock + "\t" + workTime());
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
