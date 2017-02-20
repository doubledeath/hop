package com.github.doubledeath.hop.api2.producer;

import com.github.doubledeath.hop.api2.database.entity.HallEntity;
import com.github.doubledeath.hop.api2.base.Mapper;
import com.github.doubledeath.hop.api2.database.entity.SimpleTagEntity;
import com.github.doubledeath.hop.api2.model.Hall;
import com.github.doubledeath.hop.api2.model.SimpleTag;
import com.github.doubledeath.hop.api2.model.mapper.HallToEntityMapper;
import com.github.doubledeath.hop.api2.model.mapper.SimpleTagToEntityMapper;

import javax.enterprise.inject.Produces;

/**
 * Created by doubledeath on 2/20/17.
 */
@SuppressWarnings("unused")
public class MapperProducer {

    @Produces
    public Mapper<Hall, HallEntity> hallToEntityMapper() {
        return new HallToEntityMapper();
    }

    @Produces
    public Mapper<SimpleTag, SimpleTagEntity> simpleTagToEntityMapper() {
        return new SimpleTagToEntityMapper();
    }

}
