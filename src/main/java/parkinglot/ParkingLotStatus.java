package parkinglot;

public class ParkingLotStatus {

    private ParkingLot parkingLot;

    public void add(String vehcileNumber,Customer customer) {
        parkingLot.add(vehcileNumber,customer);
    }

    public int getParkingLotSize() {
        return parkingLot.getSize();
    }

}
