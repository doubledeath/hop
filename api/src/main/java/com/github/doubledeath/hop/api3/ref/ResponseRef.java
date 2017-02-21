package com.github.doubledeath.hop.api3.ref;

/**
 * Created by doubledeath on 2/21/17.
 */
public final class ResponseRef {
    //@formatter:off
    public static final class Code {
        public static final Long UNKNOWN_ERROR =                            10000L;
        public static final Long ACCESS_DENIED_ERROR =                      10001L;
        public static final Long BAD_REQUEST_ERROR =                        10002L;

        public static final Long USER_UNKNOWN_ERROR =                       20000L;
        public static final Long USER_NOT_FOUND_ERROR =                     20001L;

        public static final Long HALL_UNKNOWN_ERROR =                       20100L;
        public static final Long HALL_NOT_FOUND_ERROR =                     20101L;
    }

    public static final class Message {
        public static final String UNKNOWN_ERROR =                          "unknown error";
        public static final String UNKNOWN_RUNTIME_ERROR =                  "unknown runtime error";
        public static final String ACCESS_DENIED_ERROR =                    "access denied";
        public static final String BAD_REQUEST_ERROR =                      "bad request";

        public static final String USER_UNKNOWN_ERROR =                     "unknown user error";
        public static final String USER_NOT_FOUND_ERROR =                   "user not found";

        public static final String HALL_UNKNOWN_ERROR =                     "unknown hall error";
        public static final String HALL_NOT_FOUND_ERROR =                   "hall not found";
    }
    //@formatter:on
}
