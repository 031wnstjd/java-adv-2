package io.member.impl;

import io.member.Member;
import io.member.MemberRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectMemberRepository implements MemberRepository {

    private static final String FILE_PATH = "temp/members-obj.dat";

    @Override
    public void add(Member member) {
        List<Member> members = findAll(); // 컬렉션 자체를 저장해야하기 때문에 회원 목록 조회
        members.add(member);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(members); // 컬렉션 자체(회원 목록 통째로)를 파일에 저장함
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Member>) ois.readObject(); // 컬렉션 자체를 읽고, 타입 캐스팅해서 반환
        } catch (FileNotFoundException e) {
            return new ArrayList<>(); // 파일이 없는 경우 빈 리스트 반환
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
