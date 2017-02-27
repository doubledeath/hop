package com.github.doubledeath.hop.api.endpoint.response;

import com.github.doubledeath.hop.api.helper.response.ResponseHelper;
import com.github.doubledeath.hop.api.model.Hall;
import com.github.doubledeath.hop.api.model.User;
import com.github.doubledeath.hop.api.model.mapper.HallMapper;
import com.github.doubledeath.hop.api.service.HallService;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by doubledeath on 2/18/17.
 */
public final class HallResponse {

    public static Response create(Hall hall, User user) {
        return info(hall, user);
    }

    public static Response update(Hall hall, User user) {
        return info(hall, user);
    }

    public static Response delete() {
        return ResponseHelper.buildOkEmptyResponse(MediaType.APPLICATION_JSON_TYPE);
    }

    public static Response enter(HallService.EnterResult result, Hall hall, User user) {
        if (result == HallService.EnterResult.SUCCESS) {
            return info(hall, user);
        } else if (result == HallService.EnterResult.USER_BANNED_ERROR) {
            return ResponseHelper.buildCodeMessageDetailsResponse(
                    MediaType.APPLICATION_JSON_TYPE,
                    Response.Status.FORBIDDEN,
                    ResponseCodes.USER_BANNED_ERROR,
                    ResponseMessages.USER_BANNED_ERROR
            );
        } else {
            return ResponseHelper.buildCodeMessageDetailsResponse(
                    MediaType.APPLICATION_JSON_TYPE,
                    Response.Status.CONFLICT,
                    ResponseCodes.HALL_IS_FULL_ERROR,
                    ResponseMessages.HALL_IS_FULL_ERROR
            );
        }
    }

    public static Response leave(HallService.LeaveBanUnbanResult result) {
        if (result == HallService.LeaveBanUnbanResult.SUCCESS) {
            return ResponseHelper.buildOkEmptyResponse(MediaType.APPLICATION_JSON_TYPE);
        } else {
            return ResponseHelper.buildCodeMessageDetailsResponse(
                    MediaType.APPLICATION_JSON_TYPE,
                    Response.Status.CONFLICT,
                    ResponseCodes.HALL_LIST_CONFLICT_ERROR,
                    "can not leave from hall you are not entering to"
            );
        }
    }

    public static Response kick(HallService.LeaveBanUnbanResult result) {
        if (result == HallService.LeaveBanUnbanResult.SUCCESS) {
            return ResponseHelper.buildOkEmptyResponse(MediaType.APPLICATION_JSON_TYPE);
        } else {
            return ResponseHelper.buildCodeMessageDetailsResponse(
                    MediaType.APPLICATION_JSON_TYPE,
                    Response.Status.CONFLICT,
                    ResponseCodes.HALL_LIST_CONFLICT_ERROR,
                    "can not kick user which not in this hall"
            );
        }
    }

    public static Response ban(HallService.LeaveBanUnbanResult result) {
        if (result == HallService.LeaveBanUnbanResult.SUCCESS) {
            return ResponseHelper.buildOkEmptyResponse(MediaType.APPLICATION_JSON_TYPE);
        } else {
            return ResponseHelper.buildCodeMessageDetailsResponse(
                    MediaType.APPLICATION_JSON_TYPE,
                    Response.Status.CONFLICT,
                    ResponseCodes.HALL_LIST_CONFLICT_ERROR,
                    "can not ban user which not in this hall"
            );
        }
    }

    public static Response unban(HallService.LeaveBanUnbanResult result) {
        if (result == HallService.LeaveBanUnbanResult.SUCCESS) {
            return ResponseHelper.buildOkEmptyResponse(MediaType.APPLICATION_JSON_TYPE);
        } else {
            return ResponseHelper.buildCodeMessageDetailsResponse(
                    MediaType.APPLICATION_JSON_TYPE,
                    Response.Status.CONFLICT,
                    ResponseCodes.HALL_LIST_CONFLICT_ERROR,
                    "can not unban user which not in ban list for this hall"
            );
        }
    }

    public static Response info(Hall hall, User user) {
        return Response.ok()
                .entity(HallMapper.toHallInfoResponse(hall, user))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

}
