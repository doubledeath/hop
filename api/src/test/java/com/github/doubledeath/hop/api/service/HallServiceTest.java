package com.github.doubledeath.hop.api.service;

import com.github.doubledeath.hop.api.TestBuilder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

/**
 * Created by doubledeath on 3/5/17.
 */
@SuppressWarnings("unused")
@RunWith(Arquillian.class)
public class HallServiceTest {

    @EJB
    private HallService hallService;

    @Deployment
    public static Archive createDeployment() {
        return TestBuilder.buildDeployment();
    }

    @Test
    public void test() {
        hallService.test();
    }

}
