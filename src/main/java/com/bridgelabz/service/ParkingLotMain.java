package com.bridgelabz.service;

import com.bridgelabz.VehiclePOJO;
import com.bridgelabz.exception.ParkingLotException;
import java.util.LinkedHashMap;

public class ParkingLotMain {

    Object vehicle=null;
    LinkedHashMap<String,Object> parkingLot = new LinkedHashMap<String, Object>();

    // park vehicle and check parking lot
    public void park(VehiclePOJO vehicle) throws ParkingLotException {
        if (parkingLot.size()%2==0 && parkingLot.size() != 0) {
            parkingLot = null;
            throw new ParkingLotException("Parking lot is full");
        }
        this.vehicle = vehicle;
        parkingLot.put(vehicle.getVehicleNumber(),vehicle);
    }

    // Check Vehicle is present or not
    public boolean isVehiclePark(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }

    // un park vehicle
    public boolean unPark(VehiclePOJO vehicle) {
        if (vehicle == null) return false;
        if (this.vehicle.equals(vehicle)){
            this.vehicle = null;
            parkingLot.remove(vehicle.getVehicleNumber());
            return true;
        }
        else return false;
    }
}
