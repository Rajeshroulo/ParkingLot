package parkinglot;

public class ParkedDetails {

    private final long parkedTime;
    private int spot;
    Vehicle vehicle;

    public ParkedDetails(Vehicle vehicle,int spot, long parkedTime) {
        this.spot = spot;
        this.parkedTime = parkedTime;
        this.vehicle = vehicle;

    }

    public long getParkedTime() {
        return parkedTime;
    }

    public void setSpot(int spot) {
        this.spot = spot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getSpot() {
        return spot;
    }

}
