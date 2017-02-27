package com.github.doubledeath.hop.api.exception.mapper;

import com.github.doubledeath.hop.api.exception.ExceptionCodes;
import com.github.doubledeath.hop.api.exception.ExceptionMessages;
import com.github.doubledeath.hop.api.helper.response.ResponseHelper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Created by doubledeath on 2/16/17.
 */
@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        exception.printStackTrace();

        return ResponseHelper.buildCodeMessageDetailsResponse(
                MediaType.APPLICATION_JSON_TYPE,
                Response.Status.INTERNAL_SERVER_ERROR,
                ExceptionCodes.UNKNOWN_ERROR,
                ExceptionMessages.UNKNOWN_ERROR,
                buildExceptionDetails(exception)
        );
    }

    private static String buildExceptionDetails(Exception exception) {
        String details = "";

        if (exception.getMessage() != null) {
            details = exception.getMessage();
        }

        return details;
    }

}
