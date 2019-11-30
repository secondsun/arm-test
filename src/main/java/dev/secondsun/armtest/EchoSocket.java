package dev.secondsun.armtest;


import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint("/echo")
@ApplicationScoped
public class EchoSocket {

    Logger LOG = LoggerFactory.getLogger(EchoSocket.class);

    @OnOpen
    public void onOpen(Session session) {
        LOG.info("open");
    }

    @OnClose
    public void onClose(Session session) {
        LOG.info("close");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        LOG.error("error", throwable);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        LOG.info("message %s", message);

        session.getAsyncRemote().sendText(message);

        LOG.info("message %s", message);
    }

}