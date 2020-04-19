package com.bridgelabz.service;

import com.bridgelabz.Observer;
import com.bridgelabz.VehiclePOJO;
import com.bridgelabz.exception.ParkingLotException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class ParkingLotMain {

    LinkedHashMap<Integer,Object> parkingLot = new LinkedHashMap<Integer, Object>();
    private List<Observer> observableList = new ArrayList<>();
    private String isFull;
    Integer capacity = 3;
    Integer key = 0;

    public ParkingLotMain() {
        for (Integer key = 1; key<=capacity; key++){
            parkingLot.put(key,null);
        }
    }

    public void addObserver(Observer observable) {
        this.observableList.add(observable);
    }

     public void setStatus(String isFull) {
        this.isFull = isFull;
        for (Observer observable : this.observableList) {
            observable.update(this.isFull);
        }
    }

    // park vehicle and check parking lot
    public String park(VehiclePOJO vehicle) throws  ParkingLotException {
        if (parkingLot.containsValue(vehicle))
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_ALREADY_PARK,"This vehicle already park");
       key = vehicleParkLotNumber();
        parkingLot.replace(key,vehicle);
        setStatus("this vehicle charge Rs.10");
        if (key%3 == 0)
            setStatus("Full");
        return "park vehicle";
    }

    // Check Vehicle is present or not
    public String  isVehiclePark(VehiclePOJO vehicle) throws ParkingLotException {
        if (parkingLot.containsValue(vehicle)) {
            int lotPosition = occupiedParkingLot(vehicle);
            return "vehicle park in lot number "+lotPosition;
        }
        else {
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_NOT_PARK,
                                                                           "This vehicle not park in my parking lot");
        }
    }
    public int vehicleParkLotNumber(){
        Integer k=1;
        for ( ; k<=capacity ; k++)
            if (parkingLot.get(k) == null)
                return k;
        return k;
    }
    public int occupiedParkingLot(VehiclePOJO vehicle){
        int k = 0;
        for (Object o: parkingLot.values()) {
            k++;
            if (o==vehicle)
                return k;
        }
        return k+1;
    }
    // un park vehicle
    public String unPark(VehiclePOJO vehicle) throws ParkingLotException {
        int key = occupiedParkingLot(vehicle);
        if (parkingLot.containsValue(vehicle)) {
            parkingLot.replace(key,null);
            setStatus("Have Space lot number "+key);
            return "unpark";
        }
        else{
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_NOT_PARK,
                                                                           "This vehicle not park in my parking lot");
        }
    }
}
