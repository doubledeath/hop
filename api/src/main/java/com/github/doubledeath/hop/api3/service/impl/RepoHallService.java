package com.github.doubledeath.hop.api3.service.impl;

import com.github.doubledeath.hop.api3.common.Mapper;
import com.github.doubledeath.hop.api3.database.entity.HallEntity;
import com.github.doubledeath.hop.api3.database.repo.HallRepo;
import com.github.doubledeath.hop.api3.exception.AccessDeniedException;
import com.github.doubledeath.hop.api3.exception.ConflictException;
import com.github.doubledeath.hop.api3.exception.NotFoundException;
import com.github.doubledeath.hop.api3.model.Hall;
import com.github.doubledeath.hop.api3.model.Key;
import com.github.doubledeath.hop.api3.model.Tag;
import com.github.doubledeath.hop.api3.model.User;
import com.github.doubledeath.hop.api3.ref.ResponseRef;
import com.github.doubledeath.hop.api3.service.HallService;
import com.github.doubledeath.hop.api3.service.TagService;
import com.github.doubledeath.hop.api3.service.UserService;
import com.github.doubledeath.hop.api3.service.request.HallCreateRequest;
import com.github.doubledeath.hop.api3.service.request.HallUpdateRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by doubledeath on 2/22/17.
 */
@SuppressWarnings("unused")
@Dependent
public class RepoHallService implements HallService {

    @Inject
    private HallRepo hallRepo;
    @Inject
    private TagService tagService;
    @Inject
    private UserService userService;
    @Inject
    private Mapper<Hall, HallEntity> mapper;

    @NotNull
    @Override
    public Hall create(@NotNull HallCreateRequest hallCreateRequest) {
        HallEntity hallEntity = hallRepo.create(
                tagService.create(null).getSimpleValue(),
                hallCreateRequest.getOwner().getSimpleValue(),
                hallCreateRequest.getSize(),
                hallCreateRequest.getDisplayName()
        );

        if (hallCreateRequest.getKey() != null || hallCreateRequest.getDescription() != null) {
            if (hallCreateRequest.getKey() != null) {
                hallEntity.setKey(hallCreateRequest.getKey().getValue());
            }

            if (hallCreateRequest.getDescription() != null) {
                hallEntity.setDescription(hallCreateRequest.getDescription());
            }

            hallEntity = hallRepo.update(hallEntity);
        }

        return mapper.from(hallEntity);
    }

    @NotNull
    @Override
    public Hall find(@NotNull Tag tag) {
        return mapper.from(findImpl(tag));
    }

    @NotNull
    @Override
    public Hall update(@NotNull Tag tag, @NotNull HallUpdateRequest hallUpdateRequest) {
        HallEntity hallEntity = findImpl(tag);

        if (hallUpdateRequest.getSize() == null && hallUpdateRequest.getDisplayName() == null &&
                hallUpdateRequest.getKey() == null && hallUpdateRequest.getDescription() == null) {
            return mapper.from(hallEntity);
        }

        if (hallUpdateRequest.getSize() != null) {
            if (hallUpdateRequest.getSize() > hallEntity.getUserList().size()) {
                throw new ConflictException(ResponseRef.Code.HALL_SIZE_ERROR, ResponseRef.Message.HALL_SIZE_ERROR);
            }

            hallEntity.setSize(hallUpdateRequest.getSize());
        }

        if (hallUpdateRequest.getDisplayName() != null) {
            hallEntity.setDisplayName(hallUpdateRequest.getDisplayName());
        }

        if (hallUpdateRequest.getKey() != null) {
            hallEntity.setKey(hallUpdateRequest.getKey().getValue());
        }

        if (hallUpdateRequest.getDescription() != null) {
            hallEntity.setDescription(hallUpdateRequest.getDescription());
        }

        return mapper.from(hallRepo.update(hallEntity));
    }

    @Override
    public void delete(@NotNull Tag tag) {
        HallEntity hallEntity = findImpl(tag);

        hallEntity.setSize(0L);
        hallEntity.setUserList(new ArrayList<>());

        hallRepo.update(hallEntity);
        //always trigger
        checkHallState(tag);
    }

    @Override
    public void enter(@NotNull Tag hallTag, @NotNull Tag userTag, @Nullable Key key) {
        Hall hall = mapper.from(findImpl(hallTag));
        User user = userService.find(userTag);
        boolean allow = key == null || key.equals(hall.getKey());

        if (allow) {
            if (hall.getUserBanlist().contains(user)) {
                throw new AccessDeniedException(ResponseRef.Code.HALL_USER_IN_BANLIST_ERROR, ResponseRef.Message.HALL_USER_IN_BANLIST_ERROR);
            }

            if (!hall.getUserList().contains(user)) {
                hall.getUserList().add(user);

                hallRepo.update(mapper.to(hall));
            }
        } else {
            throw new AccessDeniedException(ResponseRef.Code.HALL_INVALID_KEY_ERROR, ResponseRef.Message.HALL_INVALID_KEY_ERROR);
        }
    }

    @Override
    public void leave(@NotNull Tag hallTag, @NotNull Tag userTag) {
        Hall hall = mapper.from(findImpl(hallTag));
        User user = userService.find(userTag);

        if (hall.getUserList().contains(user)) {
            hall.getUserList().remove(user);

            hallRepo.update(mapper.to(hall));
        }

        checkHallState(hallTag);
    }

    @Override
    public void ban(@NotNull Tag hallTag, @NotNull Tag userTag) {
        Hall hall = mapper.from(findImpl(hallTag));
        User user = userService.find(userTag);

        if (!hall.getUserBanlist().contains(user)) {
            hall.getUserBanlist().add(user);

            hallRepo.update(mapper.to(hall));
        }

        checkHallState(hallTag);
    }

    @Override
    public void unban(@NotNull Tag hallTag, @NotNull Tag userTag) {
        Hall hall = mapper.from(findImpl(hallTag));
        User user = userService.find(userTag);

        if (hall.getUserBanlist().contains(user)) {
            hall.getUserBanlist().remove(user);

            hallRepo.update(mapper.to(hall));
        }
    }

    @Override
    public boolean isOwner(@NotNull Tag hallTag, @NotNull Tag userTag) {
        Hall hall = mapper.from(findImpl(hallTag));
        User user = userService.find(userTag);

        return hall.getOwner().equals(user);
    }

    private void checkHallState(@NotNull Tag tag) {
        HallEntity hallEntity = findImpl(tag);

        if (hallEntity.getUserList().size() == 0) {
            hallRepo.delete(findImpl(tag));
        }
    }

    @NotNull
    private HallEntity findImpl(@NotNull Tag tag) {
        HallEntity hallEntity = hallRepo.findOneByTag(tag.getSimpleValue());

        if (hallEntity == null) {
            throw new NotFoundException(ResponseRef.Code.HALL_NOT_FOUND_ERROR, ResponseRef.Message.HALL_NOT_FOUND_ERROR);
        }

        return hallEntity;
    }

}
