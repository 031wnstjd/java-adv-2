package chat.server.command;

import chat.server.Session;
import chat.server.SessionManager;

/**
 * 커맨드 패턴 내 역할 - ConcreteCommand(구체적인 명령 클래스) [명령을 수행하는 객체]
 */
public class ChangeCommand implements Command {

    private final SessionManager sessionManager;

    public ChangeCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) {
        String changeName = args[1];
        sessionManager.sendAll(session.getUsername() + "님이 " + changeName + "로 이름을 변경했습니다.");
        session.setUsername(changeName);
    }
}
