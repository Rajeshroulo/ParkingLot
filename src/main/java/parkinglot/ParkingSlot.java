package parkinglot;

import java.util.*;


public class ParkingSlot {
    Vehicle vehicle;
    public final int CAPACITY;
    Map<Vehicle, ParkedDetails> parkingDetails = new HashMap<>();
    boolean[] spots;
    List<ParkedDetails> parkedDetailsList = new ArrayList<>();

    public ParkingSlot(int capacity) {
        this.CAPACITY = capacity;
        spots = new boolean[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            spots[i] = false;
        }

    }

    ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
    AirportSecurity airportSecurity = new AirportSecurity();

    public boolean parkVehicle(Vehicle vehicle, Driver driver) {
        if (vehicle == null && driver == null)
            throw new ParkingLotException("Entered Null", ParkingLotException.ExceptionType.ENTERED_NULL);
        if (parkedDetailsList.size() == CAPACITY)
            throw new ParkingLotException("Parking lot is full",
                    ParkingLotException.ExceptionType.PARKINGLOT_IS_FULL);
        if (parkingDetails.containsKey(vehicle))
            throw new ParkingLotException("Entered vehicle number existing in the list",
                    ParkingLotException.ExceptionType.NUMBER_EXISTING);
        vehicleDistribution(vehicle, driver);
        return true;
    }

    private void vehicleDistribution(Vehicle vehicle, Driver driver) {
        int spot = 0;
        for (int i = 0; i < CAPACITY; i++) {
            if (!spots[i]) {
                spots[i] = true;
                spot = i + 1;
                break;
            }
        }
        ParkedDetails parkedDetails = new ParkedDetails(vehicle, driver, spot, System.currentTimeMillis());
        parkedDetailsList.add(parkedDetails);
        int swapSpot = 0;
        if (driver.equals(Driver.HANDICAPPED)) {
            for (int i = 0; i < parkedDetailsList.size(); i++) {
                if (parkedDetailsList.get(i).getDriver().equals(Driver.NORMAL)) {
                    swapSpot = i + 1;
                    break;
                }
            }
            parkedDetailsList.get(swapSpot - 1).setSpot(spot);
            parkedDetailsList.get(spot - 1).setSpot(swapSpot);
            Collections.swap(parkedDetailsList, swapSpot - 1, spot - 1);
        }
        parkingDetails.put(vehicle, parkedDetails);
        this.isFull();
    }

    public boolean unparkVehicle(Vehicle vehicle) {
        if (vehicle == null)
            throw new ParkingLotException("Entered Null", ParkingLotException.ExceptionType.ENTERED_NULL);
        if (!parkingDetails.containsKey(vehicle))
            throw new ParkingLotException("Entered vehicle number is not present",
                    ParkingLotException.ExceptionType.NUMBER_IS_NOT_PRESENT);
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

    private void isFull() {
        if (parkingDetails.size() == CAPACITY) {
            parkingLotOwner.full(true);
            airportSecurity.full(true);
        }
    }

    public long getParkedTime(Vehicle vehicle) {
        return System.currentTimeMillis() - parkingDetails.get(vehicle).getParkedTime();
    }

    private void setParkedSpot(Vehicle vehicle) {
        int parkedSpot = this.getParkedSpot(vehicle);
        spots[parkedSpot - 1] = false;
    }

}
