package was.httpserver.servlet.reflect;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;
import was.httpserver.PageNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ReflectionServlet implements HttpServlet {

    private final List<Object> controllers;

    public ReflectionServlet(List<Object> controllers) {
        this.controllers = controllers;
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        String path = request.getPath(); // site1

        for (Object controller : controllers) { // SiteControllerV6, SearchControllerV6
            Class<?> clazz = controller.getClass(); // SiteControllerV6
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) { // site1, site2
                String methodName = method.getName();
                if (path.equals("/" + methodName)) { // /site1.equals(/site1)
                    invoke(controller, method, request, response);
                    return;
                }
            }
        }
        throw new PageNotFoundException("request = " + path);
    }

    private static void invoke(Object controller, Method method, HttpRequest request, HttpResponse response) {
        try {
            method.invoke(controller, request, response);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}