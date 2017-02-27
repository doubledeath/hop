package com.github.doubledeath.hop.api.db.repo;

import com.github.doubledeath.hop.api.db.entity.TagEntity;
import org.jetbrains.annotations.NotNull;

import javax.ejb.Local;

/**
 * Created by doubledeath on 2/27/17.
 */
@Local
public interface TagRepo {

    @NotNull
    TagEntity update();

}
