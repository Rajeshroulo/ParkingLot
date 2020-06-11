package parkinglot;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static parkinglot.ParkingLotException.ExceptionType.*;

public class ParkingLotProblemTest {

    ParkingLot parkingLot;

    @Before
    public void setup() throws Exception {
        parkingLot = new ParkingLot(5);
    }

  @Test
  public void givenVehicle_whenParkedinParkingLot_shouldReturnCorrectSize(){
     parkingLot.parkVehicle("AP 30 M 2832");
     int size = parkingLot.getSize();
     assertEquals(1,size);
   }

  @Test
  public void whenNoVehicleisParkedinParkingLot_shouldReturnEmpty() {
        int size = parkingLot.getSize();
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
            parkingLot.parkVehicle("");
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_EMPTY,e.type);
        }
    }

  @Test
  public void givenVehicle_whenUnparkedfromParkingLot_shouldReturnCorrectSize(){
        parkingLot.parkVehicle("AP 30 M 2832");
        parkingLot.parkVehicle("AP 30 R 2843");
        parkingLot.unparkVehicle("AP 30 M 2832");
        int size = parkingLot.getSize();
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
            parkingLot.unparkVehicle("");
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_EMPTY,e.type);
        }
    }

  @Test
  public void givenVehicleNumbersToPark_whenParkingLotIsFull_shouldThrowException() {
        try {
        parkingLot.parkVehicle("TS08CV5421");
        parkingLot.parkVehicle("TA07EC3633");
        parkingLot.parkVehicle("AP24AC7684");
        parkingLot.parkVehicle("TN11WA4563");
        parkingLot.parkVehicle("KA12TH4651");
        } catch (ParkingLotException e) {
            assertEquals(ParkingLotException.ExceptionType.PARKINGLOT_IS_FULL, e.type);
        }
    }

  @Test
  public void givenWrongVehicleNumberToUnPark_shouldThrowException() {
        try {
        parkingLot.parkVehicle("TS08CV5421");
        parkingLot.parkVehicle("TA07EC3633");
        parkingLot.unparkVehicle("TA07TD8945");
        } catch (ParkingLotException e) {
            assertEquals(ParkingLotException.ExceptionType.NUMBER_IS_NOT_PRESENT,e.type);
        }
    }

    @Test
    public void givenSameVehicleNumberisParked_shouldThrowException() {
        try {
            parkingLot.parkVehicle("TS08CV5421");
            parkingLot.parkVehicle("TA07EC3633");
            parkingLot.parkVehicle("TA07EC3633");
        } catch (ParkingLotException e) {
            assertEquals(ParkingLotException.ExceptionType.NUMBER_EXISTING,e.type);
        }
    }


}

