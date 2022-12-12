import java.util.ArrayList;
import java.util.Scanner;

/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public class Runner {

	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		int stations = 0, totalVehicles = 0;
		double avgArrival = 0, avgWash = 0;
		System.out.println("Please enter amount of washing stations: ");
		stations = input.nextInt();
		System.out.println("Please enter amount of vehicles: ");
		totalVehicles = input.nextInt();
		System.out.println("Please enter average time for vehicle's arrival: ");
		avgArrival = input.nextDouble();
		System.out.println("Please enter average time for vehicle's wash: ");
		avgWash = input.nextDouble();

		WehicleWasher washer = new WehicleWasher(avgArrival, avgWash, stations, totalVehicles);
		
		ArrayList<Wehicle> vehicles = new ArrayList<Wehicle>(totalVehicles);
		try {
			for (int i = 0; i < totalVehicles; i++) {
				vehicles.add(generateVehicle(washer, i+1));// creation of randomized type of vehicle with unique id
				
			}
		} catch (InterruptedException e) {
		} catch (Exception e) {
		}
		
		SUV s = new SUV(washer);
		Thread t =  new Thread(s);
		t.start();
		//		
//		for (int i = 0; i < totalVehicles; i++) {
//			new Thread(vehicles.get(i)).start();
//		}

	}

	public static Wehicle generateVehicle(WehicleWasher washer, int id) throws Exception {
		int indicator = (int) ((Math.random() * 3) + 1); // generate random int between 1 and 4
		Wehicle generatedType = null;

		switch (indicator) {
		case 1:
			generatedType = new Car(washer, id);
			break;
		case 2:
			generatedType = new Truck(washer, id);
			break;
		case 3:
			generatedType = new SUV(washer, id);
			break;
		case 4:
			generatedType = new MiniBus(washer, id);
			break;
		default:
			throw new Exception("Invalid random number generated");
		}
		return generatedType;
	}
}
