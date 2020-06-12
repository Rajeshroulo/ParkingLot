package parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    Vehicle vehicle;
    int capacity;
    List<Vehicle> parkingDetails = new ArrayList<>();

    public ParkingLot(int capacity){
        this.capacity=capacity;
    }

    ParkingLotOwner parkingLotOwner= new ParkingLotOwner();
    AirportSecurity airportSecurity = new AirportSecurity();


    public boolean parkVehicle(Vehicle vehicle) {
         if (parkingDetails.size() == capacity )
            throw new ParkingLotException("Parking lot is full",
                    ParkingLotException.ExceptionType.PARKINGLOT_IS_FULL);
                   this.isFull();
        if(parkingDetails.contains(vehicle))
            throw new ParkingLotException("Entered vehicle number existing in the list",
                    ParkingLotException.ExceptionType.NUMBER_EXISTING);
        parkingDetails.add(vehicle);
        return true;
        }

    public boolean unparkVehicle(Vehicle vehicle) {
         if (!parkingDetails.contains(vehicle))
            throw new ParkingLotException("Entered vehicle number is not present", ParkingLotException.ExceptionType.NUMBER_IS_NOT_PRESENT);
        parkingDetails.remove(vehicle);
        return true;
    }

    public int getSize() {
            return parkingDetails.size();
        }

    private void isFull(){
        if (parkingDetails.size() == capacity ){
            parkingLotOwner.full();
            airportSecurity.full();
        }
    }

}
