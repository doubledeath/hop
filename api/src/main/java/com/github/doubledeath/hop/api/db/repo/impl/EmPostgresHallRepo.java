package com.github.doubledeath.hop.api.db.repo.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.doubledeath.hop.api.db.entity.HallEntity;
import com.github.doubledeath.hop.api.db.repo.HallRepo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by doubledeath on 2/27/17.
 */
@SuppressWarnings("unused")
@Stateless
public class EmPostgresHallRepo implements HallRepo {

    @PersistenceContext
    private EntityManager entityManager;
    private ObjectMapper objectMapper = new ObjectMapper();

    @NotNull
    @Override
    public HallEntity create(@NotNull Long tag, @NotNull Long owner, @NotNull Long size, @NotNull String displayName) {
        try {
            String[] columnArray = {"tag", "owner", "size", "displayname", "userlist"};
            Object[] valueArray = {
                    tag,
                    owner,
                    size,
                    "'" + displayName + "'",
                    "'" + objectMapper.writeValueAsString(Collections.singleton(owner)) + "'"
            };
            String columns = Arrays.stream(columnArray)
                    .collect(Collectors.joining(", "));
            String values = Arrays.stream(valueArray)
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));

            entityManager.createNativeQuery(
                    "insert into hallentity (" + columns + ") values (" + values + ");"
            ).executeUpdate();
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }

        HallEntity hallEntity = findByTagImpl(tag);

        if (hallEntity == null) {
            throw new RuntimeException();
        }

        return hallEntity;
    }

    @Nullable
    @Override
    public HallEntity findByTag(@NotNull Long tag) {
        return findByTagImpl(tag);
    }

    @NotNull
    @Override
    public HallEntity update(@NotNull HallEntity hallEntity) {
        try {
            String[] columnArray = {"tag", "owner", "size", "displayname", "key", "description", "userlist", "userbanlist"};
            Object[] valueArray = {
                    hallEntity.getTag(),
                    hallEntity.getOwner(),
                    hallEntity.getSize(),
                    "'" + hallEntity.getDisplayName() + "'",
                    "'" + hallEntity.getKey() + "'",
                    "'" + hallEntity.getDescription() + "'",
                    "'" + objectMapper.writeValueAsString(hallEntity.getUserList()) + "'",
                    "'" + objectMapper.writeValueAsString(hallEntity.getUserBanlist()) + "'"
            };
            String set = IntStream.range(0, columnArray.length)
                    .mapToObj(i -> columnArray[i] + " = " + valueArray[i])
                    .collect(Collectors.joining(", "));

            entityManager.createNativeQuery(
                    "update hallentity set " + set + " where id = " + hallEntity.getId() + ";"
            ).executeUpdate();
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }

        return entityManager.find(HallEntity.class, hallEntity.getId());
    }

    @Override
    public void delete(@NotNull Long tag) {
        HallEntity hallEntity = findByTagImpl(tag);

        if (hallEntity != null) {
            entityManager.remove(hallEntity);
        }
    }

    @Nullable
    private HallEntity findByTagImpl(@NotNull Long tag) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<HallEntity> criteriaQuery = criteriaBuilder.createQuery(HallEntity.class);
        HallEntity hallEntity;

        try {
            hallEntity = entityManager
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
