package com.isober;

import android.hardware.Sensor;
import android.hardware.SensorEvent;


public class SenMan {
    private Container listOfPoints;

    SenMan() {
        listOfPoints = new Container();
    }
    public void getEvent(SensorEvent event) {
        Sensor mySensor = event.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            listOfPoints.append(new Coordinate(x,y,z));
        }

    }

    public Coordinate displayDifference() {
        Coordinate dif = listOfPoints.difference();
        return dif;
    }

    public void clearContainer() {
        listOfPoints.empty();
    }

}
