
public class Truck extends Wehicle{
	
	public String printStats() {
		String out  = super.getStats() + "  Vehicle type: Truck";
		System.out.println(out);
		return out;
	}

}
