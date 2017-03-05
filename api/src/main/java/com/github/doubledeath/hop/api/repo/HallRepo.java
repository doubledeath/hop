package com.github.doubledeath.hop.api.repo;

import com.github.doubledeath.hop.api.model.Hall;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ejb.Local;

/**
 * Created by doubledeath on 3/5/17.
 */
@Local
public interface HallRepo {

    @NotNull
    Hall create(@NotNull String tag, @NotNull String owner);

    @Nullable
    Hall findByTag(@NotNull String tag);

    @NotNull
    Hall update(@NotNull Hall hall);

    void delete(@NotNull Hall hall);

}
