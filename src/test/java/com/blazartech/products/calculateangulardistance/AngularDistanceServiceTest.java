/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.blazartech.products.calculateangulardistance;

import com.blazartech.products.calculateangulardistance.calc.CalculateAngularDistancePAB;
import com.blazartech.products.calculateangulardistance.calc.CalculateAngularDistancePABImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author scott
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(AngularDistanceService.class)
public class AngularDistanceServiceTest {
    
    private static final Logger logger = LoggerFactory.getLogger(AngularDistanceServiceTest.class);
    
    @TestConfiguration
    @PropertySource("classpath:test.properties")
    public static class AngularDistanceServiceTestConfiguration {
        
        @Bean
        public CalculateAngularDistancePAB calculator() {
            return new CalculateAngularDistancePABImpl();
        }
    }
    
    @Autowired
    private MockMvc mockMvc;
    
    public AngularDistanceServiceTest() {
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

    @Value("${firstCoordinate}")
    private String firstCoordinateString;
    
    @Value("${secondCoordinate}")
    private String secondCoordinateString;
    
    @Value("${expectedDistance}")
    private double expectedDistance;
    
    private Coordinate buildCoordinate(String coordinateString) {
        String[] pieces = coordinateString.split(",");
        Coordinate c = new Coordinate();
        c.setLatitude(Double.parseDouble(pieces[0]));
        c.setLongitude(Double.parseDouble(pieces[1]));
        return c;
    }
    
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Test of getDistance method, of class AngularDistanceService.
     */
    @Test
    public void testGetDistance() {
        logger.info("getDistance");
        
        Coordinate firstCoordinate = buildCoordinate(firstCoordinateString);
        Coordinate secondCoordinate = buildCoordinate(secondCoordinateString);
        
        AngularDistance distance = new AngularDistance();
        distance.setFirstCoordinate(firstCoordinate);
        distance.setSecondCoordinate(secondCoordinate);
        distance.setUnit(DistanceUnit.miles);
        
        try {
            MvcResult result = mockMvc
                    .perform(
                            post("/distance")
                                    .content(asJsonString(distance))
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andReturn();

            String response = result.getResponse().getContentAsString();
            AngularDistance createdDistance = objectMapper.readValue(response, AngularDistance.class);

            assertNotNull(createdDistance);
            assertEquals(expectedDistance, createdDistance.getDistance(), 0.0);

        } catch (Exception e) {
            throw new RuntimeException("error running test: " + e.getMessage(), e);
        }
    }
    
}
