package chat;

import java.io.DataInputStream;
import java.io.IOException;

import static util.MyLogger.log;

public class ReadHandler implements Runnable {

    private final DataInputStream input;
    private final Client client; // 예외 발생 시 클라이언트 자원 정리를 위해 참조

    public boolean closed = false;

    public ReadHandler(DataInputStream input, Client client) {
        this.input = input;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String received = input.readUTF();
                System.out.println(received); // 서버로부터 받은 메시지 콘솔 출력
            }
        } catch (IOException e) {
            log(e);
        } finally {
            client.close(); // 클라이언트 자원 정리
        }
    }

    public synchronized void close() {
        if (closed) {
            return;
        }
        // 종료 로직 필요시 작성
        closed = true;
        log("readHandler 종료");
    }
}
