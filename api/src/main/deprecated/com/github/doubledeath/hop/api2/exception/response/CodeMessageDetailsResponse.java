package com.github.doubledeath.hop.api2.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by doubledeath on 2/21/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeMessageDetailsResponse {

    private Long code;
    private String message;
    private String[] details;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getDetails() {
        return details;
    }

    public void setDetails(String[] details) {
        this.details = details;
    }

}
