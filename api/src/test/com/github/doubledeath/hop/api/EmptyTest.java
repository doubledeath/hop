package com.github.doubledeath.hop.api;

import com.github.doubledeath.hop.api.ref.EndpointRef;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.undertow.WARArchive;

/**
 * Created by doubledeath on 2/27/17.
 */
@RunWith(Arquillian.class)
public class EmptyTest {

    @Deployment
    public static Archive createDeployment() {
        WARArchive war = ShrinkWrap.create(WARArchive.class)
                .addPackages(true, "com.github.doubledeath.hop.api")
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("project-defaults.yml");

        return war;
    }

    @Test
    public void impl() {
        Assert.assertTrue(EndpointRef.API.equals("/api"));
    }

}
