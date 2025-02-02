package chat.server.command;

import chat.server.Session;

import java.io.IOException;

/**
 * 커맨드 패턴 내 역할 - ConcreteCommand(구체적인 명령 클래스) [명령을 수행하는 객체]
 */
public class ExitCommand implements Command {

    @Override
    public void execute(String[] args, Session session) throws IOException {
        throw new IOException("exit");
    }
}
