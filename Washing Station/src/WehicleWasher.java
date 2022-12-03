/*System-Prgramming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
import java.util.ArrayDeque;
import java.util.ArrayList;

public class WehicleWasher {
	//3 Queues for documenting the station's status
	//All empty by default
	private ArrayDeque<Wehicle> preWash = new ArrayDeque<Wehicle>();
	private ArrayList<Wehicle> inWash = new ArrayList<Wehicle>();
	private ArrayList<Wehicle> postWash = new ArrayList<Wehicle>();
	
	
	private double arrivalPeriod;
	private double washPeriod;
	private int washingPositions;
	public int leftVehicles;
	
	public WehicleWasher(double arrivalPeriod, double washPeriod
							,int washingPositions,int leftVehicles) {
		
		this.arrivalPeriod = arrivalPeriod;
		this.washPeriod = washPeriod;
		this.washingPositions = washingPositions;
		this.leftVehicles = leftVehicles;
		
		for(int i = 0 ; i < washingPositions ; ++i) {
			//TODO create washing positions threads and initiate them
			
		}
		
	}
	
	//============================Work Day Procedure====================================

	public void workDay() {
		while (leftVehicles > 0) {
			
			try {
				preWash.add(generateVehicle());
				Thread.sleep((long) calcNext(true));//Calculate and wait for the next vehicle
				--leftVehicles;//decrease today's quantity
				
				//Address possible issues 
			} catch (InterruptedException e) {
				e.printStackTrace();
			
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}//Close for loop
	}
	
	
	 public double calcNext(boolean calcArrival) {
		/**This method finds the next time for a vehicle to arrive or
		 * for a vehicle to get washed depending on the boolean flag it gets.*/
		
		double U = Math.random();
		double nextTime = -ln(U);//Till now both calculations are the same
		
		//Differ calculations
		if(calcArrival)
			nextTime /= arrivalPeriod;
		else 
			nextTime /= washPeriod;
		
		return nextTime;
	}
	
	
	private double ln (double operand) {
		/**Since Java's Math class does not include the LOGe = LN 
		 * function, this will simulate the mathematics function
		 * */
		
		double numerator = Math.log(operand);
		double denominator = Math.log(Math.E);
		
		return numerator/denominator;
		/*Used the logarithm laws 
		 * d
		 * log(a,b) / log(a,c) = log (b,c)
		 * 
		 *  therefore: 
		 *  log (operand,2) / log (Math.E,2) = log(operand,Math.E) = ln(operand)
		 * */
	}
	
	private Wehicle generateVehicle() throws Exception{
		 int indicator =  (int) ((Math.random() * (4 - 1)) + 1);
		 Wehicle returnWehicle = null;
		 
		 switch(indicator) {
		 case 1: 
			 returnWehicle = new Car();
			 break;
		 case 2: 
			 returnWehicle = new Truck();
			 break;

		 case 3: 
			 returnWehicle = new SUV();
			 break;

		 case 4: 
			 returnWehicle = new MiniBus();
			 break;

		 default:
			 throw new Exception ("Bad random number generated");
			  
		 }
		 return returnWehicle;

	}
	
	//========================================================================
	
	
	public Wehicle startWash() throws NoQueueException {
		/**This method takes a vehicle from the waiting line*/
		
		if(preWash.size() == 0 ) // Thread cannot take a vehicle from an empty line
			throw new NoQueueException();
		
		Wehicle wehicle = preWash.remove();
		inWash.add(wehicle);
		return wehicle;
		
	}
	
	
	public void  endWash(int index ) {
		/**This method moves the given vehicle to the washed cars list*/
		postWash.add(inWash.remove(index));
	}
	
	
	public double getWashPeriod() {return washPeriod;}
	
}
