package capstone.triplanner.member.dto;

import capstone.triplanner.member.Member;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.List;
import java.util.Map;

// 비밀번호 없는 DTO도 생성해야함

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class MemberDTO {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Map<String, List<String>> routeGroup;


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
