package com.github.doubledeath.hop.api.ref;

import com.github.doubledeath.hop.api.App;

/**
 * Created by doubledeath on 2/21/17.
 */
public final class EndpointRef {
    //@formatter:off
    public static final String API =                    "/api/" + App.VERSION;
    public static final class Public {
        private static final String PUBLIC =                "/public";
    }
    public static final class Secured {
        private static final String SECURED =               "/secured";
    }
    //@formatter:on
}
