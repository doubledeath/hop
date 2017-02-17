package com.github.doubledeath.hop.api.producer;

import com.github.doubledeath.hop.api.database.repo.TagRepo;
import com.github.doubledeath.hop.api.database.repo.impl.EntityManagerRepoHelper;
import com.github.doubledeath.hop.api.database.repo.impl.EntityManagerSimpleTagRepo;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

/**
 * Created by doubledeath on 2/16/17.
 */
@SuppressWarnings("unused")
public class RepoProducer {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Produces
    @Singleton
    public EntityManagerFactory entityManagerFactory() {
        return entityManagerFactory;
    }

    @Produces
    public EntityManagerRepoHelper entityManagerRepoHelper(EntityManagerFactory entityManagerFactory, UserTransaction userTransaction) {
        return new EntityManagerRepoHelper(entityManagerFactory, userTransaction);
    }

    @Produces
    public TagRepo entityManagerSimpleTagRepo(EntityManagerRepoHelper entityManagerRepoHelper) {
        return new EntityManagerSimpleTagRepo(entityManagerRepoHelper);
    }

}
