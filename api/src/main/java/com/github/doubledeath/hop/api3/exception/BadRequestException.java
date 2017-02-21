package com.github.doubledeath.hop.api3.exception;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.core.Response;

/**
 * Created by doubledeath on 2/21/17.
 */
public class BadRequestException extends StatusCodeMessageException {

    private static final long serialVersionUID = -4094571946140482178L;

    public BadRequestException(@NotNull Long code, @NotNull String message) {
        super(Response.Status.BAD_REQUEST, code, message);
    }

}
