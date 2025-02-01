package network.exception.close.normal;

import java.io.*;
import java.net.Socket;

import static util.MyLogger.log;

public class NormalCloseClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결: " + socket);
        InputStream input = socket.getInputStream();

        readByInputStream(input, socket);
        readByBufferedReader(input, socket);
        readByDataInputStream(input, socket);

        log("연결 종료: " + socket.isClosed());
    }

    private static void readByInputStream(InputStream input, Socket socket) throws IOException {
        int read = input.read(); // 서버 측에서 FIN 패킷을 보내면 여기서 -1 (EOF)를 받음
        log("read = " + read);
        if (read == -1) {
            input.close();
            socket.close(); // TCP 연결 정상 종료를 위해서는 클라이언트에서도 FIN 패킷을 서버에 보내야 함
        }
    }

    private static void readByBufferedReader(InputStream input, Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(input)); // 서버 측에서 FIN 패킷을 보내면 여기서 null (EOF)를 받음
        String readString = br.readLine();
        log("readString = " + readString);
        if (readString == null) {
            br.close();
            socket.close(); // TCP 연결 정상 종료를 위해서는 클라이언트에서도 FIN 패킷을 서버에 보내야 함
        }
    }

    private static void readByDataInputStream(InputStream input, Socket socket) throws IOException {
        DataInputStream dis = new DataInputStream(input);
        try {
            String s = dis.readUTF(); // 서버 측에서 FIN 패킷을 보내면 여기서 EOFException 발생
        } catch (EOFException e) {
            log(e);
        } finally {
            dis.close();
            socket.close(); // TCP 연결 정상 종료를 위해서는 클라이언트에서도 FIN 패킷을 서버에 보내야 함
        }
    }
}
