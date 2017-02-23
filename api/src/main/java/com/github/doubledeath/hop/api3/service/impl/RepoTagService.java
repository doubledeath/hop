package com.github.doubledeath.hop.api3.service.impl;

import com.github.doubledeath.hop.api3.model.Tag;
import com.github.doubledeath.hop.api3.service.TagService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;

/**
 * Created by doubledeath on 2/22/17.
 */
@SuppressWarnings("unused")
@Dependent
public class RepoTagService implements TagService {

    @NotNull
    @Override
    public Tag create(@Nullable String prefix) {
        return null;
    }

}
