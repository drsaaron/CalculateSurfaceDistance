/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.calculateangulardistance.calc;

import com.blazartech.products.calculateangulardistance.Coordinate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author AAR1069
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
    CalculateAngularDistancePABImplTest.CalculateAngularDistancePABImplTestConfiguration.class
})
public class CalculateAngularDistancePABImplTest {
    
    private static final Logger logger = LoggerFactory.getLogger(CalculateAngularDistancePABImplTest.class);
    
    @Configuration
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
    private double expectedDistance;
    
    private Coordinate buildCoordinate(String coordinateString) {
        String[] pieces = coordinateString.split(",");
        Coordinate c = new Coordinate();
        c.setLatitude(Double.valueOf(pieces[0]));
        c.setLongitude(Double.valueOf(pieces[1]));
        return c;
    }
    
    public CalculateAngularDistancePABImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
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
        double result = pab.calculateDistance(firstCoordinate, secondCoordinate);
        assertEquals(expectedDistance, Math.round(result), 0.0);
    }
    
}
