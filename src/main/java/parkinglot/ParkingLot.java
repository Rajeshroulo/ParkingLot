package parkinglot;


import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


public class ParkingLot {
    Vehicle vehicle;
    private final int CAPACITY=5;
    Map<Vehicle,ParkedDetails> parkingDetails = new HashMap<>();
    boolean[] spots = new boolean[CAPACITY];
    List<ParkedDetails> parkedDetailsList  = new ArrayList<>();

    public ParkingLot(){
        for(int i=0; i < CAPACITY;i++){
            spots[i] = false;
        }

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
        int spot = this.getParkingSpot(vehicle);
        ParkedDetails parkedDetails = new ParkedDetails(vehicle,spot, System.currentTimeMillis());
        parkingDetails.put(vehicle,parkedDetails);
        parkedDetailsList.add(parkedDetails);
        return true;
        }

    private int getParkingSpot(Vehicle vehicle) {
        for(int i = 0; i < CAPACITY ;i++){
            if(!spots[i]) {
                spots[i] = true;
                if(vehicle.getDriver().equals(Driver.HANDICAPPED))
                    return this.getParkingSpotForDisabled(i+1);
                return i + 1;
            }
        }
        return -1;
    }

    public boolean unparkVehicle(Vehicle vehicle) {
         if (!parkingDetails.containsKey(vehicle))
            throw new ParkingLotException("Entered vehicle number is not present", ParkingLotException.ExceptionType.NUMBER_IS_NOT_PRESENT);
        this.setParkedSpot(vehicle);
        parkingDetails.remove(vehicle);
        parkingLotOwner.availableSpace(CAPACITY - parkingDetails.size());
        return true;
    }


    public int getParkedSpot(Vehicle vehicle) {
        return parkingDetails.get(vehicle).getSpot();
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

    public long getParkedTime(Vehicle vehicle) {
        return System.currentTimeMillis() - parkingDetails.get(vehicle).getParkedTime();
    }

    private void setParkedSpot(Vehicle vehicle) {
        int parkedSpot = this.getParkedSpot(vehicle);
        spots[parkedSpot-1] = false;
    }

    private int getParkingSpotForDisabled(int spot) {
        parkedDetailsList.get(0).setSpot(spot);
        spots[spot-1] = true;
        return 1;
    }


}
