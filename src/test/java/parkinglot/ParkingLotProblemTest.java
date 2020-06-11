package parkinglot;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static parkinglot.ParkingLotException.ExceptionType.*;

public class ParkingLotProblemTest {

    ParkingLot parkingLot;

    @Before
    public void setup() throws Exception {
        parkingLot = new ParkingLot();
    }

    @Test
  public void givenVehicle_whenParkedinParkingLot_shouldReturnCorrectSize(){
     parkingLot.park("AP 30 M 2832");
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
            parkingLot.park(null);
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenEmptyCarNumber_shouldThrowExecption() {
        try {
            parkingLot.park("");
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenVehicle_whenUnparkedfromParkingLot_shouldReturnCorrectSize(){
        parkingLot.park("AP 30 M 2832");
        parkingLot.park("AP 30 R 2843");
        parkingLot.unpark("AP 30 M 2832");
        int size = parkingLot.getSize();
        assertEquals(1,size);

    }

    @Test
    public void givenVehicle_whentheNumberisNull_shouldThrowExecption() {
        try {
            parkingLot.unpark(null);
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenEmptyCarNumber_whenUnparked_shouldThrowExecption() {
        try {
            parkingLot.unpark("");
        } catch (ParkingLotException e) {
            assertEquals(ENTERED_EMPTY,e.type);
        }
    }


}

