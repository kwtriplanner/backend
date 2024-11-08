package capstone.triplanner.member.controller;

import capstone.triplanner.member.dto.LoginRequestDTO;
import capstone.triplanner.member.dto.LoginResponseDTO;
import capstone.triplanner.member.dto.MemberDTO;
import capstone.triplanner.member.service.AuthService;
import capstone.triplanner.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class MemberController {

    private final MemberService memberService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            String token = authService.authenticate(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
            return ResponseEntity.ok(new LoginResponseDTO(loginRequestDTO.getUsername(), token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid credentials");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody MemberDTO memberDTO) {
        if (memberService.isUsernameTaken(memberDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken");
        }
        memberService.createMember(memberDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Sign up success");
    }
}
