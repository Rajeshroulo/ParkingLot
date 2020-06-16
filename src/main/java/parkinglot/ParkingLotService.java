package parkinglot;

import java.util.List;

public class ParkingLotService {
    private final int numberOfParkingSlots;
    List<ParkingSlot> parkingSlots;
    int currentSlot = 0;

    public ParkingLotService(List<ParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
        this.numberOfParkingSlots = parkingSlots.size();
    }

    public int getNumberOfParkingSlots(){
        return parkingSlots.size();
    }

    public void parkVehicle(Vehicle vehicle,Driver driver){
        this.directVehicle(vehicle,driver);
    }

    private void directVehicle(Vehicle vehicle,Driver driver) {
        for (int Slot = 0; Slot <= numberOfParkingSlots;Slot++) {
            if (parkingSlots.get(currentSlot).getOccupiedSpots() < parkingSlots.get(currentSlot).CAPACITY){
                parkingSlots.get(currentSlot).parkVehicle(vehicle, driver);
                return;
            }
            currentSlot++;
            if(currentSlot > numberOfParkingSlots-1)
                currentSlot = 0;
            this.checkParkingSlotsFull();
        }
    }

    private void checkParkingSlotsFull() {
        int numberOfLotsFull = 0;
        for (ParkingSlot parkingLot: parkingSlots) {
            if(parkingLot.getOccupiedSpots() == parkingLot.CAPACITY)
                numberOfLotsFull++;
        }
        if(numberOfLotsFull == numberOfParkingSlots)
            throw new ParkingLotException("All lots are full",ParkingLotException.ExceptionType.ALL_PARKING_LOTS_ARE_FULL);
    }

    public int getOccupiedSpotsInALot(ParkingSlot parkingSlot) {
        for (int i = 0; i < numberOfParkingSlots; i++) {
            if (parkingSlots.get(i).equals(parkingSlot))
                return parkingSlots.get(i).getOccupiedSpots();
        }
        throw new ParkingLotException("Not in the lots list",ParkingLotException.ExceptionType.NOT_IN_THE_SLOTS);

    }

    public void unParkVehicle(Vehicle vehicle, ParkingSlot parkingSlot) {
        parkingSlots.get(1).unparkVehicle(vehicle);
    }

}
