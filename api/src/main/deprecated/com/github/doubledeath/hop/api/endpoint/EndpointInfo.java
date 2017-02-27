package com.github.doubledeath.hop.api.endpoint;

/**
 * Created by doubledeath on 2/16/17.
 */
@SuppressWarnings("WeakerAccess")
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
    public static final String HALL = "/hall";
    public static final class Hall {
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";
        public static final class UpdateParams {
            public static final String TAG = "tag";
        }
        public static final String DELETE = "/delete";
        public static final class DeleteParams {
            public static final String TAG = "tag";
        }
        public static final String ENTER = "/enter";
        public static final class EnterParams {
            public static final String TAG = "tag";
        }
        public static final String LEAVE = "/leave";
        public static final class LeaveParams {
            public static final String TAG = "tag";
        }
        public static final String KICK = "/kick";
        public static final class KickParams {
            public static final String HALL_TAG = "hall_tag";
            public static final String USER_TAG = "user_tag";
        }
        public static final String BAN = "/ban";
        public static final class BanParams {
            public static final String HALL_TAG = "hall_tag";
            public static final String USER_TAG = "user_tag";
        }
        public static final String UNBAN = "/unban";
        public static final class UnbanParams {
            public static final String HALL_TAG = "hall_tag";
            public static final String USER_TAG = "user_tag";
        }
        public static final String INFO = "/info";
        public static final class InfoParams {
            public static final String TAG = "tag";
        }
    }

}
