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
        parkingLot = new ParkingLot(5);
    }

    @Test
    public void givenCar_whenParkedinparkingLot_shouldReturnTrue() {
        boolean result = parkingLot.parkVehicle(new Vehicle("OD 07 R 5160"));
        assertTrue(result);
    }

    @Test
  public void givenVehicle_whenParkedinParkingLot_shouldReturnCorrectSize(){
     parkingLot.parkVehicle(new Vehicle("AP 30 M 2832"));
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
            parkingLot.parkVehicle(new Vehicle(""));
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_EMPTY,e.type);
        }
    }

  @Test
  public void givenVehicle_whenUnparkedfromParkingLot_shouldReturnCorrectSize(){
        parkingLot.parkVehicle(new Vehicle("AP 30 M 2832"));
        Vehicle vehicle = new Vehicle("AP 30 R 2843");
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
            parkingLot.unparkVehicle(new Vehicle(""));
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_EMPTY,e.type);
        }
    }

  @Test
  public void givenVehicleNumbersToPark_whenParkingLotIsFull_shouldThrowException() {
        try {
        parkingLot.parkVehicle(new Vehicle("TS08CV5421"));
        parkingLot.parkVehicle(new Vehicle("TA07EC3633"));
        parkingLot.parkVehicle(new Vehicle("AP24AC7684"));
        parkingLot.parkVehicle(new Vehicle("TN11WA4563"));
        parkingLot.parkVehicle(new Vehicle("KA12TH4651"));
        } catch (ParkingLotException e) {
            assertEquals(ParkingLotException.ExceptionType.PARKINGLOT_IS_FULL, e.type);
        }
    }

  @Test
  public void givenWrongVehicleNumberToUnPark_shouldThrowException() {
        try {
        parkingLot.parkVehicle(new Vehicle("TS08CV5421"));
        parkingLot.parkVehicle(new Vehicle("TA07EC3633"));
        parkingLot.unparkVehicle(new Vehicle("TA07TD8945"));
        } catch (ParkingLotException e) {
            assertEquals(ParkingLotException.ExceptionType.NUMBER_IS_NOT_PRESENT,e.type);
        }
    }

  @Test
  public void givenSameVehicleNumberisParked_shouldThrowException() {
        try {
            parkingLot.parkVehicle(new Vehicle("TS08CV5421"));
            parkingLot.parkVehicle(new Vehicle("TA07EC3633"));
            parkingLot.parkVehicle(new Vehicle("TA07EC3633"));
        } catch (ParkingLotException e) {
            assertEquals(ParkingLotException.ExceptionType.NUMBER_EXISTING,e.type);
        }
    }

  @Test
    public void givenCarToUnpark_whenunparked_shouldReturnTrue(){
          parkingLot.parkVehicle(new Vehicle("TS08CV5421"));
          Vehicle vehicle = new Vehicle("TA07EC3633");
          parkingLot.parkVehicle(vehicle);
          parkingLot.parkVehicle(new Vehicle("AP 30 M 2832"));
          boolean result =  parkingLot.unparkVehicle(vehicle);
          assertTrue(result);
     }

  @Test
    public void givenNewCar_whenParkedinparkingLot_shouldReturnTrue() {
      parkingLot.parkVehicle(new Vehicle("AP 30 M 2832"));
      boolean result = parkingLot.parkVehicle(new Vehicle("OD 07 R 5160"));
        assertTrue(result);
    }

  @Test
  public void givenVehicle_whenFound_shouldReturnParkedSpot(){
      Vehicle vehicle1 = new Vehicle("TA07R3633");
      parkingLot.parkVehicle(vehicle1);
      Vehicle vehicle2 = new Vehicle("AP08A4554");
      parkingLot.parkVehicle(vehicle2);
      Vehicle vehicle3 = new Vehicle("OD05J1997");
      parkingLot.parkVehicle(vehicle3);
      int parkedSpot = parkingLot.getParkedSpot(vehicle2);
      assertEquals(2,parkedSpot);



  }

}

