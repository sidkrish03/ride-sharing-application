package app.java.models;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.ListIterator;

public class Rider extends Person {
	private static ArrayList<Ride> allRides = new ArrayList<Ride>(); // Contains completed rides as well as ongoing rides
	
	public Rider(String name) {
		this.name = name;
	}
	
	public Ride getOngoingRide(int id) {	
		Ride ongoingRide = new Ride();
		
		for(int i = allRides.size() - 1; i >= 0; i--) {
			if(allRides.get(i).getId() == id) {
				ongoingRide = allRides.get(i);
				break;
			}
		}
		
		return ongoingRide;
	}
	
	public void createRide(int id, int origin, int dest, int seats) {
		if(origin >= dest) {
			System.out.println("Invalid values of Origin and Destination provided. Cannot create ride. Please try again!");
			return;
		}
		
		Ride currentRide = new Ride();
		
		currentRide.setId(id);
		currentRide.setOrigin(origin);
		currentRide.setDest(dest);
		currentRide.setSeats(seats);
		currentRide.setRideStatus(RideStatus.CREATED);
		
		allRides.add(currentRide);
		
//		for(Ride r : allRides) {
//			System.out.println(r.getRideStatus());
//		}
	}
	
	public void updateRide(int id, int origin, int dest, int seats) {
		Ride rideToUpdate = getOngoingRide(id);
		
		if(rideToUpdate.getRideStatus() != RideStatus.CREATED) {
			System.out.println("Ride not in progress. Cannot update ride.");
			return;
		}
		
		rideToUpdate.setOrigin(origin);
		rideToUpdate.setDest(dest);
		rideToUpdate.setSeats(seats);
	}
	
	public void withdrawRide(int id) {
		Ride rideToWithdraw = getOngoingRide(id);
		
		if(rideToWithdraw.getRideStatus() != RideStatus.CREATED) {
			System.out.println("Ride not in progress. Cannot withdraw ride.");
			return;
		}
		
		rideToWithdraw.setRideStatus(RideStatus.WITHDRAWN);
		
		allRides.remove(rideToWithdraw);
	}
	
	public double closeRide(int id) {
		Ride rideToClose = getOngoingRide(id);
		
		if(rideToClose.getRideStatus() != RideStatus.CREATED) {
			System.out.println("Ride not in progress. Cannot close ride.");
			return 0.0;
		}
		
		rideToClose.setRideStatus(RideStatus.COMPLETED);
		
		return rideToClose.calculateFare(allRides.size() >= 10);
	}
}
