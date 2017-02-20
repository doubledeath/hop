package com.github.doubledeath.hop.api.exception;

/**
 * Created by doubledeath on 2/18/17.
 */
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -6314276951744845550L;

    private String[] details;

    public BadRequestException(String[] details) {
        this.details = details;
    }

    public String[] getDetails() {
        return details;
    }

    public void setDetails(String[] details) {
        this.details = details;
    }

}
