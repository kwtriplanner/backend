package capstone.triplanner.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
    private String username;
    private String password;

    public Member() {}

    @Builder
    public Member(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
