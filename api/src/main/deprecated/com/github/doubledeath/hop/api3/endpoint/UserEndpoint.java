package com.github.doubledeath.hop.api3.endpoint;

import com.github.doubledeath.hop.api3.common.Mapper;
import com.github.doubledeath.hop.api3.endpoint.request.SignUpUserRequest;
import com.github.doubledeath.hop.api3.endpoint.request.UpdateUserRequest;
import com.github.doubledeath.hop.api3.endpoint.response.UserResponse;
import com.github.doubledeath.hop.api3.model.User;
import com.github.doubledeath.hop.api3.model.builder.TagBuilder;
import com.github.doubledeath.hop.api3.ref.EndpointRef;
import com.github.doubledeath.hop.api3.service.UserService;
import com.github.doubledeath.hop.api3.service.form.CreateUserForm;
import com.github.doubledeath.hop.api3.service.form.UpdateUserForm;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by doubledeath on 2/22/17.
 */
@SuppressWarnings("unused")
@Path(EndpointRef.USER)
public class UserEndpoint {

    @Inject
    private UserService userService;
    @Inject
    private Mapper<SignUpUserRequest, CreateUserForm> signUpMapper;
    @Inject
    private Mapper<UpdateUserRequest, UpdateUserForm> updateMapper;
    @Inject
    private Mapper<User, UserResponse> responseMapper;
    @Context
    private SecurityContext securityContext;
    private TagBuilder tagBuilder = new TagBuilder();

    @GET
    @Path(EndpointRef.User.SIGN_UP)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUp(@Valid SignUpUserRequest request) {
        User user = userService.create(signUpMapper.to(request));

        return Response.ok()
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(responseMapper.to(user))
                .build();
    }

    @GET
    @Path(EndpointRef.User.INFO)
    @Produces(MediaType.APPLICATION_JSON)
    public Response info(@QueryParam(EndpointRef.User.Params.TAG) Long tag) {
        User user = userService.find(tagBuilder.build(tag, null));

        return Response.ok()
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(responseMapper.to(user))
                .build();
    }

    @GET
    @Path(EndpointRef.User.SIGN_UP)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid UpdateUserRequest request) {
        User user = userService.update(tagBuilder.build(securityContext), updateMapper.to(request));

        return Response.ok()
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(responseMapper.to(user))
                .build();
    }

}
