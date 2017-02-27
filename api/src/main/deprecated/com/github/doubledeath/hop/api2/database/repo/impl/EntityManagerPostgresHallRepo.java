package com.github.doubledeath.hop.api2.database.repo.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.doubledeath.hop.api2.database.entity.HallEntity;
import com.github.doubledeath.hop.api2.database.repo.HallRepo;

import javax.inject.Qualifier;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Collections;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/20/17.
 */
@EntityManagerPostgresHallRepo.Impl
public class EntityManagerPostgresHallRepo implements HallRepo {

    private EntityManagerRepoHelper helper;
    private ObjectMapper objectMapper = new ObjectMapper();

    public EntityManagerPostgresHallRepo(EntityManagerRepoHelper helper) {
        this.helper = helper;
    }

    @Override
    public HallEntity findOneByTag(Long tag) {
        return findOneByTagImpl(tag);
    }

    @Override
    public HallEntity create(Long tag, Long owner, HallEntity.Visibility visibility, String displayName, Long size) {
        helper.runInTransaction(() -> {
            try {
                helper.entityManager().createNativeQuery(
                        "insert into hallentity " +
                                "(tag, owner, visibility, displayname, description, size, userlist, userbanlist) values (" +
                                tag + ", " +
                                owner + ", " +
                                "'" + visibility.name() + "', '" +
                                "'" + displayName + "', " +
                                "'', " + //description
                                size + ", " +
                                "'" + objectMapper.writeValueAsString(Collections.singleton(owner)) + "', " + //userList with owner
                                "'[]');" //userBanList
                ).executeUpdate();
            } catch (JsonProcessingException exception) {
                throw new RuntimeException(exception);
            }
        });

        return findOneByTagImpl(tag);
    }

    @Override
    public HallEntity update(HallEntity target) {
        HallEntity hallEntity = findOneByTagImpl(target.getTag());

        helper.runInTransaction(() -> {
            try {
                helper.entityManager().createNativeQuery(
                        "update hallentity set" +
                                " tag = " + target.getTag() +
                                ", visibility = '" + target.getVisibility().name() + "'" +
                                ", displayname = '" + target.getDisplayName() + "'" +
                                ", description = '" + target.getDescription() + "'" +
                                ", size = " + target.getSize() +
                                ", userlist = '" + objectMapper.writeValueAsString(target.getUserList()) + "'" +
                                ", userbanlist = '" + objectMapper.writeValueAsString(target.getUserBanList()) + "'" +
                                " where id = " + hallEntity.getId() + ";"
                ).executeUpdate();
            } catch (JsonProcessingException exception) {
                throw new RuntimeException(exception);
            }
        });

        hallEntity.setTag(target.getTag());
        hallEntity.setVisibility(target.getVisibility());
        hallEntity.setDisplayName(target.getDisplayName());
        hallEntity.setDescription(target.getDescription());
        hallEntity.setSize(target.getSize());
        hallEntity.setUserList(target.getUserList());
        hallEntity.setUserBanList(target.getUserBanList());

        return hallEntity;
    }

    @Override
    public void delete(HallEntity hallEntity) {
        helper.runInTransaction(() -> helper.entityManager().createNativeQuery(
                "delete from hallentity where tag = " + hallEntity.getTag() + ";"
        ).executeUpdate());
    }

    private HallEntity findOneByTagImpl(Long tag) {
        CriteriaBuilder criteriaBuilder = helper.entityManager().getCriteriaBuilder();
        CriteriaQuery<HallEntity> criteriaQuery = criteriaBuilder.createQuery(HallEntity.class);
        HallEntity hallEntity;

        //if table is empty hibernate throwing NoResultException instead of return null
        try {
            hallEntity = helper.entityManager()
                    .createQuery(criteriaQuery
                            .select(criteriaQuery.from(HallEntity.class))
                            .where(criteriaBuilder.equal(criteriaQuery
                                    .from(HallEntity.class)
                                    .get("tag"), tag)))
                    .getSingleResult();
        } catch (NoResultException exception) {
            hallEntity = null;
        }

        return hallEntity;
    }

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    @interface Impl {

    }

}
