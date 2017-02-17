package com.github.doubledeath.hop.api.helper.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

/**
 * Created by doubledeath on 2/16/17.
 */
public final class ResponseHelper {

    private static final Object EMPTY_JSON = "{}";

    /**
     * Supports only application/json
     */
    public static Response buildEmptyResponse(Response.Status status, MediaType mediaType) {
        checkMediaType(mediaType);

        return Response.status(status)
                .entity(EMPTY_JSON)
                .type(mediaType)
                .build();
    }

    /**
     * Supports only application/json
     */
    public static Response buildOkEmptyResponse(MediaType mediaType) {
        return buildEmptyResponse(Response.Status.OK, mediaType);
    }

    /**
     * Supports only application/json, arguments must be not null
     */
    public static Response buildCodeMessageDetailsResponse(MediaType mediaType, Response.Status status, Long code, String message, String... details) {
        checkMediaType(mediaType);
        checkNullsVarargs(status, code, message);
        checkNullsArray(details);

        Object entity;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            CodeMessageDetailsResponse value = new CodeMessageDetailsResponse(code, message);

            if (details != null && details.length > 0) {
                value.setDetails(Arrays.asList(details));
            }

            entity = objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }

        return Response.status(status)
                .entity(entity)
                .type(mediaType)
                .build();
    }

    private static void checkMediaType(MediaType mediaType) {
        if (mediaType == null || mediaType != MediaType.APPLICATION_JSON_TYPE) {
            throw new IllegalArgumentException("mediaType == null || mediaType != APPLICATION_JSON_TYPE");
        }
    }

    private static void checkNullsVarargs(Object... objects) {
        checkNullsArray(objects);
    }

    private static void checkNullsArray(Object[] objects) {
        if (objects != null && objects.length > 0) {
            for (Object object : objects) {
                if (object == null) {
                    throw new IllegalArgumentException("arguments must be not null");
                }
            }
        }
    }

}
