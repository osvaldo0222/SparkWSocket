package Example;

import Example.Broadcast.Broadcast;
import org.eclipse.jetty.websocket.api.Session;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class Main {

    public static List<Session> sessions = new ArrayList<>();

    public static void main(String[] args) {
        //Static files
        staticFiles.location("/public");

        webSocket("/broadcast", Broadcast.class);
        //init(); // Needed if you don't define any HTTP routes after your WebSocket routes

        get("/", (request, response) -> {
            return "index.html";
        });
    }
}
