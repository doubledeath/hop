package com.github.doubledeath.hop.api.repo;

import com.github.doubledeath.hop.api.TestBuilder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

/**
 * Created by doubledeath on 2/28/17.
 */
@SuppressWarnings("unused")
@RunWith(Arquillian.class)
public class HallRepoTest {

    @EJB
    private HallRepo hallRepo;

    @Deployment
    public static Archive createDeployment() {
        return TestBuilder.buildDeployment();
    }

    @Test
    public void create() {
//        System.out.println("id: " + hallRepo.create(1L, 1L, 1L, "test").getId());
    }

}
