package com.github.doubledeath.hop.api2.service.impl;

import com.github.doubledeath.hop.api2.base.Mapper;
import com.github.doubledeath.hop.api2.database.entity.HallEntity;
import com.github.doubledeath.hop.api2.database.repo.HallRepo;
import com.github.doubledeath.hop.api2.exception.AccessDeniedException;
import com.github.doubledeath.hop.api2.exception.ConflictException;
import com.github.doubledeath.hop.api2.info.Response;
import com.github.doubledeath.hop.api2.model.Hall;
import com.github.doubledeath.hop.api2.model.User;
import com.github.doubledeath.hop.api2.service.HallService;
import com.github.doubledeath.hop.api2.service.TagService;

import javax.inject.Inject;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Collections;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/20/17.
 */
@RepoHallService.Impl
public class RepoHallService implements HallService {

    private HallRepo hallRepo;
    private TagService tagService;
    private Mapper<Hall, HallEntity> mapper;

    @Inject
    public RepoHallService(HallRepo hallRepo, TagService tagService, Mapper<Hall, HallEntity> mapper) {
        this.hallRepo = hallRepo;
        this.tagService = tagService;
        this.mapper = mapper;
    }

    @Override
    public Hall findOneByTagValue(Long tag) {
        return mapper.from(hallRepo.findOneByTag(tag));
    }

    @Override
    public Hall create(Long owner, Hall.Visibility visibility, String displayName, Long size) {
        return mapper.from(hallRepo.create(
                tagService.createSimpleTag().getValue(),
                owner,
                HallEntity.Visibility.valueOf(visibility.name()),
                displayName,
                size
        ));
    }

    @Override
    public Hall update(Hall hall) {
        return updateImpl(hall);
    }

    @Override
    public void delete(Hall target) {
        Hall hall = mapper.from(hallRepo.findOneByTag(target.getTag()));

        hall.setUserList(Collections.emptyList());
        //in this situation, check will always do job
        checkHallState(hall);
    }

    @Override
    public void enter(Hall hall, User user) {
        if (isUserInBanList(hall, user)) {
            throw new AccessDeniedException(Response.Code.USER_IN_BAN_LIST_ERROR, Response.Message.USER_IN_BAN_LIST_ERROR);
        }

        if (hall.getUserList().size() + 1 > hall.getSize()) {
            throw new ConflictException(Response.Code.HALL_IS_FULL_ERROR, Response.Message.HALL_IS_FULL_ERROR);
        }

        if (!hall.getUserList().contains(user.getSimpleTag().getValue())) {
            hall.getUserList().add(user.getSimpleTag().getValue());

            updateImpl(hall);
        }
    }

    @Override
    public void leave(Hall hall, User user) {
        if (hall.getUserList().contains(user.getSimpleTag().getValue())) {
            hall.getUserList().remove(user.getSimpleTag().getValue());

            updateImpl(hall);

            checkHallState(hall);
        }
    }

    @Override
    public void ban(Hall hall, User user) {
        if (!isUserInBanList(hall, user)) {
            hall.getUserBanList().add(user.getSimpleTag().getValue());

            updateImpl(hall);

            checkHallState(hall);
        }
    }

    @Override
    public void unban(Hall hall, User user) {
        if (isUserInBanList(hall, user)) {
            hall.getUserBanList().remove(user.getSimpleTag().getValue());

            updateImpl(hall);

            checkHallState(hall);
        }
    }

    @Override
    public boolean isUserOwner(Hall hall, User user) {
        return hall.getOwner().equals(user.getSimpleTag().getValue());
    }

    private boolean isUserInBanList(Hall hall, User user) {
        return hall.getUserBanList().contains(user.getSimpleTag().getValue());
    }

    private Hall updateImpl(Hall hall) {
        HallEntity hallEntity = hallRepo.findOneByTag(hall.getTag());

        //visibility changed, need to generate new tag
        if (!hall.getVisibility().name().equals(hallEntity.getVisibility().name())) {
            hallEntity.setTag(tagService.createSimpleTag().getValue());
        }

        return mapper.from(hallRepo.update(hallEntity));
    }

    /**
     * Hall have to be deleted automatically, when user list is empty,
     * this method is do this job
     */
    private void checkHallState(Hall hall) {
        if (hall.getUserList().size() == 0) {
            hallRepo.delete(mapper.to(hall));
        }
    }

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    @interface Impl {

    }

}
