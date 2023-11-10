package com.example.oBootMybatis01.repository;

import java.util.List;
import java.util.Optional;

import com.example.oBootMybatis01.domain.Member;

public interface MemberJpaRepository {
	Member				save(Member member);				// 회원등록
	List<Member> 		findAll();							// 전체회원조회 List
	Optional<Member> 	findById(Long memberId);			// 이름누르면 상세 내역 조회
	void 				updateByMember(Member member);		// 업데이트 수정
}
