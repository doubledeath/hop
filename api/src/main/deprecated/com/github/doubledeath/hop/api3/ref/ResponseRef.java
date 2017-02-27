package com.github.doubledeath.hop.api3.ref;

/**
 * Created by doubledeath on 2/21/17.
 */
@SuppressWarnings("unused")
public final class ResponseRef {
    //@formatter:off
    public static final class Code {
        public static final Long UNKNOWN_ERROR =                            10000L;
        public static final Long ACCESS_DENIED_ERROR =                      10001L;
        public static final Long BAD_REQUEST_ERROR =                        10002L;

        public static final Long USER_UNKNOWN_ERROR =                       20000L;
        public static final Long USER_NOT_FOUND_ERROR =                     20001L;
        public static final Long USERNAME_TAKEN_ERROR =                     20002L;

        public static final Long HALL_UNKNOWN_ERROR =                       20100L;
        public static final Long HALL_NOT_FOUND_ERROR =                     20101L;
        public static final Long HALL_SIZE_ERROR =                          20102L;
        public static final Long HALL_INVALID_KEY_ERROR =                   20103L;
        public static final Long HALL_USER_IN_BANLIST_ERROR =               20104L;
    }

    public static final class Message {
        public static final String UNKNOWN_ERROR =                          "unknown error";
        public static final String UNKNOWN_RUNTIME_ERROR =                  "unknown runtime error";
        public static final String ACCESS_DENIED_ERROR =                    "access denied";
        public static final String BAD_REQUEST_ERROR =                      "bad form";

        public static final String USER_UNKNOWN_ERROR =                     "unknown user error";
        public static final String USER_NOT_FOUND_ERROR =                   "user not found";
        public static final String USERNAME_TAKEN_ERROR =                   "username already taken";

        public static final String HALL_UNKNOWN_ERROR =                     "unknown hall error";
        public static final String HALL_NOT_FOUND_ERROR =                   "hall not found";
        public static final String HALL_SIZE_ERROR =                        "hall size is less then user list size";
        public static final String HALL_INVALID_KEY_ERROR =                 "invalid key to hall";
        public static final String HALL_USER_IN_BANLIST_ERROR =             "user in banlist for this hall";
    }
    //@formatter:on
}
