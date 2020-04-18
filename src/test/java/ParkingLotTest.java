import com.bridgelabz.AirportSecurity;
import com.bridgelabz.Owner;
import com.bridgelabz.VehiclePOJO;
import com.bridgelabz.exception.ParkingLotException;
import com.bridgelabz.service.ParkingLotMain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    ParkingLotMain parkingLotMain = null;
    VehiclePOJO vehicle = null;
    Owner owner = null;
    AirportSecurity airportSecurity = null;

    @Before
    public void setUp(){
        vehicle = new VehiclePOJO();
        parkingLotMain = new ParkingLotMain();
        owner = new Owner();
        airportSecurity = new AirportSecurity();
    }

    @Test
    public void givenVehicle_WhenParke_ThenReturnTrue() {
        try {
            vehicle.setVehicleName("sujuki");vehicle.setVehicleNumber("MH4R4545");
            String result = parkingLotMain.park(vehicle);
            Assert.assertEquals("park vehicle",result);
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
    public void givenVehicle_WhenUnPark_ThenReturnUnpark() {
        try {
            parkingLotMain.park(vehicle);
            String  result = parkingLotMain.unPark(vehicle);
            Assert.assertEquals("unpark",result);
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
            Assert.assertEquals("park vehicle",result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotIsFull_WhenInformAirportSecurity_ThenReturnTrue() {
        try {
            parkingLotMain.addObserver(airportSecurity);
            VehiclePOJO vehicle = new VehiclePOJO();
            vehicle.setVehicleName("suzuki");vehicle.setVehicleNumber("MH4R4545");
            parkingLotMain.park(vehicle);
            vehicle.setVehicleName("suzuki");vehicle.setVehicleNumber("MH4R4547");
            parkingLotMain.park(vehicle);
            vehicle.setVehicleName("suzuki");vehicle.setVehicleNumber("MH4R4548");
            parkingLotMain.park(vehicle);
            Assert.assertEquals("Full",airportSecurity.getParkingSlotFullOrNot());
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
        parkingLotMain.addObserver(owner);
        VehiclePOJO vehicle = new VehiclePOJO();
        vehicle.setVehicleName("suzuki");vehicle.setVehicleNumber("MH4R4545");
        parkingLotMain.park(vehicle);
        vehicle.setVehicleName("suzuki");vehicle.setVehicleNumber("MH4R4547");
        parkingLotMain.park(vehicle);
        vehicle.setVehicleName("suzuki");vehicle.setVehicleNumber("MH4R4548");
        parkingLotMain.park(vehicle);
        parkingLotMain.unPark(vehicle);
        Assert.assertEquals("Have Space lot number 3",owner.getParkingSpace());
    }
    @Test
    public void givenfindMyCar_WhenParke_ThenReturnCarPosition() {
        try {
            vehicle.setVehicleName("sujuki");vehicle.setVehicleNumber("MH4R4545");
            parkingLotMain.park(vehicle);
            vehicle.setVehicleName("suzuki");vehicle.setVehicleNumber("MH4R4547");
            parkingLotMain.park(vehicle);
            String result = parkingLotMain.isVehiclePark(vehicle);
            Assert.assertEquals("vehicle park in lot number 2",result);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleParkInLot_WhenCharge_ThenReturnTrue() {
        try {
            parkingLotMain.addObserver(owner);
            vehicle.setVehicleName("sujuki");vehicle.setVehicleNumber("MH4R4545");
            parkingLotMain.park(vehicle);
            Assert.assertEquals("this vehicle charge Rs.10",owner.getParkingCharge());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
