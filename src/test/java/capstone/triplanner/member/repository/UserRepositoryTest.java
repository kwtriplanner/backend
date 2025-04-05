package capstone.triplanner.member.repository;

import capstone.triplanner.member.Member;
import capstone.triplanner.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @DisplayName("아이디 저장 후 이름과 아이디, 비밀번호가 모두 일치해야 한다.")
    void saveAndFind() {
        //given
        Member member = new Member("김주현", "zzq", "1234", "zzq3902@naver.com", "01012345678");

        //when
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId()).orElseThrow();

        //then
        assertThat(findMember.getName()).isEqualTo(member.getName()); // 이름 비교
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername()); // 아이디 비교
        assertThat(findMember.getPassword()).isEqualTo(member.getPassword()); // 비밀번호 비교

    }
}