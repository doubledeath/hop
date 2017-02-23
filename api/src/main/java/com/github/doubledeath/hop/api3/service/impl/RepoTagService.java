package com.github.doubledeath.hop.api3.service.impl;

import com.github.doubledeath.hop.api.database.repo.TagRepo;
import com.github.doubledeath.hop.api3.model.Tag;
import com.github.doubledeath.hop.api3.model.builder.TagBuilder;
import com.github.doubledeath.hop.api3.service.TagService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * Created by doubledeath on 2/22/17.
 */
@SuppressWarnings("unused")
@Dependent
public class RepoTagService implements TagService {

    @Inject
    private TagRepo tagRepo;
    private TagBuilder tagBuilder = new TagBuilder();

    @NotNull
    @Override
    public Tag create(@Nullable String prefix) {
        return tagBuilder.build(tagRepo.create(), prefix);
    }

}
