package com.github.doubledeath.hop.api.model.mapper;

import com.github.doubledeath.hop.api.database.entity.HallEntity;
import com.github.doubledeath.hop.api.endpoint.response.HallInfoResponse;
import com.github.doubledeath.hop.api.model.Hall;
import com.github.doubledeath.hop.api.model.User;

/**
 * Created by doubledeath on 2/18/17.
 */
public final class HallMapper {

    public static Hall fromHallEntity(HallEntity hallEntity) {
        Hall hall = new Hall();

        hall.setTag(hallEntity.getTag());
        hall.setOwner(hallEntity.getOwner());
        hall.setVisibility(Hall.Visibility.valueOf(hallEntity.getVisibility().name()));
        hall.setDisplayName(hallEntity.getDisplayName());
        hall.setDescription(hallEntity.getDescription());
        hall.setSize(hallEntity.getSize());
        hall.setUserList(hallEntity.getUserList());
        hall.setBannedUserList(hallEntity.getBannedUserList());

        return hall;
    }

    public static HallEntity toHallEntity(Hall hall) {
        HallEntity hallEntity = new HallEntity();

        hallEntity.setTag(hall.getTag());
        hallEntity.setOwner(hall.getOwner());
        hallEntity.setVisibility(HallEntity.Visibility.valueOf(hall.getVisibility().name()));
        hallEntity.setDisplayName(hall.getDisplayName());
        hallEntity.setDescription(hall.getDescription());
        hallEntity.setSize(hall.getSize());
        hallEntity.setUserList(hall.getUserList());
        hallEntity.setBannedUserList(hall.getBannedUserList());

        return hallEntity;
    }

    public static HallInfoResponse toHallInfoResponse(Hall hall, User owner) {
        HallInfoResponse hallInfoResponse = new HallInfoResponse();

        hallInfoResponse.setTag(hall.getTag());
        hallInfoResponse.setVisibility(hall.getVisibility().name().toLowerCase());
        hallInfoResponse.setDisplayName(hall.getDisplayName());
        hallInfoResponse.setDescription(hall.getDescription());
        hallInfoResponse.setCurrentUserCount((long) hall.getUserList().size());
        hallInfoResponse.setMaxUserCount(hall.getSize());
        hallInfoResponse.setOwnerTag(owner.getSimpleTag());
        hallInfoResponse.setOwnerDisplayName(owner.getDisplayName());

        return hallInfoResponse;
    }

}
