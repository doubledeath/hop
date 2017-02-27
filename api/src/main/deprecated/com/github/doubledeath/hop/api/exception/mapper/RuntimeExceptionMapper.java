package com.github.doubledeath.hop.api.exception.mapper;

import com.github.doubledeath.hop.api.exception.*;
import com.github.doubledeath.hop.api.helper.response.ResponseHelper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Created by doubledeath on 2/16/17.
 */
@Provider
public class RuntimeExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException runtimeException) {
        runtimeException.printStackTrace();

        Response.Status status;
        Long code;
        String message;
        String[] details;

        if (runtimeException instanceof NotFoundException) {
            NotFoundException notFoundException = (NotFoundException) runtimeException;
            status = Response.Status.NOT_FOUND;
            code = notFoundException.getCode();
            message = notFoundException.getMessage();
            details = null;
        } else if (runtimeException instanceof BadRequestException) {
            BadRequestException badRequestException = (BadRequestException) runtimeException;
            status = Response.Status.BAD_REQUEST;
            code = ExceptionCodes.BAD_REQUEST_ERROR;
            message = ExceptionMessages.BAD_REQUEST_ERROR;
            details = badRequestException.getDetails();
        } else if (runtimeException instanceof AccessDeniedException) {
            status = Response.Status.FORBIDDEN;
            code = ExceptionCodes.ACCESS_DENIED_ERROR;
            message = ExceptionMessages.ACCESS_DENIED_ERROR;
            details = null;
        } else {
            status = Response.Status.INTERNAL_SERVER_ERROR;
            code = ExceptionCodes.UNKNOWN_RUNTIME_ERROR;
            message = ExceptionMessages.UNKNOWN_RUNTIME_ERROR;
            details = buildRuntimeExceptionDetails(runtimeException);
        }

        return ResponseHelper.buildCodeMessageDetailsResponse(
                MediaType.APPLICATION_JSON_TYPE,
                status,
                code,
                message,
                details
        );
    }

    private static String[] buildRuntimeExceptionDetails(Exception exception) {
        String details = "";

        if (exception.getMessage() != null) {
            details = exception.getMessage();
        }

        return new String[]{details};
    }

}
