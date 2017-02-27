package com.github.doubledeath.hop.api3.database.repo.impl;

import com.github.doubledeath.hop.api3.database.entity.SimpleValueTagEntity;
import com.github.doubledeath.hop.api3.database.repo.SimpleValueTagRepo;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Created by doubledeath on 2/22/17.
 */
@SuppressWarnings("unused")
@Dependent
public class EntityManagerSimpleValueTagRepo implements SimpleValueTagRepo {

    private static final Long INIT_VALUE = 1L;

    @Inject
    private EntityManagerRepoHelper entityManagerRepoHelper;

    @NotNull
    @Override
    public SimpleValueTagEntity update() {
        CriteriaBuilder criteriaBuilder = entityManagerRepoHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<SimpleValueTagEntity> criteriaQuery = criteriaBuilder.createQuery(SimpleValueTagEntity.class);
        SimpleValueTagEntity initSimpleValueTagEntity;

        //if table is empty hibernate throwing NoResultException instead of return null
        try {
            initSimpleValueTagEntity = entityManagerRepoHelper.entityManager()
                    .createQuery(criteriaQuery.select(criteriaQuery.from(SimpleValueTagEntity.class)))
                    .getSingleResult();
        } catch (NoResultException exception) {
            initSimpleValueTagEntity = null;
        }

        if (initSimpleValueTagEntity == null) {
            initSimpleValueTagEntity = new SimpleValueTagEntity();
            initSimpleValueTagEntity.setValue(INIT_VALUE);
        } else {
            initSimpleValueTagEntity.setValue(initSimpleValueTagEntity.getValue() + 1);
        }

        SimpleValueTagEntity resultSimpleValueTagEntity = initSimpleValueTagEntity;

        entityManagerRepoHelper.runInTransaction(() -> entityManagerRepoHelper.entityManager().persist(resultSimpleValueTagEntity));

        return resultSimpleValueTagEntity;
    }

}
