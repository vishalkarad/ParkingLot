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

    public boolean isVehiclePark(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }


}
