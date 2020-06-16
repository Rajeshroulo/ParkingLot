package parkinglot;

import java.util.Objects;
import java.awt.*;

public class Vehicle {
    private String vehicleNumber;
    private final VehicleSize vehicleSize;
    private final VehicleColor vehicleColor;

    public Vehicle(String vehicleNumber, VehicleSize vehicleSize, VehicleColor vehicleColor ){
        if(vehicleNumber == null)
            throw new ParkingLotException("Entered Null", ParkingLotException.ExceptionType.ENTERED_NULL);
        if(vehicleNumber.length() == 0)
            throw new ParkingLotException("Entered Empty", ParkingLotException.ExceptionType.ENTERED_EMPTY);
        this.vehicleNumber = vehicleNumber;
        this.vehicleSize = vehicleSize;
        this.vehicleColor = vehicleColor;
    }

    public VehicleColor getVehicleColor() {
        return vehicleColor;
    }

    enum VehicleSize {
        SMALL,LARGE
    }

    enum VehicleColor{
        WHITE,BLACK
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(vehicleNumber, vehicle.vehicleNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleNumber);
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleSize getVehicleSize() {
        return vehicleSize;
    }

}
