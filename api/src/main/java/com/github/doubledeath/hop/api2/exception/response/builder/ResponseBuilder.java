package com.github.doubledeath.hop.api2.exception.response.builder;

import com.github.doubledeath.hop.api2.exception.response.CodeMessageDetailsResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by doubledeath on 2/21/17.
 */
public class ResponseBuilder {

    public Response buildCodeMessageDetailsResponse(Response.Status status, Long code, String message, String... details) {
        CodeMessageDetailsResponse entity = new CodeMessageDetailsResponse();

        entity.setCode(code);
        entity.setMessage(message);
        entity.setDetails(details);

        return Response.status(status)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(entity)
                .build();
    }

}
