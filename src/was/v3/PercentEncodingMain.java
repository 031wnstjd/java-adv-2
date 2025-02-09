package was.v3;

import java.net.URLDecoder;
import java.net.URLEncoder;

import static java.nio.charset.StandardCharsets.UTF_8;

public class PercentEncodingMain {

    public static void main(String[] args) {
        String encode = URLEncoder.encode("가", UTF_8); // ASCII 문자가 아닌 경우 퍼센트 인코딩됨
        System.out.println("encode = " + encode);

        String decode = URLDecoder.decode(encode, UTF_8);
        System.out.println("decode = " + decode);
    }
}
