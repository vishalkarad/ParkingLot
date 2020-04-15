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
}
