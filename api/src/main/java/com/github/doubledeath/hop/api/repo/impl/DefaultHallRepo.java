package com.github.doubledeath.hop.api.repo.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.doubledeath.hop.api.model.Hall;
import com.github.doubledeath.hop.api.repo.HallRepo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by doubledeath on 3/5/17.
 */
@SuppressWarnings("unused")
@Stateless
public class DefaultHallRepo implements HallRepo {

    @PersistenceContext
    private EntityManager entityManager;
    private ObjectMapper objectMapper = new ObjectMapper();

    @TransactionAttribute
    @NotNull
    @Override
    public Hall create(@NotNull String tag, @NotNull String owner) {
        String[] columnArray = {
                "tag",
                "owner",
                "pendingUserList",
                "enteredUserList"
        };
        String[] valueArray = {
                tag,
                owner,
                "[]",
                "[" + owner + "]"
        };
        String columns = Arrays
                .stream(columnArray)
                .collect(Collectors.joining(", "));
        String values = Arrays
                .stream(valueArray)
                .map(this::quote)
                .collect(Collectors.joining(", "));

        entityManager
                .createNativeQuery(
                        "insert into Hall (" + columns + ") values (" + values + ")")
                .executeUpdate();

        Hall hall = findByTag(tag);

        //never happens
        if (hall == null) {
            throw new RuntimeException();
        }

        return hall;
    }

    @TransactionAttribute
    @Nullable
    @Override
    public Hall findByTag(@NotNull String tag) {
        return findByTagImpl(tag);
    }

    @TransactionAttribute
    @NotNull
    @Override
    public Hall update(@NotNull Hall hall) {
        String[] columnArray;
        String[] valueArray;

        try {
            columnArray = new String[] {
                    "tag",
                    "owner",
                    "description",
                    "pendingUserList",
                    "enteredUserList"
            };
            valueArray = new String[] {
                    hall.getTag(),
                    hall.getOwner(),
                    hall.getDescription(),
                    objectMapper.writeValueAsString(hall.getPendingUserList()),
                    objectMapper.writeValueAsString(hall.getEnteredUserList())
            };
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }

        String set = IntStream.range(0, columnArray.length)
                .mapToObj(i -> columnArray[i] + " = " + quote(valueArray[i]))
                .collect(Collectors.joining(", "));

        entityManager
                .createNativeQuery(
                    "update hallentity set " + set + " where tag = " + hall.getId())
                .executeUpdate();

        return hall;
    }

    @TransactionAttribute
    @Override
    public void delete(@NotNull Hall hall) {
        entityManager
                .createNativeQuery(
                        "delete from Hall where id = " + hall.getId())
                .executeUpdate();
    }

    @Nullable
    private Hall findByTagImpl(@NotNull String tag) {
        Hall hall;

        try {
            hall = (Hall) entityManager
                    .createNativeQuery(
                            "select * from Hall where tag = " + quote(tag), Hall.class)
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
