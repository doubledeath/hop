package com.github.doubledeath.hop.api3.service;

import com.github.doubledeath.hop.api3.model.Tag;
import com.github.doubledeath.hop.api3.model.User;
import com.github.doubledeath.hop.api3.service.request.UserCreateRequest;
import com.github.doubledeath.hop.api3.service.request.UserUpdateRequest;
import org.jetbrains.annotations.NotNull;

/**
 * Created by doubledeath on 2/21/17.
 */
public interface UserService {

    @NotNull
    User create(@NotNull UserCreateRequest userCreateRequest);

    @NotNull
    User find(@NotNull Tag tag);

    @NotNull
    User update(@NotNull Tag tag, @NotNull UserUpdateRequest userUpdateRequest);

}
