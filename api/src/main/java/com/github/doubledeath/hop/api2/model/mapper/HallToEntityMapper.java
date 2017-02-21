package com.github.doubledeath.hop.api2.model.mapper;

import com.github.doubledeath.hop.api2.base.Mapper;
import com.github.doubledeath.hop.api2.database.entity.HallEntity;
import com.github.doubledeath.hop.api2.model.Hall;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/20/17.
 */
@HallToEntityMapper.Impl
public class HallToEntityMapper extends Mapper<Hall, HallEntity> {

    @Override
    protected Hall fromImpl(HallEntity from) {
        Hall hall = new Hall();

        hall.setTag(from.getTag());
        hall.setOwner(from.getOwner());
        hall.setVisibility(Hall.Visibility.valueOf(from.getVisibility().name()));
        hall.setDisplayName(from.getDisplayName());
        hall.setDescription(from.getDescription());
        hall.setSize(from.getSize());
        hall.setUserList(from.getUserList());
        hall.setUserBanList(from.getUserBanList());

        return hall;
    }

    @Override
    protected HallEntity toImpl(Hall to) {
        HallEntity hallEntity = new HallEntity();

        hallEntity.setTag(to.getTag());
        hallEntity.setOwner(to.getOwner());
        hallEntity.setVisibility(HallEntity.Visibility.valueOf(to.getVisibility().name()));
        hallEntity.setDisplayName(to.getDisplayName());
        hallEntity.setDescription(to.getDescription());
        hallEntity.setSize(to.getSize());
        hallEntity.setUserList(to.getUserList());
        hallEntity.setUserBanList(to.getUserBanList());

        return hallEntity;
    }

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    @interface Impl {

    }

}
