package app.java.models;

class Ride {
	private int rideId;
	private int origin, dest;
	private int seats;
	private RideStatus rideStatus;
	private static final int AMT_PER_KM = 20;
	
	Ride() {
		rideId = origin = dest = seats = 0;
		rideStatus = RideStatus.IDLE;
	}
	
	public double calculateFare(boolean isPreferred) {
		int distance = dest - origin;
		double fare = 0.0;
		
		if(seats < 2) {
			fare = distance * AMT_PER_KM * (isPreferred ? 0.75 : 1);
		} else if(seats >= 2) {
			fare = distance * AMT_PER_KM * (isPreferred ? 0.5 : 0.75);
		}
		
		return fare;
	}
	
	public final int getRideId() {
		return rideId;
	}
	
	public void setRideId(int rideId) {
		this.rideId = rideId;
	}
	
	public final  RideStatus getRideStatus() {
		return rideStatus;
	}
	
	public void setRideStatus(RideStatus rideStatus) {
		this.rideStatus = rideStatus;
	}
	
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	
	public void setDest(int dest) {
		this.dest = dest;
	}
	
	public final void setSeats(int seats) {
		this.seats = seats;
	}
}

