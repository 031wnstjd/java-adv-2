package reflection;

import reflection.data.User;

import java.lang.reflect.Field;

public class FieldV2 {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        User user = new User("id1", "userA", 20);
        System.out.println("기존 이름 = " + user.getName());

        Class<? extends User> clazz = user.getClass();
        Field nameField = clazz.getDeclaredField("name");

        // private 필드에 접근 허용, private 메서드도 이렇게 호출 가능
        nameField.setAccessible(true); // private 필드 접근 허용
        nameField.set(user, "userB"); // user 인스턴스에 있는 name 필드 값을 userB로 변경
        System.out.println("변경된 이름 = " + user.getName());
    }
}
