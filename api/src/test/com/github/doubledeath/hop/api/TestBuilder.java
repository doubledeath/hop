package com.github.doubledeath.hop.api;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.undertow.WARArchive;

/**
 * Created by doubledeath on 2/27/17.
 */
public class TestBuilder {

    public static Archive buildDeployment() {
        return ShrinkWrap.create(WARArchive.class)
                .addPackages(true, App.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("project-defaults.yml");
    }

}
