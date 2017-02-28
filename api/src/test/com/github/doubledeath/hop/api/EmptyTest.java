package com.github.doubledeath.hop.api;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by doubledeath on 2/28/17.
 */
@SuppressWarnings("unused")
@RunWith(Arquillian.class)
public class EmptyTest {

    @Deployment
    public static Archive createDeployment() {
        return TestBuilder.buildDeployment();
    }

    @Test
    public void nothing() {

    }

}
