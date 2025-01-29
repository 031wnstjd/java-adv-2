package io.member.impl;

import io.member.Member;
import io.member.MemberRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataMemberRepository implements MemberRepository {

    private static final String FILE_PATH = "temp/member-data.dat";

    @Override
    public void add(Member member) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_PATH, true))) {
            dos.writeUTF(member.getId()); // writeUTF()는 UTF-8 형식으로 저장하는데, 저장할 때 2byte를 추가로 사용해서 앞에 글자의 길이를 저장해둠.
            dos.writeUTF(member.getName());
            dos.writeInt(member.getAge()); // Int는 4byte를 사용하기 때문에 4byte를 사용해서 파일을 저장하고, 읽을 때도 4byte를 읽어서 복원함.
        } catch (IOException e) { // 파일이 없으면 파일을 만들기 때문에 FileNotFoundException 잡을 필요 X
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(FILE_PATH))) {
            while (dis.available() > 0) {
                Member member = new Member(dis.readUTF(), dis.readUTF(), dis.readInt());
                members.add(member);
            }
            return members;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
