package com.github.doubledeath.hop.api.service;

import com.github.doubledeath.hop.api.model.User;
import org.jetbrains.annotations.NotNull;

import javax.ejb.Local;

/**
 * Created by doubledeath on 2/27/17.
 */
@Local
public interface UserService {

    @NotNull
    User create(@NotNull User user);

    @NotNull
    User findOneByTag(@NotNull Long tag);

    @NotNull
    User update(@NotNull User user);

}
