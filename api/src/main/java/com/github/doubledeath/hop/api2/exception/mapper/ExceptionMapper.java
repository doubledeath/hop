package com.github.doubledeath.hop.api2.exception.mapper;

import com.github.doubledeath.hop.api2.exception.response.builder.ResponseBuilder;
import com.github.doubledeath.hop.api2.info.Response;

import javax.ws.rs.ext.Provider;

/**
 * Created by doubledeath on 2/20/17.
 */
@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

    private ResponseBuilder responseBuilder = new ResponseBuilder();

    @Override
    public javax.ws.rs.core.Response toResponse(Exception exception) {
        exception.printStackTrace();

        return responseBuilder.buildCodeMessageDetailsResponse(
                javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR,
                Response.Code.UNKNOWN_ERROR,
                Response.Message.UNKNOWN_ERROR,
                exception.getMessage()
        );
    }

}
