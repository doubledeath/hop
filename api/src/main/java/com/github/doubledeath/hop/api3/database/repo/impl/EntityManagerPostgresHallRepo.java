package com.github.doubledeath.hop.api3.database.repo.impl;

import com.github.doubledeath.hop.api3.database.entity.HallEntity;
import com.github.doubledeath.hop.api3.database.repo.HallRepo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/22/17.
 */
@EntityManagerPostgresHallRepo.Impl
public class EntityManagerPostgresHallRepo implements HallRepo {

    @Nullable
    @Override
    public HallEntity findOneByTag(@NotNull Long tag) {
        return null;
    }

    @NotNull
    @Override
    public HallEntity create(@NotNull Long tag, @NotNull Long owner, @NotNull Long size, @NotNull String displayName) {
        return null;
    }

    @NotNull
    @Override
    public HallEntity update(@NotNull HallEntity hallEntity) {
        return null;
    }

    @Override
    public void delete(@NotNull HallEntity hallEntity) {

    }

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    @interface Impl {

    }

}
