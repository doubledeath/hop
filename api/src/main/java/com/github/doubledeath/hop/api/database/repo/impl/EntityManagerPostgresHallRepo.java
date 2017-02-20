package com.github.doubledeath.hop.api.database.repo.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.doubledeath.hop.api.database.entity.HallEntity;
import com.github.doubledeath.hop.api.database.repo.HallRepo;

import javax.inject.Inject;
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
    private ObjectMapper objectMapper;

    @Inject
    public EntityManagerPostgresHallRepo(EntityManagerRepoHelper entityManagerRepoHelper) {
        this.entityManagerRepoHelper = entityManagerRepoHelper;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public HallEntity create(Long tag, Long owner, HallEntity.Visibility visibility, String displayName, Long size) {
        entityManagerRepoHelper.runInTransaction(() -> {
            try {
                entityManagerRepoHelper.entityManager().createNativeQuery(
                        "insert into hallentity " +
                                "(tag, owner, visibility, displayname, description, size, userlist, banneduserlist) values (" +
                                tag + ", " +
                                owner + ", " +
                                "'" + visibility.name() + "', '" +
                                "'" + displayName + "', " +
                                "'', " + //description
                                size + ", " +
                                "'" + objectMapper.writeValueAsString(Collections.singleton(owner)) + "', " + //userList with owner
                                "'[]');" //bannedUserList
                ).executeUpdate();
            } catch (JsonProcessingException exception) {
                throw new RuntimeException(exception);
            }
        });

        return findOneByTag(tag);
    }

    @Override
    public HallEntity update(HallEntity target) {
        HallEntity hallEntity = findOneByTag(target.getTag());

        entityManagerRepoHelper.runInTransaction(() -> {
            try {
                entityManagerRepoHelper.entityManager().createNativeQuery(
                        "update hallentity set" +
                                " tag = " + target.getTag() +
                                ", visibility = '" + target.getVisibility().name() + "'" +
                                ", displayname = '" + target.getDisplayName() + "'" +
                                ", description = '" + target.getDescription() + "'" +
                                ", size = " + target.getSize() +
                                ", userlist = '" + objectMapper.writeValueAsString(target.getUserList()) + "'" +
                                ", banneduserlist = '" + objectMapper.writeValueAsString(target.getBannedUserList()) + "'" +
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
        hallEntity.setBannedUserList(target.getBannedUserList());

        return hallEntity;
    }

    @Override
    public void delete(HallEntity hallEntity) {
        entityManagerRepoHelper.runInTransaction(() -> entityManagerRepoHelper.entityManager().createNativeQuery(
                "delete from hallentity where tag = " + hallEntity.getTag() + ";"
        ).executeUpdate());
    }

    @Override
    public HallEntity findOneByTag(Long tag) {
        CriteriaBuilder criteriaBuilder = entityManagerRepoHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<HallEntity> criteriaQuery = criteriaBuilder.createQuery(HallEntity.class);
        HallEntity hallEntity;

        //if table is empty hibernate throwing NoResultException instead of return null
        try {
            hallEntity = entityManagerRepoHelper.entityManager()
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

}
