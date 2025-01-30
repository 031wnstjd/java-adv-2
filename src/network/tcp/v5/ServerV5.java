package network.tcp.v5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV5 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        while (true) {
            Socket socket = serverSocket.accept(); // BLOCKING (클라이언트 연결 수락 - OS backlog queue에 있는 TCP 연결 정보를 바탕으로 Socket 객체 생성)
            log("소켓 연결: " + socket);

            SessionV5 session = new SessionV5(socket);
            Thread thread = new Thread(session); // 별도의 스레드에서 실행
            thread.start();
        }
    }
}
