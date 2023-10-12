package com.busanit.springmvc.repository;

import com.busanit.springmvc.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
