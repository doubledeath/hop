package com.github.doubledeath.hop.api.endpoint;

import com.github.doubledeath.hop.api.database.repo.HallRepo;
import com.github.doubledeath.hop.api.endpoint.request.CreateHallRequest;
import com.github.doubledeath.hop.api.endpoint.request.UpdateHallRequest;
import com.github.doubledeath.hop.api.endpoint.response.HallResponse;
import com.github.doubledeath.hop.api.endpoint.response.ResponseCodes;
import com.github.doubledeath.hop.api.endpoint.response.ResponseMessages;
import com.github.doubledeath.hop.api.exception.AccessDeniedException;
import com.github.doubledeath.hop.api.exception.BadRequestException;
import com.github.doubledeath.hop.api.exception.NotFoundException;
import com.github.doubledeath.hop.api.model.Hall;
import com.github.doubledeath.hop.api.model.User;
import com.github.doubledeath.hop.api.model.mapper.UserMapper;
import com.github.doubledeath.hop.api.service.HallService;
import com.github.doubledeath.hop.api.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by doubledeath on 2/17/17.
 */
@Path(EndpointInfo.HALL)
@SuppressWarnings("UnusedDeclaration")
public class HallEndpoint {

    @Inject
    private HallService hallService;
    @Inject
    private UserService userService;
    @Context
    private SecurityContext securityContext;

    @POST
    @Path(EndpointInfo.Hall.CREATE)
    public Response create(@Valid CreateHallRequest request) {
        return HallResponse.create(hallService.create(
                UserMapper.fromSecurityContext(securityContext).getSimpleTag(),
                Hall.Visibility.valueOf(request.getVisibility().toUpperCase()),
                request.getDisplayName(),
                request.getSize()
        ),  UserMapper.fromSecurityContext(securityContext));
    }

    @POST
    @Path(EndpointInfo.Hall.UPDATE)
    public Response update(@Valid UpdateHallRequest request,
                           @QueryParam(EndpointInfo.Hall.UpdateParams.TAG) Long tag) {
        User user = UserMapper.fromSecurityContext(securityContext);
        Hall hall = hallService.getHallForTag(tag);

        checkHallNotNull(hall);
        checkOwner(hall, user);

        return HallResponse.update(hallService.update(hall), user);
    }

    @POST
    @Path(EndpointInfo.Hall.DELETE)
    public Response delete(@QueryParam(EndpointInfo.Hall.DeleteParams.TAG) Long tag) {
        User user = UserMapper.fromSecurityContext(securityContext);
        Hall hall = hallService.getHallForTag(tag);

        checkHallNotNull(hall);
        checkOwner(hall, user);

        hallService.delete(hall);

        return HallResponse.delete();
    }

    @POST
    @Path(EndpointInfo.Hall.ENTER)
    public Response enter(@QueryParam(EndpointInfo.Hall.EnterParams.TAG) Long tag) {
        User user = UserMapper.fromSecurityContext(securityContext);
        Hall hall = hallService.getHallForTag(tag);

        checkHallNotNull(hall);

        hallService.enter(hall, user);

        return HallResponse.enter(hallService.enter(hall, user), hall, userService.findBySimpleTag(hall.getOwner()));
    }

    @POST
    @Path(EndpointInfo.Hall.LEAVE)
    public Response leave(@QueryParam(EndpointInfo.Hall.LeaveParams.TAG) Long tag) {
        Hall hall = hallService.getHallForTag(tag);

        checkHallNotNull(hall);

        return HallResponse.leave(hallService.leave(hall, userService.findBySimpleTag(hall.getOwner())));
    }

    @POST
    @Path(EndpointInfo.Hall.KICK)
    public Response kick(@QueryParam(EndpointInfo.Hall.KickParams.HALL_TAG) Long hallTag,
                         @QueryParam(EndpointInfo.Hall.KickParams.USER_TAG) Long userTag) {
        User user = userService.findBySimpleTag(userTag);
        Hall hall = hallService.getHallForTag(hallTag);

        checkHallNotNull(hall);
        checkUserNotNull(user);
        checkOwner(hall, user);

        return HallResponse.kick(hallService.leave(hall, user));
    }

    @POST
    @Path(EndpointInfo.Hall.BAN)
    public Response ban(@QueryParam(EndpointInfo.Hall.BanParams.HALL_TAG) Long hallTag,
                        @QueryParam(EndpointInfo.Hall.BanParams.USER_TAG) Long userTag) {
        User user = userService.findBySimpleTag(userTag);
        Hall hall = hallService.getHallForTag(hallTag);

        checkHallNotNull(hall);
        checkUserNotNull(user);
        checkOwner(hall, user);

        return HallResponse.ban(hallService.ban(hall, user));
    }

    @POST
    @Path(EndpointInfo.Hall.UNBAN)
    public Response unban(@QueryParam(EndpointInfo.Hall.UnbanParams.HALL_TAG) Long hallTag,
                          @QueryParam(EndpointInfo.Hall.UnbanParams.USER_TAG) Long userTag) {
        User user = userService.findBySimpleTag(userTag);
        Hall hall = hallService.getHallForTag(hallTag);

        checkHallNotNull(hall);
        checkUserNotNull(user);
        checkOwner(hall, user);

        return HallResponse.unban(hallService.unban(hall, user));
    }

    @GET
    @Path(EndpointInfo.Hall.INFO)
    public Response info(@QueryParam(EndpointInfo.Hall.InfoParams.TAG) Long tag) {
        User user = UserMapper.fromSecurityContext(securityContext);
        Hall hall = hallService.getHallForTag(tag);

        checkHallNotNull(hall);

        if (hallService.isUserBanned(hall, user)) {
            throw new NotFoundException(ResponseCodes.HALL_NOT_FOUND_ERROR, ResponseMessages.HALL_NOT_FOUND_ERROR);
        }

        return HallResponse.info(hall, userService.findBySimpleTag(hall.getOwner()));
    }

    private static void checkHallNotNull(Hall hall) {
        if (hall == null) {
            throw new NotFoundException(ResponseCodes.HALL_NOT_FOUND_ERROR, ResponseMessages.HALL_NOT_FOUND_ERROR);
        }
    }

    private static void checkUserNotNull(User user) {
        if (user == null) {
            throw new NotFoundException(ResponseCodes.USER_NOT_FOUND_ERROR, ResponseMessages.USER_NOT_FOUND_ERROR);
        }
    }

    private void checkOwner(Hall hall, User user) {
        if (!hallService.isUserOwner(hall, user)) {
            throw new AccessDeniedException();
        }
    }

}
