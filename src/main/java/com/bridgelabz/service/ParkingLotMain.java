package com.bridgelabz.service;

import com.bridgelabz.AirportSecurity;
import com.bridgelabz.Owner;
import com.bridgelabz.VehiclePOJO;
import com.bridgelabz.exception.ParkingLotException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class ParkingLotMain {

    LinkedHashMap<String,Object> parkingLot = new LinkedHashMap<String, Object>();
    AirportSecurity airportSecurity = new AirportSecurity();

    // park vehicle and check parking lot
    public String park(VehiclePOJO vehicle) throws  ParkingLotException {
        if (parkingLot.containsKey(vehicle.getVehicleNumber()))
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_ALREADY_PARK,"This vehicle already park");
        if (parkingLot.size()%2==0 && parkingLot.size() != 0) {
            parkingLot.put(vehicle.getVehicleNumber(),vehicle);
            airportSecurity.setParkingSlotFullOrNot("parking lot is full");
            return "parking lot is full";
        }else {
            parkingLot.put(vehicle.getVehicleNumber(),vehicle);
            return "record Insert";
        }
    }

    // Check Vehicle is present or not
    public String  isVehiclePark(VehiclePOJO vehicle) throws ParkingLotException {
        if (parkingLot.containsKey(vehicle.getVehicleNumber())) {
            int lotPosition = vehicleParkLotNumber(vehicle);
            return "vehicle park in lot number "+(lotPosition+1);
        }
        else {
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_NOT_PARK,
                    "This vehicle not park in my parking lot");
        }
    }
    public int vehicleParkLotNumber(VehiclePOJO vehicle){
        Set<String> keys = parkingLot.keySet();
        List<String> listKeys = new ArrayList<String>( keys );
        return listKeys.indexOf(vehicle.getVehicleNumber());
    }
    // un park vehicle
    public String unPark(VehiclePOJO vehicle) throws ParkingLotException {
        if (parkingLot.containsKey(vehicle.getVehicleNumber())) {
            int lotPosition = vehicleParkLotNumber(vehicle);
            parkingLot.remove(vehicle.getVehicleNumber());
            if (parkingLot.size() < 3) {
                new Owner().setParkingFullOrNot("parking lot space available "+(lotPosition+1));
                return "space available";
            }
            return "unpark";
        }
        else{
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_NOT_PARK,
                                                                           "This vehicle not park in my parking lot");
        }
    }
}
