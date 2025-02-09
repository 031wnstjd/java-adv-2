package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Method;

public class MethodV1 {

    public static void main(String[] args) {
        Class<BasicData> helloClass = BasicData.class;

        System.out.println("===== methods() =====");
        Method[] methods = helloClass.getMethods(); // 해당 클래스와 상위 클래스에서 상속된 모든 public 메서드 반환
        for (Method method : methods) {
            System.out.println("method = " + method);
        }

        System.out.println("===== declaredMethods() =====");
        Method[] declaredMethods = helloClass.getDeclaredMethods(); // 클래스 내 선언된 모든 메서드 반환 (접근 제어자 관계 없이 반환, 상속된 메서드 미포함)
        for (Method method : declaredMethods) {
            System.out.println("declaredMethod = " + method);
        }
    }
}
