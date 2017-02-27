package com.github.doubledeath.hop.api.model.mapper;

import com.github.doubledeath.hop.api.endpoint.response.UserInfoResponse;
import com.github.doubledeath.hop.api.helper.KeycloakHelper;
import com.github.doubledeath.hop.api.helper.TagHelper;
import com.github.doubledeath.hop.api.model.User;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

/**
 * Created by doubledeath on 2/16/17.
 */
public final class UserMapper {

    public static User fromUserRepresentation(UserRepresentation userRepresentation) {
        User user = new User();

        user.setId(userRepresentation.getId());
        user.setLogin(userRepresentation.getUsername());
        user.setSimpleTag(TagHelper.getSimpleTag(userRepresentation.getFirstName()));
        user.setComplexTag(userRepresentation.getFirstName());
        user.setDisplayName(userRepresentation.getAttributes().get(User.Attribute.DISPLAY_NAME.name().toLowerCase()).get(0));
        user.setDescription(userRepresentation.getAttributes().get(User.Attribute.DESCRIPTION.name().toLowerCase()).get(0));

        return user;
    }

    public static UserRepresentation toUserRepresentation(User user) {
        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setId(user.getId());
        userRepresentation.setUsername(user.getLogin());
        userRepresentation.setFirstName(user.getComplexTag());
        userRepresentation.singleAttribute(User.Attribute.DISPLAY_NAME.name().toLowerCase(), user.getDisplayName());
        userRepresentation.singleAttribute(User.Attribute.DESCRIPTION.name().toLowerCase(), user.getDescription());

        return userRepresentation;
    }

    public static User fromSecurityContext(SecurityContext securityContext) {
        User user = new User();
        Principal principal = securityContext.getUserPrincipal();

        user.setId(principal.getName());

        if (principal instanceof KeycloakPrincipal) {
            AccessToken accessToken = ((KeycloakPrincipal) principal).getKeycloakSecurityContext().getToken();

            user.setLogin(accessToken.getPreferredUsername());
            user.setSimpleTag(TagHelper.getSimpleTag(accessToken.getGivenName()));
            user.setComplexTag(accessToken.getGivenName());

            AccessToken.Access access = accessToken.getResourceAccess(KeycloakHelper.TARGET_CLIENT_ID);

            if (access.isUserInRole(User.Role.GAME_MASTER.name().toLowerCase())) {
                user.setRole(User.Role.GAME_MASTER);
            } else if (access.isUserInRole(User.Role.PLAYER.name().toLowerCase())) {
                user.setRole(User.Role.PLAYER);
            } else if (access.isUserInRole(User.Role.GUEST.name().toLowerCase())) {
                user.setRole(User.Role.GUEST);
            }

            user.setDisplayName(String.valueOf(accessToken.getOtherClaims().get(User.Attribute.DISPLAY_NAME.name().toLowerCase())));
            user.setDescription(String.valueOf(accessToken.getOtherClaims().get(User.Attribute.DESCRIPTION.name().toLowerCase())));
        }

        return user;
    }

    public static UserInfoResponse toUserInfoResponse(User user) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();

        userInfoResponse.setTag(user.getSimpleTag());
        userInfoResponse.setDisplayName(user.getDisplayName());
        userInfoResponse.setDescription(user.getDescription());

        return userInfoResponse;
    }

}
