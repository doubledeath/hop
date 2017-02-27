package com.github.doubledeath.hop.api3.exception;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.core.Response;

/**
 * Created by doubledeath on 2/21/17.
 */
public class AccessDeniedException extends StatusCodeMessageException {

    private static final long serialVersionUID = -6276331997095249362L;

    public AccessDeniedException(@NotNull Long code, @NotNull String message) {
        super(Response.Status.FORBIDDEN, code, message);
    }

}
