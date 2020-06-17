package parkinglot;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;



public class ParkingLotService {

    private final int numberOfParkingSlots;
    List<ParkingSlot> parkingSlots;
    List<Vehicle> totalVehicles = new ArrayList<>();
    int currentSlot = 0;

    ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
    AirportSecurity airportSecurity = new AirportSecurity();
    ParkingStrategy parkingStrategy = new ParkingStrategy();



    public ParkingLotService(List<ParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
        this.numberOfParkingSlots = parkingSlots.size();
    }

    public int getNumberOfParkingSlots(){
        return parkingSlots.size();
    }

    public void parkVehicle(Vehicle vehicle,Driver driver){
        if(vehicle == null && driver == null)
            throw new ParkingLotException("Entered null",ParkingLotException.ExceptionType.ENTERED_NULL);
        parkingStrategy.parkVehicle(parkingSlots,vehicle,driver);
    }

    private void directVehicle(Vehicle vehicle, Driver driver) {
        for (int slot = 0; slot <= numberOfParkingSlots; slot++) {
            if(currentSlot == numberOfParkingSlots)
              currentSlot = 0;
           if (parkingSlots.get(currentSlot).getOccupiedSpots() < parkingSlots.get(currentSlot).CAPACITY){
             parkingSlots.get(currentSlot).parkVehicle(vehicle, driver);
              currentSlot++;
              return;
         }
          currentSlot++;
  //      this.checkParkingSlotsFull();
     }

    }

    private void checkParkingSlotsFull() {
        int numberOfLotsFull = 0;
        for (ParkingSlot parkingLot: parkingSlots) {
            if(parkingLot.getOccupiedSpots() == parkingLot.CAPACITY)
                numberOfLotsFull++;
        }
        if(numberOfLotsFull == numberOfParkingSlots)
            parkingLotOwner.full(true);
            airportSecurity.full(true);
        throw new ParkingLotException("All lots are full",ParkingLotException.ExceptionType.ALL_PARKING_LOTS_ARE_FULL);
    }

    public int getOccupiedSpotsInASlot(ParkingSlot parkingSlot) {
        for (int i = 0; i < numberOfParkingSlots; i++) {
            if (parkingSlots.get(i).equals(parkingSlot))
                return parkingSlots.get(i).getOccupiedSpots();
        }
        throw new ParkingLotException("Not in the lots list",ParkingLotException.ExceptionType.NOT_IN_THE_SLOTS);

    }

    public void unparkVehicle(Vehicle vehicle ) {
        if(vehicle==null)
            throw new ParkingLotException("Entered null value",ParkingLotException.ExceptionType.ENTERED_NULL);
        ParkingSlot parkingSlot = this.getParkedSlot(vehicle);
        if(parkingSlot == null)
            throw new ParkingLotException("Entered vehicle not in list",ParkingLotException.ExceptionType.NOT_IN_THE_SLOTS);
        parkingSlot.unparkVehicle(vehicle);
    }

    public ParkingSlot getParkedSlot(Vehicle vehicle) {
        for(ParkingSlot parkingSlot: parkingSlots)
            if(parkingSlot.parkingDetails.containsKey(vehicle))
                return parkingSlot;
        return null;
    }

    public int getParkedSpot(Vehicle vehicle){
        ParkingSlot parkingSlot = this.getParkedSlot(vehicle);
        return parkingSlot.getParkedSpot(vehicle);
    }

    public int getAvailableSpotsInASlot(ParkingSlot parkingSlot) {
        return parkingSlot.CAPACITY - parkingSlot.parkingDetails.size();
    }

    private List<Vehicle> getListOfVehiclesInParkingLot() {
        totalVehicles = this.getAllParkedDetails().stream()
                .map(ParkedDetails::getVehicle)
                .collect(Collectors.toList());
        return totalVehicles;
    }

    private List<ParkedDetails> getAllParkedDetails() {
        List<ParkedDetails> parkedDetailsList = new ArrayList<>();
        for(int i = 0 ; i < numberOfParkingSlots ; i++){
            Collection<ParkedDetails> parkedDetailsListInASlot = parkingSlots.get(i).parkingDetails.values();
            parkedDetailsList.addAll(parkedDetailsListInASlot);
        }
        return parkedDetailsList;

    }

    public int getNumberOfVehiclesInParkingLot(){
        return this.getListOfVehiclesInParkingLot().size();
    }

    public List<Vehicle> getAllVehiclesBasedOnColor(Vehicle.VehicleColor color) {
        List<Vehicle> listOfVehiclesInParkingLot = this.getListOfVehiclesInParkingLot();
        return listOfVehiclesInParkingLot.stream()
                .filter(vehicle -> vehicle.getVehicleColor().equals(color))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesBasedonBrand(Vehicle.VehicleBrand vehicleBrand) {
        List<Vehicle> listOfVehiclesInParkingLot = this.getListOfVehiclesInParkingLot();
        return listOfVehiclesInParkingLot.stream()
                .filter(vehicle -> vehicle.getVehicleBrand().equals(vehicleBrand))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesBasedonSize(Vehicle.VehicleSize vehicleSize) {
        List<Vehicle> listOfVehiclesInParkingLot = this.getListOfVehiclesInParkingLot();
        return listOfVehiclesInParkingLot.stream()
                .filter(vehicle -> vehicle.getVehicleSize().equals(vehicleSize))
                .collect(Collectors.toList());
    }


}
