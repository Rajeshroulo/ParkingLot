package parkinglot;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static parkinglot.ParkingLotException.ExceptionType.*;

public class ParkingLotProblemTest {

    ParkingLot parkingLot;

    @Before
    public void setup()   {
        parkingLot = new ParkingLot( );
    }

    @Test
    public void givenCar_whenParkedinparkingLot_shouldReturnTrue() {
        boolean result = parkingLot.parkVehicle(new Vehicle("OD 07 R 5160",Driver.NORMAL));
        assertTrue(result);
    }

    @Test
  public void givenVehicle_whenParkedinParkingLot_shouldReturnCorrectSize(){
     parkingLot.parkVehicle(new Vehicle("AP 30 M 2832",Driver.NORMAL));
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
            parkingLot.parkVehicle(null);
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_NULL,e.type);
        }
    }

  @Test
  public void givenEmptyCarNumber_shouldThrowExecption() {
        try {
            parkingLot.parkVehicle(new Vehicle("",Driver.NORMAL));
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_EMPTY,e.type);
        }
    }

  @Test
  public void givenVehicle_whenUnparkedfromParkingLot_shouldReturnCorrectSize(){
        parkingLot.parkVehicle(new Vehicle("AP 30 M 2832",Driver.NORMAL));
        Vehicle vehicle = new Vehicle("AP 30 R 2843",Driver.NORMAL);
        parkingLot.parkVehicle(vehicle);
        parkingLot.unparkVehicle(vehicle);
        int size = parkingLot.getOccupiedSpots();
        assertEquals(1,size);
    }

  @Test
  public void givenVehicle_whentheNumberisNull_shouldThrowExecption() {
        try {
            parkingLot.unparkVehicle(null);
        } catch (ParkingLotException e) {
            assertEquals(NUMBER_IS_NOT_PRESENT,e.type);
        }
    }

  @Test
  public void givenEmptyCarNumber_whenUnparked_shouldThrowExecption() {
        try {
            parkingLot.unparkVehicle(new Vehicle("",Driver.NORMAL));
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_EMPTY,e.type);
        }
    }

  @Test
  public void givenVehicleNumbersToPark_whenParkingLotIsFull_shouldThrowException() {
        try {
        parkingLot.parkVehicle(new Vehicle("TS08CV5421",Driver.NORMAL));
        parkingLot.parkVehicle(new Vehicle("TA07EC3633",Driver.NORMAL));
        parkingLot.parkVehicle(new Vehicle("AP24AC7684",Driver.NORMAL));
        parkingLot.parkVehicle(new Vehicle("TN11WA4563",Driver.NORMAL));
        parkingLot.parkVehicle(new Vehicle("KA12TH4651",Driver.NORMAL));
        } catch (ParkingLotException e) {
            assertEquals(ParkingLotException.ExceptionType.PARKINGLOT_IS_FULL, e.type);
        }
    }

  @Test
  public void givenWrongVehicleNumberToUnPark_shouldThrowException() {
        try {
        parkingLot.parkVehicle(new Vehicle("TS08CV5421",Driver.NORMAL));
        parkingLot.parkVehicle(new Vehicle("TA07EC3633",Driver.NORMAL));
        parkingLot.unparkVehicle(new Vehicle("TA07TD8945",Driver.NORMAL));
        } catch (ParkingLotException e) {
            assertEquals(ParkingLotException.ExceptionType.NUMBER_IS_NOT_PRESENT,e.type);
        }
    }

  @Test
  public void givenSameVehicleNumberisParked_shouldThrowException() {
        try {
            parkingLot.parkVehicle(new Vehicle("TS08CV5421",Driver.NORMAL));
            parkingLot.parkVehicle(new Vehicle("TA07EC3633",Driver.NORMAL));
            parkingLot.parkVehicle(new Vehicle("TA07EC3633",Driver.NORMAL));
        } catch (ParkingLotException e) {
            assertEquals(ParkingLotException.ExceptionType.NUMBER_EXISTING,e.type);
        }
    }

  @Test
    public void givenCarToUnpark_whenunparked_shouldReturnTrue(){
          parkingLot.parkVehicle(new Vehicle("TS08CV5421",Driver.NORMAL));
          Vehicle vehicle = new Vehicle("TA07EC3633",Driver.NORMAL);
          parkingLot.parkVehicle(vehicle);
          parkingLot.parkVehicle(new Vehicle("AP 30 M 2832",Driver.NORMAL));
          boolean result =  parkingLot.unparkVehicle(vehicle);
          assertTrue(result);
     }

  @Test
    public void givenNewCar_whenParkedinparkingLot_shouldReturnTrue() {
      parkingLot.parkVehicle(new Vehicle("AP 30 M 2832",Driver.NORMAL));
      boolean result = parkingLot.parkVehicle(new Vehicle("OD 07 R 5160",Driver.NORMAL));
        assertTrue(result);
    }

  @Test
  public void givenVehicle_whenFound_shouldReturnParkedSpot(){
      Vehicle vehicle1 = new Vehicle("TA07R3633",Driver.NORMAL);
      parkingLot.parkVehicle(vehicle1);
      Vehicle vehicle2 = new Vehicle("AP08A4554",Driver.NORMAL);
      parkingLot.parkVehicle(vehicle2);
      Vehicle vehicle3 = new Vehicle("OD05J1997",Driver.NORMAL);
      parkingLot.parkVehicle(vehicle3);
      int parkedSpot = parkingLot.getParkedSpot(vehicle2);
      assertEquals(2,parkedSpot);
  }

  @Test
    public void givenVehicleNumbersToPark_whenParked_shouldReturnNumberOfVehiclesParked() {
        parkingLot.parkVehicle(new Vehicle("TS08CV5421",Driver.NORMAL));
        parkingLot.parkVehicle(new Vehicle("AP07EC1254",Driver.NORMAL));
        int numberOfOccupiedSpots = parkingLot.getOccupiedSpots();
        assertEquals(2,numberOfOccupiedSpots);
    }


  @Test
  public void givenVehicleNumbersToUnPark_whenUnPark_shouldReturnParkedTime() {
      Vehicle vehicle = new Vehicle("AP30M2832",Driver.NORMAL);
      parkingLot.parkVehicle(vehicle);
      long parkedTime = parkingLot.getParkedTime(vehicle);
      assertEquals(0,parkedTime);
  }

  @Test
    public void givenVehicleToPark_whenDriverisHandicapped_shouldParkAtNearestFreeSpot(){
      Vehicle vehicle = new Vehicle("OD27R0205",Driver.NORMAL);
      parkingLot.parkVehicle(vehicle);
      parkingLot.parkVehicle(new Vehicle("AP07EC1254",Driver.NORMAL));
      Vehicle vehicle1 = new Vehicle("AP30M2832",Driver.HANDICAPPED);
      parkingLot.parkVehicle(vehicle1);
      assertEquals(3,parkingLot.getParkedSpot(vehicle));
  }

}

