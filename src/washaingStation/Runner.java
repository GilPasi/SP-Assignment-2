package washaingStation;
/*System-Prgramming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
import java.util.ArrayList;
import java.util.Scanner;


public class Runner {

	public static void main(String[] args) {
		
	
		//Declare variables
		Scanner s = new Scanner(System.in);
		final int DISQ_VAL = -1;//Disqualify value so the first loop will occur
		int leftVehicles = DISQ_VAL;
		int washingPositions = DISQ_VAL;
		double creationGap = DISQ_VAL;
		double washGap = DISQ_VAL;
			
			//Gather all necessary data
		System.out.println("Welcome to the wehicle washer!\n"
				+ "How many vehicles for today?");
		
		while(leftVehicles <= 0) {
			leftVehicles = s.nextInt();
			
			if(leftVehicles <= 0)
				System.out.println("Invalid input, please enter again");
			
		}
			
			System.out.println("Please enter the amount of washin positions");
			
			while(washingPositions <= 0) {
				washingPositions = s.nextInt();
				
				if(washingPositions <= 0)
					System.out.println("Invalid input, please enter again");
				
			}
			
			
			
			System.out.println("Please enter the LAMDA_1 (gap between vehicle creations)");
			
			while(creationGap <= 0) {
				creationGap = s.nextDouble();
				
				if(creationGap <= 0)
					System.out.println("Invalid input, please enter again");
				
			}
			
			System.out.println("Please enter the LAMDA_2 (gap between vehicle washes)");
			
			while(washGap <= 0) {
				washGap = s.nextDouble();
				
				if(washGap <= 0)
					System.out.println("Invalid input, please enter again");
			
				
			}	
			
			//Create washing area
			WehicleWasher ww = new WehicleWasher(washGap,washingPositions,leftVehicles);
			
			//Create all today's customers
			ArrayList<Thread> customers = new ArrayList<>();
			for(int i = 0; i < leftVehicles; ++i)
				customers.add(new Thread (generateVehicle(ww)));
			
			//Start work day
			workDay(ww,leftVehicles,creationGap,customers);
			
			//Sum work day
			multipleJoin(customers);
			ww.printPostWash();
			
	}
	
	
	public static void multipleJoin(ArrayList<Thread> array) {
		
		for(int i = 0; i < array.size(); ++i)
			try {
				array.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}
	
	
	public static void workDay(WehicleWasher ww,int  leftVehicles,double creationGap,ArrayList<Thread> customers) {
		/**This method  simulates the whole day. Initially all vehicles are created.
		 * Their activation-gaps is calculated by poisson distribution(WehicleWasher static method).
		 * In This way we guarantee different arrival times.
		 * */
		
		//Create instances

		
		for(int i = 0; i < leftVehicles; ++i) {
			
			customers.get(i).start();
			
			try {
				Thread.sleep(WehicleWasher.calcNext(creationGap));//Sleep as calculated (Poisson distribution)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static  Runnable generateVehicle(WehicleWasher ww)  {
		 int indicator =  (int) ((Math.random() * (5 - 1)) + 1);
		 Runnable returnWehicle = null;
		 
		 switch(indicator) {
		 case 1: 
			 returnWehicle = new Car(ww);
			 break;
		 case 2: 
			 returnWehicle = new Truck(ww);
			 break;

		 case 3: 
			 returnWehicle = new SUV(ww);
			 break;

		 case 4: 
			 returnWehicle = new MiniBus(ww);
			 break;

		 default:
			 System.out.println("Bad random number generated");
			  
		 }
		 return returnWehicle;

	}
}
