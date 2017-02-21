package com.github.doubledeath.hop.api2.model.builder;

import com.github.doubledeath.hop.api2.model.ComplexTag;
import com.github.doubledeath.hop.api2.model.User;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

/**
 * Created by doubledeath on 2/20/17.
 */
public class UserBuilder {

    private static final String CLIENT_ID = "api";

    private static final String DISPLAY_NAME = "display_name";
    private static final String DESCRIPTION = "description";

    private TagBuilder tagBuilder = new TagBuilder();

    public User buildUser(SecurityContext securityContext) {
        User user = new User();
        Principal principal = securityContext.getUserPrincipal();

        user.setId(principal.getName());

        if (principal instanceof KeycloakPrincipal) {
            AccessToken accessToken = ((KeycloakPrincipal) principal).getKeycloakSecurityContext().getToken();
            ComplexTag complexTag = new ComplexTag();

            complexTag.setValue(accessToken.getGivenName());

            user.setLogin(accessToken.getPreferredUsername());
            user.setSimpleTag(tagBuilder.buildSimpleTag(complexTag));
            user.setComplexTag(complexTag);

            AccessToken.Access access = accessToken.getResourceAccess(CLIENT_ID);

            if (access.isUserInRole(User.Role.GAME_MASTER.name().toLowerCase())) {
                user.setRole(User.Role.GAME_MASTER);
            } else if (access.isUserInRole(User.Role.PLAYER.name().toLowerCase())) {
                user.setRole(User.Role.PLAYER);
            } else if (access.isUserInRole(User.Role.GUEST.name().toLowerCase())) {
                user.setRole(User.Role.GUEST);
            }

            user.setDisplayName(String.valueOf(accessToken.getOtherClaims().get(DISPLAY_NAME)));
            user.setDescription(String.valueOf(accessToken.getOtherClaims().get(DESCRIPTION)));
        }

        return user;
    }

}
