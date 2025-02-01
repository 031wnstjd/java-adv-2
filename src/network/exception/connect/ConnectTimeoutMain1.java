package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class ConnectTimeoutMain1 {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        try {
            Socket socket = new Socket("192.168.1.250", 45678); // IP, PORT 정보가 있으므로 객체 생성시 바로 TCP 연결 시도
        } catch (ConnectException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("end = " + (end - start));
    }
}
