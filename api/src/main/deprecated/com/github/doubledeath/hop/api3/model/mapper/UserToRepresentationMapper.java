package com.github.doubledeath.hop.api3.model.mapper;

import com.github.doubledeath.hop.api3.common.Mapper;
import com.github.doubledeath.hop.api3.model.User;
import com.github.doubledeath.hop.api3.model.builder.TagBuilder;
import org.jetbrains.annotations.NotNull;
import org.keycloak.representations.idm.UserRepresentation;

import javax.enterprise.context.Dependent;

/**
 * Created by doubledeath on 2/22/17.
 */
@SuppressWarnings("unused")
@Dependent
public class UserToRepresentationMapper extends Mapper<User, UserRepresentation> {

    private static final String DISPLAY_NAME = "display_name";
    private static final String DESCRIPTION = "description";

    private TagBuilder tagBuilder = new TagBuilder();

    @NotNull
    @Override
    protected User fromImpl(@NotNull UserRepresentation from) {
        User user = new User(tagBuilder.build(from.getFirstName()), from.getAttributes().get(DISPLAY_NAME).get(0));

        user.setDescription(from.getAttributes().get(DESCRIPTION).get(0));

        return user;
    }

    @NotNull
    @Override
    protected UserRepresentation toImpl(@NotNull User to) {
        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setFirstName(to.getTag().getComplexValue());
        userRepresentation.singleAttribute(DISPLAY_NAME, to.getDisplayName());
        userRepresentation.singleAttribute(DESCRIPTION, to.getDescription());

        return userRepresentation;
    }

}
