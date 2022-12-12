/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public abstract class Wehicle implements Runnable{

	private int id;
	private static long startTime = System.currentTimeMillis();
	private Status vehicleStatus;

	// Constructor
	public Wehicle(int id) {
		this.id = id;
		vehicleStatus = Status.UNKOWN;// Status is yet to be determined
	}

	public void run() {
	}

	public String getStats() {
		/**
		 * This method will print the required message for a specific 3 cases: 
		 * -A vehicle is joined into the queue 
		 * -A washing position was allocated to vehicle
		 * -Washing process is over
		 * 
		 * Nonetheless it is not the method's responsibility to determine those situations.
		 * 
		 * It also returns the string so it will be easier to log it into the file.
		 */

		long timeGap = System.currentTimeMillis() - startTime;

		return "Time since the beginning: " + timeGap + 
				"\nStatus changed to " + vehicleStatus + 
				"\nVehicle ID: " + id;
	}

	public void setStatus(Status newStatus) {
		switch (newStatus) {
		case PENDING:
			this.vehicleStatus = Status.PENDING;
			break;
		case IN_WASH:
			this.vehicleStatus = Status.IN_WASH;
			break;
		case DONE:
			this.vehicleStatus = Status.DONE;
			break;
		default:
			this.vehicleStatus = Status.UNKOWN;
		}
	}
	
	public Status getVehicleStatus() {
		return vehicleStatus;
	}
	
	public int getID() {
		return id;
	}
}
