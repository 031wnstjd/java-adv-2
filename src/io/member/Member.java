package io.member;

import java.io.Serializable;

/**
 * - Serializable(마커 인터페이스)이 없으면 객체 직렬화 시 NoSerializableException이 발생함
 * - 현대에는 객체 직렬화를 거의 사용하지 않음 (더 좋은 대안 기술들이 많음)
 *   [객체 직렬화를 사용하지 않는 이유]
 *   - 버전 관리의 어려움 (클래스 구조가 변경되면 이전에 직렬화된 객체와의 호환성 문제가 발생함)
 *   - 플랫폼 종속성 (자바 직렬화는 자바 플랫폼에 종속적이어서 다른 언어나 시스템과의 상호 운용성이 떨어짐)
 *   - 성능 이슈 (직렬화/역직렬화 과정이 상대적으로 느리고, 리소스를 많이 사용한다.)
 *   - 유연성 부족 (직렬화된 형식을 커스터마이즈하기 어렵다.)
 *   - 크기 효율성 (직렬화된 데이터 크기가 상대적으로 크다.)
 */
public class Member implements Serializable {
    private String id;
    private String name;
    private Integer age;

    public Member() {}

    public Member(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
