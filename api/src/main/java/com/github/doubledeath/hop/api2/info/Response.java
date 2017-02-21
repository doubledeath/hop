package com.github.doubledeath.hop.api2.info;

/**
 * Created by doubledeath on 2/20/17.
 */
@SuppressWarnings("unused")
public final class Response {
    //@formatter:off
    public static final class Code {
        public static final Long UNKNOWN_ERROR =                            10000L;
        public static final Long UNKNOWN_RUNTIME_ERROR =                    10100L;
        public static final Long ACCESS_DENIED_ERROR =                      10101L;
        public static final Long BAD_REQUEST_ERROR =                        10102L;

        public static final Long USER_UNKNOWN_ERROR =                       20000L;
        public static final Long USER_NOT_FOUND_ERROR =                     20001L;
        public static final Long USER_ALREADY_EXISTS_ERROR =                20002L;
        public static final Long USER_IN_BAN_LIST_ERROR =                   20003L;
        public static final Long USER_UPDATE_REQUEST_IS_EMPTY_ERROR =       20004L;
        public static final Long HALL_UNKNOWN_ERROR =                       20100L;
        public static final Long HALL_NOT_FOUND_ERROR =                     20101L;
        public static final Long HALL_IS_FULL_ERROR =                       20102L;
        public static final Long HALL_LIST_CONFLICT_ERROR =                 20103L;
    }

    public static final class Message {
        public static final String UNKNOWN_ERROR =                          "unknown error";
        public static final String UNKNOWN_RUNTIME_ERROR =                  "unknown runtime error";
        public static final String ACCESS_DENIED_ERROR =                    "access denied";
        public static final String BAD_REQUEST_ERROR =                      "bad request";

        public static final String USER_UNKNOWN_ERROR =                     "unknown user error";
        public static final String USER_NOT_FOUND_ERROR =                   "user not found";
        public static final String USER_ALREADY_EXISTS_ERROR =              "user already exists";
        public static final String USER_IN_BAN_LIST_ERROR =                 "user in ban list";
        public static final String USER_UPDATE_REQUEST_IS_EMPTY_ERROR =     "user request is empty, pass display_name or description";
        public static final String HALL_UNKNOWN_ERROR =                     "unknown hall error";
        public static final String HALL_NOT_FOUND_ERROR =                   "hall not found";
        public static final String HALL_IS_FULL_ERROR =                     "hall is full";
        public static final String HALL_LIST_CONFLICT_ERROR =               "hall list conflict";
    }
    //@formatter:on
}
