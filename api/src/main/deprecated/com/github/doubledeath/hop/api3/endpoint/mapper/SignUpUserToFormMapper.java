package com.github.doubledeath.hop.api3.endpoint.mapper;

import com.github.doubledeath.hop.api3.common.Mapper;
import com.github.doubledeath.hop.api3.endpoint.request.SignUpUserRequest;
import com.github.doubledeath.hop.api3.service.form.CreateUserForm;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;

/**
 * Created by doubledeath on 2/26/17.
 */
@SuppressWarnings("unused")
@Dependent
public class SignUpUserToFormMapper extends Mapper<SignUpUserRequest, CreateUserForm> {

    @NotNull
    @Override
    protected SignUpUserRequest fromImpl(@NotNull CreateUserForm from) {
        SignUpUserRequest request = new SignUpUserRequest();

        request.setLogin(from.getLogin());
        request.setPassword(from.getPassword());
        request.setDisplayName(from.getDisplayName());
        request.setDescription(from.getDescription());

        return request;
    }

    @NotNull
    @Override
    protected CreateUserForm toImpl(@NotNull SignUpUserRequest to) {
        CreateUserForm form = new CreateUserForm(to.getLogin(), to.getPassword());

        if (to.getDisplayName() != null) {
            form.setDisplayName(to.getDisplayName());
        }

        form.setDescription(to.getDescription());

        return form;
    }

}
