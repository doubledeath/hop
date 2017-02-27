package com.github.doubledeath.hop.api.exception;

/**
 * Created by doubledeath on 2/18/17.
 */
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2630323867008232013L;

    private Long code;
    private String message;

    public NotFoundException(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
