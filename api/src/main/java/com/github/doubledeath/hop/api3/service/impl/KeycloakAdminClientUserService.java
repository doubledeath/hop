package com.github.doubledeath.hop.api3.service.impl;

import com.github.doubledeath.hop.api3.model.Tag;
import com.github.doubledeath.hop.api3.model.User;
import com.github.doubledeath.hop.api3.service.UserService;
import com.github.doubledeath.hop.api3.service.request.UserCreateRequest;
import com.github.doubledeath.hop.api3.service.request.UserUpdateRequest;
import org.jetbrains.annotations.NotNull;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/22/17.
 */
@KeycloakAdminClientUserService.Impl
public class KeycloakAdminClientUserService implements UserService {

    @NotNull
    @Override
    public User create(@NotNull UserCreateRequest userCreateRequest) {
        return null;
    }

    @NotNull
    @Override
    public User find(@NotNull Tag tag) {
        return null;
    }

    @NotNull
    @Override
    public User update(@NotNull Tag tag, @NotNull UserUpdateRequest userUpdateRequest) {
        return null;
    }

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    @interface Impl {

    }

}
