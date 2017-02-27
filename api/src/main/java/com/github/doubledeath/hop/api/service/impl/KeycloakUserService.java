package com.github.doubledeath.hop.api.service.impl;

import com.github.doubledeath.hop.api.model.User;
import com.github.doubledeath.hop.api.service.UserService;
import org.jetbrains.annotations.NotNull;

import javax.ejb.Stateless;

/**
 * Created by doubledeath on 2/27/17.
 */
@Stateless
public class KeycloakUserService implements UserService {

    @NotNull
    @Override
    public User create(@NotNull User user) {
        return null;
    }

    @NotNull
    @Override
    public User findOneByTag(@NotNull Long tag) {
        return null;
    }

    @Override
    public @NotNull User update(@NotNull User user) {
        return null;
    }

}
