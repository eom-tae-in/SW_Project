package com.example.sheetmusiclist.entity.member;

import com.example.sheetmusiclist.entity.common.EntityDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends EntityDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String username, String password, String email, Authority authority) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authority = authority;
    }
}