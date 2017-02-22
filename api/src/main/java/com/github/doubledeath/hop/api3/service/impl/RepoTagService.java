package com.github.doubledeath.hop.api3.service.impl;

import com.github.doubledeath.hop.api3.model.Tag;
import com.github.doubledeath.hop.api3.service.TagService;
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
@RepoTagService.Impl
public class RepoTagService implements TagService {

    @NotNull
    @Override
    public Tag create(@Nullable String prefix) {
        return null;
    }

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    @interface Impl {

    }

}
