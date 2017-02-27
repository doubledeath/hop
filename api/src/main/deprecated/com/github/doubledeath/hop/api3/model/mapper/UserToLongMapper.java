package com.github.doubledeath.hop.api3.model.mapper;

import com.github.doubledeath.hop.api3.common.Mapper;
import com.github.doubledeath.hop.api3.model.User;
import com.github.doubledeath.hop.api3.model.builder.TagBuilder;
import com.github.doubledeath.hop.api3.service.UserService;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * Created by doubledeath on 2/23/17.
 */
@SuppressWarnings("unused")
@Dependent
public class UserToLongMapper extends Mapper<User, Long> {

    @Inject
    private UserService userService;
    private TagBuilder tagBuilder = new TagBuilder();

    @NotNull
    @Override
    protected User fromImpl(@NotNull Long from) {
        return userService.find(tagBuilder.build(from, null));
    }

    @NotNull
    @Override
    protected Long toImpl(@NotNull User to) {
        return to.getTag().getSimpleValue();
    }

}
