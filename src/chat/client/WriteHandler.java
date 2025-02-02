package chat.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static util.MyLogger.log;

public class WriteHandler implements Runnable {

    private static final String DELIMITER = "|";

    private final DataOutputStream output;
    private final Client client; // 예외 발생 시 클라이언트 자원 정리를 위해 참조

    private boolean closed = false;

    public WriteHandler(DataOutputStream output, Client client) {
        this.output = output;
        this.client = client;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            String username = inputUsername(scanner);
            output.writeUTF("/join" + DELIMITER + username);

            while (true) {
                String toSend = scanner.nextLine(); // 블로킹
                if (toSend.isEmpty()) {
                    continue;
                }

                if (toSend.equals("/exit")) {
                    output.writeUTF(toSend);
                    break;
                }

                // "/"로 시작하면 명령어, 나머지는 일반 메시지
                if (toSend.startsWith("/")) {
                    output.writeUTF(toSend);
                } else {
                    output.writeUTF("/message" + DELIMITER + toSend);
                }
            }


        } catch (IOException | NoSuchElementException e) { // scanner가 닫히면 NoSuchElementException 발생
            log(e);
        } finally {
            client.close(); // 클라이언트 자원 정리
        }
    }

    private static String inputUsername(Scanner scanner) {
        System.out.println("이름을 입력하세요.");
        String username;
        do {
            username = scanner.nextLine();
        } while (username.isEmpty()); // user 이름이 비어 있으면 계속 입력 받음 (사용자의 입력을 최소 한 번은 받아야 하므로 do-while문 사용)
        return username;
    }

    public synchronized void close() {
        if (closed) {
            return;
        }
        try {
            System.in.close(); // Scanner 입력 중지 (사용자의 입력을 닫음) -> scanner.nextLine() 코드에서 대기하는 스레드에 NoSuchElementException 발생시킴
        } catch (IOException e) {
            log(e);
        }
        closed = true;
        log("writeHandler 종료");
    }
}
