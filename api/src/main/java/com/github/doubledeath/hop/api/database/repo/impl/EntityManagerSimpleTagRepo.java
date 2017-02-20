package com.github.doubledeath.hop.api.database.repo.impl;

import com.github.doubledeath.hop.api.database.entity.SimpleTagEntity;
import com.github.doubledeath.hop.api.database.repo.TagRepo;

import javax.inject.Inject;
import javax.inject.Qualifier;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/16/17.
 */
@EntityManagerSimpleTagRepo.Impl
public class EntityManagerSimpleTagRepo implements TagRepo {

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD})
    @interface Impl {

    }

    private static final Long INIT_VALUE = 1L;

    private EntityManagerRepoHelper entityManagerRepoHelper;

    @Inject
    public EntityManagerSimpleTagRepo(EntityManagerRepoHelper entityManagerRepoHelper) {
        this.entityManagerRepoHelper = entityManagerRepoHelper;
    }

    @Override
    public Long create() {
        CriteriaBuilder criteriaBuilder = entityManagerRepoHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<SimpleTagEntity> criteriaQuery = criteriaBuilder.createQuery(SimpleTagEntity.class);
        SimpleTagEntity initSimpleTagEntity;

        //if table is empty hibernate throwing NoResultException instead of return null
        try {
            initSimpleTagEntity = entityManagerRepoHelper.entityManager()
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

        entityManagerRepoHelper.runInTransaction(() -> entityManagerRepoHelper.entityManager().persist(resultSimpleTagEntity));

        return resultSimpleTagEntity.getValue();
    }

}
