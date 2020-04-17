import com.bridgelabz.VehiclePOJO;
import com.bridgelabz.exception.ParkingLotException;
import com.bridgelabz.service.ParkingLotMain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    ParkingLotMain parkingLotMain = null;
    VehiclePOJO vehicle = null;

    @Before
    public void setUp(){
        vehicle = new VehiclePOJO();
         parkingLotMain = new ParkingLotMain();
    }

    @Test
    public void givenVehicle_WhenParke_ThenReturnTrue() {
        try {
            vehicle.setVehicleName("sujuki");vehicle.setVehicleNumber("MH4R4545");
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
            vehicle.setVehicleName("sujuki");vehicle.setVehicleNumber("MH4R4545");
            parkingLotMain.park(vehicle);
            parkingLotMain.park(vehicle);
        } catch (ParkingLotException e) {
             Assert.assertEquals("This vehicle already park",e.getMessage());
        }
    }

    @Test
    public void givenVehicle_WhenUnPark_ThenTrue() {
        try {
            parkingLotMain.park(vehicle);
            String  result=parkingLotMain.unPark(vehicle);
            Assert.assertEquals("space available",result);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenFull_ThenReturnTrue() {
        try {
            VehiclePOJO vehicle = new VehiclePOJO();
            vehicle.setVehicleName("suzuki");vehicle.setVehicleNumber("MH4R4545");
            parkingLotMain.park(vehicle);
            vehicle.setVehicleName("suzuki");vehicle.setVehicleNumber("MH4R4547");
            parkingLotMain.park(vehicle);
            vehicle.setVehicleName("suzuki");vehicle.setVehicleNumber("MH4R4548");
            String result = parkingLotMain.park(vehicle);
            Assert.assertEquals("parking lot is full",result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotIsFull_WhenInformAirportSecurity_ThenReturnTrue() {
        try {
            VehiclePOJO vehicle = new VehiclePOJO();
            vehicle.setVehicleName("suzuki");vehicle.setVehicleNumber("MH4R4545");
            parkingLotMain.park(vehicle);
            vehicle.setVehicleName("suzuki");vehicle.setVehicleNumber("MH4R4547");
            parkingLotMain.park(vehicle);
            vehicle.setVehicleName("suzuki");vehicle.setVehicleNumber("MH4R4548");
            String parkingLotFull = parkingLotMain.park(vehicle);
            Assert.assertEquals("parking lot is full",parkingLotFull);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleNotPresentInParkingLot_WhenUnPark_ThenThrowException() {
        try {
            vehicle.setVehicleName("suzuki");vehicle.setVehicleNumber("MH4R4545");
            parkingLotMain.park(vehicle);
            vehicle.setVehicleName("suzuki");vehicle.setVehicleNumber("MH4R4547");
            String  result=parkingLotMain.unPark(vehicle);;
        } catch (ParkingLotException e) {
            Assert.assertEquals("This vehicle not park in my parking lot",e.getMessage());
        }
    }

    @Test
    public void givenAgainParkingSpaceAvailable_WhenInformOwner_ThenReturnTrue() throws ParkingLotException {
        VehiclePOJO vehicle = new VehiclePOJO();
        vehicle.setVehicleName("suzuki");vehicle.setVehicleNumber("MH4R4545");
        parkingLotMain.park(vehicle);
        vehicle.setVehicleName("suzuki");vehicle.setVehicleNumber("MH4R4547");
        parkingLotMain.park(vehicle);
        vehicle.setVehicleName("suzuki");vehicle.setVehicleNumber("MH4R4548");
        parkingLotMain.park(vehicle);
        String result=parkingLotMain.unPark(vehicle);
        Assert.assertEquals("space available",result);
    }

}
