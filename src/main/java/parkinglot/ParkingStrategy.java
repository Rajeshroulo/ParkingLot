package parkinglot;

import java.util.List;

public class ParkingStrategy {
    private int currentSlot = 0;
    public void parkVehicle(List<ParkingSlot> parkingSlots, Vehicle vehicle, Driver driver) {
        if(vehicle.getVehicleSize().equals(Vehicle.VehicleSize.SMALL)&& driver.equals(Driver.NORMAL)) {
            int numberOfSlots = parkingSlots.size();
            for (int slot = 0; slot <= numberOfSlots; slot++) {
                if (currentSlot == numberOfSlots)
                    currentSlot = 0;
                if (parkingSlots.get(currentSlot).getOccupiedSpots() < parkingSlots.get(currentSlot).CAPACITY) {
                    parkingSlots.get(currentSlot).parkVehicle(vehicle, driver);
                    currentSlot++;
                    return;
                }
                currentSlot++;
            }
        }

        if(driver.equals(Driver.HANDICAPPED)){
            int numberOfSlots = parkingSlots.size();
            for (int slot = 0; slot <= numberOfSlots; slot++) {
                if (parkingSlots.get(slot).getOccupiedSpots() < parkingSlots.get(slot).CAPACITY) {
                    parkingSlots.get(slot).parkVehicle(vehicle, driver);
                    return;
                }
            }
        }

        if(vehicle.getVehicleSize().equals(Vehicle.VehicleSize.LARGE)){
            int numberOfSlots = parkingSlots.size();
            int requiredSpots = 2;
            for (int slot = 0; slot < numberOfSlots; slot++) {
                int availableSpots = parkingSlots.get(slot).CAPACITY - parkingSlots.get(slot).getOccupiedSpots();
                if (availableSpots >= requiredSpots) {
                    parkingSlots.get(slot).parkVehicle(vehicle, driver);
                    return;
                }
            }
        }
    }
}
