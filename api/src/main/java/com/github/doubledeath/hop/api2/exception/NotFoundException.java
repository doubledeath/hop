package com.github.doubledeath.hop.api2.exception;

import javax.ws.rs.core.Response;

/**
 * Created by doubledeath on 2/20/17.
 */
public class NotFoundException extends StatusCodeMessageException {

    private static final long serialVersionUID = 5254037821040089501L;

    public NotFoundException(Long code, String message) {
        super(Response.Status.NOT_FOUND, code, message);
    }

}
