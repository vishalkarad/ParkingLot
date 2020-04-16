package com.bridgelabz.service;

import com.bridgelabz.AirportSecurity;
import com.bridgelabz.VehiclePOJO;
import com.bridgelabz.exception.ParkingLotException;
import java.util.LinkedHashMap;

public class ParkingLotMain {

    LinkedHashMap<String,Object> parkingLot = new LinkedHashMap<String, Object>();
    AirportSecurity airportSecurity = new AirportSecurity();

    // park vehicle and check parking lot
    public void park(VehiclePOJO vehicle) throws  ParkingLotException {
        if (parkingLot.containsKey(vehicle.getVehicleNumber()))
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_ALREADY_PARK,"This vehicle already park");
        if (parkingLot.size()%2==0 && parkingLot.size() != 0) {
            parkingLot.put(vehicle.getVehicleNumber(),vehicle);
            airportSecurity.setParkingSlotFullOrNot("parking lot is full");
            throw new ParkingLotException(ParkingLotException.MyexceptionType.LOT_IS_FULL,airportSecurity.getParkingSlotFullOrNot());
        }else {
            parkingLot.put(vehicle.getVehicleNumber(),vehicle);
        }
    }

    // Check Vehicle is present or not
    public boolean isVehiclePark(VehiclePOJO vehicle) {
        if (parkingLot.containsKey(vehicle.getVehicleNumber()))
            return true;
        return false;
    }

    // un park vehicle
    public boolean unPark(VehiclePOJO vehicle) {
        if (parkingLot.containsKey(vehicle.getVehicleNumber())){
            parkingLot.remove(vehicle.getVehicleNumber());
            return true;
        }
        else return false;
    }
}
