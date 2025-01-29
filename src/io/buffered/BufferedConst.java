package io.buffered;

public class BufferedConst {
    public static final String FILE_NAME = "temp/buffered.dat";
    public static final int FILE_SIZE = 10 * 1024 * 1024; // 10MB
    public static final int BUFFER_SIZE = 8192; // 8KB (디스크나 파일 시스템에서 읽고 쓰는 기본 단위는 보통 4KB, 8KB)
}
