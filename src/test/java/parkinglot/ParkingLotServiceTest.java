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
        parkingLotList.add(parkingSlot1);
        parkingLotList.add(parkingSlot2);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotList);
        parkingLotService.parkVehicle(new Vehicle("TS01AB1234"), Driver.NORMAL);
        parkingLotService.parkVehicle(new Vehicle("TS02AB5678"), Driver.NORMAL);
        parkingLotService.parkVehicle(new Vehicle("TS03AB1234"), Driver.NORMAL);
        int occupiedSpotsInAlot = parkingLotService.getOccupiedSpotsInALot(parkingSlot2);
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
            parkingLotService.parkVehicle(new Vehicle("TS01AB1234"), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS02AB5678"), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS03AB1234"), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS04AB5678"), Driver.NORMAL);
            parkingLotService.parkVehicle(new Vehicle("TS05AB4567"), Driver.NORMAL);
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

}
