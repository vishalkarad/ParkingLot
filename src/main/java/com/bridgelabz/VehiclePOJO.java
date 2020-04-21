package com.bridgelabz;

import com.bridgelabz.service.ParkingLotMain;

import java.util.Comparator;

public class VehiclePOJO {

    private String vehicleName;
    private String vehicleNumber;
    public String vehicleType;

    // setter method
    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
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
