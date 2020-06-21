package com.isober;

public class Coordinate {
    public float coordinateX;
    public float coordinateY;
    public float coordinateZ;

    Coordinate(float x, float y, float z) {
        coordinateX = x;
        coordinateY = y;
        coordinateZ = z;
    }

    public String text() {
        String result = "x: " + coordinateX + " y: " + coordinateY + " z: " + coordinateZ;
        return result;
    }

    public float getSum() {
        float result = 0;
        result += coordinateX;
        result += coordinateY;
        result += coordinateZ;
        return result;
    }

}
