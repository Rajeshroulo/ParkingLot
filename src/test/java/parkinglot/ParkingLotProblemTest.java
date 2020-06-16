package parkinglot;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static parkinglot.ParkingLotException.ExceptionType.*;

public class ParkingLotProblemTest {

    ParkingSlot parkingLot;

    @Before
    public void setup()   {
        parkingLot = new ParkingSlot( 5);
    }

    @Test
    public void givenCar_whenParkedinparkingLot_shouldReturnTrue() {
        boolean result = parkingLot.parkVehicle(new Vehicle("OD 07 R 5160",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE),Driver.NORMAL);
        assertTrue(result);
    }

    @Test
  public void givenVehicle_whenParkedinParkingLot_shouldReturnCorrectSize(){
     parkingLot.parkVehicle(new Vehicle("AP 30 M 2832",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE),Driver.NORMAL);
     int size = parkingLot.getOccupiedSpots();
     assertEquals(1,size);
   }

  @Test
  public void whenNoVehicleisParkedinParkingLot_shouldReturnEmpty() {
        int size = parkingLot.getOccupiedSpots();
        assertEquals(0,size);
    }

  @Test
  public void givenVehicle_whenNumberisNull_shouldThrowExecption() {
        try {
            parkingLot.parkVehicle(null,Driver.NORMAL);
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_NULL,e.type);
        }
    }

  @Test
  public void givenEmptyCarNumber_shouldThrowExecption() {
        try {
            parkingLot.parkVehicle(new Vehicle("",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE),Driver.NORMAL);
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_EMPTY,e.type);
        }
    }

  @Test
  public void givenVehicle_whenUnparkedfromParkingLot_shouldReturnCorrectSize(){
        parkingLot.parkVehicle(new Vehicle("AP 30 M 2832",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE),Driver.NORMAL);
        Vehicle vehicle = new Vehicle("AP 30 R 2843",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE);
        parkingLot.parkVehicle(vehicle,Driver.NORMAL);
        parkingLot.unparkVehicle(vehicle);
        int size = parkingLot.getOccupiedSpots();
        assertEquals(1,size);
    }

  @Test
  public void givenVehicle_whentheNumberisNull_shouldThrowExecption() {
        try {
            parkingLot.unparkVehicle(null);
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_NULL,e.type);
        }
    }

  @Test
  public void givenEmptyCarNumber_whenUnparked_shouldThrowExecption() {
        try {
            Vehicle vehicle = new Vehicle("",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE);
            parkingLot.unparkVehicle(vehicle);
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_EMPTY,e.type);
        }
    }

  @Test
  public void givenVehicleNumbersToPark_whenParkingLotIsFull_shouldThrowException() {
        try {
        parkingLot.parkVehicle(new Vehicle("TS08CV5421",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE),Driver.NORMAL);
        parkingLot.parkVehicle(new Vehicle("TA07EC3633",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.BLACK),Driver.NORMAL);
        parkingLot.parkVehicle(new Vehicle("AP24AC7684",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE),Driver.NORMAL);
        parkingLot.parkVehicle(new Vehicle("TN11WA4563",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.BLACK),Driver.NORMAL);
        parkingLot.parkVehicle(new Vehicle("KA12TH4651",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE),Driver.NORMAL);
        } catch (ParkingLotException e) {
            assertEquals(ParkingLotException.ExceptionType.PARKINGLOT_IS_FULL, e.type);
        }
    }

  @Test
  public void givenWrongVehicleNumberToUnPark_shouldThrowException() {
        try {
        parkingLot.parkVehicle(new Vehicle("TS08CV5421",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE),Driver.NORMAL);
        parkingLot.parkVehicle(new Vehicle("TA07EC3633",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE),Driver.NORMAL);
        Vehicle vehicle = new Vehicle("TA07TD8945",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE);
        parkingLot.unparkVehicle(vehicle);
        } catch (ParkingLotException e) {
            assertEquals(ParkingLotException.ExceptionType.NUMBER_IS_NOT_PRESENT,e.type);
        }
    }

  @Test
  public void givenSameVehicleNumberisParked_shouldThrowException() {
        try {
            parkingLot.parkVehicle(new Vehicle("TS08CV5421",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE),Driver.NORMAL);
            parkingLot.parkVehicle(new Vehicle("TA07EC3633",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.BLACK),Driver.NORMAL);
            parkingLot.parkVehicle(new Vehicle("TA07EC3633",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE),Driver.NORMAL);
        } catch (ParkingLotException e) {
            assertEquals(ParkingLotException.ExceptionType.NUMBER_EXISTING,e.type);
        }
    }

  @Test
    public void givenCarToUnpark_whenunparked_shouldReturnTrue(){
          parkingLot.parkVehicle(new Vehicle("TS08CV5421",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE),Driver.NORMAL);
          Vehicle vehicle = new Vehicle("TA07EC3633",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.BLACK);
          parkingLot.parkVehicle(vehicle,Driver.NORMAL);
          parkingLot.parkVehicle(new Vehicle("AP 30 M 2832",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE),Driver.NORMAL);
          boolean result =  parkingLot.unparkVehicle(vehicle);
          assertTrue(result);
     }

  @Test
    public void givenNewCar_whenParkedinparkingLot_shouldReturnTrue() {
      parkingLot.parkVehicle(new Vehicle("AP 30 M 2832",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE),Driver.NORMAL);
      boolean result = parkingLot.parkVehicle(new Vehicle("OD 07 R 5160",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE),Driver.NORMAL);
        assertTrue(result);
    }

  @Test
  public void givenVehicle_whenFound_shouldReturnParkedSpot(){
      Vehicle vehicle1 = new Vehicle("TA07R3633",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE);
      parkingLot.parkVehicle(vehicle1,Driver.NORMAL);
      Vehicle vehicle2 = new Vehicle("AP08A4554",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE);
      parkingLot.parkVehicle(vehicle2,Driver.NORMAL);
      Vehicle vehicle3 = new Vehicle("OD05J1997",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE);
      parkingLot.parkVehicle(vehicle3,Driver.NORMAL);
      int parkedSpot = parkingLot.getParkedSpot(vehicle2);
      assertEquals(2,parkedSpot);
  }

  @Test
    public void givenVehicleNumbersToPark_whenParked_shouldReturnNumberOfVehiclesParked() {
        parkingLot.parkVehicle(new Vehicle("TS08CV5421",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE),Driver.NORMAL);
        parkingLot.parkVehicle(new Vehicle("AP07EC1254",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.BLACK),Driver.NORMAL);
        int numberOfOccupiedSpots = parkingLot.getOccupiedSpots();
        assertEquals(2,numberOfOccupiedSpots);
    }


  @Test
  public void givenVehicleNumbersToUnPark_whenUnPark_shouldReturnParkedTime() {
      Vehicle vehicle = new Vehicle("AP30M2832",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE);
      parkingLot.parkVehicle(vehicle,Driver.NORMAL);
      long parkedTime = parkingLot.getParkedTime(vehicle);
      assertEquals(0,parkedTime);
  }

  @Test
    public void givenVehicleToPark_whenDriverisHandicapped_shouldParkAtNearestFreeSpot(){
      Vehicle vehicle = new Vehicle("OD27R0205",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE);
      parkingLot.parkVehicle(vehicle,Driver.NORMAL);
      parkingLot.parkVehicle(new Vehicle("AP07EC1254",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.BLACK),Driver.NORMAL);
      Vehicle vehicle1 = new Vehicle("AP30M2832",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE);
      parkingLot.parkVehicle(vehicle1,Driver.HANDICAPPED);
      assertEquals(3,parkingLot.getParkedSpot(vehicle));
  }

}

