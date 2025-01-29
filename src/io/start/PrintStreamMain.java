package io.start;

import java.io.IOException;
import java.io.PrintStream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class PrintStreamMain {

    public static void main(String[] args) throws IOException {
        PrintStream printStream = System.out; // PrintStream은 OutputStream을 상속 받음

        byte[] bytes = "Hello!\n".getBytes(UTF_8);
        printStream.write(bytes); // OutputStream 부모 클래스가 제공하는 기능
        printStream.println("Print!"); // PrintStream이 자체적으로 제공하는 추가 기능 (내부적으로 String을 byte로 변환하는 작업을 거침)
    }
}
