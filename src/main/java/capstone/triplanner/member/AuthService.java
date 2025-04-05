package capstone.triplanner.member;

import capstone.triplanner.member.dto.MemberDTO;
import capstone.triplanner.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    public String authenticate(String username, String rawPassword) {
        MemberDTO memberDTO = memberService.findMemberByUsername(username);

        if (memberDTO == null || !verifyPassword(rawPassword, memberDTO.getPassword())) {
            throw new BadCredentialsException("login failed");
        }
        return generateToken(memberDTO.getUsername());
    }

    private String generateToken(String username) {
        return tokenProvider.createToken(username);
    }

    public boolean validateToken(String token) {
        return tokenProvider.validateToken(token);
    }

    private boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public String getUsernameFromToken(String token) {
        return tokenProvider.getUsername(token);
    }
}
