package network.problem;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SessionManager {

    private static List<Session> sessions = new CopyOnWriteArrayList<>();

    public void add(Session session) {
        sessions.add(session);
    }

    public void remove(Session session) {
        sessions.remove(session);
    }

    public void closeAll() {
        for (Session session : sessions) {
            session.close();
        }
        sessions.clear();
    }

    public void sendAll(String message) {
        for (Session session : sessions) {
            session.send(message);
        }
    }

    public List<String> getUserNames() {
        return sessions.stream().map(Session::getName).toList();
    }
}
