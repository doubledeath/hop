package com.github.doubledeath.hop.api.service;

import com.github.doubledeath.hop.api.model.Hall;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ejb.Local;

/**
 * Created by doubledeath on 2/27/17.
 */
@Local
public interface HallService {

    @NotNull
    Hall create(@NotNull Long owner, @NotNull Long size, @NotNull String displayName);

    @NotNull
    Hall findOneByTag(@NotNull Long tag);

    @NotNull
    Hall update(@NotNull Hall hall, @NotNull Long owner, @Nullable String key);

    void delete(@NotNull Long tag, @NotNull Long owner);

    void enter(@NotNull Long tag, @NotNull Long user, @Nullable String key);

    void leave(@NotNull Long tag, @NotNull Long user);

    void kick(@NotNull Long tag, @NotNull Long owner, @NotNull Long user);

    void ban(@NotNull Long tag, @NotNull Long owner, @NotNull Long user);

    void unban(@NotNull Long tag, @NotNull Long owner, @NotNull Long user);

}
