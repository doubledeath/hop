package com.github.doubledeath.hop.api.endpoint;

import com.github.doubledeath.hop.api.service.HallService;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by doubledeath on 3/7/17.
 */
@SuppressWarnings("unused")
@ServerEndpoint("/test")
public class TestEndpoint {

    @Inject
    private HallService hallService;
    @Context
    private SecurityContext securityContext;
//    private Set<Session> sessionSet = Collections.synchronizedSet();

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println(message);
//        System.out.println(session == null);

        if (message.equals("1")) {
            throw new RuntimeException("lolchto?");
        }

//        session.getBasicRemote()
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("err " + throwable.getMessage());
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("open " + (hallService == null));
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("close");
    }

}
