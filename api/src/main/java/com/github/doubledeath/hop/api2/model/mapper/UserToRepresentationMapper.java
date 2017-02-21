package com.github.doubledeath.hop.api2.model.mapper;

import com.github.doubledeath.hop.api2.base.Mapper;
import com.github.doubledeath.hop.api2.model.ComplexTag;
import com.github.doubledeath.hop.api2.model.User;
import com.github.doubledeath.hop.api2.model.builder.TagBuilder;
import org.keycloak.representations.idm.UserRepresentation;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/20/17.
 */
public class UserToRepresentationMapper extends Mapper<User, UserRepresentation> {

    private static final String DISPLAY_NAME = "display_name";
    private static final String DESCRIPTION = "description";

    private TagBuilder tagBuilder = new TagBuilder();

    @Override
    protected User fromImpl(UserRepresentation from) {
        User user = new User();
        ComplexTag complexTag = new ComplexTag();

        complexTag.setValue(from.getFirstName());

        user.setId(from.getId());
        user.setLogin(from.getUsername());
        user.setSimpleTag(tagBuilder.buildSimpleTag(complexTag));
        user.setComplexTag(complexTag);
        user.setDisplayName(from.getAttributes().get(DISPLAY_NAME).get(0));
        user.setDescription(from.getAttributes().get(DESCRIPTION).get(0));

        return user;
    }

    @Override
    protected UserRepresentation toImpl(User to) {
        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setId(to.getId());
        userRepresentation.setUsername(to.getLogin());
        userRepresentation.setFirstName(to.getComplexTag().getValue());
        userRepresentation.singleAttribute(DISPLAY_NAME, to.getDisplayName());
        userRepresentation.singleAttribute(DESCRIPTION, to.getDescription());

        return userRepresentation;
    }

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    @interface Impl {

    }

}
