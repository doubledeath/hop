package com.github.doubledeath.hop.api3.database.repo.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.doubledeath.hop.api3.database.entity.HallEntity;
import com.github.doubledeath.hop.api3.database.repo.HallRepo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by doubledeath on 2/22/17.
 */
@SuppressWarnings("unused")
@Dependent
public class EntityManagerPostgresHallRepo implements HallRepo {

    @Inject
    private EntityManagerRepoHelper entityManagerRepoHelper;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Nullable
    @Override
    public HallEntity findOneByTag(@NotNull Long tag) {
        return findOneByTagImpl(tag);
    }

    @NotNull
    @Override
    public HallEntity create(@NotNull Long tag, @NotNull Long owner, @NotNull Long size, @NotNull String displayName) {
        entityManagerRepoHelper.runInTransaction(() -> {
            try {
                String[] columnsArray = {
                        "tag",
                        "owner",
                        "size",
                        "displayname",
                        "userlist",
                        "userbanlist"
                };
                String[] valuesArray = {
                        String.valueOf(tag),
                        String.valueOf(owner),
                        String.valueOf(size),
                        "'" + displayName + "'",
                        "'" + objectMapper.writeValueAsString(Collections.singleton(owner)) + "'",
                        "'[]'"
                };
                String columns = Arrays.stream(columnsArray)
                        .collect(Collectors.joining(", "));
                String values = Arrays.stream(valuesArray)
                        .collect(Collectors.joining(", "));

                entityManagerRepoHelper.entityManager().createNativeQuery(
                        "insert into hallentity (" + columns + ") values (" + values + ");"
                ).executeUpdate();
            } catch (JsonProcessingException exception) {
                throw new RuntimeException(exception);
            }
        });

        HallEntity resultHallEntity = findOneByTagImpl(tag);

        if (resultHallEntity == null) {
            throw new RuntimeException();
        }

        return resultHallEntity;
    }

    @NotNull
    @Override
    public HallEntity update(@NotNull HallEntity hallEntity) {
        entityManagerRepoHelper.runInTransaction(() -> {
            try {
                String[] columnsArray = {
                        "tag",
                        "owner",
                        "size",
                        "displayname",
                        "key",
                        "description",
                        "userlist",
                        "userbanlist"
                };
                String[] valuesArray = {
                        String.valueOf(hallEntity.getTag()),
                        String.valueOf(hallEntity.getOwner()),
                        String.valueOf(hallEntity.getSize()),
                        "'" + hallEntity.getDisplayName() + "'",
                        hallEntity.getKey() == null ? null : "'" + hallEntity.getKey() + "'",
                        hallEntity.getDescription() == null ? null : "'" + hallEntity.getDescription() + "'",
                        "'" + objectMapper.writeValueAsString(hallEntity.getUserList()) + "'",
                        "'" + objectMapper.writeValueAsString(hallEntity.getUserBanlist()) + "'"
                };
                String set = IntStream.range(0, columnsArray.length)
                        .mapToObj(i -> columnsArray[i] + " = " + valuesArray[i])
                        .collect(Collectors.joining(", "));

                entityManagerRepoHelper.entityManager().createNativeQuery(
                        "update hallentity set " + set + " where tag = " + String.valueOf(hallEntity.getTag()) + ";"
                ).executeUpdate();
            } catch (JsonProcessingException exception) {
                throw new RuntimeException(exception);
            }
        });

        HallEntity resultHallEntity = findOneByTagImpl(hallEntity.getTag());

        if (resultHallEntity == null) {
            throw new RuntimeException();
        }

        return resultHallEntity;
    }

    @Override
    public void delete(@NotNull HallEntity hallEntity) {
        entityManagerRepoHelper.runInTransaction(() -> entityManagerRepoHelper.entityManager().createNativeQuery(
                "delete from hallentity where tag = " + hallEntity.getTag() + ";"
        ).executeUpdate());
    }

    @Nullable
    private HallEntity findOneByTagImpl(@NotNull Long tag) {
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
