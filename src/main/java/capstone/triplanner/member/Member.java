package capstone.triplanner.member;

import capstone.triplanner.member.dto.MemberDTO;
import capstone.triplanner.review.Review;
import capstone.triplanner.trip.Trip;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Trip> trips = new ArrayList<>();

    @Builder
    public Member(String name, String username, String password, String email, String phoneNumber) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void update(MemberDTO memberDTO) {
        this.name = memberDTO.getName();
        this.username = memberDTO.getUsername();
        this.password = memberDTO.getPassword();
        this.email = memberDTO.getEmail();
        this.phoneNumber = memberDTO.getPhoneNumber();
    }

}
