package washaingStation;
/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
import java.util.ArrayDeque;
import java.util.ArrayList;

public class WehicleWasher {
	
	//Assistant properties
	public  WehicleLogger logger;
	private static long startTime = System.currentTimeMillis();
	
	//Documentation properties
	private ArrayDeque<Wehicle> preWash = new ArrayDeque<Wehicle>();
	private ArrayList<Wehicle> inWash = new ArrayList<Wehicle>();
	
	//Post wash lists
	private ArrayList<Wehicle> washedCars = new ArrayList<Wehicle>();
	private ArrayList<Wehicle> washedTrucks = new ArrayList<Wehicle>();
	private ArrayList<Wehicle> washedSUVs = new ArrayList<Wehicle>();
	private ArrayList<Wehicle> washedMiniBuses = new ArrayList<Wehicle>();

	//Modifiers
	private double washPeriod;
	private int washingPositions;
	private int leftVehicles;

	
	public WehicleWasher(double washPeriod,int washingPositions,int leftVehicles) {		
		
		//Avoid invalid arguments
		this.washPeriod = washPeriod > 0 ? washPeriod : 1;
		this.washingPositions = washingPositions > 0 ? washingPositions : 1;
		this.leftVehicles = leftVehicles > 0 ? leftVehicles : 1;
		
		logger = new WehicleLogger();		
	}
	

	public synchronized void startWash(Wehicle w){	
		/**This method takes a vehicle from the waiting line*/
		
		while(!w.isFirst) {//@PRE the vehicle must be the first in line
			try {	
				wait();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
					
			
		while(!isFreePosition()) {//@PRE the washer must have at least one position free
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
		}	
		}
		
		//Transfer vehicle
		Wehicle first = preWash.pop();//Take out the first
		inWash.add(first);
		w.promote();
		
		//Documentation
		String stats = w.getStats();
		System.out.println(stats);
		logger.log(stats,leftVehicles);	
		
		//Declare new first
		if(preWash.size() > 0)
			preWash.peek().makeFirst();
		
		notifyAll();
	}
	
	
	public synchronized boolean isFreePosition() {
		return inWash.size() < washingPositions;
		
	}
	
	
	public synchronized void  endWash(Wehicle w)    {
		/**This method moves the given vehicle to the washed cars list*/		

		int index = inWash.indexOf(w);

		if(w instanceof Car)
			washedCars.add(inWash.remove(index));
		
		if(w instanceof Truck)
			washedTrucks.add(inWash.remove(index));
		
		if(w instanceof SUV)
			washedSUVs.add(inWash.remove(index));
		
		if(w instanceof MiniBus)
			washedMiniBuses.add(inWash.remove(index));
		
		w.promote();
		
		//Documentation
		String stats = w.getStats();
		System.out.println(stats);
		
		logger.log(stats,--leftVehicles);
		
		double timeGap = System.currentTimeMillis() - startTime;	
		w.addTotalTime(timeGap);
		
		notifyAll();
	}
	
	
	public synchronized void getInLine(Wehicle w) {
				
		if(preWash.size() == 0)
			w.makeFirst();
		
		preWash.add(w);
		w.promote();
		
		//Documentation
		String stats = w.getStats();
		System.out.println(stats);
		logger.log(stats,leftVehicles);	
		
		
	}
	
	
	 public static long calcNext(double  lamda) {
		/**This method finds the next time for a vehicle to arrive or
		 * for a vehicle to get washed.*/
		
		double U = Math.random();
		long nextTime = (long) (-ln(U) / lamda);//Perform calculation
		
		return nextTime;
	}
	
	
	public static double ln (double operand) {
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
	public void printPostWash() {
		
		System.out.println(washedCars);
		System.out.printf(" Average wait time: " +  String.format("%.2f", Car.getAverageWait())  + " seconds\n\n");
		
		System.out.println(washedSUVs);
		System.out.printf(" Average wait time: " + String.format("%.2f",  SUV.getAverageWait()) + " seconds\n\n");
		
		System.out.println(washedTrucks);
		System.out.printf(" Average wait time: " + String.format("%.2f", Truck.getAverageWait()) + " seconds\n\n");

		System.out.println(washedMiniBuses);
		System.out.printf("Average wait time: " + String.format("%.2f", MiniBus.getAverageWait()) + " seconds\n\n");


	}
	
	
	public double getWashPeriod() {return washPeriod;}
	public int getLeftVehicles() {return leftVehicles;}
	
}
