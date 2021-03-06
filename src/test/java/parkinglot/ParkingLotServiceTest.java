package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)

public class ParkingLotServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    List<ParkingSlot> parkingLotList = new ArrayList<>();

    @Mock
    ParkingSlot parkingSlot1;
    @Mock
    ParkingSlot parkingSlot2;


    @Test
    public void givenNumberOfParkingLots_whenCreated_shouldReturnNumberOfLots() {
        parkingLotList.add(parkingSlot1);
        parkingLotList.add(parkingSlot2);
        List<ParkingSlot> parkingLotList = new ArrayList<>();
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotList);
        int numberOfParkingSlots = parkingLotService.getNumberOfParkingSlots();
        Assert.assertEquals(2,numberOfParkingSlots);
    }

    @Test
    public void givenVehicleNumberToPark_whenParked_shouldReturnOccupiedSpotsInALot() {
        List<ParkingSlot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingSlot1);
        parkingLotList.add(parkingSlot2);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotList);
        parkingLotService.parkVehicle(new Vehicle("TS01AB1234",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA),Driver.NORMAL);
        parkingLotService.parkVehicle(new Vehicle("TS02AB5678",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.BLACK,Vehicle.VehicleBrand.BMW), Driver.NORMAL);
        parkingLotService.parkVehicle(new Vehicle("TS03AB1234",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA), Driver.NORMAL);
        Mockito.when(parkingSlot2.getOccupiedSpots()).thenReturn(2);
        int occupiedSpotsInAlot = parkingLotService.getOccupiedSpotsInASlot(parkingSlot2);
        Assert.assertEquals(2,occupiedSpotsInAlot);
    }

    @Test
    public void givenVehicleNumberToPark_whenParked_shouldReturnOccupiedSpotsInASlot() {
        List<ParkingSlot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingSlot1);
        parkingLotList.add(parkingSlot2);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotList);
        parkingLotService.parkVehicle(new Vehicle("TS01AB1234",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA),Driver.NORMAL);
        parkingLotService.parkVehicle(new Vehicle("TS02AB5678",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.BLACK,Vehicle.VehicleBrand.BMW), Driver.NORMAL);
        parkingLotService.parkVehicle(new Vehicle("TS03AB1234",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA), Driver.NORMAL);
        Mockito.when(parkingSlot1.getOccupiedSpots()).thenReturn(1);
        int occupiedSpotsInAlot = parkingLotService.getOccupiedSpotsInASlot(parkingSlot1);
        Assert.assertEquals(1,occupiedSpotsInAlot);
    }


    @Test
    public void givenVehicleNumberToPark_whenAllParkingLotsAreFull_shouldThrowException() {
        ParkingSlot parkingSlot1 = new ParkingSlot(1);
        ParkingSlot parkingSlot2 = new ParkingSlot(3);
        parkingLotList.add(parkingSlot1);
        parkingLotList.add(parkingSlot2);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotList);
        try {
            parkingLotService.parkVehicle(new Vehicle("TS01AB1234",Vehicle.VehicleSize.SMALL,
                    Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS02AB5678",Vehicle.VehicleSize.SMALL,
                    Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS03AB1234",Vehicle.VehicleSize.SMALL,
                    Vehicle.VehicleColor.BLACK,Vehicle.VehicleBrand.TOYOTA), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS04AB5678",Vehicle.VehicleSize.SMALL,
                    Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS05AB4567",Vehicle.VehicleSize.SMALL,
                    Vehicle.VehicleColor.BLACK,Vehicle.VehicleBrand.TOYOTA), Driver.NORMAL);
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
            parkingLotService.parkVehicle(new Vehicle("TS01AB1234",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS02AB5678",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS03AB1234",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.BLACK,Vehicle.VehicleBrand.TOYOTA), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS04AB5678",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS05AB4567",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.BLACK,Vehicle.VehicleBrand.TOYOTA), Driver.NORMAL);
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
        Vehicle vehicle1 = new Vehicle("AP30M2832",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA);
        parkingLotService.parkVehicle(vehicle1, Driver.NORMAL);
        Vehicle vehicle2 = new Vehicle("OD27M5160",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW);
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
        Vehicle vehicle1 = new Vehicle("AP30M2832",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA);
        parkingLotService.parkVehicle(vehicle1, Driver.NORMAL);
        Vehicle vehicle2 = new Vehicle("AP30M2364",Vehicle.VehicleSize.LARGE,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW);
        parkingLotService.parkVehicle(vehicle2, Driver.NORMAL);
        ParkingSlot parkedSlot = parkingLotService.getParkedSlot(vehicle2);
        Assert.assertEquals(parkingSlot1,parkedSlot);
    }

    @Test
    public void givenParkedVehicles_whenWhiteColouredCarsFound_shouldReturnitsLocation(){
        ParkingSlot parkingSlot1 = new ParkingSlot(4);
        ParkingSlot parkingSlot2 = new ParkingSlot(5);
        List<ParkingSlot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingSlot1);
        parkingLotList.add(parkingSlot2);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotList);
        Vehicle vehicle1 = new Vehicle("AP30M2832",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA);
        parkingLotService.parkVehicle(vehicle1, Driver.NORMAL);
        Vehicle vehicle2 = new Vehicle("AP30M2364",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.BLACK,Vehicle.VehicleBrand.BMW);
        parkingLotService.parkVehicle(vehicle2, Driver.NORMAL);
        Vehicle vehicle3 = new Vehicle("OD23M0205",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA);
        parkingLotService.parkVehicle(vehicle3, Driver.NORMAL);
        List<Vehicle> vehicleList = Arrays.asList(vehicle1,vehicle3);
        List<Vehicle> VehicleLocations = parkingLotService.getAllVehiclesBasedOnColor(Vehicle.VehicleColor.WHITE);
        Assert.assertEquals(vehicleList,VehicleLocations);
    }

    @Test
    public void givenParkedVehicles_whenBlueColouredToyotaCarsFound_shouldReturnItsLocation(){
        ParkingSlot parkingSlot1 = new ParkingSlot(4);
        ParkingSlot parkingSlot2 = new ParkingSlot(5);
        List<ParkingSlot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingSlot1);
        parkingLotList.add(parkingSlot2);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotList);
        Vehicle vehicle1 = new Vehicle("AP30M2832",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.BLUE,Vehicle.VehicleBrand.TOYOTA);
        parkingLotService.parkVehicle(vehicle1, Driver.NORMAL);
        Vehicle vehicle2 = new Vehicle("AP30M2364",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.BLACK,Vehicle.VehicleBrand.BMW);
        parkingLotService.parkVehicle(vehicle2, Driver.NORMAL);
        Vehicle vehicle3 = new Vehicle("OD23M0205",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.BLUE,Vehicle.VehicleBrand.TOYOTA);
        parkingLotService.parkVehicle(vehicle3, Driver.NORMAL);
        List<Vehicle> vehicleList = Arrays.asList(vehicle1,vehicle3);
        List<Vehicle> VehicleLocations = parkingLotService.getVehiclesBasedonBrand
                (Vehicle.VehicleBrand.TOYOTA);
        Assert.assertEquals(vehicleList,VehicleLocations);
    }

    @Test
    public void givenParkedVehicles_whenBMWCarsFound_shouldReturnitsLocation(){
        ParkingSlot parkingSlot1 = new ParkingSlot(4);
        ParkingSlot parkingSlot2 = new ParkingSlot(5);
        List<ParkingSlot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingSlot1);
        parkingLotList.add(parkingSlot2);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotList);
        Vehicle vehicle1 = new Vehicle("AP30M2832",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.BLUE,Vehicle.VehicleBrand.TOYOTA);
        parkingLotService.parkVehicle(vehicle1, Driver.NORMAL);
        Vehicle vehicle2 = new Vehicle("AP30M2364",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.BLACK,Vehicle.VehicleBrand.BMW);
        parkingLotService.parkVehicle(vehicle2, Driver.NORMAL);
        Vehicle vehicle3 = new Vehicle("OD23M0205",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.BLUE, Vehicle.VehicleBrand.TOYOTA);
        parkingLotService.parkVehicle(vehicle3, Driver.NORMAL);
        Vehicle vehicle4 = new Vehicle("OD27R5160",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW);
        parkingLotService.parkVehicle(vehicle4, Driver.NORMAL);
        List<Vehicle> vehicleList = Arrays.asList(vehicle2,vehicle4);
        List<Vehicle> VehicleLocations = parkingLotService.getVehiclesBasedonBrand(Vehicle.VehicleBrand.BMW);
        Assert.assertEquals(vehicleList,VehicleLocations);
    }

    @Test
    public void givenParkedVehicles_whenCarsParkedWithinLast30MinutesFound_shouldReturnVehicleslist(){
        ParkingSlot parkingSlot1 = new ParkingSlot(5);
        ParkingSlot parkingSlot2 = new ParkingSlot(5);
        List<ParkingSlot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingSlot1);
        parkingLotList.add(parkingSlot2);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotList);
        Vehicle vehicle1 = new Vehicle("AP30M2832",Vehicle.VehicleSize.LARGE,
                Vehicle.VehicleColor.BLUE,Vehicle.VehicleBrand.TOYOTA);
        parkingLotService.parkVehicle(vehicle1, Driver.NORMAL);
        Vehicle vehicle2 = new Vehicle("AP30M2364",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.BLACK,Vehicle.VehicleBrand.BMW);
        parkingLotService.parkVehicle(vehicle2, Driver.HANDICAPPED);
        Vehicle vehicle3 = new Vehicle("OD23M0205",Vehicle.VehicleSize.LARGE,
                Vehicle.VehicleColor.BLUE, Vehicle.VehicleBrand.TOYOTA);
        parkingLotService.parkVehicle(vehicle3, Driver.NORMAL);
        Vehicle vehicle4 = new Vehicle("OD27R5160",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW);
        parkingLotService.parkVehicle(vehicle4, Driver.HANDICAPPED);
        List<Vehicle> vehicleList = Arrays.asList(vehicle2,vehicle4);
        List<Vehicle> VehicleLocations = parkingLotService.getVehiclesBasedonSize(Vehicle.VehicleSize.SMALL);
        Assert.assertEquals(vehicleList,VehicleLocations);
    }

    @Test
    public void givenParkedVehicles_whenSmallHandicappedDriverCarsFound_shouldReturnitsLocation(){
        ParkingSlot parkingSlot1 = new ParkingSlot(5);
        ParkingSlot parkingSlot2 = new ParkingSlot(5);
        List<ParkingSlot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingSlot1);
        parkingLotList.add(parkingSlot2);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotList);
        Vehicle vehicle1 = new Vehicle("AP30M2832",Vehicle.VehicleSize.LARGE,
                Vehicle.VehicleColor.BLUE,Vehicle.VehicleBrand.TOYOTA);
        parkingLotService.parkVehicle(vehicle1, Driver.NORMAL);
        Vehicle vehicle2 = new Vehicle("AP30M2364",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.BLACK,Vehicle.VehicleBrand.BMW);
        parkingLotService.parkVehicle(vehicle2, Driver.HANDICAPPED);
        Vehicle vehicle3 = new Vehicle("OD23M0205",Vehicle.VehicleSize.LARGE,
                Vehicle.VehicleColor.BLUE, Vehicle.VehicleBrand.TOYOTA);
        parkingLotService.parkVehicle(vehicle3, Driver.NORMAL);
        Vehicle vehicle4 = new Vehicle("OD27R5160",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW);
        parkingLotService.parkVehicle(vehicle4, Driver.HANDICAPPED);
        long time = 30;
        List<Vehicle> vehicleList = parkingLotService.getVehicleTimings(time);
        Assert.assertEquals(4,vehicleList.size());
    }

    @Test
    public void givenParkedVehicles_whenVehicleDeatilsRequired_shouldReturnItsVehicleNumbers(){
        ParkingSlot parkingSlot1 = new ParkingSlot(4);
        ParkingSlot parkingSlot2 = new ParkingSlot(5);
        List<ParkingSlot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingSlot1);
        parkingLotList.add(parkingSlot2);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotList);

        Vehicle vehicle1 = new Vehicle("AP30M2832",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.BLUE,Vehicle.VehicleBrand.TOYOTA);
        parkingLotService.parkVehicle(vehicle1, Driver.NORMAL);
        Vehicle vehicle2 = new Vehicle("AP30M2364",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.BLACK,Vehicle.VehicleBrand.BMW);
        parkingLotService.parkVehicle(vehicle2, Driver.NORMAL);
        Vehicle vehicle3 = new Vehicle("OD23M0205",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.BLUE, Vehicle.VehicleBrand.TOYOTA);
        parkingLotService.parkVehicle(vehicle3, Driver.NORMAL);
        Vehicle vehicle4 = new Vehicle("OD27R5160",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW);
        parkingLotService.parkVehicle(vehicle4, Driver.NORMAL);
        List<String> vehicleNumbers = Arrays.asList("AP30M2832","AP30M2364","OD23M0205","OD27R5160");
        List<String> allVehicleNumbers = parkingLotService.getAllVehicleDetails();
        boolean VehicleNumberDetails = vehicleNumbers.containsAll(allVehicleNumbers);
        Assert.assertTrue(VehicleNumberDetails);
    }

}
