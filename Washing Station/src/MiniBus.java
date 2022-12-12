/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public class MiniBus extends Wehicle {

	WehicleWasher washer;

	public MiniBus(WehicleWasher washer, int id) {
		super(id);
		this.washer = washer;
	}

	public String printStats() {
		String out = super.getStats() + " Vehicle type: MiniBus";
		System.out.println(out);
		return out;
	}
}
