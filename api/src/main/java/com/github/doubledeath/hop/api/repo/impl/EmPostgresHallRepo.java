package com.github.doubledeath.hop.api.repo.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.doubledeath.hop.api.model.Hall;
import com.github.doubledeath.hop.api.repo.HallRepo;
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
    public Hall create(@NotNull Long tag, @NotNull Long owner, @NotNull Long size, @NotNull String displayName) {
        try {
            String[] columnArray = {
                    "tag",
                    "owner",
                    "size",
                    "displayname",
                    "userlist"
            };
            Object[] valueArray = {
                    tag,
                    owner,
                    size,
                    quote(displayName),
                    quote(objectMapper.writeValueAsString(Collections.singleton(owner)))
            };
            String columns = Arrays.stream(columnArray)
                    .collect(Collectors.joining(", "));
            String values = Arrays.stream(valueArray)
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));

            entityManager.createNativeQuery(
                    "insert into hall (" + columns + ") values (" + values + ");"
            ).executeUpdate();
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }

        Hall hall = findByTagImpl(tag);

        if (hall == null) {
            throw new RuntimeException();
        }

        return hall;
    }

    @Nullable
    @Override
    public Hall findByTag(@NotNull Long tag) {
        return findByTagImpl(tag);
    }

    @NotNull
    @Override
    public Hall update(@NotNull Hall hall) {
        try {
            String[] columnArray = {
                    "tag",
                    "owner",
                    "size",
                    "displayname",
                    "key",
                    "description",
                    "userlist",
                    "userbanlist"
            };
            Object[] valueArray = {
                    hall.getTag(),
                    hall.getOwner(),
                    hall.getSize(),
                    quote(hall.getDisplayName()),
                    quote(hall.getKey()),
                    quote(hall.getDescription()),
                    quote(objectMapper.writeValueAsString(hall.getUserList())),
                    quote(objectMapper.writeValueAsString(hall.getUserBanlist()))
            };
            String set = IntStream.range(0, columnArray.length)
                    .mapToObj(i -> columnArray[i] + " = " + valueArray[i])
                    .collect(Collectors.joining(", "));

            entityManager.createNativeQuery(
                    "update hall set " + set + " where id = " + hall.getId() + ";"
            ).executeUpdate();
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }

        return entityManager.find(Hall.class, hall.getId());
    }

    @Override
    public void delete(@NotNull Long id) {
        Hall hall = entityManager.find(Hall.class, id);

        if (hall != null) {
            entityManager.remove(hall);
        }
    }

    @Nullable
    private Hall findByTagImpl(@NotNull Long tag) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Hall> criteriaQuery = criteriaBuilder.createQuery(Hall.class);
        Hall hall;

        try {
            hall = entityManager
                    .createQuery(criteriaQuery
                            .select(criteriaQuery.from(Hall.class))
                            .where(criteriaBuilder.equal(criteriaQuery
                                    .from(Hall.class)
                                    .get("tag"), tag)))
                    .getSingleResult();
        } catch (NoResultException exception) {
            hall = null;
        }

        return hall;
    }

    @Nullable
    private String quote(@Nullable String target) {
        return target == null ? null : "'" + target + "'";
    }

}
