package com.github.doubledeath.hop.api.endpoint;

import com.github.doubledeath.hop.api.endpoint.request.SignUpUserRequest;
import com.github.doubledeath.hop.api.endpoint.request.UpdateUserRequest;
import com.github.doubledeath.hop.api.endpoint.response.ResponseCodes;
import com.github.doubledeath.hop.api.endpoint.response.ResponseMessages;
import com.github.doubledeath.hop.api.endpoint.response.UserResponse;
import com.github.doubledeath.hop.api.helper.response.ResponseHelper;
import com.github.doubledeath.hop.api.model.User;
import com.github.doubledeath.hop.api.model.mapper.UserMapper;
import com.github.doubledeath.hop.api.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by doubledeath on 2/16/17.
 */
@Path(EndpointInfo.USER)
public class UserEndpoint {

    @Inject
    private UserService userService;
    @Context
    private SecurityContext securityContext;

    @POST
    @Path(EndpointInfo.User.SIGN_UP)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUp(@Valid SignUpUserRequest request) {
        return UserResponse.signUp(userService.signUp(request.getLogin(), request.getPassword()));
    }

    @POST
    @Path(EndpointInfo.User.UPDATE)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid UpdateUserRequest request) {
        if (request.getDisplayName() == null && request.getDescription() == null) {
            return ResponseHelper.buildCodeMessageDetailsResponse(
                    MediaType.APPLICATION_JSON_TYPE,
                    Response.Status.BAD_REQUEST,
                    ResponseCodes.USER_INFO_ERROR,
                    ResponseMessages.USER_INFO_ERROR,
                    "json does not contain display_name or description fields"
            );
        }

        User user = UserMapper.fromSecurityContext(securityContext);

        user.setDisplayName(request.getDisplayName());
        user.setDescription(request.getDescription());

        return UserResponse.update(userService.updateInfo(user));
    }

    @GET
    @Path(EndpointInfo.User.INFO)
    @Produces(MediaType.APPLICATION_JSON)
    public Response info(@QueryParam(EndpointInfo.User.InfoParams.TAG) Long tag) {
        User user = UserMapper.fromSecurityContext(securityContext);
        boolean self = tag == null || user.getSimpleTag().equals(tag);

        if (!self) {
            user = userService.findBySimpleTag(tag);
        }

        return UserResponse.info(user, self);
    }


}
