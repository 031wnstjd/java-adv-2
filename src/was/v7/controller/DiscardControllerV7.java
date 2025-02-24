package was.v7.controller;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.servlet.annotation.Mapping;

public class DiscardControllerV7 {

    @Mapping("/favicon.ico")
    public void discard(HttpRequest request, HttpResponse response) {
        // empty
    }
}
