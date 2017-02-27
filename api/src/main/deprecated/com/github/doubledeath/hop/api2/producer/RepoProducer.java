package com.github.doubledeath.hop.api2.producer;

import com.github.doubledeath.hop.api2.database.repo.HallRepo;
import com.github.doubledeath.hop.api2.database.repo.SimpleTagRepo;
import com.github.doubledeath.hop.api2.database.repo.impl.EntityManagerPostgresHallRepo;
import com.github.doubledeath.hop.api2.database.repo.impl.EntityManagerRepoHelper;
import com.github.doubledeath.hop.api2.database.repo.impl.EntityManagerSimpleTagRepo;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

/**
 * Created by doubledeath on 2/20/17.
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
    public SimpleTagRepo entityManagerSimpleTagRepo(EntityManagerRepoHelper entityManagerRepoHelper) {
        return new EntityManagerSimpleTagRepo(entityManagerRepoHelper);
    }

    @Produces
    public HallRepo entityManagerPostgresHallRepo(EntityManagerRepoHelper entityManagerRepoHelper) {
        return new EntityManagerPostgresHallRepo(entityManagerRepoHelper);
    }

}
