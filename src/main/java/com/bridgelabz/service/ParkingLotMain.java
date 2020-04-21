package com.bridgelabz.service;
import com.bridgelabz.Observer;
import com.bridgelabz.ParkingLotAttendant;
import com.bridgelabz.VehiclePOJO;
import com.bridgelabz.exception.ParkingLotException;
import java.util.*;

public class ParkingLotMain {

    public LinkedHashMap<Integer,Object> parkingLot = new LinkedHashMap<>();
    private List<Observer> observableList = new ArrayList<>();
    ParkingLotAttendant attendant;
    private String isFull;
    Integer key = 0;

    // constructor to put key and null value
    public ParkingLotMain(Integer capacity,int slot) {
        attendant = new ParkingLotAttendant(parkingLot,capacity,slot);
        for (Integer key = 1; key<=capacity; key++){
            parkingLot.put(key,null);
        }
    }
    // add object to observableList
    public void addObserver(Observer observable) {
        this.observableList.add(observable);
    }
    // send notification
     public void setStatus(String isFull) {
        this.isFull = isFull;
        for (Observer observable : this.observableList)
            observable.update(this.isFull);
    }
    // park vehicle and check parking lot
    public String park(VehiclePOJO vehicle) throws  ParkingLotException {
        if (parkingLot.containsValue(vehicle))
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_ALREADY_PARK,"This vehicle already park");
        key = attendant.vehicleParkLotNumber();
        parkingLot.replace(key,vehicle);
        setStatus("this vehicle charge Rs.10");
        String lotStatus = attendant.isLotFull();
        setStatus(lotStatus);

        return "park vehicle";
    }
    // Check Vehicle is present or not
    public String  isVehiclePark(VehiclePOJO vehicle) throws ParkingLotException {
        if (parkingLot.containsValue(vehicle))
            return "vehicle park in lot number "+attendant.occupiedParkingLot(vehicle);
        else
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_NOT_PARK,
                                                                           "This vehicle not park in my parking lot");
    }
    // un park vehicle
    public String unPark(VehiclePOJO vehicle) throws ParkingLotException {
        int key = attendant.occupiedParkingLot(vehicle);
        if (parkingLot.containsValue(vehicle)) {
            parkingLot.replace(key,null);
            setStatus("Have Space lot number "+key);
            return "unpark";
        }
        else
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_NOT_PARK,
                                                                           "This vehicle not park in my parking lot");
    }

}
