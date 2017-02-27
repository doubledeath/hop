package com.github.doubledeath.hop.api.exception;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.core.Response;

/**
 * Created by doubledeath on 2/21/17.
 */
public class ConflictException extends StatusCodeMessageException {

    private static final long serialVersionUID = 1831709801913492514L;

    public ConflictException(@NotNull Long code, @NotNull String message) {
        super(Response.Status.CONFLICT, code, message);
    }

}
