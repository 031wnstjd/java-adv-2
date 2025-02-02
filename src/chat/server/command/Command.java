package chat.server.command;

import chat.server.Session;

import java.io.IOException;

/**
 * 커맨드 패턴 내 역할 - Command(명령 객체) [실행할 동작을 캡슐화한 인터페이스 또는 추상 클래스]
 */
public interface Command {
    void execute(String[] args, Session session) throws IOException;
}
