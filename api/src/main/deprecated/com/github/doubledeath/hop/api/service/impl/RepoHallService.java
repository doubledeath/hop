package com.github.doubledeath.hop.api.service.impl;

import com.github.doubledeath.hop.api.database.entity.HallEntity;
import com.github.doubledeath.hop.api.database.repo.HallRepo;
import com.github.doubledeath.hop.api.model.Hall;
import com.github.doubledeath.hop.api.model.User;
import com.github.doubledeath.hop.api.model.mapper.HallMapper;
import com.github.doubledeath.hop.api.service.TagService;

import javax.inject.Inject;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Collections;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/17/17.
 */
@RepoHallService.Impl
public class RepoHallService implements HallService {

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD})
    @interface Impl {

    }

    private HallRepo hallRepo;
    private TagService tagService;

    @Inject
    public RepoHallService(HallRepo hallRepo, TagService tagService) {
        this.hallRepo = hallRepo;
        this.tagService = tagService;
    }

    @Override
    public Hall getHallForTag(Long tag) {
        HallEntity hallEntity = hallRepo.findOneByTag(tag);

        return hallEntity == null ? null : HallMapper.fromHallEntity(hallEntity);
    }

    @Override
    public Hall create(Long owner, Hall.Visibility visibility, String displayName, Long size) {
        return HallMapper.fromHallEntity(hallRepo.create(
                tagService.createSimpleTag(),
                owner,
                HallEntity.Visibility.valueOf(visibility.name()),
                displayName,
                size
        ));
    }

    @Override
    public Hall update(Hall hall) {
        HallEntity hallEntity = hallRepo.findOneByTag(hall.getTag());

        //visibility changed, need to generate new tag
        if (!hall.getVisibility().name().equals(hallEntity.getVisibility().name())) {
            hallEntity.setTag(tagService.createSimpleTag());
        }

        return HallMapper.fromHallEntity(hallRepo.update(hallEntity));
    }

    @Override
    public void delete(Hall target) {
        Hall hall = HallMapper.fromHallEntity(hallRepo.findOneByTag(target.getTag()));

        hall.setUserList(Collections.emptyList());
        //in this situation, check will always do job
        checkHallState(hall);
    }

    @Override
    public EnterResult enter(Hall hall, User user) {
        if (hall.getBannedUserList().contains(user.getSimpleTag())) {
            return EnterResult.USER_BANNED_ERROR;
        }

        if (hall.getUserList().size() + 1 >= hall.getSize()) {
            return EnterResult.MAX_SIZE_ERROR;
        }

        if (!hall.getUserList().contains(user.getSimpleTag())) {
            hall.getUserList().add(user.getSimpleTag());

            update(hall);
        }

        return EnterResult.SUCCESS;
    }

    @Override
    public LeaveBanUnbanResult leave(Hall hall, User user) {
        if (hall.getUserList().contains(user.getSimpleTag())) {
            hall.getUserList().remove(user.getSimpleTag());

            update(hall);
        } else {
            return LeaveBanUnbanResult.LIST_CONFLICT_ERROR;
        }

        checkHallState(hall);

        return LeaveBanUnbanResult.SUCCESS;
    }

    @Override
    public LeaveBanUnbanResult ban(Hall hall, User user) {
        if (!isUserBanned(hall, user)) {
            hall.getBannedUserList().add(user.getSimpleTag());

            update(hall);
        } else {
            return LeaveBanUnbanResult.LIST_CONFLICT_ERROR;
        }

        checkHallState(hall);

        return LeaveBanUnbanResult.SUCCESS;
    }

    @Override
    public LeaveBanUnbanResult unban(Hall hall, User user) {
        if (isUserBanned(hall, user)) {
            hall.getBannedUserList().remove(user.getSimpleTag());

            update(hall);
        } else {
            return LeaveBanUnbanResult.LIST_CONFLICT_ERROR;
        }

        return LeaveBanUnbanResult.SUCCESS;
    }

    @Override
    public boolean isUserBanned(Hall hall, User user) {
        return hall.getBannedUserList().contains(user.getSimpleTag());
    }

    @Override
    public boolean isUserOwner(Hall hall, User user) {
        return hall.getOwner().equals(user.getSimpleTag());
    }

    /**
     * Hall have to be deleted automatically, when user list is empty,
     * this method is do this job
     */
    private void checkHallState(Hall hall) {
        if (hall.getUserList().size() == 0) {
            hallRepo.delete(HallMapper.toHallEntity(hall));
        }
    }

}
