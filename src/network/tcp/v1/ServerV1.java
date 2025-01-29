package network.tcp.v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV1 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        /**
         * - ServerSocket: 클라이언트가 서버에 접속할 수 있게끔 TCP 연결 제공 및 접속 정보를 OS backlog queue에 저장
         * - Socket: 실제로 클라이언트와 통신하는 건 이 소켓 객체 (클라이언트 소켓 객체 - 서버 소켓 객체 간 통신)
         */
        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(PORT); // 12345번 포트 열어놓음 (ServerSocket은 TCP 연결을 지원하는 특별한 소켓)
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        /**
         * - serverSocket.accept(): OS backlog queue에서 TCP 연결 정보를 조회 후, Socket 객체 생성 (사용한 TCP 정보는 OS backlog queue에서 제거)
         */
        Socket socket = serverSocket.accept(); // 12345번 포트에 클라이언트가 접속하면, 그걸 갖고 socket을 만듦 -> 이 socket으로 클라이언트와 통신
        log("소켓 연결: " + socket);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        // 클라이언트로부터 문자 받기
        String received = input.readUTF();
        log("client -> server: " + received);

        // 클라이언트에게 문자 보내기
        String toSend = received + " World";
        output.writeUTF(toSend);
        log("client <- server: " + toSend);

        // 자원 정리
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
    }
}
