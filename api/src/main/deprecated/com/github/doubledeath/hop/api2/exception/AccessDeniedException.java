package com.github.doubledeath.hop.api2.exception;

import javax.ws.rs.core.Response;

/**
 * Created by doubledeath on 2/20/17.
 */
public class AccessDeniedException extends StatusCodeMessageException {

    private static final long serialVersionUID = 2835528049686937312L;

    public AccessDeniedException(Long code, String message) {
        super(Response.Status.FORBIDDEN, code, message);
    }

}
