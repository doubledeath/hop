package com.github.doubledeath.hop.api.db.repo;

import com.github.doubledeath.hop.api.TestBuilder;
import com.github.doubledeath.hop.api.db.entity.TagEntity;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
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
public class TagRepoTest {

    private static final Long ID = 1L;
    private static final Long INIT_VALUE = 1L;
    private static final Long STEP_VALUE = 1L;

    @EJB
    private TagRepo tagRepo;

    @Deployment
    public static Archive createDeployment() {
        return TestBuilder.buildDeployment();
    }

    @Test
    public void update() {
        TagEntity tagEntity = new TagEntity();

        tagEntity.setId(ID);
        tagEntity.setValue(INIT_VALUE);

        for (int i = 0; i < 3; i++) {
            Assert.assertTrue(tagEntity.equals(tagRepo.update()));

            tagEntity.setValue(tagEntity.getValue() + STEP_VALUE);
        }
    }

}
