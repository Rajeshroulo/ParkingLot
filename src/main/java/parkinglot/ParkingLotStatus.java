package parkinglot;

public class ParkingLotStatus {

    private ParkingLot parkingLot;

    public void add(String vehcileNumber) {
        parkingLot.park(vehcileNumber);
    }

    public void remove(String vehcileNumber) {
        parkingLot.unpark(vehcileNumber);
    }


    public int getParkingLotSize() {
        return parkingLot.getSize();
    }

}
