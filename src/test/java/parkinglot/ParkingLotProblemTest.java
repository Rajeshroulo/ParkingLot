package parkinglot;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ParkingLotProblemTest {

    ParkingLot parkingLot;

    @Before
    public void setup() throws Exception {
        parkingLot = new ParkingLot();
    }

    @Test
  public void givenVehicle_whenParkedinParkingLot_shouldReturnCorrectSize(){
     parkingLot.add("AP 30 M 2832",new Customer("Rajesh",VehicleType.CAR));
     int size = parkingLot.getSize();
     assertEquals(1,size);

  }

    @Test
    public void whenNoVehicleisParkedinParkingLot_shouldReturnEmpty() {
        int size = parkingLot.getSize();
        assertEquals(0,size);
    }



}
