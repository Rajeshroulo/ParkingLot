package parkinglot;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;



public class ParkingLotService {

    private final int numberOfParkingSlots;
    List<ParkingSlot> parkingSlots;
    int currentSlot = 0;

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
         return this.getAllParkedDetails().stream()
                .map(ParkedDetails::getVehicle)
                .collect(Collectors.toList());

    }

    private List<ParkedDetails> getAllParkedDetails() {
        List<ParkedDetails> parkedDetailsList = new ArrayList<>();
        for (ParkingSlot parkingSlot : parkingSlots) {
            Collection<ParkedDetails> parkedDetailsListInASlot = parkingSlot.parkingDetails.values();
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

    public List<Vehicle> getVehicleTimings(long time){
        return this.getAllParkedDetails().stream()
                .filter(parkedDetails -> (parkedDetails.getParkedTime() - System.currentTimeMillis()) * 0.000016667 <= time)
                .map(ParkedDetails::getVehicle)
                .collect(Collectors.toList());
    }

    public List<String> getAllVehicleDetails() {
        return this.getAllParkedDetails().stream()
                .map(parkedDetails -> parkedDetails.getVehicle().getVehicleNumber())
                .collect(Collectors.toList());
    }

}
