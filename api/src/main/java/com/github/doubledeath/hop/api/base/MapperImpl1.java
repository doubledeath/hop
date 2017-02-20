package com.github.doubledeath.hop.api.base;

/**
 * Created by doubledeath on 2/20/17.
 */
//@Mapper(Mapper.Type.IMPL_1)
public class MapperImpl1 extends BaseMapper<Object, Long> {

    @Override
    protected Long fromImpl(Object from) {
        return null;
    }

    @Override
    protected Object toImpl(Long to) {
        return null;
    }

}
