package io.buffered;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.BUFFER_SIZE;
import static io.buffered.BufferedConst.FILE_NAME;

public class ReadFileV3 {

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(FILE_NAME);
        BufferedInputStream bis = new BufferedInputStream(fis, BUFFER_SIZE);
        long startTime = System.currentTimeMillis();

        /**
         * [BufferedInputStream 내부 동작 원리]
         * bis.read() 호출 시, BufferedInputStream Buffer 내 데이터가 있는지 확인 후,
         * 있으면, Buffer에 있는 데이터를 순차적으로 읽음.
         * 없으면, FileInputStream을 통해 BUFFER_SIZE 만큼 DISK에서 데이터를 메모리(Buffer)에 적재 후 읽음.
         * 
         * [ReadFileV2(직접 Buffer 구현)보다 느린 이유]
         * -> Buffered 스트림에는 멀티스레딩 안정성을 고려한 동기화(Lock 걸고 풂) 로직이 포함되어 있기 때문
         */
        int fileSize = 0;
        int data;
        while ((data = bis.read()) != -1) {
            fileSize++;
        }
        bis.close();

        long endTime = System.currentTimeMillis();

        System.out.println("File created: " + FILE_NAME);
        System.out.println("File size: " + fileSize / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
