package com.github.doubledeath.hop.api3.exception.mapper;

import com.github.doubledeath.hop.api3.exception.response.CodeMessageDetailsResponse;
import com.github.doubledeath.hop.api3.ref.ResponseRef;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Created by doubledeath on 2/21/17.
 */
@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        exception.printStackTrace();

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
        Long code = ResponseRef.Code.UNKNOWN_ERROR;
        String message = ResponseRef.Message.UNKNOWN_ERROR;

        return Response.status(status)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new CodeMessageDetailsResponse(code, message))
                .build();
    }

}
