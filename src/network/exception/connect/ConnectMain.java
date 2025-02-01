package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectMain {

    public static void main(String[] args) throws IOException {
        unKnownHostEx1();
        unKnownHostEx2();
        connectionRefused();
    }

    private static void unKnownHostEx1() throws IOException {
        try {
            // 이러한 IP 대역은 없으므로 UnknownException 발생 (호스트를 알 수 없음)
            Socket socket = new Socket("999.999.999.999", 80);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void unKnownHostEx2() throws IOException {
        try {
            // 이러한 도메인은 없으므로 UnknownException 발생 (호스트를 알 수 없음)
            Socket socket = new Socket("google.gogo", 80);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void connectionRefused() throws IOException {
        try {
            // 이러한 포트는 없으므로 ConnectException 발생 (클라이언트에서 연결 시도 시, 서버 측 OS에서 RST(Reset) 패킷을 날려서 연결 거절)
            Socket socket = new Socket("localhost", 45678);
        } catch (ConnectException e) {
            e.printStackTrace();
        }
    }
}
