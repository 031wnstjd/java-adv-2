package chat.server;

import java.io.IOException;

/**
 * 클라이언트에게 전달받은 메시지를 처리하는 인터페이스
 */
public interface CommandManager {
    void execute(String totalMessage, Session session) throws IOException;
}
