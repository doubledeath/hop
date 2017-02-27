package com.github.doubledeath.hop.api.endpoint.response;

import com.github.doubledeath.hop.api.helper.response.ResponseHelper;
import com.github.doubledeath.hop.api.model.User;
import com.github.doubledeath.hop.api.model.mapper.UserMapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by doubledeath on 2/17/17.
 */
public final class UserResponse {

    public static Response signUp(UserService.SignUpResult result) {
        switch (result) {
            case SUCCESS:
                return ResponseHelper.buildOkEmptyResponse(MediaType.APPLICATION_JSON_TYPE);
            case USER_EXISTS_ERROR:
                return ResponseHelper.buildCodeMessageDetailsResponse(
                        MediaType.APPLICATION_JSON_TYPE,
                        Response.Status.CONFLICT,
                        ResponseCodes.USER_EXISTS_ERROR,
                        ResponseMessages.USER_EXISTS_ERROR
                );
            case UNKNOWN_ERROR:
            default:
                return ResponseHelper.buildCodeMessageDetailsResponse(
                        MediaType.APPLICATION_JSON_TYPE,
                        Response.Status.INTERNAL_SERVER_ERROR,
                        ResponseCodes.USER_UNKNOWN_ERROR,
                        ResponseMessages.USER_UNKNOWN_ERROR
                );
        }
    }

    public static Response update(User user) {
        return info(user, true);
    }

    public static Response info(User user, boolean self) {
        return Response.ok()
                .entity(UserMapper.toUserInfoResponse(user))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

}
