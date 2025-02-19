package annotation.mapping;

import java.lang.reflect.Method;

public class TestControllerMain {

    public static void main(String[] args) {
        TestController testController = new TestController();

        Class<? extends TestController> aClass = testController.getClass();
        for (Method method : aClass.getDeclaredMethods()) {
            SimpleMapping simpleMapping = method.getAnnotation(SimpleMapping.class); // annotation을 런타임 시점에 찾아옴
            if (simpleMapping != null) {
                System.out.println("[" + simpleMapping.value() + "] -> " + method);
            }
        }
    }
}
