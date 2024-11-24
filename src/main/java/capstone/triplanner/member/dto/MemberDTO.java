package capstone.triplanner.member.dto;

import capstone.triplanner.member.Member;
import lombok.Builder;
import lombok.Getter;

// 비밀번호 없는 DTO도 생성해야함

@Getter
@Builder
public class MemberDTO {

    private final Long id;
    private final String name;
    private final String username;
    private final String password;
    private final String email;
    private final String phoneNumber;


    public static MemberDTO from(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .username(member.getUsername())
                .password(member.getPassword())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}
