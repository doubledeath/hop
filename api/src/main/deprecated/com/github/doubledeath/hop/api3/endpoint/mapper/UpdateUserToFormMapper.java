package com.github.doubledeath.hop.api3.endpoint.mapper;

import com.github.doubledeath.hop.api3.common.Mapper;
import com.github.doubledeath.hop.api3.endpoint.request.UpdateUserRequest;
import com.github.doubledeath.hop.api3.service.form.UpdateUserForm;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;

/**
 * Created by doubledeath on 2/27/17.
 */
@SuppressWarnings("unused")
@Dependent
public class UpdateUserToFormMapper extends Mapper<UpdateUserRequest, UpdateUserForm> {

    @NotNull
    @Override
    protected UpdateUserRequest fromImpl(@NotNull UpdateUserForm from) {
        return null;
    }

    @NotNull
    @Override
    protected UpdateUserForm toImpl(@NotNull UpdateUserRequest to) {
        return null;
    }

}
