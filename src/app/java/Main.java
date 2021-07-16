package app.java;

import java.util.ArrayList;

import app.java.models.Driver;
import app.java.models.Rider;
import app.java.models.User;

public class Main {

	public static void main(String[] args) {
		Rider rider1 = new Rider(1, "Lucifer");
		Driver driver = new Driver(1, "Amenadiel");
		Rider rider2 = new Rider(2, "Chloe");
		Rider rider3 = new Rider(3, "Maze");
		
		ArrayList<Rider> riders = new ArrayList<Rider>();
		riders.add(rider1);
		riders.add(rider2);
		riders.add(rider3);
		
		User user = new User(3, riders);
		
		// Test Case 1 - When a ride is created, closed and then updated
		// Expected result - Fail/Error -> since a closed ride cannot be updated
		rider1.createRide(1, 50, 60, 1);
		System.out.println(rider1.closeRide(1));
		
		rider1.updateRide(1, 50, 60, 2);
		System.out.println(rider1.closeRide(1));
		
		System.out.println("*******************************************************************************************");
		
		user.createRide(1, 1, 50, 60, 1);
		user.withdrawRide(1, 1);
		user.updateRide(1, 1, 50, 60, 2);
		System.out.println(user.closeRide(1, 1));
		
		// Test Case 2 - When a ride is created, withdrawn and then updated
		// Expected result - Fail/Error -> since a withdrawn ride cannot be updated
//		rider1.createRide(1, 50, 60, 1);
//		rider1.withdrawRide(1);
//		rider1.updateRide(1, 50, 60, 2);
//		System.out.println(rider1.closeRide(1));
		
		System.out.println("*******************************************************************************************");
		
		// Test Case 3 - When a ride is created, updated and then closed
		// Expected result - Pass/Success
		user.createRide(1, 1, 50, 60, 1);
		user.updateRide(1, 1, 50, 60, 3);
		System.out.println(user.closeRide(1, 1));
	}	

}
