package com.github.doubledeath.hop.api.db.repo;

import com.github.doubledeath.hop.api.db.entity.HallEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ejb.Local;

/**
 * Created by doubledeath on 2/27/17.
 */
@Local
public interface HallRepo {

    @NotNull
    HallEntity create(@NotNull Long tag, @NotNull Long owner, @NotNull Long size, @NotNull String displayName);

    @Nullable
    HallEntity findByTag(@NotNull Long tag);

    @NotNull
    HallEntity update(@NotNull HallEntity hallEntity);

    void delete(@NotNull Long tag);

}
