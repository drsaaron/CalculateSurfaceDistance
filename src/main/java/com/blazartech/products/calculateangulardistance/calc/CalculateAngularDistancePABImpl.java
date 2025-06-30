/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.calculateangulardistance.calc;

import com.blazartech.products.calculateangulardistance.Coordinate;
import com.blazartech.products.calculateangulardistance.DistanceUnit;
import static com.blazartech.products.calculateangulardistance.DistanceUnit.kilometers;
import static com.blazartech.products.calculateangulardistance.DistanceUnit.miles;
import static java.lang.Math.asin;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;
import java.math.BigDecimal;
import java.util.Map;
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
    
    private static final double EARTH_RADIUS_MILES = 3959; // units are miles, bad scientist
    private static final double EARTH_RADIUS_KILOMETERS = 6378; // km, good scientist
    private static final Map<DistanceUnit, Double> EARTH_RADIUS = Map.of(
            miles, EARTH_RADIUS_MILES,
            kilometers, EARTH_RADIUS_KILOMETERS
    );
        
    @Override
    public BigDecimal calculateDistance(Coordinate firstCoordinate, Coordinate secondCoordinate, DistanceUnit distanceUnit) {
        logger.info("calculating distance between {} and {} with units {}", firstCoordinate,secondCoordinate, distanceUnit);
        
        // do the calculation myself according to https://en.wikipedia.org/wiki/Great-circle_distance
        double firstLatitude = toRadians(firstCoordinate.getLatitude());
        double firstLongitude = toRadians(firstCoordinate.getLongitude());
        double secondLatitude = toRadians(secondCoordinate.getLatitude());
        double secondLongitude = toRadians(secondCoordinate.getLongitude());

        double delLatitude = secondLatitude - firstLatitude;
        double delLongitude = secondLongitude - firstLongitude;

        double term1 = Math.sin(delLatitude / 2);
        double term2 = Math.sin(delLongitude / 2);

        double delSigma = 2 * asin(sqrt(Math.pow(term1, 2) + cos(firstLatitude) * cos(secondLatitude) * pow(term2, 2)));
        double earthRadius = EARTH_RADIUS.get(distanceUnit);
        double myDistance = round(earthRadius * delSigma);
        
        return BigDecimal.valueOf(myDistance);
    }
    
}
