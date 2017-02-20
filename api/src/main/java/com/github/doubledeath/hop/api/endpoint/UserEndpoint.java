package com.github.doubledeath.hop.api.endpoint;

import com.github.doubledeath.hop.api.base.BaseMapper;
import com.github.doubledeath.hop.api.base.Mapper;
import com.github.doubledeath.hop.api.endpoint.request.SignUpUserRequest;
import com.github.doubledeath.hop.api.endpoint.request.UpdateUserRequest;
import com.github.doubledeath.hop.api.endpoint.response.ResponseCodes;
import com.github.doubledeath.hop.api.endpoint.response.ResponseMessages;
import com.github.doubledeath.hop.api.endpoint.response.UserResponse;
import com.github.doubledeath.hop.api.exception.BadRequestException;
import com.github.doubledeath.hop.api.exception.NotFoundException;
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
@SuppressWarnings("UnusedDeclaration")
public class UserEndpoint {

//    @Inject
    private UserService userService;
    @Context
    private SecurityContext securityContext;
    @Inject
//    @Mapper(Mapper.Type.IMPL_1)
    private BaseMapper<Object, Long> mapper;
    @Inject
//    @Mapper(Mapper.Type.IMPL_2)
    private BaseMapper<Object, String> mapper2;

//    @POST
//    @Path(EndpointInfo.User.SIGN_UP)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response signUp(@Valid SignUpUserRequest request) {
//        return UserResponse.signUp(userService.signUp(request.getLogin(), request.getPassword()));
//    }

    @POST
    @Path(EndpointInfo.User.SIGN_UP)
    public void signUp() {
        System.out.println(mapper + "!");
        System.out.println(mapper2 + "!");
//        return UserResponse.signUp(userService.signUp(request.getLogin(), request.getPassword()));
    }

    @POST
    @Path(EndpointInfo.User.UPDATE)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInfo(@Valid UpdateUserRequest request) {
        if (request.getDisplayName() == null && request.getDescription() == null) {
            throw new BadRequestException(new String[]{"json does not contain display_name or description fields"});
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

            if (user == null) {
                throw new NotFoundException(ResponseCodes.USER_NOT_FOUND_ERROR, ResponseMessages.USER_NOT_FOUND_ERROR);
            }
        }

        return UserResponse.info(user, self);
    }


}
