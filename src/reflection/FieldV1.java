package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Field;

public class FieldV1 {

    public static void main(String[] args) {
        Class<BasicData> helloClass = BasicData.class;

        System.out.println("===== fields() =====");
        Field[] fields = helloClass.getFields(); // 해당 클래스와 상위 클래스에서 상속된 모든 public 필드 반환
        for (Field field : fields) {
            System.out.println(field);
        }
        
        System.out.println("===== declaredFields() =====");
        Field[] declaredFields = helloClass.getDeclaredFields(); // 해당 클레스에서 선언된 모든 필드를 반환(접근제어자 관계없이 반환, 상속된 필드 미포함)
        for (Field field : declaredFields) {
            System.out.println(field);
        }
    }
}
