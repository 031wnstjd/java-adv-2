package network.problem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class Session implements Runnable {

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManager sessionManager;
    private volatile String name;
    private boolean closed = false;

    public Session(Socket socket, SessionManager sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        this.sessionManager.add(this);
        this.name = null;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String read = input.readUTF();
                log("read = " + read);
                String[] split = read.split("\\|");
                String command = split[0];
                switch (command) {
                    case "/join":
                        name = split[1]; // 사용자 이름 할당
                        log("[채팅 입장] " + name);
                        sessionManager.sendAll(name + "님이 채팅에 입장하였습니다.");
                        break;
                    case "/message":
                        String message = name + ": " + split[1];
                        log("[메시지 전송] " + message);
                        sessionManager.sendAll(message); // 모든 사용자에게 메시지 전송
                        break;
                    case "/change":
                        String changeName = split[1];
                        log("[사용자 이름 변경] " + name + " -> " + changeName);
                        name = changeName;
                        break;
                    case "/users":
                        String userNamesString = String.join(",", sessionManager.getUserNames());
                        send(userNamesString);
                        log("[사용자 목록 조회] " + userNamesString);
                        break;
                    case "/exit":
                        sessionManager.sendAll(name + "님이 채팅에서 나갔습니다.");
                        return;
                }
            }
        } catch (IOException e) {
            log(e);
        } finally {
            sessionManager.remove(this);
            close();
        }
    }

    public synchronized void close() {
        if (closed) {
            return;
        }

        closeAll(socket, input, output);
        closed = true;
        log("연결 종료: " + socket);
    }

    public void send(String message) {
        try {
            output.writeUTF(message); // 클라이언트에게 메시지 전송
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name;
    }
}
