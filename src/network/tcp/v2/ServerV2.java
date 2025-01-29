package network.tcp.v2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV2 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        /**
         * [소켓 주요 특징]
         * - 소켓 객체 없이 서버 소켓만으로도 TCP 연결은 완료된다.
         * - 소켓 객체 없이도 클라이언트 측에서 OutputStream으로 데이터 송신은 가능하다. (서버 측 OS 수신 버퍼에 쌓임)
         * - 하지만, 연결 이후에 서로 메시지를 주고 받으려면(양방향) 소켓 객체가 필요하다. (서버 측 OS 수신 버퍼를 읽기 위해선 소켓 객체 필요)
         */
        // 12345번 포트 열어놓음 (ServerSocket은 TCP 연결을 지원하는 특별한 소켓)
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        // 이미 연결된 TCP 연결 정보(OS backlog queue)를 기반으로 소켓 객체 생성 => 이 소켓 객체가 있어야 양방향 통신 가능
        Socket socket = serverSocket.accept(); // BLOCKING
        log("소켓 연결: " + socket);

        DataInputStream input = new DataInputStream(socket.getInputStream()); // OS 수신 버퍼에 쌓인 데이터를 읽음
        DataOutputStream output = new DataOutputStream(socket.getOutputStream()); // OS 송신 버퍼에 데이터를 씀

        while (true) {
            // 클라이언트로부터 문자 받기
            String received = input.readUTF(); // BLOCKING
            log("client -> server: " + received);

            if (received.equals("exit")) {
                break;
            }

            // 클라이언트에게 문자 보내기
            String toSend = received + " World";
            output.writeUTF(toSend);
            log("client <- server: " + toSend);
        }

        // 자원 정리
        log("연결 종료: " + socket);
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
    }
}
