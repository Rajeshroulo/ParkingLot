package parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    Vehicle vehicle;
    int capacity =5;
    List<String> parkingDetails = new ArrayList<>();

    public ParkingLot(int capacity){
        this.capacity=capacity;
    }

    public ParkingLot() {

    }

    public boolean parkVehicle(String vehicleNumber) {
        if(vehicleNumber == null)
            throw new ParkingLotException("Entered Null", ParkingLotException.ExceptionType.ENTERED_NULL);
        if(vehicleNumber.length() == 0)
            throw new ParkingLotException("Entered Empty", ParkingLotException.ExceptionType.ENTERED_EMPTY);
        if (parkingDetails.size() == capacity )
            throw new ParkingLotException("Parking lot is full",
                    ParkingLotException.ExceptionType.PARKINGLOT_IS_FULL);
               parkingDetails.add(vehicleNumber);
            return true;
        }

    public boolean unparkVehicle(String vehicleNumber) {
        if(vehicleNumber == null)
            throw new ParkingLotException("Entered Null", ParkingLotException.ExceptionType.ENTERED_NULL);
        if(vehicleNumber.length() == 0)
            throw new ParkingLotException("Entered Empty", ParkingLotException.ExceptionType.ENTERED_EMPTY);
        parkingDetails.remove(vehicleNumber);
        return true;
    }


    public int getSize() {
            return parkingDetails.size();
        }
}
