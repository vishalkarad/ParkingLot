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
    Owner owner = new Owner();
    // park vehicle and check parking lot
    public String park(VehiclePOJO vehicle) throws  ParkingLotException {
        if (parkingLot.containsKey(vehicle.getVehicleNumber()))
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_ALREADY_PARK,"This vehicle already park");
        parkingLot.put(vehicle.getVehicleNumber(),vehicle);
        owner.setParkingCharge(vehicle.getVehicleNumber()+" this vehicle charge Rs.10");
        if (parkingLot.size()%3==0 && parkingLot.size() != 0) {
            new AirportSecurity().setParkingSlotFullOrNot("parking lot is full");
            owner.setParkingFullOrNot("parking lot is full");
            return "parking lot is full";
        }
            return "park vehicle";
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
