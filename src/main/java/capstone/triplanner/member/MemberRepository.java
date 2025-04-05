package capstone.triplanner.member;

import capstone.triplanner.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // findById, findAll, save, deleteById, existsById, count
    Optional<Member> findByUsername(String username);
}
