package com.github.doubledeath.hop.api3.exception;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.core.Response;

/**
 * Created by doubledeath on 2/21/17.
 */
public class NotFoundException extends StatusCodeMessageException {

    private static final long serialVersionUID = -9096394721887944756L;

    public NotFoundException(@NotNull Long code, @NotNull String message) {
        super(Response.Status.NOT_FOUND, code, message);
    }

}
