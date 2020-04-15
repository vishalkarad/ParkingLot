package com.bridgelabz;

public class VehiclePOJO {

    private String vehicleName;
    private String vehicleNumber;
    private String vehicleModel;

    // setter method
    public void setVehicaleName(String vehicaleName) {
        this.vehicleName = vehicaleName;
    }
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    // getter method
    public String getVehicaleName() {
        return vehicleName;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    public String getVehicleModel() {
        return vehicleModel;
    }

    @Override
    public String toString() {
        return "VehiclePOJO{" +
                "vehicaleName='" + vehicleName + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                '}';
    }
}
