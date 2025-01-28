package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

public class EncodingMain1 {

    private static final Charset EUC_KR = Charset.forName("EUC-kr");
    private static final Charset MS_949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("== ASCII 영문 처리 ==");
        encoding("A", US_ASCII);
        encoding("A", ISO_8859_1);
        encoding("A", EUC_KR);
        encoding("A", UTF_8);
        encoding("A", UTF_16BE); // ASCII와 호환 안됨 (ASCII - 1Byte 사용, UTF-16 - 2Byte 사용)

        System.out.println("== 한글 지원 ==");
        encoding("가", EUC_KR); // 2Byte 사용
        encoding("가", MS_949); // 2Byte 사용
        encoding("가", UTF_8); // 3Byte 사용
        encoding("가", UTF_16BE); // 2Byte 사용

        String str = "a";
        byte[] bytes = str.getBytes(); // Encoder 지정 안하면 DefaultCharset 사용
        System.out.println("bytes = " + Arrays.toString(bytes));
    }

    private static void encoding(String text, Charset charset) {
        byte[] bytes = text.getBytes(charset);
        System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte\n", text, charset, Arrays.toString(bytes), bytes.length);
    }
}
