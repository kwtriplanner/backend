//package capstone.triplanner.member.service;
//
//import capstone.triplanner.member.Member;
//import capstone.triplanner.member.MemberService;
//import capstone.triplanner.member.dto.MemberDTO;
//import jakarta.persistence.EntityNotFoundException;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Map;
//
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
////@Transactional
//class MemberServiceTest {
//
//    @Autowired
//    private MemberService memberService;
//
//    @Test
//    @DisplayName("회원 생성 후 ID로 조회한 결과가 일치해야 한다(비밀번호 제외)")
//    void createAndFindMemberById() {
//        //given
//        Member member = createTestMember();
//
//        //when
//        MemberDTO memberDTO = memberService.createMember(MemberDTO.from(member));
//        MemberDTO foundMemberDTO = memberService.findMemberById(memberDTO.getId());
//
//        //then
//        assertThat(member.getName()).isEqualTo(foundMemberDTO.getName());
//        assertThat(member.getUsername()).isEqualTo(foundMemberDTO.getUsername());
//    }
//
//
//    @Test
//    @DisplayName("회원 삭제 후 조회 시 EntityNotFoundException이 발생해야 한다.")
//    void deleteMember() {
//        //given
//        Member member = createTestMember();
//
//        //when
//        MemberDTO memberDTO = memberService.createMember(MemberDTO.from(member));
//        memberService.deleteMember(memberDTO.getId());
//
//        //then
//        assertThatThrownBy(() -> memberService.findMemberById(memberDTO.getId()))
//                .isInstanceOf(EntityNotFoundException.class);
//    }
//
//    @Test
//    @DisplayName("회원 id로 조회")
//    void findMemberByUsername() {
//        //given
//        Member member = createTestMember();
//
//        //when
//        memberService.createMember(MemberDTO.from(member));
//        MemberDTO foundMemberDTO = memberService.findMemberByUsername(member.getUsername());
//
//        //then
//        assertThat(foundMemberDTO.getName()).isEqualTo(member.getName());
//        assertThat(foundMemberDTO.getUsername()).isEqualTo(member.getUsername());
//    }
//
//    @Test
//    @DisplayName("중복일시 true, 아니면 false 반환")
//    void isUsernameTaken() {
//        //given
//        Member member = createTestMember();
//
//        //when
//        memberService.createMember(MemberDTO.from(member));
//
//        //then
//        assertThat(memberService.isUsernameTaken(member.getUsername())).isEqualTo(true);
//        assertThat(memberService.isUsernameTaken("notUsername")).isEqualTo(false);
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("업데이트 후 변경 확인")
//    void updateMember() {
//        //given
//        Member member = createTestMember();
//
//        //when
//        MemberDTO memberDTO = memberService.createMember(MemberDTO.from(member));
//        MemberDTO updatedMemberDTO = MemberDTO.builder()
//                .name("업데이트 테스트")
//                .username("test")
//                .password("1234")
//                .email("zzq3902@naver.com")
//                .phoneNumber("01012345678")
//                .build();
//        memberService.updateMember(memberDTO.getId(), updatedMemberDTO);
//
//        //then
//        assertThat(memberService.findMemberById(memberDTO.getId()).getName()).isEqualTo(updatedMemberDTO.getName());
//    }
////
////    @Test
////    @Transactional
////    @DisplayName("경로 생성 확인")
////    void createRoute() {
////        //given
////        Member member = createTestMember();
////
////        //when
////        MemberDTO memberDTO = memberService.createMember(MemberDTO.from(member));
////        memberService.createRoute(memberDTO.getId(), List.of("1", "2", "3"));
////
////        //then
////        Map<String, List<String>> routeGroup = memberService.findMemberById(memberDTO.getId()).getRouteGroup();
////        System.out.println(routeGroup.get("a"));
////    }
//    private Member createTestMember() {
//        Member member = Member.builder()
//                .name("테스트")
//                .username("test")
//                .password("1234")
//                .email("zzq3902@naver.com")
//                .phoneNumber("01012345678")
//                .build();
//        return member;
//    }
//
//
//}