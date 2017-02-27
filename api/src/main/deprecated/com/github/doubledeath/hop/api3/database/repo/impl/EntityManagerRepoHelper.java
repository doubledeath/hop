package com.github.doubledeath.hop.api3.database.repo.impl;

import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Qualifier;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/22/17.
 */
public class EntityManagerRepoHelper {

    private EntityManager entityManager;
    private UserTransaction userTransaction;

    @Inject
    public EntityManagerRepoHelper(EntityManagerFactory entityManagerFactory, UserTransaction userTransaction) {
        this.entityManager = entityManagerFactory.createEntityManager();
        this.userTransaction = userTransaction;
    }

    @NotNull
    EntityManager entityManager() {
        return entityManager;
    }

    void runInTransaction(@NotNull Runnable runnable) {
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
