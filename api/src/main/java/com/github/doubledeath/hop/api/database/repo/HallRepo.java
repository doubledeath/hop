package com.github.doubledeath.hop.api.database.repo;

import com.github.doubledeath.hop.api.database.entity.HallEntity;

/**
 * Created by doubledeath on 2/17/17.
 */
public interface HallRepo {

    HallEntity create(Long tag, Long owner, HallEntity.Visibility visibility, String displayName, Long size);

    HallEntity update(HallEntity hallEntity);

    void delete(HallEntity hallEntity);

    HallEntity findOneByTag(Long tag);

}
