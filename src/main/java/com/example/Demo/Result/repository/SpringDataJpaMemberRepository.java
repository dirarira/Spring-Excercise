package com.example.Demo.Result.repository;

import com.example.Demo.Result.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // select m from Member m where m.name = ?
    // interface의 method 이름 정하는 방식으로 복잡한 쿼리 가능.
    @Override
    Optional<Member> findByName(String name);
}
