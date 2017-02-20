package com.github.doubledeath.hop.api2.model.mapper;

import com.github.doubledeath.hop.api2.database.entity.SimpleTagEntity;
import com.github.doubledeath.hop.api2.base.Mapper;
import com.github.doubledeath.hop.api2.model.SimpleTag;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/20/17.
 */
@SimpleTagToEntityMapper.Impl
public class SimpleTagToEntityMapper extends Mapper<SimpleTag, SimpleTagEntity> {

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    @interface Impl {

    }

    @Override
    protected SimpleTag fromImpl(SimpleTagEntity from) {
        return null;
    }

    @Override
    protected SimpleTagEntity toImpl(SimpleTag to) {
        return null;
    }

}
