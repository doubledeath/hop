package com.github.doubledeath.hop.api.producer;

import com.github.doubledeath.hop.api.base.BaseMapper;
import com.github.doubledeath.hop.api.base.Mapper;
import com.github.doubledeath.hop.api.base.MapperImpl1;
import com.github.doubledeath.hop.api.base.MapperImpl2;

import javax.enterprise.inject.Produces;

/**
 * Created by doubledeath on 2/20/17.
 */
public class MapperProducer {

    @Produces
//    @Mapper(Mapper.Type.IMPL_1)
    public BaseMapper<Object, Long> mapperImpl1() {
        return new MapperImpl1();
    }

    @Produces
//    @Mapper(Mapper.Type.IMPL_2)
    public BaseMapper<Object, String> mapperImpl2() {
        return new MapperImpl2();
    }

}
