/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.calculateangulardistance.calc;

import com.blazartech.products.calculateangulardistance.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author AAR1069
 */
@Service
public class CalculateAngularDistancePABImpl implements CalculateAngularDistancePAB {

    private static final Logger logger = LoggerFactory.getLogger(CalculateAngularDistancePABImpl.class);
    
    private double toRadians(double degrees) {
        return Math.toRadians(degrees);
    }
    
    private static final double EARTH_RADIUS = 3959;
    
    @Override
    public double calculateDistance(Coordinate firstCoordinate, Coordinate secondCoordinate) {
        logger.info("calculating distance between " + firstCoordinate + " and " + secondCoordinate);
        
        // do the calculation myself according to https://en.wikipedia.org/wiki/Great-circle_distance
        double firstLatitude = toRadians(firstCoordinate.getLatitude());
        double firstLongitude = toRadians(firstCoordinate.getLongitude());
        double secondLatitude = toRadians(secondCoordinate.getLatitude());
        double secondLongitude = toRadians(secondCoordinate.getLongitude());

        double delLatitude = secondLatitude - firstLatitude;
        double delLongitude = secondLongitude - firstLongitude;

        double term1 = Math.sin(delLatitude / 2);
        double term2 = Math.sin(delLongitude / 2);

        double delSigma = 2 * Math.asin(Math.sqrt(Math.pow(term1, 2) + Math.cos(firstLatitude) * Math.cos(secondLatitude) * Math.pow(term2, 2)));
        double myDistance = Math.round(EARTH_RADIUS * delSigma);
        
        return myDistance;
    }
    
}
