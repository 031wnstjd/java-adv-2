package network.exception.close.reset;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import static util.MyLogger.log;

public class ResetCloseClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결: " + socket);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // client <- server: FIN
        Thread.sleep(1000); // 서버가 close() 호출할 때까지 잠시 대기

        // client -> server: PUSH[1] (FIN을 보내야하지만, 그냥 메시지를 보냄 -> TCP/IP 규약에서 벗어남)
        output.write(1);

        // client <- server: RST (TCP/IP 규약에 어긋난 행위가 발생했으므로, 서버 측에서 RST를 보내 TCP 연결 강제 종료)
        Thread.sleep(1000); // RST 메시지 전송 대기

        try {
            /**
             * [RST 패킷을 받은 이후에 read() 호출 시]
             * - MacOS: java.net.SocketException: Connection reset (이미 연결 종료 됐다는 의미)
             * - Windows: java.net.SocketException: 현재 연결은 사용자의 호스트 시스템의 소프트웨어의 의해 중단되었습니다
             */
            int read = input.read();
            System.out.println("read = " + read);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            /**
             * [RST 패킷을 받은 이후에 write() 호출 시]
             * - MacOS: java.net.SocketException: Broken pipe (이미 연결 종료 됐다는 의미)
             * - Windows: java.net.SocketException: 현재 연결은 사용자의 호스트 시스템의 소프트웨어의 의해 중단되었습니다
             */
            output.write(1);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
