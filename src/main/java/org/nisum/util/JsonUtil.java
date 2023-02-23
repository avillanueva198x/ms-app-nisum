package org.nisum.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Alexis Villanueva
 * @created 22/02/2023
 */
@Slf4j
public class JsonUtil {
    

    public static final String getJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Unable to convert Object to JSON " + e, e);
            return null;
        }
    }
}
