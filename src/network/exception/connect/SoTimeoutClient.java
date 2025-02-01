package network.exception.connect;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SoTimeoutClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        InputStream input = socket.getInputStream();

        try {
            // 타임 아웃 시간 설정 -> 3초 후 SocketTimeoutException 발생 (설정 안 하면 무한 대기)
            // (실무에서 이 설정을 하지 않아서 장애가 발생하는 경우가 많음)
            socket.setSoTimeout(3000);
            int read = input.read();
            System.out.println("read = " + read);
        } catch (Exception e) {
            e.printStackTrace();
        }
        socket.close();
    }
}
