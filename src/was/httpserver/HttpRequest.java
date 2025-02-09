package was.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpRequest {

    private String method;
    private String path;
    private final Map<String, String> queryParameters = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();

    public HttpRequest(BufferedReader reader) throws IOException {
        parseRequestLine(reader);
        parseHeaders(reader);
        // 메시지 바디는 이후에 처리
    }

    private void parseRequestLine(BufferedReader reader) throws IOException {
        String requestLine = reader.readLine();
        if (requestLine == null) {
            throw new IOException("EOF: No request line received");
        }
        String[] parts = requestLine.split(" ");
        if (parts.length != 3) {
            throw new IOException("Invalid request line: " + requestLine);
        }

        method = parts[0];
        String[] pathParts = parts[1].split("\\?"); // /search?key1=value1&key2=value2
        path = pathParts[0]; // /search

        if (pathParts.length > 1) {
            parseQueryParameters(pathParts[1]); // key1=value1&key2=value2
        }
    }

    // key1=value1&key2=value2
    // 키1=값1 -> %키1=%값1 (한글 퍼센트 디코딩 필요)
    // q= -> value가 없는 경우, 빈 문자열("") 넣어줌
    private void parseQueryParameters(String queryString) {
        for (String param : queryString.split("&")) {
            String[] keyValue = param.split("=");
            String key = URLDecoder.decode(keyValue[0], UTF_8); // 퍼센트 디코딩
            String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], UTF_8) : ""; // value가 없는 경우, 빈 문자열("") 넣어줌
            queryParameters.put(key, value);
        }

    }

    // Host: localhost:12345
    // Connection: keep-alive
    // sec-ch-ua: "Not(A:Brand";v="99", "Google Chrome";v="133", "Chromium";v="133"
    // sec-ch-ua-mobile: ?0
    // sec-ch-ua-platform: "Windows"
    //
    private void parseHeaders(BufferedReader reader) throws IOException {
        String line;
        while (!(line = reader.readLine()).isEmpty()) { // 빈 공백 올때까지 헤더 read
            String[] keyValue = line.split(":");
            // trim() 앞 뒤 공백 제거
            headers.put(keyValue[0].trim(), keyValue[1].trim());
        }
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getParameter(String name) {
        return queryParameters.get(name);
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", queryParameters=" + queryParameters +
                ", headers=" + headers +
                '}';
    }
}