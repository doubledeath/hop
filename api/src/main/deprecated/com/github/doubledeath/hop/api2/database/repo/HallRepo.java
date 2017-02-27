package com.github.doubledeath.hop.api2.database.repo;

import com.github.doubledeath.hop.api2.database.entity.HallEntity;

/**
 * Created by doubledeath on 2/20/17.
 */
public interface HallRepo {

    HallEntity findOneByTag(Long tag);

    HallEntity create(Long tag, Long owner, HallEntity.Visibility visibility, String displayName, Long size);

    HallEntity update(HallEntity hallEntity);

    void delete(HallEntity hallEntity);

}
