import com.bridgelabz.AirportSecurity;
import com.bridgelabz.Owner;
import com.bridgelabz.VehiclePOJO;
import com.bridgelabz.exception.ParkingLotException;
import com.bridgelabz.service.Driver;
import com.bridgelabz.service.ParkingLotMain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    ParkingLotMain parkingLotMain = null;
    Owner owner = null;
    PoliceDepartment police = null;
    AirportSecurity airportSecurity = null;
    Integer capacity = 3;
    int slot = 3;

    @Before
    public void setUp(){
        parkingLotMain = new ParkingLotMain(capacity,slot);
        owner = new Owner();
        police = new PoliceDepartment();
        airportSecurity = new AirportSecurity();
    }

    @Test
    public void givenVehicle_WhenParke_ThenReturnTrue() {
        try {
            VehiclePOJO vehicle = new VehiclePOJO("suzuki","MH4R4545","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
            String result = parkingLotMain.park(vehicle);
            Assert.assertEquals("park vehicle",result);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicle_WhenAlreadyParked_ThenReturnException() {
        try {
            VehiclePOJO vehicle = new VehiclePOJO("suzuki","MH4R4545","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
            parkingLotMain.park(vehicle);
            parkingLotMain.park(vehicle);
        } catch (ParkingLotException e) {
             Assert.assertEquals("This vehicle already park",e.getMessage());
        }
    }

    @Test
    public void givenVehicle_WhenUnPark_ThenReturnUnpark() {
        try {
            VehiclePOJO vehicle = new VehiclePOJO("suzuki","MH4R4545","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
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
            parkingLotMain.addObserver(owner);
            VehiclePOJO vehicle1 = new VehiclePOJO("suzuki","MH4R4545","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
            parkingLotMain.park(vehicle1);
            VehiclePOJO vehicle2 = new VehiclePOJO("suzuki","MH4R4546","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
            parkingLotMain.park(vehicle2);
            VehiclePOJO vehicle = new VehiclePOJO("suzuki","MH4R4547","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
            parkingLotMain.park(vehicle);
            Assert.assertEquals("Full Lot 1",owner.getParkingFull());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotIsFull_WhenInformAirportSecurity_ThenReturnTrue() {
        try {
            parkingLotMain.addObserver(airportSecurity);
            VehiclePOJO vehicle1 = new VehiclePOJO("suzuki","MH4R4545","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
            parkingLotMain.park(vehicle1);
            VehiclePOJO vehicle2 = new VehiclePOJO("suzuki","MH4R4546","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
            parkingLotMain.park(vehicle2);
            VehiclePOJO vehicle = new VehiclePOJO("suzuki","MH4R4545","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
            parkingLotMain.park(vehicle);
            Assert.assertEquals("Full Lot 1",airportSecurity.getParkingSlotFullOrNot());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleNotPresentInParkingLot_WhenUnPark_ThenThrowException() {
        try {
            VehiclePOJO vehicle = new VehiclePOJO("suzuki","MH4R4545","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
            parkingLotMain.park(vehicle);
            VehiclePOJO vehicle1 = new VehiclePOJO("suzuki","MH4R4546","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
            parkingLotMain.unPark(vehicle1);;
        } catch (ParkingLotException e) {
            Assert.assertEquals("This vehicle not park in my parking lot",e.getMessage());
        }
    }

    @Test
    public void givenAgainParkingSpaceAvailable_WhenInformOwner_ThenReturnTrue() throws ParkingLotException {
        parkingLotMain.addObserver(owner);
        VehiclePOJO vehicle1 = new VehiclePOJO("suzuki","MH4R4545","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
        parkingLotMain.park(vehicle1);
        VehiclePOJO vehicle2 = new VehiclePOJO("suzuki","MH4R4546","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
        parkingLotMain.park(vehicle2);
        VehiclePOJO vehicle = new VehiclePOJO("suzuki","MH4R4547","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
        parkingLotMain.park(vehicle);
        parkingLotMain.unPark(vehicle);
        Assert.assertEquals("Have Space lot number 3",owner.getParkingSpace());
    }

    @Test
    public void givenfindMyCar_WhenPark_ThenReturnCarPosition() {
        try {
            VehiclePOJO vehicle = new VehiclePOJO("suzuki","MH4R4545","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
            parkingLotMain.park(vehicle);
            VehiclePOJO vehicle1 = new VehiclePOJO("suzuki","MH4R4546","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
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
            VehiclePOJO vehicle = new VehiclePOJO("suzuki","MH4R4545","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
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
            VehiclePOJO vehicle = new VehiclePOJO("suzuki","MH4R4545","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
            parkingLotMain.park(vehicle);
            VehiclePOJO vehicle1 = new VehiclePOJO("suzuki","MH4R4546","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
            parkingLotMain.park(vehicle1);
            VehiclePOJO vehicle2 = new VehiclePOJO("suzuki","MH4R4547","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
            parkingLotMain.park(vehicle2);
            Assert.assertEquals("Full Lot 1",owner.getParkingFull());
            VehiclePOJO vehicle3 = new VehiclePOJO("suzuki","MH4R4548" ,"SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
            parkingLotMain.park(vehicle3);
            Assert.assertEquals("Full Lot 2",owner.getParkingFull());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenHandicapDriver_WhenNearestFreeSpace_ThenParkCar() throws ParkingLotException {
        this.capacity = 4;
        this.slot = 2;
        parkingLotMain.addObserver(owner);
        VehiclePOJO vehicle1 = new VehiclePOJO("suzuki","MH4R4545","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
        parkingLotMain.park(vehicle1);
            VehiclePOJO vehicle2 = new VehiclePOJO("suzuki","MH4R4546","SMALL",new Driver(Driver.DriverType.HANDICAP),"WHITE");
        parkingLotMain.park(vehicle2);
        VehiclePOJO vehicle3 = new VehiclePOJO("suzuki","MH4R4547","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
        parkingLotMain.park(vehicle2);
        VehiclePOJO vehicle = new VehiclePOJO("suzuki","MH4R4547","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
        String result = parkingLotMain.park(vehicle);
        Assert.assertEquals("park vehicle",result);
    }

    @Test
    public void givenVehicle_WhenOwnerWantAttendant_ShouldDirectLargeVehicleToHighestNumberOfFreeSpace() throws ParkingLotException {
        parkingLotMain.addObserver(owner);
        parkingLotMain.addObserver(police);
        VehiclePOJO vehicle1 = new VehiclePOJO("suzuki","MH4R4545","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
        parkingLotMain.park(vehicle1);
        VehiclePOJO vehicle2 = new VehiclePOJO("suzuki","MH4R4546","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
        parkingLotMain.park(vehicle2);
        VehiclePOJO vehicle = new VehiclePOJO("suzuki","MH4R4547","SMALL",new Driver(Driver.DriverType.NORMAL),"WHITE");
        String result = parkingLotMain.park(vehicle);
        parkingLotMain.serching("WHITE");
        Assert.assertEquals("1,2,3,",police.getVehicleColore());
    }
}