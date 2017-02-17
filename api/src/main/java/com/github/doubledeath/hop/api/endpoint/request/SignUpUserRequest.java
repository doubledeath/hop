package com.github.doubledeath.hop.api.endpoint.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by doubledeath on 2/16/17.
 */
public class SignUpUserRequest {

    @NotNull
    @Pattern(regexp = "^[^#]+$")
    private String login;
    @NotNull
    private String password;

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

}
