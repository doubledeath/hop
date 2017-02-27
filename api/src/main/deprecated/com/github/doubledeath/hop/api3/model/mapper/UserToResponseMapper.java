package com.github.doubledeath.hop.api3.model.mapper;

import com.github.doubledeath.hop.api3.common.Mapper;
import com.github.doubledeath.hop.api3.endpoint.response.UserResponse;
import com.github.doubledeath.hop.api3.model.User;
import com.github.doubledeath.hop.api3.model.builder.TagBuilder;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;

/**
 * Created by doubledeath on 2/27/17.
 */
@SuppressWarnings("unused")
@Dependent
public class UserToResponseMapper extends Mapper<User, UserResponse> {

    private TagBuilder tagBuilder = new TagBuilder();

    @NotNull
    @Override
    protected User fromImpl(@NotNull UserResponse from) {
        User user = new User(tagBuilder.build(from.getTag(), null), from.getDisplayName());

        user.setDescription(from.getDescription());

        return user;
    }

    @NotNull
    @Override
    protected UserResponse toImpl(@NotNull User to) {
        UserResponse response = new UserResponse();

        response.setTag(to.getTag().getSimpleValue());
        response.setDisplayName(to.getDisplayName());
        response.setDescription(to.getDescription());

        return response;
    }

}
