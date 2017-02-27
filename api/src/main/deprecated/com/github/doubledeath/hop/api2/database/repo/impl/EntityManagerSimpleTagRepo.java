package com.github.doubledeath.hop.api2.database.repo.impl;

import com.github.doubledeath.hop.api2.database.entity.SimpleTagEntity;
import com.github.doubledeath.hop.api2.database.repo.SimpleTagRepo;

import javax.inject.Inject;
import javax.inject.Qualifier;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/20/17.
 */
@EntityManagerSimpleTagRepo.Impl
public class EntityManagerSimpleTagRepo implements SimpleTagRepo {

    private static final Long INIT_VALUE = 1L;

    private EntityManagerRepoHelper helper;

    @Inject
    public EntityManagerSimpleTagRepo(EntityManagerRepoHelper helper) {
        this.helper = helper;
    }

    @Override
    public SimpleTagEntity update() {
        CriteriaBuilder criteriaBuilder = helper.entityManager().getCriteriaBuilder();
        CriteriaQuery<SimpleTagEntity> criteriaQuery = criteriaBuilder.createQuery(SimpleTagEntity.class);
        SimpleTagEntity initSimpleTagEntity;

        //if table is empty hibernate throwing NoResultException instead of return null
        try {
            initSimpleTagEntity = helper.entityManager()
                    .createQuery(criteriaQuery.select(criteriaQuery.from(SimpleTagEntity.class)))
                    .getSingleResult();
        } catch (NoResultException exception) {
            initSimpleTagEntity = null;
        }

        if (initSimpleTagEntity == null) {
            initSimpleTagEntity = new SimpleTagEntity();
            initSimpleTagEntity.setValue(INIT_VALUE);
        } else {
            initSimpleTagEntity.setValue(initSimpleTagEntity.getValue() + 1);
        }

        SimpleTagEntity resultSimpleTagEntity = initSimpleTagEntity;

        helper.runInTransaction(() -> helper.entityManager().persist(resultSimpleTagEntity));

        return resultSimpleTagEntity;
    }

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    @interface Impl {

    }

}
