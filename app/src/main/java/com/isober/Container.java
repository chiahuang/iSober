package com.isober;

import java.util.Vector;

public class Container {
    Vector<Coordinate> myvector;

    Container() {
        myvector = new Vector<Coordinate>();


    }

    public void append(Coordinate point) {
        myvector.addElement(point);
    }

    public Coordinate average() {
        float x = 0;
        float y = 0;
        float z = 0;
        Coordinate newPoint = new Coordinate(x,y,z);
        for(int i=0; i<myvector.size();i++) {
            newPoint.coordinateX += myvector.get(i).coordinateX;
            newPoint.coordinateY += myvector.get(i).coordinateY;
            newPoint.coordinateZ += myvector.get(i).coordinateZ;

        }
        newPoint.coordinateX = newPoint.coordinateX/myvector.size();
        newPoint.coordinateY = newPoint.coordinateY/myvector.size();
        newPoint.coordinateZ = newPoint.coordinateZ/myvector.size();

        return newPoint;
    }

    public Coordinate difference() {
        if (myvector.size() == 1) {
            return myvector.get(0);
        } else if(myvector.size() == 0) {
            return new Coordinate(0,0,0);
        }
        float x = 0;
        float y = 0;
        float z = 0;
        Coordinate newPoint = new Coordinate(x,y,z);
        for (int i=1; i<myvector.size(); i++) {
            newPoint.coordinateX += Math.abs(myvector.get(i).coordinateX - myvector.get(i-1).coordinateX);
            newPoint.coordinateY += Math.abs(myvector.get(i).coordinateY - myvector.get(i-1).coordinateY);
            newPoint.coordinateZ += Math.abs(myvector.get(i).coordinateZ - myvector.get(i-1).coordinateZ);
        }
        newPoint.coordinateX = newPoint.coordinateX/(myvector.size()-1);
        newPoint.coordinateY = newPoint.coordinateY/(myvector.size()-1);
        newPoint.coordinateZ = newPoint.coordinateZ/(myvector.size()-1);
        return newPoint;
    }

    public void empty() {
        myvector.clear();
    }
}