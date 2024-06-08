/*
package com.lifepill.possystem.util.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lifepill.possystem.dto.responseDTO.EmployerAuthDetailsResponseDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployerAuthDetailsResponseDTOToStringConverter implements Converter<EmployerAuthDetailsResponseDTO, String> {

    private final ObjectMapper objectMapper;

    public EmployerAuthDetailsResponseDTOToStringConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String convert(EmployerAuthDetailsResponseDTO source) {
        try {
            return objectMapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting EmployerAuthDetailsResponseDTO to JSON string", e);
        }
    }
}
*/
