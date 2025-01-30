package network.tcp.v4;

import network.tcp.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static network.tcp.SocketCloseUtil.*;
import static util.MyLogger.log;

public class ClientV4 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");

        // finally 블록에서 변수에 접근해야 한다. 따라서 try 블록에서 선언 불가.
        Socket socket = null;
        DataInputStream input = null;
        DataOutputStream output = null;

        try {
            socket = new Socket("localhost", PORT);
            input = new DataInputStream(socket.getInputStream()); // OS 수신 버퍼에 쌓인 데이터를 읽음
            output = new DataOutputStream(socket.getOutputStream()); // OS 송신 버퍼에 데이터를 씀
            log("소켓 연결: " + socket);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("전송 문자: ");
                String toSend = scanner.nextLine(); // 데이터를 읽을 때까지 BLOCKING

                // 서버에게 문자 보내기
                output.writeUTF(toSend);
                log("client -> server: " + toSend);

                if (toSend.equals("exit")) {
                    break;
                }

                // 서버로부터 문자 받기
                String received = input.readUTF(); // 데이터를 읽을 때까지 BLOCKING
                log("client <- server: " + received);
            }
        } catch (IOException e) {
            log(e);
        } finally {
            closeAll(socket, input, output); // 자원 정리
            log("연결 종료: " + socket);
        }
    }

}
