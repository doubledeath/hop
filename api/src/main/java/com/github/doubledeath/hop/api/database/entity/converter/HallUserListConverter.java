package com.github.doubledeath.hop.api.database.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.List;

/**
 * Created by doubledeath on 2/17/17.
 */
@Converter
public class HallUserListConverter implements AttributeConverter<List<Long>, String> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Long> list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();

            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Long> convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<Long>>() {
            });
        } catch (IOException exception) {
            exception.printStackTrace();

            throw new RuntimeException(exception);
        }
    }

}
