/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public class SUV extends Wehicle {

	WehicleWasher washer;

	public SUV(WehicleWasher washer, int id) {
		super(id);
		this.washer = washer;
	}

	public String printStats() {
		String out = super.getStats() + " Vehicle type: SUV";
		System.out.println(out);
		return out;
	}
}
