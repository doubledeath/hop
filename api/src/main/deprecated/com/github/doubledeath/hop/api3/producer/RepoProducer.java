package com.github.doubledeath.hop.api3.producer;

import com.github.doubledeath.hop.api3.database.repo.impl.EntityManagerRepoHelper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

/**
 * Created by doubledeath on 2/22/17.
 */
@SuppressWarnings("unused")
@Dependent
public class RepoProducer {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Produces
    @Singleton
    @ApplicationScoped
    public EntityManagerFactory entityManagerFactory() {
        return entityManagerFactory;
    }

    @Produces
    public EntityManagerRepoHelper entityManagerRepoHelper(EntityManagerFactory entityManagerFactory, UserTransaction userTransaction) {
        return new EntityManagerRepoHelper(entityManagerFactory, userTransaction);
    }

}
