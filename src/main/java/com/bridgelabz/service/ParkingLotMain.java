package com.bridgelabz.service;
import com.bridgelabz.CalculateTime;
import com.bridgelabz.Observer;
import com.bridgelabz.ParkingLotAttendant;
import com.bridgelabz.VehiclePOJO;
import com.bridgelabz.exception.ParkingLotException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ParkingLotMain {

    public LinkedHashMap<Integer,VehiclePOJO> parkingLot = new LinkedHashMap<>();
    public Map<Integer,Object> vehicleTime = new HashMap<>();
    private List<Observer> observableList = new ArrayList<>();
    ParkingLotAttendant attendant;
    private String isFull;

    // constructor to put key and null value
    public ParkingLotMain(Integer capacity,int slot) {
        attendant = new ParkingLotAttendant(parkingLot,capacity,slot);
        for (Integer key = 1; key<=capacity; key++){
            parkingLot.put(key,null);
        }
    }
    public ParkingLotMain(){}
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
        Integer key = attendant.vehicleParkLotNumber(vehicle);
        parkingLot.replace(key,vehicle);
        vehicleTime.put(key,CalculateTime.getCurrentTime());
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

    public void serching(String... contains) throws ParseException {
        String location = "";
        if (contains.length==2 && contains[0].contains(":") && contains[1].contains(":") ){
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Date startTime = format.parse(contains[0]);
            Date endTime = format.parse(contains[1]);
            try {
                for (int vehicleKey=1;vehicleKey<=vehicleTime.size();vehicleKey++) {
                    Date userDate = format.parse(vehicleTime.get(vehicleKey).toString());
                    if (userDate.after(startTime) && userDate.before(endTime))
                        location += vehicleKey +",";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            for (Object o : parkingLot.values()) {
                int count = 0;
                for (int index = 0; index < contains.length; index++)
                    if (o.toString().contains(contains[index]))
                        count++;
                if (count == contains.length)
                    location += attendant.occupiedParkingLot(o) + ",";
            }
        }
        setStatus(location);
    }
}
