
public class SUV extends Wehicle{
	
	public String printStats() {
		String out  = super.getStats() + "  Vehicle type: SUV";
		System.out.println(out);
		return out;
	}

}
