package com.example.Demo.Result.repository;

import com.example.Demo.Result.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    // 단순하게 Insert 하는 코드
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    // PK 조회를 하는 경우
    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // PK가 아닌 다른 조건으로 조회를 하는 경우.
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> members = em.createQuery("select m from Member as m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return members.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member as m", Member.class).getResultList();
    }
}
