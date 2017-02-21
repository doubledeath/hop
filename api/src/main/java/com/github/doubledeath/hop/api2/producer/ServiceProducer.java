package com.github.doubledeath.hop.api2.producer;

import com.github.doubledeath.hop.api2.base.Mapper;
import com.github.doubledeath.hop.api2.database.entity.HallEntity;
import com.github.doubledeath.hop.api2.database.entity.SimpleTagEntity;
import com.github.doubledeath.hop.api2.database.repo.HallRepo;
import com.github.doubledeath.hop.api2.database.repo.SimpleTagRepo;
import com.github.doubledeath.hop.api2.model.Hall;
import com.github.doubledeath.hop.api2.model.SimpleTag;
import com.github.doubledeath.hop.api2.model.User;
import com.github.doubledeath.hop.api2.service.HallService;
import com.github.doubledeath.hop.api2.service.TagService;
import com.github.doubledeath.hop.api2.service.UserService;
import com.github.doubledeath.hop.api2.service.impl.KeycloakAdminClientUserService;
import com.github.doubledeath.hop.api2.service.impl.RepoHallService;
import com.github.doubledeath.hop.api2.service.impl.RepoTagService;
import org.keycloak.representations.idm.UserRepresentation;

import javax.enterprise.inject.Produces;

/**
 * Created by doubledeath on 2/20/17.
 */
@SuppressWarnings("unused")
public class ServiceProducer {

    @Produces
    public TagService repoTagService(SimpleTagRepo simpleTagRepo, Mapper<SimpleTag, SimpleTagEntity> mapper) {
        return new RepoTagService(simpleTagRepo, mapper);
    }

    @Produces
    public UserService keycloakAdminClientUserService(TagService tagService, Mapper<User, UserRepresentation> mapper) {
        return new KeycloakAdminClientUserService(tagService, mapper);
    }

    @Produces
    public HallService repoHallService(HallRepo hallRepo, TagService tagService, UserService userService, Mapper<Hall, HallEntity> mapper) {
        return new RepoHallService(hallRepo, tagService, userService, mapper);
    }

}
