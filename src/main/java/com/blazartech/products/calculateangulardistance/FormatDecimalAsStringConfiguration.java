/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.products.calculateangulardistance;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.autoconfigure.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author aar1069
 */
@Configuration
public class FormatDecimalAsStringConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(FormatDecimalAsStringConfiguration.class);
    
    // a generic serializer to convert things to string values.
    private static class ToStringSerializer<T> extends JsonSerializer<T> {

        @Override
        public void serialize(T t, JsonGenerator jg, SerializerProvider sp) throws IOException {
            logger.info("serializing decimal value {}", t);
            if (null == t) {
                jg.writeNull();
            } else {
                jg.writeString(stringConverter.apply(t));
            }
        }
        
        // the function that does the conversion to string
        private final Function<T, String> stringConverter;

        public ToStringSerializer(Function<T, String> stringConverter) {
            this.stringConverter = stringConverter;
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
                .serializerByType(Double.class, new ToStringSerializer<Double>( d -> Double.toString(d) ))
                .serializerByType(BigDecimal.class, new ToStringSerializer<BigDecimal>( bd -> bd.toString() ));
    }
}
