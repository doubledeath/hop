package com.github.doubledeath.hop.api3.service.impl.mapper;

import com.github.doubledeath.hop.api3.common.Mapper;
import com.github.doubledeath.hop.api3.service.request.UserCreateRequest;
import org.jetbrains.annotations.NotNull;
import org.keycloak.representations.idm.UserRepresentation;

/**
 * Created by doubledeath on 2/22/17.
 */
public class UserCreateRequestToRepresentationMapper extends Mapper<UserCreateRequest, UserRepresentation> {

    @NotNull
    @Override
    protected UserCreateRequest fromImpl(@NotNull UserRepresentation from) {
        return null;
    }

    @NotNull
    @Override
    protected UserRepresentation toImpl(@NotNull UserCreateRequest to) {
        return null;
    }

}
