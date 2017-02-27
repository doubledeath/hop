package com.github.doubledeath.hop.api.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.doubledeath.hop.api.helper.ResourceHelper;
import com.github.doubledeath.hop.api.helper.TagHelper;
import com.github.doubledeath.hop.api.model.User;
import com.github.doubledeath.hop.api.model.mapper.UserMapper;
import com.github.doubledeath.hop.api.service.TagService;
import com.github.doubledeath.hop.api.service.UserService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
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

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/16/17.
 */
@KeycloakAdminClientUserService.Impl
public class KeycloakAdminClientUserService implements UserService {

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD})
    @interface Impl {

    }

    private static final String KEYCLOAK_ADMIN_ACCESS_JSON = "META-INF/keycloak-admin-access.json";

    private static final String SERVER_URL = "server-url";
    private static final String ACCESS_REALM = "access-realm";
    private static final String TARGET_REALM = "target-realm";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ACCESS_CLIENT_ID = "access-client-id";

    private static final String HEADER_LOCATION = "Location";

    private TagService tagService;
    private RealmResource realm;

    @Inject
    public KeycloakAdminClientUserService(TagService tagService) {
        this.tagService = tagService;

        File keycloakAdminAccessJsonFile;

        try {
            keycloakAdminAccessJsonFile = ResourceHelper.getResourceFileByName(KEYCLOAK_ADMIN_ACCESS_JSON);
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
            //test connection and permissions
            realm.users().count();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public SignUpResult signUp(String login, String password) {
        User user = new User();

        user.setLogin(login);
        user.setDisplayName(login);

        String newComplexTag = tagService.createComplexTag(login);

        user.setSimpleTag(TagHelper.getSimpleTag(newComplexTag));
        user.setComplexTag(newComplexTag);

        UserRepresentation userRepresentation = UserMapper.toUserRepresentation(user);
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);
        //creating user
        Response response = realm.users().create(userRepresentation);
        SignUpResult result;

        if (response.getStatusInfo().getStatusCode() == Response.Status.CREATED.getStatusCode()) {
            String userId = response.getHeaderString(HEADER_LOCATION).replaceAll(".*/(.*)$", "$1");
            //setting up password
            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();

            credentialRepresentation.setTemporary(false);
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
            credentialRepresentation.setValue(password);

            realm.users().get(userId).resetPassword(credentialRepresentation);

            result = SignUpResult.SUCCESS;
        } else if (response.getStatusInfo().getStatusCode() == Response.Status.CONFLICT.getStatusCode()) {
            result = SignUpResult.USER_EXISTS_ERROR;
        } else {
            result = SignUpResult.UNKNOWN_ERROR;
        }

        return result;
    }

    @Override
    public User findBySimpleTag(Long simpleTag) {
        List<UserRepresentation> userRepresentationList = realm.users()
                .search(null, TagHelper.getComplexTag("", simpleTag), null, null, null, null);

        return userRepresentationList.size() == 1 ? UserMapper.fromUserRepresentation(userRepresentationList.get(0)) : null;
    }

    @Override
    public User updateInfo(User user) {
        realm.users().get(user.getId()).update(UserMapper.toUserRepresentation(user));

        return user;
    }

}
