package com.bridgelabz;
import com.bridgelabz.service.Driver;

public class VehiclePOJO {

    // Declare variable
    private String vehicleName;
    private String vehicleNumber;
    private String vehicleType;
    private Driver driver;

    // Declare constructor
    public VehiclePOJO(String vehicleName, String vehicleNumber, String vehicleType, Driver driver) {
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.driver = driver;
    }

    // getter method
    public Driver getDriver() {
        return driver;
    }

    @Override
    public String toString() {
        return "VehiclePOJO{" +
                "vehicleName='" + vehicleName + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", driver=" + driver +
                '}';
    }
}
