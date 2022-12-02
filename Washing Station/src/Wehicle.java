
public  abstract class Wehicle {
	public enum  Status{UNKOWN,PENDING,IN_WASH,DONE;} 
	
	int id;
	static long startTime = System.currentTimeMillis();
	static int nextId = 1;//This helps to calculate the next id in such a way 						  // that each id is unique
	Status vehicleStatus;
	
	//Constructor
	public Wehicle () {
		id = nextId ++ ;//Define a new id + increase the
						//next id (in order to avoid duplications)
		vehicleStatus = Status.UNKOWN;//Status is yet to be determined
		
		
	}
	
	
	
	public String getStats() {
		/**This method will print the required message for a specific 3 cases:
		 * -A vehicle is joined into the queue
		 * -A washing position was allocated to vehicle 
		 * -Washing process is over
		 * 
		 * Nonetheless it is not the method's responsability to determine those
		 * situations.
		 * 
		 * It also returns the string so it will be easier to log it into the 
		 * file.*/
		
		long timeGap = System.currentTimeMillis() - startTime;	
		
		String out = "Time since the beginning : " + timeGap
				      + "\nStatus changed to " + vehicleStatus
				      +"\nVehicle ID : " + id;
		
		return out;
		
	}
	

}
