package com.github.doubledeath.hop.api2.exception;

import javax.ws.rs.core.Response;

/**
 * Created by doubledeath on 2/20/17.
 */
public class BadRequestException extends StatusCodeMessageException {

    private static final long serialVersionUID = -2027850723556705355L;

    public BadRequestException(Long code, String message) {
        super(Response.Status.BAD_REQUEST, code, message);
    }

}
