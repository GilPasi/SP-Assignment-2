
public class Car extends Wehicle{
	
	public String printStats() {
		String out  = super.getStats() + "  Vehicle type: Car";
		System.out.println(out);
		return out;
	}

}
