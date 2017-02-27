package com.github.doubledeath.hop.api.db.entity.converter;

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
public class ListLongToJsonbConverter implements AttributeConverter<List<Long>, String> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Long> listLong) {
        try {
            return objectMapper.writeValueAsString(listLong);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Long> convertToEntityAttribute(String jsonb) {
        try {
            return objectMapper.readValue(jsonb, new TypeReference<List<Long>>() {
            });
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
