package com.github.doubledeath.hop.api.db.repo;

import com.github.doubledeath.hop.api.TestBuilder;
import com.github.doubledeath.hop.api.db.entity.HallEntity;
import com.github.doubledeath.hop.api.db.entity.TagEntity;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

/**
 * Created by doubledeath on 2/27/17.
 */
@SuppressWarnings("unused")
@RunWith(Arquillian.class)
public class HallRepoTest {

    private static final Long INIT_TAG = 1L;
    private static final Long NEW_TAG = 2L;

    @EJB
    private HallRepo hallRepo;

    @Deployment
    public static Archive createDeployment() {
        return TestBuilder.buildDeployment();
    }

    @Test
    @InSequence(1)
    public void create() {
        Assert.assertTrue(hallRepo.create(INIT_TAG, 1L, 1L, "test").getTag().equals(INIT_TAG));
    }

    @Test
    @InSequence(2)
    public void findByTag() {
        Assert.assertFalse(hallRepo.findByTag(INIT_TAG) == null);
        Assert.assertTrue(hallRepo.findByTag(NEW_TAG) == null);
    }

    @Test
    @InSequence(3)
    public void update() {
        HallEntity hallEntity = hallRepo.findByTag(INIT_TAG);

        Assert.assertFalse(hallEntity == null);

        hallEntity.setTag(NEW_TAG);

        hallRepo.update(hallEntity);

        Assert.assertFalse(hallRepo.findByTag(NEW_TAG) == null);
    }

    @Test
    @InSequence(4)
    public void delete() {
        hallRepo.delete(NEW_TAG);

        Assert.assertTrue(hallRepo.findByTag(NEW_TAG) == null);
    }

}
