package com.github.doubledeath.hop.api.endpoint.response;

/**
 * Created by doubledeath on 2/16/17.
 */
@SuppressWarnings("WeakerAccess")
public final class ResponseCodes {

    public static final Long USER_UNKNOWN_ERROR = 10000L;
    public static final Long USER_EXISTS_ERROR = 10001L;
    public static final Long USER_NOT_FOUND_ERROR = 10002L;
    public static final Long USER_BANNED_ERROR = 10003L;
    public static final Long HALL_NOT_FOUND_ERROR = 10100L;
    public static final Long HALL_IS_FULL_ERROR = 10101L;
    public static final Long HALL_LIST_CONFLICT_ERROR = 10102L;

}
