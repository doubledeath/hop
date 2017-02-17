package com.github.doubledeath.hop.api.database.repo.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.doubledeath.hop.api.database.entity.HallEntity;
import com.github.doubledeath.hop.api.database.repo.HallRepo;
import com.github.doubledeath.hop.api.service.TagService;

import javax.inject.Inject;
import javax.inject.Qualifier;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/17/17.
 */
@EntityManagerPostgresHallRepo.Impl
public class EntityManagerPostgresHallRepo implements HallRepo {

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD})
    @interface Impl {

    }

    private EntityManagerRepoHelper entityManagerRepoHelper;
    private TagService tagService;
    private ObjectMapper objectMapper;

    @Inject
    public EntityManagerPostgresHallRepo(EntityManagerRepoHelper entityManagerRepoHelper, TagService tagService) {
        this.entityManagerRepoHelper = entityManagerRepoHelper;
        this.tagService = tagService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void test() {
        CriteriaBuilder criteriaBuilder = entityManagerRepoHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<HallEntity> criteriaQuery = criteriaBuilder.createQuery(HallEntity.class);

        List<Long> tmp = new ArrayList<>();
        tmp.add(2L);
        tmp.add(2L);
        tmp.add(2L);

        entityManagerRepoHelper.runInTransaction(() -> {
            try {
                entityManagerRepoHelper.entityManager().createNativeQuery(
                        "insert into HallEntity (displayname, owner, tag, userlist) values " +
                                "('2',2,2,'" + objectMapper.writeValueAsString(tmp) + "')"
                ).executeUpdate();
            } catch (JsonProcessingException exception) {
                throw new RuntimeException(exception);
            }
        });

//        entityManagerRepoHelper.entityManager().createNativeQuery(
//                "insert into HallEntity (displayname, owner, tag, userlist) values " +
//                        "('2',2,2,'[2,2,2]')"
//        );

        List<HallEntity> hallEntityList = entityManagerRepoHelper.entityManager()
                .createQuery(criteriaQuery
                        .select(criteriaQuery
                                .from(HallEntity.class)))
                .getResultList();

        System.out.println("------");
        hallEntityList.forEach(hallEntity -> {
            System.out.println(hallEntity.getDisplayName());
            hallEntity.getUserList().forEach(System.out::print);
            System.out.println("---");
        });
        System.out.println("------");
    }

}
