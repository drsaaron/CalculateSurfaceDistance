/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.products.calculateangulardistance;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author aar1069
 */
@Configuration
public class FormatDecimalAsStringConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(FormatDecimalAsStringConfiguration.class);
    
    // a serializer to express Double values as strings
    private static class DoubleSerializer extends JsonSerializer<Double> {

        @Override
        public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
            logger.info("serializing double value {}", value);
            if (null == value) {
                jgen.writeNull();
            } else {
                jgen.writeString(Double.toString(value));
            }
        }
    }
    
    // a serializer to express BigDecimal values as strings
    private static class BigDecimalSerializer extends JsonSerializer<BigDecimal> {

        @Override
        public void serialize(BigDecimal t, JsonGenerator jg, SerializerProvider sp) throws IOException {
            logger.info("serializing BigDecimal value {}", t);
            if (null == t) {
                jg.writeNull();
            } else {
                jg.writeString(t.toString());
            }
        }
        
    }

    /**
     * object mapper customizer that will configure the object mapper to write
     * Double and BigDecimal values as strings.  This is done instead of @JsonFormat
     * annotations on properties in order to be more re-usable.
     * @return 
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> builder.serializationInclusion(JsonInclude.Include.NON_NULL)
                .serializerByType(Double.class, new DoubleSerializer())
                .serializerByType(BigDecimal.class, new BigDecimalSerializer());
    }
}
