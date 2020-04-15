import com.bridgelabz.exception.ParkingLotException;
import com.bridgelabz.service.ParkingLotMain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    ParkingLotMain parkingLotMain = null;
    Object vehicle = null;

    @Before
    public void setUp(){
        vehicle = new Object();
         parkingLotMain = new ParkingLotMain();
    }

    @Test
    public void givenVehicle_WhenParke_ThenReturnTrue() {
        try {
            parkingLotMain.park(vehicle);
            boolean result = parkingLotMain.isVehiclePark(vehicle);
            Assert.assertTrue(result);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicle_WhenAllreadyParked_ThenReturnException() {
        try {
            parkingLotMain.park(vehicle);
            parkingLotMain.park(new Object());
        } catch (ParkingLotException e) {
            Assert.assertEquals("Parking lot is full",e.getMessage());
        }
    }

    @Test
    public void givenVehicle_WhenUnPark_ThenTrue() {
        try {
            parkingLotMain.park(vehicle);
            boolean result=parkingLotMain.unPark(vehicle);
            Assert.assertEquals(true,result);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
