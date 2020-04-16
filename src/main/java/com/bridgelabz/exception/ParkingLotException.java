package com.bridgelabz.exception;

public class ParkingLotException extends Exception{

    public enum MyexceptionType  {
        LOT_IS_FULL,VEHICLE_ALREADY_PARK;
    }
    MyexceptionType type;

    public ParkingLotException(MyexceptionType type,String message) {
        super(message);
        this.type = type;
    }


}
