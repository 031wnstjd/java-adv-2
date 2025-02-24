package was.v8;

import was.httpserver.HttpServer;
import was.httpserver.ServletManager;
import was.httpserver.servlet.annotation.AnnotationServletV2;
import was.httpserver.servlet.annotation.AnnotationServletV3;
import was.v8.controller.DiscardControllerV8;
import was.v8.controller.SearchControllerV8;
import was.v8.controller.SiteControllerV8;

import java.io.IOException;
import java.util.List;

public class ServerMainV8 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        List<Object> controllers = List.of(
                new SiteControllerV8(),
                new SearchControllerV8(),
                new DiscardControllerV8()
        );
//        AnnotationServletV2 annotationServlet = new AnnotationServletV2(controllers);
        AnnotationServletV3 annotationServlet = new AnnotationServletV3(controllers);

        ServletManager servletManager = new ServletManager();
        servletManager.setDefaultServlet(annotationServlet);

        HttpServer server = new HttpServer(PORT, servletManager);
        server.start();
    }
}
