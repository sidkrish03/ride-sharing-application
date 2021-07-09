package app.java.models;

import java.util.ArrayList;
import java.util.List;

public class Rider extends Person {
	private List<Ride> completedRides = new ArrayList<Ride>();
	private Ride currentRide = new Ride();
	
	public Rider(String name) {
		this.name = name;
	}
	
	public void createRide(int id, int origin, int dest, int seats) {
		if(origin >= dest) {
			System.out.println("Invalid values of Origin and Destination provided. Cannot create ride. Please try again!");
			return;
		}
		
		currentRide.setId(id);
		currentRide.setOrigin(origin);
		currentRide.setDest(dest);
		currentRide.setSeats(seats);
		currentRide.setRideStatus(RideStatus.CREATED);
	}
	
	public void updateRide(int id, int origin, int dest, int seats) {
		if(currentRide.getRideStatus() == RideStatus.WITHDRAWN) {
			System.out.println("Cannot update ride as the current ride has been withdrawn already. ");
			return;
		}
		
		if(currentRide.getRideStatus() == RideStatus.COMPLETED) {
			System.out.println("Cannot update ride the current ride is already completed.");
			return;
		}
		
		createRide(id, origin, dest, seats);
	}
	
	public void withdrawRide(int id) {
		if(currentRide.getId() != id) {
			System.out.println("Invalid ride Id. Cannot withdraw ride!");
			return;
		}
		
		if(currentRide.getRideStatus() != RideStatus.CREATED) {
			System.out.println("No rides currently in progress. Cannt withdraw ride!");
		}
		
		currentRide.setRideStatus(RideStatus.WITHDRAWN);
	}
	
	public double closeRide() {
		if(currentRide.getRideStatus() != RideStatus.CREATED) {
			System.out.println("No rides currently in progress. Cannt close ride!");
			return 0.0;
		}
		
		currentRide.setRideStatus(RideStatus.COMPLETED);
		completedRides.add(currentRide);
		
		return currentRide.calculateFare(completedRides.size() >= 10);
	}
}
