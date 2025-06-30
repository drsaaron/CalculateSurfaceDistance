/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.calculateangulardistance;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author AAR1069
 */
public class AngularDistance implements Serializable {
    
    private Coordinate firstCoordinate;
    private Coordinate secondCoordinate;
    
//    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal distance;
    
    private DistanceUnit unit;

    public DistanceUnit getUnit() {
        return unit;
    }

    public void setUnit(DistanceUnit unit) {
        this.unit = unit;
    }
    
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

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }
    
    
}
