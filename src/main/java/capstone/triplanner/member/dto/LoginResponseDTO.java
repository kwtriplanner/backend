package capstone.triplanner.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
