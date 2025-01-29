package io.buffered;

import java.io.FileOutputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.FILE_NAME;
import static io.buffered.BufferedConst.FILE_SIZE;

public class CreateFileV4 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);

        long startTime = System.currentTimeMillis();

        /**
         * - 10MB 파일(buffer) 한 번에 write
         * - 시스템 콜 횟수는 1회로 많이 줄어들지만
         *   실제 OS 레벨에서 DISK I/O Write 작업은 8KB 단위로 처리되기 때문에
         *   결국 실제 I/O 횟수는 CreateFileV2에서와 동일함.
         */
        byte[] buffer = new byte[FILE_SIZE];
        for (int i = 0; i < FILE_SIZE; i++) {
            buffer[i] = 1;
        }
        fos.write(buffer); // 10MB 파일(buffer) 한 번에 write
        fos.close();

        long endTime = System.currentTimeMillis();

        System.out.println("File created: " + FILE_NAME);
        System.out.println("File size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
