package capstone.triplanner.member.service;

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
        return generateToken(memberDTO.getId());
    }

    private String generateToken(Long id) {
        return tokenProvider.createToken(id);
    }

    public boolean validateToken(String token) {
        return tokenProvider.validateToken(token);
    }

    private boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public Long getIdFromToken(String token) {
        return tokenProvider.getIdFromToken(token);
    }
}
