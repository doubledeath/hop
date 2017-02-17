package com.github.doubledeath.hop.api.exception.mapper;

import com.github.doubledeath.hop.api.exception.ExceptionCodes;
import com.github.doubledeath.hop.api.exception.ExceptionMessages;
import com.github.doubledeath.hop.api.helper.response.ResponseHelper;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by doubledeath on 2/16/17.
 */
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException validationException) {
        Response.Status status;
        Long code;
        String message;
        String[] details;

        if (validationException instanceof ConstraintViolationException) {
            status = Response.Status.BAD_REQUEST;
            code = ExceptionCodes.CONSTRAINT_VIOLATION_ERROR;
            message = ExceptionMessages.CONSTRAINT_VIOLATION_ERROR;
            details = buildConstraintViolationDetails((ConstraintViolationException) validationException);
        } else {
            status = Response.Status.INTERNAL_SERVER_ERROR;
            code = ExceptionCodes.UNKNOWN_VALIDATION_ERROR;
            message = ExceptionMessages.UNKNOWN_VALIDATION_ERROR;
            details = buildValidationExceptionDetails(validationException);
        }

        return ResponseHelper.buildCodeMessageDetailsResponse(
                MediaType.APPLICATION_JSON_TYPE,
                status,
                code,
                message,
                details
        );
    }

    private static String[] buildConstraintViolationDetails(ConstraintViolationException constraintViolationException) {
        final List<String> details = new ArrayList<>();

        constraintViolationException.getConstraintViolations().forEach(constraintViolation -> {
            String property = String.valueOf(constraintViolation.getPropertyPath());

            if (property.contains(".")) {
                property = property.substring(property.lastIndexOf(".") + 1);
            }

            details.add(property + " " + constraintViolation.getMessage());
        });

        return details.toArray(new String[details.size()]);
    }

    private static String[] buildValidationExceptionDetails(Exception exception) {
        String details = "";

        if (exception.getMessage() != null) {
            details = exception.getMessage();
        }

        return new String[]{details};
    }

}
