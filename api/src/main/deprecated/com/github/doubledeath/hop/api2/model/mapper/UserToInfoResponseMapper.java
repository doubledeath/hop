package com.github.doubledeath.hop.api2.model.mapper;

import com.github.doubledeath.hop.api2.base.Mapper;
import com.github.doubledeath.hop.api2.endpoint.response.UserInfoResponse;
import com.github.doubledeath.hop.api2.model.User;
import com.github.doubledeath.hop.api2.model.builder.TagBuilder;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/21/17.
 */
@UserToInfoResponseMapper.Impl
public class UserToInfoResponseMapper extends Mapper<User, UserInfoResponse> {

    @Override
    protected User fromImpl(UserInfoResponse from) {
        return null;
    }

    @Override
    protected UserInfoResponse toImpl(User to) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();

        userInfoResponse.setTag(to.getSimpleTag().getValue());
        userInfoResponse.setDisplayName(to.getDisplayName());
        userInfoResponse.setDescription(to.getDescription());

        return userInfoResponse;
    }

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    @interface Impl {

    }

}
