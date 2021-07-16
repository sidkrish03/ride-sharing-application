package app.java;

import app.java.models.Driver;
import app.java.models.Rider;

public class Main {

	public static void main(String[] args) {
		Rider rider = new Rider("Lucifer");
		Driver driver = new Driver("Amenadiel");
		
		// Test Case 1 - When a ride is created, closed and then updated
		// Expected result - Fail/Error -> since a closed ride cannot be updated
		rider.createRide(1, 50, 60, 1);
		System.out.println(rider.closeRide(1));
		
		rider.updateRide(1, 50, 60, 2);
		System.out.println(rider.closeRide(1));
		
		System.out.println("*******************************************************************************************");
		
		// Test Case 2 - When a ride is created, withdrawn and then updated
		// Expected result - Fail/Error -> since a withdrawn ride cannot be updated
		rider.createRide(1, 50, 60, 1);
		rider.withdrawRide(1);
		rider.updateRide(1, 50, 60, 2);
		System.out.println(rider.closeRide(1));
		
		System.out.println("*******************************************************************************************");
		
		// Test Case 3 - When a ride is created, updated and then closed
		// Expected result - Pass/Success
		rider.createRide(1, 50, 60, 1);
		rider.updateRide(1, 50, 60, 3);
		System.out.println(rider.closeRide(1));
	}	

}
