/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.products.calculateangulardistance;

import java.math.BigDecimal;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.json.JsonMapper.Builder;
import tools.jackson.databind.module.SimpleModule;

/**
 *
 * @author aar1069
 */
@Configuration
public class FormatDecimalAsStringConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(FormatDecimalAsStringConfiguration.class);
    
    // a generic serializer to convert things to string values.
    private static class ToStringSerializer<T> extends ValueSerializer<T> {

        @Override
        public void serialize(T t, JsonGenerator jg, SerializationContext sp) {
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
    public Builder objectMapper() {
        SimpleModule sm = new SimpleModule();        
        sm.addSerializer(Double.class, new ToStringSerializer<>( d -> Double.toString(d) ));
        sm.addSerializer(BigDecimal.class, new ToStringSerializer<>( bd -> bd.toString() ));

        Builder mapper = JsonMapper.builder()
  //              .serializationInclusion(JsonInclude.Include.NON_NULL)
                .addModule(sm);
        return mapper;
    }
}
