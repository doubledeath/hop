package com.github.doubledeath.hop.api.producer;

import com.github.doubledeath.hop.api.database.repo.HallRepo;
import com.github.doubledeath.hop.api.database.repo.TagRepo;
import com.github.doubledeath.hop.api.service.TagService;
import com.github.doubledeath.hop.api.service.impl.KeycloakAdminClientUserService;
import com.github.doubledeath.hop.api.service.impl.RepoHallService;
import com.github.doubledeath.hop.api.service.impl.RepoTagService;

import javax.enterprise.inject.Produces;

/**
 * Created by doubledeath on 2/16/17.
 */
@SuppressWarnings("unused")
public class ServiceProducer {

    @Produces
    public TagService repoTagService(TagRepo tagRepo) {
        return new RepoTagService(tagRepo);
    }

    @Produces
    public UserService keycloakAdminClientUserService(TagService tagService) {
        return new KeycloakAdminClientUserService(tagService);
    }

    @Produces
    public HallService repoHallService(HallRepo hallRepo, TagService tagService) {
        return new RepoHallService(hallRepo, tagService);
    }

}
