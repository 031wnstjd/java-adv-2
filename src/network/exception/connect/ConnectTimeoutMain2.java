package network.exception.connect;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ConnectTimeoutMain2 {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        try {
            Socket socket = new Socket(); // IP, PORT 정보가 없으므로 TCP 연결 시도 X
            // socket.connect() -> TCP 연결 Timeout 설정 가능
            socket.connect(new InetSocketAddress("192.168.1.250", 45678), 1000); // 1초 대기, 응답 없으면 SocketTimeoutException 던짐
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("end = " + (end - start));
    }
}
