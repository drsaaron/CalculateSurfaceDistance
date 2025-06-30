/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.calculateangulardistance.calc;

import com.blazartech.products.calculateangulardistance.Coordinate;
import com.blazartech.products.calculateangulardistance.DistanceUnit;
import java.math.BigDecimal;

/**
 *
 * @author AAR1069
 */
public interface CalculateAngularDistancePAB {
    
    public BigDecimal calculateDistance(Coordinate firstCoordinate, Coordinate secondCoordinate, DistanceUnit unit);
}
