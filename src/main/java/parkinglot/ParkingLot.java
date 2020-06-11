package parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    List<String> parkingDetails = new ArrayList<>();

    public boolean park(String vehicleNumber) {
            if(vehicleNumber == null)
                throw new ParkingLotException("Entered Null", ParkingLotException.ExceptionType.ENTERED_NULL);
            if(vehicleNumber.length() == 0)
                throw new ParkingLotException("Entered Empty", ParkingLotException.ExceptionType.ENTERED_EMPTY);
            parkingDetails.add(vehicleNumber);
            return true;
        }

    public boolean unpark(String vehicleNumber) {
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
