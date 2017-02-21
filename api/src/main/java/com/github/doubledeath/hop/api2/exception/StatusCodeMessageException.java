package com.github.doubledeath.hop.api2.exception;

import javax.ws.rs.core.Response;

/**
 * Created by doubledeath on 2/21/17.
 */
public abstract class StatusCodeMessageException extends RuntimeException {

    private static final long serialVersionUID = -1676820439336661242L;

    private Response.Status status;
    private Long code;

    StatusCodeMessageException(Response.Status status, Long code, String message) {
        super(message);

        this.status = status;
        this.code = code;
    }

    public Response.Status getStatus() {
        return status;
    }

    public Long getCode() {
        return code;
    }

}
