package chat.server.command;

import chat.server.Session;
import chat.server.SessionManager;

/**
 * 커맨드 패턴 내 역할 - ConcreteCommand(구체적인 명령 클래스) [명령을 수행하는 객체]
 */
public class JoinCommand implements Command {

    private final SessionManager sessionManager;

    public JoinCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) {
        String username = args[1];
        session.setUsername(username);
        sessionManager.sendAll(username + "님이 입장했습니다.");
    }
}
