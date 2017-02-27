package com.github.doubledeath.hop.api3.exception.mapper;

import com.github.doubledeath.hop.api3.exception.StatusCodeMessageException;
import com.github.doubledeath.hop.api3.exception.response.CodeMessageDetailsResponse;
import com.github.doubledeath.hop.api3.ref.ResponseRef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Created by doubledeath on 2/21/17.
 */
@Provider
public class RuntimeExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException runtimeException) {
        runtimeException.printStackTrace();

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
        Long code = ResponseRef.Code.UNKNOWN_ERROR;
        String message = ResponseRef.Message.UNKNOWN_ERROR;
        String[] details = null;

        if (runtimeException instanceof StatusCodeMessageException) {
            StatusCodeMessageException statusCodeMessageException = (StatusCodeMessageException) runtimeException;

            status = statusCodeMessageException.getStatus();
            code = statusCodeMessageException.getCode();
            message = statusCodeMessageException.getMessage();
        } else if (runtimeException instanceof ConstraintViolationException) {
            status = Response.Status.BAD_REQUEST;
            code = ResponseRef.Code.BAD_REQUEST_ERROR;
            message = ResponseRef.Message.BAD_REQUEST_ERROR;
            details = buildConstraintViolationExceptionDetails((ConstraintViolationException) runtimeException);
        }

        return Response.status(status)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new CodeMessageDetailsResponse(code, message, details))
                .build();
    }

    @Nullable
    private String[] buildConstraintViolationExceptionDetails(@NotNull ConstraintViolationException constraintViolationException) {
        return constraintViolationException.getConstraintViolations() == null ?
                null :
                constraintViolationException.getConstraintViolations().stream()
                        .map(constraintViolation -> {
                            String property = String.valueOf(constraintViolation.getPropertyPath());

                            if (property.contains(".")) {
                                property = property.substring(property.lastIndexOf(".") + 1);
                            }

                            return property + " " + constraintViolation.getMessage();
                        })
                        .toArray(String[]::new);
    }

}
