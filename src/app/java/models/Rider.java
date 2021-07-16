package app.java.models;

import java.util.ArrayList;

public class Rider extends Person {
	private int riderId;
	private static ArrayList<Ride> allRides = new ArrayList<Ride>(); // Contains completed rides as well as ongoing rides
	
	public Rider(int riderId, String name) {
		this.riderId = riderId;
		this.name = name;
	}
	
	public int getRiderId() {
		return riderId;
	}

	public Ride getOngoingRide(int rideId) {	
		Ride ongoingRide = new Ride();
		
		for(int i = allRides.size() - 1; i >= 0; i--) {
			if(allRides.get(i).getRideId() == rideId) {
				ongoingRide = allRides.get(i);
				break;
			}
		}
		
		return ongoingRide;
	}
	
	public void createRide(int rideId, int origin, int dest, int seats) {
		if(origin >= dest) {
			System.out.println("Invalid values of Origin and Destination provided. Cannot create ride. Please try again!");
			return;
		}
		
		Ride currentRide = new Ride();
		
		currentRide.setRideId(rideId);
		currentRide.setOrigin(origin);
		currentRide.setDest(dest);
		currentRide.setSeats(seats);
		currentRide.setRideStatus(RideStatus.CREATED);
		
		allRides.add(currentRide);
	}
	
	public void updateRide(int rideId, int origin, int dest, int seats) {
		Ride rideToUpdate = getOngoingRide(rideId);
		
		if(rideToUpdate.getRideStatus() != RideStatus.CREATED) {
			System.out.println("Ride not in progress. Cannot update ride.");
			return;
		}
		
		rideToUpdate.setOrigin(origin);
		rideToUpdate.setDest(dest);
		rideToUpdate.setSeats(seats);
	}
	
	public void withdrawRide(int rideId) {
		Ride rideToWithdraw = getOngoingRide(rideId);
		
		if(rideToWithdraw.getRideStatus() != RideStatus.CREATED) {
			System.out.println("Ride not in progress. Cannot withdraw ride.");
			return;
		}
		
		rideToWithdraw.setRideStatus(RideStatus.WITHDRAWN);
		
		allRides.remove(rideToWithdraw);
	}
	
	public double closeRide(int rideId) {
		Ride rideToClose = getOngoingRide(rideId);
		
		if(rideToClose.getRideStatus() != RideStatus.CREATED) {
			System.out.println("Ride not in progress. Cannot close ride.");
			return 0.0;
		}
		
		rideToClose.setRideStatus(RideStatus.COMPLETED);
		
		return rideToClose.calculateFare(allRides.size() >= 10);
	}
}
