package parkinglot;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static parkinglot.ParkingLotException.ExceptionType.*;

public class ParkingSlotProblemTest {

    ParkingSlot parkingSlot;

    @Before
    public void setup()   {
        parkingSlot = new ParkingSlot( 5);
    }

    @Test
    public void givenCar_whenParkedinparkingLot_shouldReturnTrue() {
        boolean result = parkingSlot.parkVehicle(new Vehicle("OD 07 R 5160",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA),Driver.NORMAL);
        assertTrue(result);
    }

    @Test
     public void givenVehicle_whenParkedinParkingLot_shouldReturnCorrectSize(){
     parkingSlot.parkVehicle(new Vehicle("AP 30 M 2832",Vehicle.VehicleSize.SMALL,
             Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA),Driver.NORMAL);
     int size = parkingSlot.getOccupiedSpots();
     assertEquals(1,size);
   }

  @Test
  public void whenNoVehicleisParkedinParkingLot_shouldReturnEmpty() {
        int size = parkingSlot.getOccupiedSpots();
        assertEquals(0,size);
    }

  @Test
  public void givenVehicle_whenNumberisNull_shouldThrowExecption() {
        try {
            parkingSlot.parkVehicle(null,Driver.NORMAL);
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_NULL,e.type);
        }
    }

  @Test
  public void givenEmptyCarNumber_shouldThrowExecption() {
        try {
            parkingSlot.parkVehicle(new Vehicle("",Vehicle.VehicleSize.SMALL,
                    Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA),Driver.NORMAL);
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_EMPTY,e.type);
        }
    }

  @Test
  public void givenVehicle_whenUnparkedfromParkingLot_shouldReturnCorrectSize(){
        parkingSlot.parkVehicle(new Vehicle("AP 30 M 2832",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA),Driver.NORMAL);
        Vehicle vehicle = new Vehicle("AP 30 R 2843",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW);
        parkingSlot.parkVehicle(vehicle,Driver.NORMAL);
        parkingSlot.unparkVehicle(vehicle);
        int size = parkingSlot.getOccupiedSpots();
        assertEquals(1,size);
    }

  @Test
  public void givenVehicle_whentheNumberisNull_shouldThrowExecption() {
        try {
            parkingSlot.unparkVehicle(null);
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_NULL,e.type);
        }
    }

  @Test
  public void givenEmptyCarNumber_whenUnparked_shouldThrowExecption() {
        try {
            Vehicle vehicle = new Vehicle("",Vehicle.VehicleSize.SMALL,
                    Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA);
            parkingSlot.unparkVehicle(vehicle);
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_EMPTY,e.type);
        }
    }

  @Test
  public void givenVehicleNumbersToPark_whenParkingLotIsFull_shouldThrowException() {
        try {
        parkingSlot.parkVehicle(new Vehicle("TS08CV5421",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW),Driver.NORMAL);
        parkingSlot.parkVehicle(new Vehicle("TA07EC3633",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.BLACK,Vehicle.VehicleBrand.TOYOTA),Driver.NORMAL);
        parkingSlot.parkVehicle(new Vehicle("AP24AC7684",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW),Driver.NORMAL);
        parkingSlot.parkVehicle(new Vehicle("TN11WA4563",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.BLACK,Vehicle.VehicleBrand.TOYOTA),Driver.NORMAL);
        parkingSlot.parkVehicle(new Vehicle("KA12TH4651",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW),Driver.NORMAL);
        } catch (ParkingLotException e) {
            assertEquals(ParkingLotException.ExceptionType.PARKINGLOT_IS_FULL, e.type);
        }
    }

  @Test
  public void givenWrongVehicleNumberToUnPark_shouldThrowException() {
        try {
        parkingSlot.parkVehicle(new Vehicle("TS08CV5421",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA),Driver.NORMAL);
        parkingSlot.parkVehicle(new Vehicle("TA07EC3633",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW),Driver.NORMAL);
        Vehicle vehicle = new Vehicle("TA07TD8945",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA);
        parkingSlot.unparkVehicle(vehicle);
        } catch (ParkingLotException e) {
            assertEquals(ParkingLotException.ExceptionType.NUMBER_IS_NOT_PRESENT,e.type);
        }
    }

  @Test
  public void givenSameVehicleNumberisParked_shouldThrowException() {
        try {
            parkingSlot.parkVehicle(new Vehicle("TS08CV5421",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA),Driver.NORMAL);
            parkingSlot.parkVehicle(new Vehicle("TA07EC3633",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.BLACK,Vehicle.VehicleBrand.BMW),Driver.NORMAL);
            parkingSlot.parkVehicle(new Vehicle("TA07EC3633",Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA),Driver.NORMAL);
        } catch (ParkingLotException e) {
            assertEquals(ParkingLotException.ExceptionType.NUMBER_EXISTING,e.type);
        }
    }

  @Test
    public void givenCarToUnpark_whenunparked_shouldReturnTrue(){
          parkingSlot.parkVehicle(new Vehicle("TS08CV5421",Vehicle.VehicleSize.SMALL,
                  Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW),Driver.NORMAL);
          Vehicle vehicle = new Vehicle("TA07EC3633",Vehicle.VehicleSize.SMALL,
                  Vehicle.VehicleColor.BLACK,Vehicle.VehicleBrand.TOYOTA);
          parkingSlot.parkVehicle(vehicle,Driver.NORMAL);
          parkingSlot.parkVehicle(new Vehicle("AP 30 M 2832",Vehicle.VehicleSize.SMALL,
                  Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW),Driver.NORMAL);
          boolean result =  parkingSlot.unparkVehicle(vehicle);
          assertTrue(result);
     }

  @Test
    public void givenNewCar_whenParkedinparkingLot_shouldReturnTrue() {
      parkingSlot.parkVehicle(new Vehicle("AP 30 M 2832",Vehicle.VehicleSize.SMALL,
              Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA),Driver.NORMAL);
      boolean result = parkingSlot.parkVehicle(new Vehicle("OD 07 R 5160",
              Vehicle.VehicleSize.SMALL,Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW), Driver.NORMAL);
        assertTrue(result);
    }

  @Test
  public void givenVehicle_whenFound_shouldReturnParkedSpot(){
      Vehicle vehicle1 = new Vehicle("TA07R3633",Vehicle.VehicleSize.SMALL,
              Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA);
      parkingSlot.parkVehicle(vehicle1,Driver.NORMAL);
      Vehicle vehicle2 = new Vehicle("AP08A4554",Vehicle.VehicleSize.SMALL,
              Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW);
      parkingSlot.parkVehicle(vehicle2,Driver.NORMAL);
      Vehicle vehicle3 = new Vehicle("OD05J1997",Vehicle.VehicleSize.SMALL,
              Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA);
      parkingSlot.parkVehicle(vehicle3,Driver.NORMAL);
      int parkedSpot = parkingSlot.getParkedSpot(vehicle2);
      assertEquals(2,parkedSpot);
  }

  @Test
    public void givenVehicleNumbersToPark_whenParked_shouldReturnNumberOfVehiclesParked() {
        parkingSlot.parkVehicle(new Vehicle("TS08CV5421",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW),Driver.NORMAL);
        parkingSlot.parkVehicle(new Vehicle("AP07EC1254",Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColor.BLACK,Vehicle.VehicleBrand.TOYOTA),Driver.NORMAL);
        int numberOfOccupiedSpots = parkingSlot.getOccupiedSpots();
        assertEquals(2,numberOfOccupiedSpots);
    }


  @Test
  public void givenVehicleNumbersToUnPark_whenUnPark_shouldReturnParkedTime() {
      Vehicle vehicle = new Vehicle("AP30M2832",Vehicle.VehicleSize.SMALL,
              Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.TOYOTA);
      parkingSlot.parkVehicle(vehicle,Driver.NORMAL);
      long parkedTime = parkingSlot.getParkedTime(vehicle);
      assertEquals(0,parkedTime);
  }

  @Test
    public void givenVehicleToPark_whenDriverisHandicapped_shouldParkAtNearestFreeSpot(){
      Vehicle vehicle = new Vehicle("OD27R0205",Vehicle.VehicleSize.SMALL,
              Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW);
      parkingSlot.parkVehicle(vehicle,Driver.NORMAL);
      parkingSlot.parkVehicle(new Vehicle("AP07EC1254",Vehicle.VehicleSize.SMALL,
              Vehicle.VehicleColor.BLACK,Vehicle.VehicleBrand.TOYOTA),Driver.NORMAL);
      Vehicle vehicle1 = new Vehicle("AP30M2832",Vehicle.VehicleSize.SMALL,
              Vehicle.VehicleColor.WHITE,Vehicle.VehicleBrand.BMW);
      parkingSlot.parkVehicle(vehicle1,Driver.HANDICAPPED);
      assertEquals(3, parkingSlot.getParkedSpot(vehicle));
  }

}

