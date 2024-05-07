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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AAR1069
 */
@RestController
@CrossOrigin
public class AngularDistanceService {
    
    private static final Logger logger = LoggerFactory.getLogger(AngularDistanceService.class);
    
    @Autowired
    private CalculateAngularDistancePAB calculator;
    
    @PostMapping(value = "/distance")
    @ResponseStatus(HttpStatus.CREATED)
    public AngularDistance getDistance(@RequestBody AngularDistance distance) {
        logger.info("calculating distance between " + distance.getFirstCoordinate() + " and " + distance.getSecondCoordinate());
        
        double angularDistance = calculator.calculateDistance(distance.getFirstCoordinate(), distance.getSecondCoordinate(), distance.getUnit());
        distance.setDistance(angularDistance);
        return distance;
    }
}
