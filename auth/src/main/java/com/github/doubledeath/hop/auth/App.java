package com.github.doubledeath.hop.auth;

import org.wildfly.swarm.Swarm;

/**
 * Created by doubledeath on 2/10/17.
 */
public class App {

    protected App() {
    }

    public static void main(String... args) throws Exception {
        (new Swarm(args)).start();
    }

}
