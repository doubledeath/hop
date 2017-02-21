package com.github.doubledeath.hop.api2.exception;

import javax.ws.rs.core.Response;

/**
 * Created by doubledeath on 2/21/17.
 */
public class ConflictException extends StatusCodeMessageException {

    private static final long serialVersionUID = -3089256858042808790L;

    public ConflictException(Long code, String message) {
        super(Response.Status.CONFLICT, code, message);
    }

}
