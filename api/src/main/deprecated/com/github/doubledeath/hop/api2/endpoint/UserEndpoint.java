package com.github.doubledeath.hop.api2.endpoint;

import com.github.doubledeath.hop.api2.base.Mapper;
import com.github.doubledeath.hop.api2.endpoint.request.SignUpUserRequest;
import com.github.doubledeath.hop.api2.endpoint.request.UpdateUserRequest;
import com.github.doubledeath.hop.api2.endpoint.response.UserInfoResponse;
import com.github.doubledeath.hop.api2.exception.BadRequestException;
import com.github.doubledeath.hop.api2.exception.NotFoundException;
import com.github.doubledeath.hop.api2.info.Endpoint;
import com.github.doubledeath.hop.api2.model.SimpleTag;
import com.github.doubledeath.hop.api2.model.User;
import com.github.doubledeath.hop.api2.model.builder.TagBuilder;
import com.github.doubledeath.hop.api2.model.builder.UserBuilder;
import com.github.doubledeath.hop.api2.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by doubledeath on 2/20/17.
 */
@Path(Endpoint.USER)
@SuppressWarnings("UnusedDeclaration")
public class UserEndpoint {

    @Inject
    private UserService userService;
    private Mapper<User, UserInfoResponse> mapper;
    @Context
    private SecurityContext securityContext;
    private UserBuilder userBuilder = new UserBuilder();
    private TagBuilder tagBuilder = new TagBuilder();

    @GET
    @Path(Endpoint.User.INFO)
    @Produces(MediaType.APPLICATION_JSON)
    public Response info(@QueryParam(Endpoint.User.Params.TAG) Long tag) {
        User user = userService.findOneBySimpleTag(tagBuilder.buildSimpleTag(tag));

        if (user == null) {
            throw new NotFoundException(
                    com.github.doubledeath.hop.api2.info.Response.Code.USER_NOT_FOUND_ERROR,
                    com.github.doubledeath.hop.api2.info.Response.Message.USER_NOT_FOUND_ERROR
            );
        }

        return Response.ok()
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(mapper.to(user))
                .build();
    }

    @POST
    @Path(Endpoint.User.SIGN_UP)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUp(@Valid SignUpUserRequest request) {
        return Response.ok()
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(mapper.to(userService.signUp(request.getLogin(), request.getPassword())))
                .build();
    }

    @POST
    @Path(Endpoint.User.UPDATE)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInfo(@Valid UpdateUserRequest request) {
        if (request.getDisplayName() == null && request.getDescription() == null) {
            throw new BadRequestException(
                    com.github.doubledeath.hop.api2.info.Response.Code.USER_UPDATE_REQUEST_IS_EMPTY_ERROR,
                    com.github.doubledeath.hop.api2.info.Response.Message.USER_UPDATE_REQUEST_IS_EMPTY_ERROR
            );
        }

        return Response.ok()
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(mapper.to(userService.updateInfo(userBuilder.buildUser(securityContext))))
                .build();
    }

}
