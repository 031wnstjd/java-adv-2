package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamStartMain1 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        fos.write(65); // byte 단위로 값을 출력 (개발툴이나, 텍스트 편집기에서 UTF-8 문자 집합을 활용해 문자로 디코딩해서 보여줌)
        fos.write(66);
        fos.write(67);
        fos.close();

        FileInputStream fis = new FileInputStream("temp/hello.dat");
        int data;
        while ((data = fis.read()) != -1) { // EOF == -1
            System.out.println(data); // byte 단위로 값을 읽음
        }
        fis.close();
    }
}

