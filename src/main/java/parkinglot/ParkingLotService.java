package parkinglot;

import java.util.List;

public class ParkingLotService {
    private final int numberOfParkingLots;
    List<ParkingLot> parkingLots;
    int currentLot = 0;

    public ParkingLotService(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        this.numberOfParkingLots = parkingLots.size();
    }

    public int getNumberOfParkingLots(){
        return parkingLots.size();
    }

    public void parkVehicle(Vehicle vehicle,Driver driver){
        this.directVehicle(vehicle,driver);
    }

    private void directVehicle(Vehicle vehicle,Driver driver) {
        for (int lot = 0; lot <= numberOfParkingLots;lot++) {
            if (parkingLots.get(currentLot).getOccupiedSpots() < parkingLots.get(currentLot).CAPACITY){
                parkingLots.get(currentLot).parkVehicle(vehicle, driver);
                return;
            }
            currentLot++;
            if(currentLot > numberOfParkingLots-1)
                currentLot = 0;
            this.checkParkingLotsFull();
        }
    }

    private void checkParkingLotsFull() {
        int numberOfLotsFull = 0;
        for (ParkingLot parkingLot: parkingLots) {
            if(parkingLot.getOccupiedSpots() == parkingLot.CAPACITY)
                numberOfLotsFull++;
        }
        if(numberOfLotsFull == numberOfParkingLots)
            throw new ParkingLotException("All lots are full",ParkingLotException.ExceptionType.ALL_PARKING_LOTS_ARE_FULL);
    }

    public int getOccupiedSpotsInAlot(ParkingLot parkingLot) {
        return parkingLots.get(1).getOccupiedSpots();
    }

}
