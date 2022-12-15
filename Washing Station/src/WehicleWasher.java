/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.concurrent.Semaphore;

public class WehicleWasher {
	// Queues for documenting the station's status
	// All empty by default
	private ArrayDeque<Wehicle> preWash = new ArrayDeque<Wehicle>();
	private ArrayList<Wehicle> inWash = new ArrayList<Wehicle>();

	private ArrayList<Wehicle> washedCars = new ArrayList<Wehicle>();
	private ArrayList<Wehicle> washedTrucks = new ArrayList<Wehicle>();
	private ArrayList<Wehicle> washedSUVs = new ArrayList<Wehicle>();
	private ArrayList<Wehicle> washedMiniBuses = new ArrayList<Wehicle>();

	private WehicleLogger logger;
	private double averageArrival;
	private double averageWash;
	private Semaphore washingStations;
	private int vehiclesAmount;

	public WehicleWasher(double averageArrival, double averageWash, int washingStations, int vehiclesAmount) {
		this.averageArrival = averageArrival;
		this.averageWash = averageWash;
		this.washingStations = new Semaphore(washingStations, true);
		this.vehiclesAmount = vehiclesAmount;
		logger = new WehicleLogger();
	}

	public synchronized void arrival(Wehicle vehicle) {

		try {
			preWash.add(vehicle);
			vehicle.setStatus(Status.PENDING);
			// logger.log(vehicle.getStats());
			System.out.println(vehicle.getStats());
			Thread.sleep((long) calcNext(averageArrival));// Calculate and wait for the next vehicle
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public synchronized void startWash(Wehicle vehicle) {
		while (preWash.getFirst() != vehicle) {
			try {
				wait();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			washingStations.acquire();
			inWash.add(preWash.pop());
			vehicle.setStatus(Status.IN_WASH);
//				logger.log(vehicle.getStats());
			Thread.sleep((long) calcNext(averageWash));// Calculate and wait for the wash
			System.out.println(vehicle.getStats());
			washingStations.release();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		notifyAll();
	}
	
	public synchronized void doneWash(Wehicle vehicle) {
		while (!inWash.isEmpty()) {
			try {
				if (vehicle instanceof Car)
					washedCars.add(vehicle);

				if (vehicle instanceof Truck)
					washedTrucks.add(vehicle);

				if (vehicle instanceof SUV)
					washedSUVs.add(vehicle);

				if (vehicle instanceof MiniBus)
					washedMiniBuses.add(vehicle);

				inWash.remove(vehicle);
				vehicle.setStatus(Status.DONE);
//					logger.log(vehicle.getStats());
				System.out.println(vehicle.getStats());
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}		
		notifyAll();
	}

	public double calcNext(double lambda) {
		/**
		 * This method finds the next time for a vehicle to arrive or for a vehicle to
		 * get washed depending on the lambda value it gets.
		 */
		double U = Math.random();
		double nextTime = -ln(U);// Till now both calculations are the same

		// Differ calculations
		return nextTime/= lambda;
	}

	private double ln(double operand) {
		/**
		 * Since Java's Math class does not include the LOGe = LN function, this will
		 * simulate the mathematics function
		 */

		double numerator = Math.log(operand);
		double denominator = Math.log(Math.E);

		return numerator / denominator;
		/*
		 * Used the logarithm laws d log(a,b) / log(a,c) = log(b,c)
		 * 
		 * therefore: log(operand,2) / log(Math.E,2) = log(operand,Math.E) = ln(operand)
		 */
	}

	public double getWashPeriod() {
		return averageWash;
	}

	public int getLeftVehicles() {
		return vehiclesAmount;
	}
}
