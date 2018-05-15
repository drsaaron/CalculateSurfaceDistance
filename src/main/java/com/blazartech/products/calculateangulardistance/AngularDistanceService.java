/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.calculateangulardistance;

import com.blazartech.products.calculateangulardistance.calc.CalculateAngularDistancePAB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AAR1069
 */
@RestController
public class AngularDistanceService {
    
    private static final Logger logger = LoggerFactory.getLogger(AngularDistanceService.class);
    
    @Autowired
    private CalculateAngularDistancePAB calculator;
    
    @RequestMapping(value = "/distance", method = RequestMethod.POST)
    public AngularDistance getDistance(@RequestBody AngularDistance distance) {
        logger.info("calculating distance between " + distance.getFirstCoordinate() + " and " + distance.getSecondCoordinate());
        
        double angularDistance = calculator.calculateDistance(distance.getFirstCoordinate(), distance.getSecondCoordinate());
        distance.setDistance(angularDistance);
        return distance;
    }
}
