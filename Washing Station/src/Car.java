/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public class Car extends Wehicle {

	WehicleWasher washer;
	
	public Car(WehicleWasher washer) {
		super();
		this.washer = washer;
	}
	
	public void run() {
		washer.arrival(this);
		washer.startWash(this);
		washer.doneWash(this);
	}
	
	public String printStats() {
		String out = super.getStats() + " Vehicle type: Car";
		System.out.println(out);
		return out;
	}
}
