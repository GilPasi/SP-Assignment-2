package washaingStation;
/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public class SUV extends Wehicle implements Runnable {
	
	private static double totalWait = 0;
	private static int membersCount = 0;

	
	public SUV(WehicleWasher ww) {
		super(ww);
		++membersCount;
	}

	public String getStats() {return super.getStats() + " Type: SUV\n\n";}

	public void run() {
		ww.getInLine(this);
		ww.startWash(this);
		ww.endWash(this);
		

	}
	
	public void addTotalTime(double time) {totalWait += time;}
	
	public static double getAverageWait() {
		if(totalWait == 0)
			return 0.0;
		
		return totalWait / (membersCount * 1000);
	}

	public String toString() {return "SUV:" + super.toString();}

}
