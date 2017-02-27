package com.github.doubledeath.hop.api.db.repo.impl;

import com.github.doubledeath.hop.api.db.entity.TagEntity;
import com.github.doubledeath.hop.api.db.repo.TagRepo;
import org.jetbrains.annotations.NotNull;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by doubledeath on 2/27/17.
 */
@SuppressWarnings("unused")
@Stateless
public class EmPostgresTagRepo implements TagRepo {

    private static final Long ID = 1L;
    private static final Long INIT_VALUE = 1L;
    private static final Long STEP_VALUE = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    @NotNull
    @Override
    public TagEntity update() {
        String[] columnArray = {"id", "value"};
        Object[] valueArray = {
                ID,
                INIT_VALUE
        };
        String columns = Arrays.stream(columnArray)
                .collect(Collectors.joining(", "));
        String values = Arrays.stream(valueArray)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        entityManager.createNativeQuery(
                "insert into tagentity (" + columns + ") " +
                        "values (" + values + ") " +
                        "on conflict (id) do update set value = tagentity.value + " + STEP_VALUE
        ).executeUpdate();

        return entityManager.find(TagEntity.class, ID);
    }

}
