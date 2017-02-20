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

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    @interface Impl {

    }

    @Override
    protected Hall fromImpl(HallEntity from) {
        return null;
    }

    @Override
    protected HallEntity toImpl(Hall to) {
        return null;
    }

}
