package com.busanit.jpashop.repository;

import com.busanit.jpashop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 쿼리 메소드
    Member findByEmail(String email);
}
