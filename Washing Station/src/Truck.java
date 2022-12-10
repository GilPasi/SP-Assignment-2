/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public class Truck extends Wehicle {

	WehicleWasher washer;

	public Truck(WehicleWasher washer, int id) {
		super(id);
		this.washer = washer;
	}

	public String printStats() {
		String out = super.getStats() + " Vehicle type: Truck";
		System.out.println(out);
		return out;
	}
}
