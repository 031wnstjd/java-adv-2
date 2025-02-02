package network.problem;

import java.io.DataInputStream;
import java.io.IOException;

import static network.tcp.SocketCloseUtil.close;
import static util.MyLogger.log;

public class ReadHandler implements Runnable {

    private final DataInputStream input;

    public ReadHandler(DataInputStream input) {
        this.input = input;
    }

    @Override
    public void run() {
        try {
            log("클라이언트 메시지 읽기 스레드 생성 완료");
            while (true) {
                String received = input.readUTF();
                log(received); // 수신 메시지 로깅
            }
        } catch (IOException e) {
            log(e);
        } finally {
            close(input);
        }
    }
}
