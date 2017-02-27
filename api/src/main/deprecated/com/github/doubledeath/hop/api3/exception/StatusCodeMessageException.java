package com.github.doubledeath.hop.api3.exception;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.core.Response;

/**
 * Created by doubledeath on 2/21/17.
 */
public abstract class StatusCodeMessageException extends RuntimeException {

    private static final long serialVersionUID = -1061783989743788602L;

    private Response.Status status;
    private Long code;

    protected StatusCodeMessageException(@NotNull Response.Status status, @NotNull Long code, @NotNull String message) {
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
