package com.github.doubledeath.hop.api3.endpoint.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by doubledeath on 2/16/17.
 */
public class SignUpUserRequest {

    @NotNull
    @Pattern(regexp = "^[^#]+$")
    @Size(min = 4)
    private String login;
    @NotNull
    @Size(min = 4)
    private String password;
    @JsonProperty("display_name")
    private String displayName;
    private String description;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
