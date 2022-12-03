/*System-Prgramming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public class Truck extends Wehicle{
	
	public String printStats() {
		String out  = super.getStats() + "  Vehicle type: Truck";
		System.out.println(out);
		return out;
	}

}
