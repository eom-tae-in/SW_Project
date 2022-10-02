package com.example.sheetmusiclist.repository.member;


import com.example.sheetmusiclist.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String Email);

}