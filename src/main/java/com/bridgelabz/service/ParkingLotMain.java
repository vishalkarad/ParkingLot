package com.bridgelabz.service;

import com.bridgelabz.exception.ParkingLotException;

public class ParkingLotMain {

    Object vehicle=null;

    // park vehicle
    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicle != null)
            throw new ParkingLotException("Parking lot is full");
        this.vehicle = vehicle;
    }

    // Check Vehicle is present or not
    public boolean isVehiclePark(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }

    // un park vehicle
    public boolean unPark(Object vehicle) {
        if (vehicle == null) return false;
        if (this.vehicle.equals(vehicle)){
            this.vehicle = null;
            return true;
        }
        else return false;
    }

}
