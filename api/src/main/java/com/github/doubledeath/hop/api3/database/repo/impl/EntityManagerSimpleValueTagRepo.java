package com.github.doubledeath.hop.api3.database.repo.impl;

import com.github.doubledeath.hop.api3.database.entity.SimpleValueTagEntity;
import com.github.doubledeath.hop.api3.database.repo.SimpleValueTagRepo;
import org.jetbrains.annotations.NotNull;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/22/17.
 */
@EntityManagerSimpleValueTagRepo.Impl
public class EntityManagerSimpleValueTagRepo implements SimpleValueTagRepo {

    @NotNull
    @Override
    public SimpleValueTagEntity update() {
        return null;
    }

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    @interface Impl {

    }

}
