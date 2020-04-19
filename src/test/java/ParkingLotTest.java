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
    Integer capacity = 3;
    int slot = 1;

    @Before
    public void setUp(){
        vehicle = new VehiclePOJO();
        parkingLotMain = new ParkingLotMain(capacity,slot);
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
            VehiclePOJO vehicle1 = new VehiclePOJO();
            vehicle1.setVehicleName("suzuki");vehicle1.setVehicleNumber("MH4R4545");
            parkingLotMain.park(vehicle1);
            VehiclePOJO vehicle2 = new VehiclePOJO();
            vehicle2.setVehicleName("suzuki");vehicle2.setVehicleNumber("MH4R4547");
            parkingLotMain.park(vehicle2);
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
            VehiclePOJO vehicle1 = new VehiclePOJO();
            vehicle1.setVehicleName("suzuki");vehicle1.setVehicleNumber("MH4R4547");
            parkingLotMain.unPark(vehicle1);;
        } catch (ParkingLotException e) {
            Assert.assertEquals("This vehicle not park in my parking lot",e.getMessage());
        }
    }

    @Test
    public void givenAgainParkingSpaceAvailable_WhenInformOwner_ThenReturnTrue() throws ParkingLotException {
        parkingLotMain.addObserver(owner);
        VehiclePOJO vehicle1 = new VehiclePOJO();
        vehicle1.setVehicleName("suzuki");vehicle1.setVehicleNumber("MH4R4545");
        parkingLotMain.park(vehicle1);
        VehiclePOJO vehicle2 = new VehiclePOJO();
        vehicle2.setVehicleName("suzuki");vehicle2.setVehicleNumber("MH4R4547");
        parkingLotMain.park(vehicle2);
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
            VehiclePOJO vehicle1 = new VehiclePOJO();
            vehicle1.setVehicleName("suzuki");vehicle1.setVehicleNumber("MH4R4545");
            parkingLotMain.park(vehicle1);
            String result = parkingLotMain.isVehiclePark(vehicle);
            Assert.assertEquals("vehicle park in lot number 1",result);
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
    @Test
    public void givenParkingAttendant_WhenEvenlyDistribution_ThenReturn() {
        try {
            this.capacity = 4;
            this.slot = 2;
            parkingLotMain=new ParkingLotMain(capacity,slot);
            parkingLotMain.addObserver(owner);
            vehicle.setVehicleName("sujuki");vehicle.setVehicleNumber("MH4R4545");
            parkingLotMain.park(vehicle);
            VehiclePOJO vehicle1 = new VehiclePOJO();
            vehicle1.setVehicleName("suzuki");vehicle1.setVehicleNumber("MH4R4549");
            parkingLotMain.park(vehicle1);
            Assert.assertEquals("Full Lot A",owner.getParkingFull());
            VehiclePOJO vehicle2 = new VehiclePOJO();
            vehicle2.setVehicleName("suzuki");vehicle2.setVehicleNumber("MH4R4548");
            parkingLotMain.park(vehicle2);
            VehiclePOJO vehicle3 = new VehiclePOJO();
            vehicle3.setVehicleName("suzuki");vehicle3.setVehicleNumber("MH4R4547");
            parkingLotMain.park(vehicle3);
            Assert.assertEquals("Full Lot B",owner.getParkingFull());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
