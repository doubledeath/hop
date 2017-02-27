package com.github.doubledeath.hop.api2.service.impl;

import com.github.doubledeath.hop.api2.base.Mapper;
import com.github.doubledeath.hop.api2.database.entity.SimpleTagEntity;
import com.github.doubledeath.hop.api2.database.repo.SimpleTagRepo;
import com.github.doubledeath.hop.api2.model.ComplexTag;
import com.github.doubledeath.hop.api2.model.SimpleTag;
import com.github.doubledeath.hop.api2.model.builder.TagBuilder;
import com.github.doubledeath.hop.api2.service.TagService;

import javax.inject.Inject;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/20/17.
 */
@RepoTagService.Impl
public class RepoTagService implements TagService {

    private SimpleTagRepo simpleTagRepo;
    private Mapper<SimpleTag, SimpleTagEntity> mapper;
    private TagBuilder tagBuilder = new TagBuilder();

    @Inject
    public RepoTagService(SimpleTagRepo simpleTagRepo, Mapper<SimpleTag, SimpleTagEntity> mapper) {
        this.simpleTagRepo = simpleTagRepo;
        this.mapper = mapper;
    }

    @Override
    public SimpleTag createSimpleTag() {
        return mapper.from(simpleTagRepo.update());
    }

    @Override
    public ComplexTag createComplexTag(String target) {
        return tagBuilder.buildComplexTag(mapper.from(simpleTagRepo.update()), target);
    }

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    @interface Impl {

    }

}
