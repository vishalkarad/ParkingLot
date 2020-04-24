package com.bridgelabz.utilities;

public class VehiclePOJO {

    // Declare variable
    private String vehicleName;
    private String vehicleNumber;
    private String vehicleType;
    private Driver driver;
    private String vehicleColor;

    // Declare constructor
    public VehiclePOJO(String vehicleName, String vehicleNumber, String vehicleType, Driver driver,String vehicleColor) {
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.driver = driver;
        this.vehicleColor = vehicleColor;
    }

    // getter method
    public Driver getDriver() {
        return driver;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    @Override
    public String toString() {
        return "VehiclePOJO{" +
                "vehicleName='" + vehicleName + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", driver=" + driver +
                ", vehicleColor='" + vehicleColor + '\'' +
                '}';
    }
}
