package com.github.doubledeath.hop.api3.model.builder;

import com.github.doubledeath.hop.api3.model.Tag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

/**
 * Created by doubledeath on 2/21/17.
 */
public class TagBuilder {

    private static final String PREFIX_SEPARATOR = "#";

    @NotNull
    public Tag build(@NotNull Long seed, @Nullable String prefix) {
        Tag tag = new Tag(seed);

        tag.setComplexValue(prefix == null ? null : prefix + PREFIX_SEPARATOR + seed);

        return tag;
    }

    @NotNull
    public Tag build(@NotNull String complexValue) {
        return buildForComplexValueImpl(complexValue);
    }

    @NotNull
    public Tag build(@NotNull SecurityContext securityContext) {
        Principal principal = securityContext.getUserPrincipal();

        if (principal instanceof KeycloakPrincipal) {
            AccessToken accessToken = ((KeycloakPrincipal) principal).getKeycloakSecurityContext().getToken();

            return buildForComplexValueImpl(accessToken.getGivenName());
        } else {
            throw new RuntimeException();
        }
    }

    @NotNull
    private Tag buildForComplexValueImpl(@NotNull String complexValue) {
        Long seed = Long.valueOf(complexValue.substring(complexValue.lastIndexOf(PREFIX_SEPARATOR) + PREFIX_SEPARATOR.length()));

        Tag tag = new Tag(seed);

        tag.setComplexValue(complexValue);

        return tag;
    }

}
