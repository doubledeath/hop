package com.github.doubledeath.hop.api.service.impl;

import com.github.doubledeath.hop.api.common.Mapper;
import com.github.doubledeath.hop.api.db.entity.HallEntity;
import com.github.doubledeath.hop.api.db.repo.HallRepo;
import com.github.doubledeath.hop.api.db.repo.TagRepo;
import com.github.doubledeath.hop.api.exception.AccessDeniedException;
import com.github.doubledeath.hop.api.exception.NotFoundException;
import com.github.doubledeath.hop.api.model.Hall;
import com.github.doubledeath.hop.api.ref.ResponseRef;
import com.github.doubledeath.hop.api.service.HallService;
import com.github.doubledeath.hop.api.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by doubledeath on 2/27/17.
 */
//@SuppressWarnings("unused")
@Stateless
public class RepoHallService implements HallService {

    @EJB
    private HallRepo hallRepo;
    @EJB
    private TagRepo tagRepo;
    @EJB
    private UserService userService;
    @Inject
    private Mapper<Hall, HallEntity> mapper;

    @NotNull
    @Override
    public Hall create(@NotNull Long owner, @NotNull Long size, @NotNull String displayName) {
        return from(hallRepo.create(tagRepo.update().getValue(), owner, size, displayName));
    }

    @NotNull
    @Override
    public Hall findOneByTag(@NotNull Long tag) {
        return findOneByTagImpl(tag);
    }

    @NotNull
    @Override
    public Hall update(@NotNull Hall hall, @NotNull Long owner, @Nullable String key) {
        getOwnedHall(hall.getTag(), owner);

        HallEntity hallEntity = hallRepo.findByTag(hall.getTag());

        //never happens
        if (hallEntity == null) {
            throw new RuntimeException();
        }

        hallEntity.setTag();
        hallEntity.setDisplayName();
        hallEntity.setDescription();

        return from(hallRepo.update(hallEntity));
    }

    @Override
    public void delete(@NotNull Long tag, @NotNull Long owner) {
        Hall ownedHall = getOwnedHall(tag, owner);
    }

    @Override
    public void enter(@NotNull Long tag, @NotNull Long user, @Nullable String key) {
        Hall hall = findOneByTagImpl(tag);

        //owner not need to pass key
        if (!isOwner(hall, user)) {

        }
    }

    @Override
    public void leave(@NotNull Long tag, @NotNull Long user) {

    }

    @Override
    public void kick(@NotNull Long tag, @NotNull Long owner, @NotNull Long user) {
        Hall ownedHall = getOwnedHall(tag, owner);
    }

    @Override
    public void ban(@NotNull Long tag, @NotNull Long owner, @NotNull Long user) {
        Hall ownedHall = getOwnedHall(tag, owner);
    }

    @Override
    public void unban(@NotNull Long tag, @NotNull Long owner, @NotNull Long user) {
        Hall ownedHall = getOwnedHall(tag, owner);
    }

    @NotNull
    private Hall getOwnedHall(@NotNull Long tag, @NotNull Long owner) {
        Hall hall = findOneByTagImpl(tag);

        if (!isOwner(hall, owner)) {
            throw new AccessDeniedException(ResponseRef.Code.USER_NOT_HALL_OWNER_ERROR, ResponseRef.Message.USER_NOT_HALL_OWNER_ERROR);
        }

        return hall;
    }

    private boolean isOwner(@NotNull Hall hall, @NotNull Long owner) {
        return hall.getOwner().equals(owner);
    }

    @NotNull
    private Hall updateImpl(@NotNull Hall hall, @Nullable String key) {
        HallEntity hallEntity = hallRepo.findByTag(hall.getTag());

        if (hallEntity == null) {
            throw new RuntimeException();
        }

        hallEntity.setTag(hall.getTag());
        hallEntity.setOwner(hall.getOwner());
        hallEntity.setSize(hall.getSize());
        hallEntity.setDisplayName(hall.getDisplayName());
        hallEntity.setKey(key);
        hallEntity.setDescription(hall.getDescription());
        hallEntity.setUserList(hall.getUserList());
        hallEntity.setUserBanlist(hall.getUserBanlist());
    }

    @NotNull
    private Hall findOneByTagImpl(@NotNull Long tag) {
        HallEntity hallEntity = hallRepo.findByTag(tag);

        if (hallEntity == null) {
            throw new NotFoundException(ResponseRef.Code.HALL_NOT_FOUND_ERROR, ResponseRef.Message.HALL_NOT_FOUND_ERROR);
        }

        return from(hallEntity);
    }

    @NotNull
    private Hall from(@NotNull HallEntity hallEntity) {
        Hall hall = new Hall(
                hallEntity.getTag(),
                hallEntity.getOwner(),
                hallEntity.getSize(),
                hallEntity.getDisplayName()
        );

        hall.setDescription(hallEntity.getDescription());
        hall.setUserList(hallEntity.getUserList());
        hall.setUserBanlist(hallEntity.getUserBanlist());

        return hall;
    }

}
