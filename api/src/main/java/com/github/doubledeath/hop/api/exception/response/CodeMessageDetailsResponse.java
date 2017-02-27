package com.github.doubledeath.hop.api.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by doubledeath on 2/21/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeMessageDetailsResponse {

    private Long code;
    private String message;
    private String[] details;

    public CodeMessageDetailsResponse(@NotNull Long code, @NotNull String message, @Nullable String... details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public Long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String[] getDetails() {
        return details;
    }

}
