package network.problem;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

public class Client {

    private static final int PORT = 12345;
    private static boolean isLogin = false;

    public static void main(String[] args) throws IOException {

        log("클라이언트 시작");
        log("/join|{name} | /message|{content} | /change|{name} | /users | /exit");

        try (Socket socket = new Socket("localhost", PORT);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

            log("소켓 연결: " + socket);

            Scanner sc = new Scanner(System.in);
            while (true) {
                String sendData = sc.nextLine();
                String[] split = sendData.split("\\|");
                String command = split[0];

                switch (command) {
                    case "/join":
                        if (isLogin) {
                            log("이미 로그인 된 상태입니다.");
                            break;
                        }

                        Thread readerThread = new Thread(new ReadHandler(input), "readThread"); // 메시지 읽기용 스레드 생성
                        readerThread.start();

                        output.writeUTF(sendData); // 사용자 이름 데이터 전달

                        isLogin = true; // 로그인 상태로 변경
                        log("채팅 입장 완료");

                        break;
                    case "/message":
                        if (!isLogin) {
                            log("로그인이 필요합니다");
                            break;
                        }

                        output.writeUTF(sendData); // 모든 사용자에게 메시지 전달
                        log("메시지 전송 완료");

                        break;
                    case "/change":
                        if (!isLogin) {
                            log("로그인이 필요합니다");
                            break;
                        }

                        output.writeUTF(sendData); // 이름 변경
                        log("이름 변경 완료");

                        break;
                    case "/users":
                        if (!isLogin) {
                            log("로그인이 필요합니다");
                            break;
                        }

                        output.writeUTF(sendData); // 사용자 목록 조회
                        log("사용자 목록 조회 완료");

                        break;
                    case "/exit":
                        output.writeUTF(sendData); // 연결 종료
                        log("연결 종료: " + socket);
                        return;
                    default:
                        log("유효하지 않은 명령어입니다.");
                        break;
                }
            }
        }
    }
}
