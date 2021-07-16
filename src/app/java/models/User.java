package app.java.models;

import java.util.ArrayList;

public class User {
	private int availableDrivers;
	private ArrayList<Rider> riders;
	
	public User(int availableDrivers, ArrayList<Rider> riders) {
		if(availableDrivers < 2 || riders.size() < 2) {
			System.out.println("Not enough drivers or riders.");
		}
		
		this.availableDrivers = availableDrivers;
		this.riders = riders;
	}
	
	public void createRide(int riderId, int rideId, int origin, int dest, int seats) {
		if(availableDrivers == 0) {
			System.out.println("No drivers around your location. Cannot create this ride at the moment. Please try again later.");
			return;
		}
		
		for(Rider rider : riders) {
			if(rider.getRiderId() == riderId) {
				rider.createRide(rideId, origin, dest, seats);
				availableDrivers--;
				break;
			}
		}
	}
	
	public void updateRide(int riderId, int rideId, int origin, int dest, int seats) {
		for(Rider rider : riders) {
			if(rider.getRiderId() == riderId) {
				rider.updateRide(rideId, origin, dest, seats);
				availableDrivers--;
				break;
			}
		}
	}
	
	public void withdrawRide(int riderId, int rideId) {
		for(Rider rider : riders) {
			if(rider.getRiderId() == riderId) {
				rider.withdrawRide(rideId);
				availableDrivers++;
				break;
			}
		}
	}
	
	public double closeRide(int riderId, int rideId) {
		for(Rider rider : riders) {
			if(rider.getRiderId() == riderId) {
				availableDrivers++;
				return rider.closeRide(rideId);
			}
		}
		
		return 0.0;
	}
}
