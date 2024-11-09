package capstone.triplanner.member.dto;

import capstone.triplanner.member.Member;
import lombok.Builder;
import lombok.Getter;

// 비밀번호 없는 DTO도 생성해야함

@Getter
public class MemberDTO {
    private final Long id;
    private final String name;
    private final String username;
    private final String password;

    @Builder
    public MemberDTO(Long id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public static MemberDTO from(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .username(member.getUsername())
                .password(member.getPassword())
                .build();
    }
}
