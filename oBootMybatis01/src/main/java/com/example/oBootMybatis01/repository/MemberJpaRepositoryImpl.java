package com.example.oBootMybatis01.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.oBootMybatis01.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepositoryImpl implements MemberJpaRepository {
	
	private final EntityManager em;
	
	// 회원등록 
	@Override
	public Member save(Member member) {
		System.out.println("MemberJpaRepositoryImpl save START");
		em.persist(member);
		return member;
	}
	
	// 전체회원조회 List
	@Override
	public List<Member> findAll() {
		System.out.println("MemberJpaRepositoryImpl findAll START");
		 // .getReultList() : list 형태로 돌려줌 
		List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList();
		return memberList;
	}
	// 이름누르면 상세 내역 조회
	@Override
	public Optional<Member> findById(Long memberId) {
		Member member = em.find(Member.class, memberId);
		return Optional.ofNullable(member);
	}
	
	// 업데이트(수정)
	@Override
	public void updateByMember(Member member) {
	//merge --> 현재 Setting 된것만 수정, 나머지는 NULL
		em.merge(member);
		
		/* 10.20일 강의
		Member member3 = em.find(Member.class, member.getId());
		member3.setName(member.getName());
		em.persist(member3);
		*/
		return;	
	}
}
