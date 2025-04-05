package capstone.triplanner.member;

import capstone.triplanner.member.Member;
import capstone.triplanner.member.dto.MemberDTO;
import capstone.triplanner.member.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberDTO createMember(MemberDTO memberDTO) {
        Member member = Member.builder()
                .name(memberDTO.getName())
                .username(memberDTO.getUsername())
                .password(encodePassword(memberDTO.getPassword()))
                .build();
        Member savedMember = memberRepository.save(member);
        return MemberDTO.from(savedMember);
    }

    public MemberDTO findMemberById(Long id) {
        return MemberDTO.from(getMemberById(id));
    }

    public MemberDTO findMemberByUsername(String username) {
        Member foundMember =  memberRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Member with username " + username + " not found"));
        return MemberDTO.from(foundMember);
    }

    @Transactional
    public void updateMember(Long id, MemberDTO memberDTO) {
        MemberDTO updateMemberDTO = MemberDTO.builder()
                .name(memberDTO.getName())
                .username(memberDTO.getUsername())
                .password(encodePassword(memberDTO.getPassword()))
                .build();
        Member foundMember = getMemberById(id);
        foundMember.update(updateMemberDTO);
    }
    @Transactional
    public void deleteMember(Long id) {
        memberRepository.delete(getMemberById(id));
    }

    private Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member with ID " + id + " not found"));
    }

    public boolean isUsernameTaken(String username) {
        return memberRepository.findByUsername(username).isPresent();
    }

    private String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
