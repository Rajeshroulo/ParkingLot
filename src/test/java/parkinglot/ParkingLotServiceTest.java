package parkinglot;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceTest {

    List<ParkingSlot> parkingLotList = new ArrayList<>();

    @Test
    public void givenNumberOfParkingLots_whenCreated_shouldReturnNumberOfLots() {
        ParkingSlot parkingSlot1 = new ParkingSlot(4);
        ParkingSlot parkingSlot2 = new ParkingSlot(5);
        parkingLotList.add(parkingSlot1);
        parkingLotList.add(parkingSlot2);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotList);
        int numberOfParkingLots = parkingLotService.getNumberOfParkingSlots();
        Assert.assertEquals(2,numberOfParkingLots);
    }

    @Test
    public void givenVehicleNumberToPark_whenParked_shouldReturnOccupiedSpotsInALot() {
        ParkingSlot parkingSlot1 = new ParkingSlot(1);
        ParkingSlot parkingSlot2 = new ParkingSlot(3);
        List<ParkingSlot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingSlot1);
        parkingLotList.add(parkingSlot2);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotList);
        parkingLotService.parkVehicle(new Vehicle("TS01AB1234",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE),Driver.NORMAL);
        parkingLotService.parkVehicle(new Vehicle("TS02AB5678",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.BLACK), Driver.NORMAL);
        parkingLotService.parkVehicle(new Vehicle("TS03AB1234",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE), Driver.NORMAL);
        int occupiedSpotsInAlot = parkingLotService.getOccupiedSpotsInASlot(parkingSlot2);
        Assert.assertEquals(2,occupiedSpotsInAlot);
    }

    @Test
    public void givenVehicleNumberToPark_whenAllParkingLotsAreFull_shouldThrowException() {
        ParkingSlot parkingSlot1 = new ParkingSlot(1);
        ParkingSlot parkingSlot2 = new ParkingSlot(3);
        parkingLotList.add(parkingSlot1);
        parkingLotList.add(parkingSlot2);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotList);
        try {
            parkingLotService.parkVehicle(new Vehicle("TS01AB1234",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS02AB5678",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS03AB1234",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.BLACK), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS04AB5678",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS05AB4567",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.BLACK), Driver.NORMAL);
        } catch(ParkingLotException e){
            Assert.assertEquals(ParkingLotException.ExceptionType.ALL_PARKING_LOTS_ARE_FULL,e.type);
        }
    }

    @Test
    public void givenVehicleNumberToUnPark_whenUnParked_shouldReturnOccupiedSpots() {
        ParkingSlot parkingSlot1 = new ParkingSlot(5);
        ParkingSlot parkingSlot2 = new ParkingSlot(7);
        parkingLotList.add(parkingSlot1);
        parkingLotList.add(parkingSlot2);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotList);
        int numberOfParkingLots = parkingLotService.getNumberOfParkingSlots();
        Assert.assertEquals(2,numberOfParkingLots);
    }

    @Test
    public void givenVehicleNumberToPark_whenParked_shouldReturnTotalVehicles() {
        ParkingSlot parkingSlot1 = new ParkingSlot(3);
        ParkingSlot parkingSlot2 = new ParkingSlot(5);
        parkingLotList.add(parkingSlot1);
        parkingLotList.add(parkingSlot2);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotList);
            parkingLotService.parkVehicle(new Vehicle("TS01AB1234",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS02AB5678",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS03AB1234",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.BLACK), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS04AB5678",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS05AB4567",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.BLACK), Driver.NORMAL);
        int totalVehicles = parkingLotService.getNumberOfVehiclesInParkingLot();
        Assert.assertEquals(5,totalVehicles);
    }

    @Test
    public void givenVehicleNumbersToPark_whenDriverDisAbled_shouldAllocateNearestSlot() {
        ParkingSlot parkingSlot1 = new ParkingSlot(2);
        ParkingSlot parkingSlot2 = new ParkingSlot(2);
        List<ParkingSlot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingSlot1);
        parkingLotList.add(parkingSlot2);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotList);
        Vehicle vehicle1 = new Vehicle("AP30M2832",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE);
        parkingLotService.parkVehicle(vehicle1, Driver.NORMAL);
        Vehicle vehicle2 = new Vehicle("OD27M5160",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE);
        parkingLotService.parkVehicle(vehicle2, Driver.HANDICAPPED);
        ParkingSlot parkedSlot = parkingLotService.getParkedSlot(vehicle2);
        Assert.assertEquals(parkingSlot1,parkedSlot);
        Assert.assertEquals(1,parkingLotService.getParkedSpot(vehicle2));
    }

    @Test
    public void givenVehicleNumbersToPark_whenVehicleIsLarge_shouldAllocateRequiredSpace() {
        ParkingSlot parkingSlot1 = new ParkingSlot(4);
        ParkingSlot parkingSlot2 = new ParkingSlot(5);
        List<ParkingSlot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingSlot1);
        parkingLotList.add(parkingSlot2);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotList);
        Vehicle vehicle1 = new Vehicle("AP30M2832",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE);
        parkingLotService.parkVehicle(vehicle1, Driver.NORMAL);
        Vehicle vehicle2 = new Vehicle("AP30M2364",Vehicle.VehicleSize.LARGE,Vehicle.VehicleColor.WHITE);
        parkingLotService.parkVehicle(vehicle2, Driver.NORMAL);
        ParkingSlot parkedSlot = parkingLotService.getParkedSlot(vehicle2);
        Assert.assertEquals(parkingSlot1,parkedSlot);
    }

}
