package com.github.doubledeath.hop.api3.ref;

/**
 * Created by doubledeath on 2/21/17.
 */
@SuppressWarnings("unused")
public final class EndpointRef {
    //@formatter:off
    public static final String API =                    "/api";
    public static final String USER =                   "/user";
    public static final class User {
        public static final String SIGN_UP =                "/sign_up";
        public static final String UPDATE =                 "/update";
        public static final String INFO =                   "/info";
        public static final class Params {
            public static final String TAG =                    "tag";
        }
    }
    public static final String HALL =                   "/hall";
    public static final class Hall {
        public static final String CREATE =                 "/create";
        public static final String UPDATE =                 "/update";
        public static final String DELETE =                 "/delete";
        public static final String ENTER =                  "/enter";
        public static final String LEAVE =                  "/leave";
        public static final String KICK =                   "/kick";
        public static final String BAN =                    "/ban";
        public static final String UNBAN =                  "/unban";
        public static final String INFO =                   "/info";
        public static final class Params {
            public static final String TAG =                    "tag";
            public static final String HALL_TAG =               "hall_tag";
            public static final String USER_TAG =               "user_tag";
        }
    }
    //@formatter:on
}
