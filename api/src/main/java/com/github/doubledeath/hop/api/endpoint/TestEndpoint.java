package com.github.doubledeath.hop.api.endpoint;

import com.github.doubledeath.hop.api.service.HallService;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by doubledeath on 3/7/17.
 */
@ServerEndpoint("/test")
public class TestEndpoint {

    @Inject
    private HallService hallService;

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println(message);
        System.out.println(session == null);
    }

    @OnOpen
    public void onOpen() {
        System.out.println("open " + (hallService == null));
    }

    @OnClose
    public void onClose() {
        System.out.println("close");
    }

}
