package capstone.triplanner.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginResponseDTO {
    private String username;
    private String token;

    public LoginResponseDTO(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
