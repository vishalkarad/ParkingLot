package com.bridgelabz;

public class VehiclePOJO {

    private String vehicleName;
    private String vehicleNumber;

    // setter method
    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }


    // getter method
    public String getVehicleName() {
        return vehicleName;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }


    @Override
    public String toString() {
        return "VehiclePOJO{" +
                "vehicleName='" + vehicleName + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                '}';
    }
}
