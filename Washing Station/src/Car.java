/*System-Prgramming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public class Car extends Wehicle{
	
	public String printStats() {
		String out  = super.getStats() + "  Vehicle type: Car";
		System.out.println(out);
		return out;
	}

}
