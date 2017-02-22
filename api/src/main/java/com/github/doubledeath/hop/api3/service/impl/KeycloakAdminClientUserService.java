package com.github.doubledeath.hop.api3.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.doubledeath.hop.api3.common.Mapper;
import com.github.doubledeath.hop.api3.exception.NotFoundException;
import com.github.doubledeath.hop.api3.model.Tag;
import com.github.doubledeath.hop.api3.model.User;
import com.github.doubledeath.hop.api3.model.builder.TagBuilder;
import com.github.doubledeath.hop.api3.ref.ResponseRef;
import com.github.doubledeath.hop.api3.service.TagService;
import com.github.doubledeath.hop.api3.service.UserService;
import com.github.doubledeath.hop.api3.service.request.UserCreateRequest;
import com.github.doubledeath.hop.api3.service.request.UserUpdateRequest;
import org.jetbrains.annotations.NotNull;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by doubledeath on 2/22/17.
 */
@SuppressWarnings("unused")
@Dependent
public class KeycloakAdminClientUserService implements UserService {

    private static final String KEYCLOAK_ADMIN_ACCESS_JSON = "META-INF/keycloak-admin-access.json";

    private static final String SERVER_URL = "server-url";
    private static final String ACCESS_REALM = "access-realm";
    private static final String TARGET_REALM = "target-realm";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ACCESS_CLIENT_ID = "access-client-id";

    private static final String LOCATION = "Location";

    @Inject
    private TagService tagService;
    @Inject
    private Mapper<UserCreateRequest, UserRepresentation> userCreateRequestToRepresentationMapper;
    @Inject
    private Mapper<User, UserRepresentation> userToRepresentationMapper;
    private RealmResource realmResource;
    private TagBuilder tagBuilder = new TagBuilder();

    public KeycloakAdminClientUserService() {
        File keycloakAdminAccessJsonFile;

        try {
            keycloakAdminAccessJsonFile = ResourceUtil.getResourceFileByName(KEYCLOAK_ADMIN_ACCESS_JSON);
        } catch (FileNotFoundException exception) {
            throw new RuntimeException(exception);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> keycloakAdminAccessJson;

        try {
            keycloakAdminAccessJson = objectMapper.readValue(keycloakAdminAccessJsonFile, new TypeReference<HashMap<String, Object>>() {
            });
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        try {
            Keycloak keycloak = Keycloak.getInstance(
                    String.valueOf(keycloakAdminAccessJson.get(SERVER_URL)),
                    String.valueOf(keycloakAdminAccessJson.get(ACCESS_REALM)),
                    String.valueOf(keycloakAdminAccessJson.get(USERNAME)),
                    String.valueOf(keycloakAdminAccessJson.get(PASSWORD)),
                    String.valueOf(keycloakAdminAccessJson.get(ACCESS_CLIENT_ID))
            );

            realm = keycloak.realm(String.valueOf(keycloakAdminAccessJson.get(TARGET_REALM)));
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @NotNull
    @Override
    public User create(@NotNull UserCreateRequest userCreateRequest) {
        UserRepresentation userRepresentation = userCreateRequestToRepresentationMapper.to(userCreateRequest);

        return null;
    }

    @NotNull
    @Override
    public User find(@NotNull Tag tag) {
        return null;
    }

    @NotNull
    @Override
    public User update(@NotNull Tag tag, @NotNull UserUpdateRequest userUpdateRequest) {
        return null;
    }

    @NotNull
    private User findImpl(@NotNull Tag tag) {
        List<UserRepresentation> userRepresentationList = realmResource.users()
                .search(null,
                        tagBuilder.build(tag.getSimpleValue(), "").getComplexValue(),
                        null,
                        null,
                        null,
                        null);

        if (userRepresentationList.size() != 1) {
            throw new NotFoundException(ResponseRef.Code.USER_NOT_FOUND_ERROR, ResponseRef.Message.USER_NOT_FOUND_ERROR);
        }

        return userToRepresentationMapper.from(userRepresentationList.get(0));
    }

}
