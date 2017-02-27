package com.github.doubledeath.hop.api;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.undertow.WARArchive;

/**
 * Created by doubledeath on 2/27/17.
 */
public class TestBuilder {

    private static final String PERSISTENCE_XML = "META-INF/persistence.xml";
    private static final String PROJECT_DEFAULTS_YML = "project-defaults.yml";

    public static Archive buildDeployment() {
        return ShrinkWrap.create(WARArchive.class)
                .addPackages(true, App.class.getPackage())
                .addAsResource(PERSISTENCE_XML)
                .addAsResource(PROJECT_DEFAULTS_YML);
    }

}
