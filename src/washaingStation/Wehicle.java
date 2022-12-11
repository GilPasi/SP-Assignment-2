package washaingStation;
/*System-Prgramming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public abstract class Wehicle{
	
	protected int id;
	protected static long startTime = System.currentTimeMillis();
	protected static int nextId = 1;//This helps to calculate the next id in such a way that each id is unique
	protected Status vehicleStatus;
	protected boolean isFirst = false;//Not first by default
	protected WehicleWasher ww;
	
	//Constructor
	public Wehicle (WehicleWasher ww ) {
		this.ww = ww;
		id = nextId ++ ;//Define a new id + increase the
						//next id (in order to avoid duplications)
		vehicleStatus = Status.UNKOWN;//Status is yet to be determined
		
		
	}
	
	
	
	public String getStats() {
		/**This method will print the required message for a specific 3 cases:
		 * -A vehicle is joined into the queue
		 * -A washing position was allocated to vehicle 
		 * -Washing process is over
		 * 
		 * Nonetheless it is not the method's responsibility to determine those
		 * situations.
		 * 
		 * It also returns the string so it will be easier to log it into the 
		 * file.*/
		
		long timeGap = System.currentTimeMillis() - startTime;	
		
		
		String out = "Time since the beginning : " + timeGap / 1000.0 + " seconds"
				      + "\nStatus changed to " + vehicleStatus
				      +"\nVehicle ID : " + id;
		
		return out;
		
	}
	
	public void promote() {
		if(vehicleStatus == Status.UNKOWN)
			vehicleStatus = Status.PENDING;
		
		else if(vehicleStatus == Status.PENDING)
			vehicleStatus = Status.IN_WASH;

		
		else if (vehicleStatus == Status.IN_WASH)
			vehicleStatus = Status.DONE;
	}
	
	public synchronized void makeFirst() {isFirst = true;}
	
	public String toString() {return "ID : " + id;}
	
	public int getId() {return id;}
	
	public Status getVehicleStatus() {return vehicleStatus; }
	
	public  abstract void addTotalTime(double time);
	

	

}
