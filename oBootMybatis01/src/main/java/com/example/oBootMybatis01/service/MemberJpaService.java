package com.example.oBootMybatis01.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.oBootMybatis01.domain.Member;
import com.example.oBootMybatis01.repository.MemberJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberJpaService {
	private final MemberJpaRepository memberJpaRepository;
	
	//회원가입
	public Long join(Member member) {
		System.out.println("MemberJpaRepository join member.getId() : " +member.getId());
		memberJpaRepository.save(member);
		return member.getId();
	}
	
	// 전체회원조회 List
	public List<Member> getListAllMember(){
		List<Member> listMember = memberJpaRepository.findAll();
		System.out.println("MemberJpaRepository getListAllMember listMember.size : " + listMember.size());
		return listMember;
	}
	
	// 이름누르면 상세 내역 조회
	public Optional<Member> findById(Long memberId) {
		System.out.println("MemberJpaRepository findById START");
		Optional<Member> member = memberJpaRepository.findById(memberId);
		return member;
	}
	
	// 업데이트(수정)
	public void memberUpdate(Member member) {
		System.out.println( "MemberJpaRepository memberUpdate member.getName(): "+ member.getName());
		System.out.println( "MemberJpaRepository memberUpdate member.getId(): "+ member.getId());
		memberJpaRepository.updateByMember(member);
		return;
	}
	
}
