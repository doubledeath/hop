package com.github.doubledeath.hop.api.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.List;

/**
 * Created by doubledeath on 2/22/17.
 */
@Converter
public class ListToJsonbConverter implements AttributeConverter<List<?>, String> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<?> list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<?> convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<?>>() {
            });
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
