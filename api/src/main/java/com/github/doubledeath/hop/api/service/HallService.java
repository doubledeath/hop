package com.github.doubledeath.hop.api.service;

import com.github.doubledeath.hop.api.database.entity.HallEntity;
import com.github.doubledeath.hop.api.model.Hall;
import com.github.doubledeath.hop.api.model.User;

/**
 * Created by doubledeath on 2/17/17.
 */
public interface HallService {

    enum EnterResult {
        SUCCESS, USER_BANNED_ERROR, MAX_SIZE_ERROR
    }

    enum LeaveBanUnbanResult {
        SUCCESS, LIST_CONFLICT_ERROR
    }

    Hall getHallForTag(Long tag);

    Hall create(Long owner, Hall.Visibility visibility, String displayName, Long size);

    Hall update(Hall hall);

    void delete(Hall hall);

    EnterResult enter(Hall hall, User user);

    LeaveBanUnbanResult leave(Hall hall, User user);

    LeaveBanUnbanResult ban(Hall hall, User user);

    LeaveBanUnbanResult unban(Hall hall, User user);

    boolean isUserBanned(Hall hall, User user);

    boolean isUserOwner(Hall hall, User user);

}
