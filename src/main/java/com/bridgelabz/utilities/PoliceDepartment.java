import com.bridgelabz.observer.Observer;

public class PoliceDepartment implements Observer {

    private String vehicleLocation;

    public String getVehiclevehicleLocation() {
        return vehicleLocation;
    }

    public void setVehiclevehicleLocation(String vehicleLocation) {
        this.vehicleLocation = vehicleLocation;
    }

    @Override
    public void update(Object status) {
        this.setVehiclevehicleLocation((String) status);
    }
}
