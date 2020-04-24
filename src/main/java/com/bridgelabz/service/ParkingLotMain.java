package com.bridgelabz.service;
import com.bridgelabz.utilities.CalculateTime;
import com.bridgelabz.observer.Observer;
import com.bridgelabz.utilities.ParkingLotAttendant;
import com.bridgelabz.utilities.PoliceDepartment;
import com.bridgelabz.utilities.VehiclePOJO;
import com.bridgelabz.exception.ParkingLotException;
import java.text.ParseException;
import java.util.*;

public class ParkingLotMain {

    public LinkedHashMap<Integer,VehiclePOJO> parkingLot = new LinkedHashMap<>();
    public Map<Integer,Object> vehicleTime = new HashMap<>();
    private List<Observer> observableList = new ArrayList<>();
    ParkingLotAttendant attendant;
    PoliceDepartment police;
    private String isFull;
    CalculateTime time;
    String location = "";
    int froudNumberplate = 0;

    // constructor to put key and null value
    public ParkingLotMain(Integer capacity,int slot) {
        attendant = new ParkingLotAttendant(parkingLot,capacity,slot);
        time = new CalculateTime(vehicleTime);
        police = new PoliceDepartment();
        for (Integer key = 1; key<=capacity; key++){
            parkingLot.put(key,null);
            vehicleTime.put(key,null);
        }
    }

    public ParkingLotMain() {

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
        Integer key = attendant.vehicleParkLotNumber(vehicle);
        parkingLot.replace(key,vehicle);
        vehicleTime.replace(key,CalculateTime.getCurrentTime());
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

    public int serching(String... contains) throws ParseException {
        if (contains.length==2 && contains[0].contains(":") && contains[1].contains(":") )
           location=time.vehicleIntime(contains);
        else {
            for (Object o : parkingLot.values()) {
                int count = 0;
                for (int index = 0; index < contains.length; index++)
                    if (o.toString().contains(contains[index]))
                        count++;
                if (count == contains.length)
                    location += attendant.occupiedParkingLot(o) + ",";
                else froudNumberplate++;
            }
        }
        setStatus(location);
        return froudNumberplate;
    }
    public void fraudulentPlate() throws ParseException {
        String fraudPlate ="";
        int i=1;
        for (Object o: police.numberRegister.values()) {
             String keyv=police.numberRegister.keySet().stream().filter(key -> o.equals(police.numberRegister.get(key))).findFirst().get();
             String value=o.toString();
             serching(keyv,value);
             if (froudNumberplate == parkingLot.size())
                 fraudPlate+=i+",";
             froudNumberplate=0;i++;
        }
        setStatus(fraudPlate);
    }
}
