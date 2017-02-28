package com.github.doubledeath.hop.api.repo;

import com.github.doubledeath.hop.api.model.Hall;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ejb.Local;

/**
 * Created by doubledeath on 2/27/17.
 */
@Local
public interface HallRepo {

    @NotNull
    Hall create(@NotNull Long tag, @NotNull Long owner, @NotNull Long size, @NotNull String displayName);

    @Nullable
    Hall findByTag(@NotNull Long tag);

    @NotNull
    Hall update(@NotNull Hall hall);

    void delete(@NotNull Long id);

}
