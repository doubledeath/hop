package com.github.doubledeath.hop.api2.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Created by doubledeath on 2/20/17.
 */
@Provider
public class RuntimeExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException runtimeException) {
        return null;
    }

}
