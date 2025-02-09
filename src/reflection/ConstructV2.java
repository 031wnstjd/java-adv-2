package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConstructV2 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName("reflection.data.BasicData");

        Constructor<?> constructor = clazz.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Object instance = constructor.newInstance("hello");
        System.out.println("instance = " + instance);

        Method method1 = clazz.getDeclaredMethod("call");
        method1.invoke(instance);
    }
}
