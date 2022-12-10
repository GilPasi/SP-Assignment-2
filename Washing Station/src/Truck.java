/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public class Truck extends Wehicle {

	WehicleWasher washer;

	public Truck(WehicleWasher washer) {
		super();
		this.washer = washer;
	}

	public void run() {
		washer.arrival(this);
		washer.startWash(this);
		washer.doneWash(this);
	}
	
	public String printStats() {
		String out = super.getStats() + " Vehicle type: Truck";
		return out;
	}
}
