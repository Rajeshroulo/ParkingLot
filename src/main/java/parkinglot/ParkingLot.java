package parkinglot;


import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    Vehicle vehicle;
    private final int CAPACITY;
    Map<Vehicle,Integer> parkingDetails = new HashMap<>();
    int occupiedSpots = 0;


    public ParkingLot(int capacity){
        this.CAPACITY=capacity;
    }

    ParkingLotOwner parkingLotOwner= new ParkingLotOwner();
    AirportSecurity airportSecurity = new AirportSecurity();

    public boolean parkVehicle(Vehicle vehicle) {
         if (parkingDetails.size() == CAPACITY )
            throw new ParkingLotException("Parking lot is full",
                    ParkingLotException.ExceptionType.PARKINGLOT_IS_FULL);
                   this.isFull();
        if(parkingDetails.containsKey(vehicle))
            throw new ParkingLotException("Entered vehicle number existing in the list",
                    ParkingLotException.ExceptionType.NUMBER_EXISTING);
        parkingDetails.put(vehicle,++occupiedSpots);
        return true;
        }

    public boolean unparkVehicle(Vehicle vehicle) {
         if (!parkingDetails.containsKey(vehicle))
            throw new ParkingLotException("Entered vehicle number is not present", ParkingLotException.ExceptionType.NUMBER_IS_NOT_PRESENT);
        parkingDetails.remove(vehicle);
        occupiedSpots--;
        parkingLotOwner.availableSpace(CAPACITY - parkingDetails.size());
        return true;
    }

    public int getParkedSpot(Vehicle vehicle) {
        return parkingDetails.get(vehicle);
    }

    public int getOccupiedSpots() {
            return parkingDetails.size();
        }

    private void isFull(){
        if (parkingDetails.size() == CAPACITY ){
            parkingLotOwner.full();
            airportSecurity.full();
        }
    }

}
