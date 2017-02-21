package com.github.doubledeath.hop.api2.exception.mapper;

import com.github.doubledeath.hop.api2.exception.StatusCodeMessageException;
import com.github.doubledeath.hop.api2.exception.response.builder.ResponseBuilder;
import com.github.doubledeath.hop.api2.info.Response;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by doubledeath on 2/20/17.
 */
@Provider
public class RuntimeExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<RuntimeException> {

    private ResponseBuilder responseBuilder = new ResponseBuilder();

    @Override
    public javax.ws.rs.core.Response toResponse(RuntimeException runtimeException) {
        runtimeException.printStackTrace();

        javax.ws.rs.core.Response.Status status;
        Long code;
        String message;
        String[] details;

        if (runtimeException instanceof StatusCodeMessageException) {
            StatusCodeMessageException statusCodeMessageException = (StatusCodeMessageException) runtimeException;

            status = statusCodeMessageException.getStatus();
            code = statusCodeMessageException.getCode();
            message = statusCodeMessageException.getMessage();
            details = null;
        } else if (runtimeException instanceof ConstraintViolationException) {
            status = javax.ws.rs.core.Response.Status.BAD_REQUEST;
            code = Response.Code.BAD_REQUEST_ERROR;
            message = Response.Message.BAD_REQUEST_ERROR;
            details = buildConstraintViolationExceptionDetails((ConstraintViolationException) runtimeException);
        } else {
            status = javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
            code = Response.Code.UNKNOWN_ERROR;
            message = Response.Message.UNKNOWN_ERROR;
            details = runtimeException.getMessage() == null ? null : new String[]{runtimeException.getMessage()};
        }

        return responseBuilder.buildCodeMessageDetailsResponse(status, code, message, details);
    }

    private String[] buildConstraintViolationExceptionDetails(ConstraintViolationException constraintViolationException) {
        List<String> details = new ArrayList<>();

        constraintViolationException.getConstraintViolations().forEach(constraintViolation -> {
            String property = String.valueOf(constraintViolation.getPropertyPath());

            if (property.contains(".")) {
                property = property.substring(property.lastIndexOf(".") + 1);
            }

            details.add(property + " " + constraintViolation.getMessage());
        });

        return details.toArray(new String[details.size()]);
    }

}
