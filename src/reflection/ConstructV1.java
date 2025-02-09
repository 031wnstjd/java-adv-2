package reflection;

import java.lang.reflect.Constructor;

public class ConstructV1 {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("reflection.data.BasicData");

        System.out.println("===== constructors() =====");
        Constructor<?>[] constructors = clazz.getConstructors(); // public 생성자 리스트 
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("===== declaredConstructors() =====");
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors(); // 모든 생성자 리스트 
        for (Constructor<?> constructor : declaredConstructors) {
            System.out.println(constructor);
        }
    }
}
