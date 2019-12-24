package Example.Broadcast;

import Example.Main;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

@WebSocket
public class Broadcast {
    @OnWebSocketConnect
    public void connected(Session session) {
        Main.sessions.add(session);
        System.out.println("Se ha conectado un nuevo usuario con ip: " + session.getRemoteAddress());
    }

    @OnWebSocketClose
    public void closed(Session session, int statusCode, String reason) {
        Main.sessions.remove(session);
        System.out.println("Se ha desconectado el usuario con ip: " + session.getRemoteAddress());
    }

    @OnWebSocketMessage
    public void message(Session session, String message) throws IOException {
        System.out.println("IP Usuario: " + session.getRemoteAddress() + " Mensaje: " + message);
        for (Session aux : Main.sessions) {
            aux.getRemote().sendString((aux.equals(session))?("Me: " + message):(aux.getRemoteAddress() + ": " + message));
        }
    }
}
