
public class MiniBus extends Wehicle{
	
	public String printStats() {
		String out  = super.getStats() + "  Vehicle type: MiniBus";
		System.out.println(out);
		return out;
	}

}
