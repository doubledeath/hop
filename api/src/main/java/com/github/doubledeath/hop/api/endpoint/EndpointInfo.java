package com.github.doubledeath.hop.api.endpoint;

/**
 * Created by doubledeath on 2/16/17.
 */
public final class EndpointInfo {

    public static final String API = "/api";

    public static final String USER = "/user";
    public static final class User {
        public static final String SIGN_UP = "/sign_up";

        public static final String INFO = "/info";
        public static final class InfoParams {
            public static final String TAG = "tag";
        }

        public static final String UPDATE = "/update";
    }

}
