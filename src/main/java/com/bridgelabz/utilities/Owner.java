package com.bridgelabz.utilities;

import com.bridgelabz.observer.Observer;

public class Owner implements Observer {
    private String parkingFull;
    private String parkingSpace;
    private String parkingCharge;

    @Override
    public void update(Object status) {
        String objecToString = status.toString();
        if (objecToString.contains("Full"))
            this.setParkingFull((String) status);
        else if (objecToString.contains("Have Space lot number "))
            this.setParkingSpace((String) status);
        else if (objecToString.contains("this vehicle charge Rs."))
            this.setParkingCharge((String) status);
    }

    // setter method
    public void setParkingFull(String parkingFull) {
        this.parkingFull = parkingFull;
    }
    public void setParkingSpace(String parkingSpace) {
        this.parkingSpace = parkingSpace;
    }
    public void setParkingCharge(String parkingCharge) {
        this.parkingCharge = parkingCharge;
    }

    // getter method
    public String getParkingFull() {
        return parkingFull;
    }
    public String getParkingSpace() {
        return parkingSpace;
    }
    public String getParkingCharge() {
        return parkingCharge;
    }
}
