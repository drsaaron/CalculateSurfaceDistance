/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.calculateangulardistance.calc;

import com.blazartech.products.calculateangulardistance.Coordinate;
import static com.blazartech.products.calculateangulardistance.DistanceUnit.miles;
import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author AAR1069
 */
@ExtendWith(SpringExtension.class)
public class CalculateAngularDistancePABImplTest {
    
    private static final Logger logger = LoggerFactory.getLogger(CalculateAngularDistancePABImplTest.class);
    
    @TestConfiguration
    @PropertySource("classpath:test.properties")
    static class CalculateAngularDistancePABImplTestConfiguration {
        
        @Bean
        public CalculateAngularDistancePABImpl getCalculateAngularDistancePABImpl() {
            return new CalculateAngularDistancePABImpl();
        }
    }
    
    @Autowired
    private CalculateAngularDistancePABImpl pab;
    
    @Value("${firstCoordinate}")
    private String firstCoordinateString;
    
    @Value("${secondCoordinate}")
    private String secondCoordinateString;
    
    @Value("${expectedDistance}")
    private BigDecimal expectedDistance;
    
    private Coordinate buildCoordinate(String coordinateString) {
        String[] pieces = coordinateString.split(",");
        Coordinate c = new Coordinate();
        c.setLatitude(Double.parseDouble(pieces[0]));
        c.setLongitude(Double.parseDouble(pieces[1]));
        return c;
    }
    
    public CalculateAngularDistancePABImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of calculateDistance method, of class CalculateAngularDistancePABImpl.
     */
    @Test
    public void testCalculateDistanceMilwaukeeBoston() {
        logger.info("testCalculateDistanceMilwaukeeBoston");
        
        Coordinate firstCoordinate = buildCoordinate(firstCoordinateString);
        Coordinate secondCoordinate = buildCoordinate(secondCoordinateString);
        BigDecimal result = pab.calculateDistance(firstCoordinate, secondCoordinate, miles);
        assertEquals(expectedDistance.doubleValue(), result.doubleValue(), 0.0);
    }
    
    @Test
    public void test0Distance() {
        logger.info("test0Distance");
        
        Coordinate c = buildCoordinate(firstCoordinateString);
        BigDecimal result = pab.calculateDistance(c, c, miles);
        assertEquals(0, result.doubleValue(), 0.0);
    }
}
