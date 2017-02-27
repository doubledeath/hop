package com.github.doubledeath.hop.api3.database.repo;

import com.github.doubledeath.hop.api3.database.entity.HallEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by doubledeath on 2/22/17.
 */
public interface HallRepo {

    @Nullable
    HallEntity findOneByTag(@NotNull Long tag);

    @NotNull
    HallEntity create(@NotNull Long tag, @NotNull Long owner, @NotNull Long size, @NotNull String displayName);

    @NotNull
    HallEntity update(@NotNull HallEntity hallEntity);

    void delete(@NotNull HallEntity hallEntity);

}
