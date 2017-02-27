package com.github.doubledeath.hop.api3.service.form;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by doubledeath on 2/21/17.
 */
public class CreateUserForm {

    private String login;
    private String password;
    private String displayName;
    private String description;

    public CreateUserForm(@NotNull String login, @NotNull String password) {
        this.login = login;
        this.password = password;
        this.displayName = login;
    }

    @NotNull
    public String getLogin() {
        return login;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    @NotNull
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(@NotNull String displayName) {
        this.displayName = displayName;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
