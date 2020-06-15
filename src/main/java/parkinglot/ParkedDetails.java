package parkinglot;

import java.util.Objects;

public class ParkedDetails {

    private final long parkedTime;
    private int spot;
    private final Driver driver;
    private final Vehicle vehicle;

    public ParkedDetails(Vehicle vehicle,Driver driver,int spot, long parkedTime) {
        this.vehicle = vehicle;
        this.driver=driver;
        this.spot = spot;
        this.parkedTime = parkedTime;
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

    public Driver getDriver() {
        return driver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkedDetails that = (ParkedDetails) o;
        return parkedTime == that.parkedTime &&
                spot == that.spot &&
                Objects.equals(vehicle, that.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicle, parkedTime, spot);
    }



}
