package com.github.doubledeath.hop.api.service.impl;

import com.github.doubledeath.hop.api.database.repo.TagRepo;
import com.github.doubledeath.hop.api.helper.TagHelper;
import com.github.doubledeath.hop.api.service.TagService;

import javax.inject.Inject;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/16/17.
 */
@RepoTagService.Impl
public class RepoTagService implements TagService {

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD})
    @interface Impl {

    }

    private TagRepo tagRepo;

    @Inject
    public RepoTagService(TagRepo tagRepo) {
        this.tagRepo = tagRepo;
    }

    @Override
    public Long newSimpleTag() {
        return tagRepo.newSimpleTag();
    }

    @Override
    public String newComplexTag(String login) {
        return TagHelper.getComplexTag(login, tagRepo.newSimpleTag());
    }

}
