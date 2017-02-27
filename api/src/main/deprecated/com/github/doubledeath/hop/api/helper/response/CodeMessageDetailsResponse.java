package com.github.doubledeath.hop.api.helper.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by doubledeath on 2/16/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("WeakerAccess")
class CodeMessageDetailsResponse {

    private Long code;
    private String message;
    private List<String> details;

    CodeMessageDetailsResponse(Long code, String message) {
        this.code = code;
        this.message = message;
    }

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

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

}
