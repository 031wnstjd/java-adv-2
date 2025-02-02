package chat.server;

import chat.server.command.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 커맨드 패턴 내 역할 - Invoker(명령을 실행하는 객체) [명령을 호출하는 객체 (Command를 실행, 취소, 저장 등 관리)]
 */
public class CommandManagerV4 implements CommandManager {

    private static final String DELIMITER = "\\|";
    private final Map<String, Command> commands = new HashMap<>();
    private final Command defaultCommand = new DefaultCommand();

    public CommandManagerV4(SessionManager sessionManager) {
        commands.put("/join", new JoinCommand(sessionManager));
        commands.put("/message", new MessageCommand(sessionManager));
        commands.put("/change", new ChangeCommand(sessionManager));
        commands.put("/users", new UsersCommand(sessionManager));
        commands.put("/exit", new ExitCommand());
    }

    /**
     * [커맨드 패턴 활용]
     * - CommandManager가 Command만 알고, 실제 수행 객체(SessionManager)는 모름
     * => sessionManager 객체의 행위는 Command 내부에서 캡슐화되어 노출 X
     */
    @Override
    public void execute(String totalMessage, Session session) throws IOException {
        String[] args = totalMessage.split(DELIMITER);
        String key = args[0];

        // NullObject Pattern
        Command command = commands.getOrDefault(key, defaultCommand);
        command.execute(args, session);
    }
}
