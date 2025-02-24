package was.v7;

import was.httpserver.HttpServer;
import was.httpserver.ServletManager;
import was.httpserver.servlet.annotation.AnnotationServletV1;
import was.v7.controller.DiscardControllerV7;
import was.v7.controller.SearchControllerV7;
import was.v7.controller.SiteControllerV7;

import java.io.IOException;
import java.util.List;

public class ServerMainV7 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        List<Object> controllers = List.of(
                new SiteControllerV7(),
                new SearchControllerV7(),
                new DiscardControllerV7()
        );
        AnnotationServletV1 annotationServletV1 = new AnnotationServletV1(controllers);

        ServletManager servletManager = new ServletManager();
        servletManager.setDefaultServlet(annotationServletV1);

        HttpServer server = new HttpServer(PORT, servletManager);
        server.start();
    }
}
