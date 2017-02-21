package com.github.doubledeath.hop.api2.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.doubledeath.hop.api2.base.Mapper;
import com.github.doubledeath.hop.api2.exception.ConflictException;
import com.github.doubledeath.hop.api2.model.ComplexTag;
import com.github.doubledeath.hop.api2.model.SimpleTag;
import com.github.doubledeath.hop.api2.model.User;
import com.github.doubledeath.hop.api2.model.builder.TagBuilder;
import com.github.doubledeath.hop.api2.service.TagService;
import com.github.doubledeath.hop.api2.service.UserService;
import com.github.doubledeath.hop.api2.util.ResourceUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import javax.inject.Inject;
import javax.inject.Qualifier;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/20/17.
 */
@KeycloakAdminClientUserService.Impl
public class KeycloakAdminClientUserService implements UserService {

    private static final String KEYCLOAK_ADMIN_ACCESS_JSON = "META-INF/keycloak-admin-access.json";

    private static final String SERVER_URL = "server-url";
    private static final String ACCESS_REALM = "access-realm";
    private static final String TARGET_REALM = "target-realm";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ACCESS_CLIENT_ID = "access-client-id";
    private static final String LOCATION = "Location";

    private TagService tagService;
    private Mapper<User, UserRepresentation> mapper;
    private TagBuilder tagBuilder = new TagBuilder();
    private RealmResource realm;

    @Inject
    public KeycloakAdminClientUserService(TagService tagService, Mapper<User, UserRepresentation> mapper) {
        this.tagService = tagService;
        this.mapper = mapper;

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

    @Override
    public User findOneBySimpleTag(SimpleTag simpleTag) {
        List<UserRepresentation> userRepresentationList = realm.users()
                .search(null, tagBuilder.buildComplexTag(simpleTag, "").getValue(), null, null, null, null);

        return userRepresentationList.size() == 1 ? mapper.from(userRepresentationList.get(0)) : null;
    }

    @Override
    public User signUp(String login, String password) {
        User user = new User();
        ComplexTag complexTag = tagService.createComplexTag(login);

        user.setLogin(login);
        user.setDisplayName(login);
        user.setSimpleTag(tagBuilder.buildSimpleTag(complexTag));
        user.setComplexTag(complexTag);

        UserRepresentation userRepresentation = mapper.to(user);
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);

        Response response = realm.users().create(userRepresentation);

        if (response.getStatusInfo().getStatusCode() == Response.Status.CREATED.getStatusCode()) {
            String userId = response.getHeaderString(LOCATION).replaceAll(".*/(.*)$", "$1");
            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            UserResource userResource = realm.users().get(userId);

            credentialRepresentation.setTemporary(false);
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
            credentialRepresentation.setValue(password);

            userResource.resetPassword(credentialRepresentation);

            return mapper.from(userResource.toRepresentation());
        } else if (response.getStatusInfo().getStatusCode() == Response.Status.CONFLICT.getStatusCode()) {
            throw new ConflictException(
                    com.github.doubledeath.hop.api2.info.Response.Code.USER_ALREADY_EXISTS_ERROR,
                    com.github.doubledeath.hop.api2.info.Response.Message.USER_ALREADY_EXISTS_ERROR
            );
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public User updateInfo(User user) {
        realm.users().get(user.getId()).update(mapper.to(user));

        return user;
    }

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    @interface Impl {

    }

}
