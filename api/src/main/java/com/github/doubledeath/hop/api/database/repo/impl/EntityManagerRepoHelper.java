package com.github.doubledeath.hop.api.database.repo.impl;

import javax.inject.Inject;
import javax.inject.Qualifier;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.*;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/16/17.
 */
@EntityManagerRepoHelper.Impl
public class EntityManagerRepoHelper {

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD})
    @interface Impl {

    }

    private EntityManager entityManager;
    private UserTransaction userTransaction;

    @Inject
    public EntityManagerRepoHelper(EntityManagerFactory entityManagerFactory, UserTransaction userTransaction) {
        this.entityManager = entityManagerFactory.createEntityManager();
        this.userTransaction = userTransaction;
    }

    EntityManager entityManager() {
        return entityManager;
    }

    void runInTransaction(Runnable runnable) {
        try {
            userTransaction.begin();

            runnable.run();

            userTransaction.commit();
        } catch (Exception exception) {
            try {
                userTransaction.rollback();
            } catch (SystemException systemException) {
                throw new RuntimeException(systemException);
            }

            throw new RuntimeException(exception);
        }
    }

}
