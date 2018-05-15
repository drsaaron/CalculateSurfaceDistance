/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.calculateangulardistance;

import java.io.Serializable;

/**
 *
 * @author AAR1069
 */
public class AngularDistance implements Serializable {
    
    private Coordinate firstCoordinate;
    private Coordinate secondCoordinate;
    private double distance;

    public Coordinate getFirstCoordinate() {
        return firstCoordinate;
    }

    public void setFirstCoordinate(Coordinate firstCoordinate) {
        this.firstCoordinate = firstCoordinate;
    }

    public Coordinate getSecondCoordinate() {
        return secondCoordinate;
    }

    public void setSecondCoordinate(Coordinate secondCoordinate) {
        this.secondCoordinate = secondCoordinate;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
    
}
