package com.example.oBootMybatis01.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.oBootMybatis01.domain.Member;
import com.example.oBootMybatis01.service.MemberJpaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberJpaController {
	
	private final MemberJpaService memberJpaService;
	
	// 입력화면 이동 
	@GetMapping(value = "/memberJpa/new")
	public String createForm() {
		System.out.println("MemberJpaController / members/new START");
		return "memberJpa/createMemberForm";
	}
	
	// 회원 등록
	@PostMapping(value = "/memberJpa/save")
	public String create(Member member) {
		System.out.println("MemberJpaController create SATRT");
		System.out.println("member.getId() : " +member.getId());
		System.out.println("member.getName() : " +member.getName());
		memberJpaService.join(member);
		
		return "memberJpa/createMemberForm";
	}
	
	// 전체회원조회 List
	@GetMapping(value = "/members")
	public String listMember(Model model) {
		System.out.println("MemberJpaController listMember SATRT");
		List<Member> memberList = memberJpaService.getListAllMember();
		model.addAttribute("members", memberList);
		return "memberJpa/memberList";
	}
	
	// 이름누르면 상세 내역 조회
	@GetMapping(value = "/memberJpa/memberUpdateForm")
	public String memberUpdateForm(Long id, Model model) {
		Member member = null;
		String rtnJsp = "";
		System.out.println("MemberJpaController memberUpdateForm id : "+id);
		// 목적 : 객체가  NULL CHeck 용이
		Optional<Member> maybeMember = memberJpaService.findById(id);
		if(maybeMember.isPresent()) {
			System.out.println("MemberJpaController memberUpdateForm IS NO NULL");
			member = maybeMember.get();
			model.addAttribute("member", member);
			rtnJsp = "memberJpa/memberModify";
		} else {
			System.out.println("MemberJpaController memberUpdateForm maybMember IS NULL");
			model.addAttribute("message", "member가 존재하지 않으니, 임력부터 수정해주세요");
			rtnJsp = "forword:/members";
		}
		return rtnJsp;
	}
	
	// 업데이트(수정)
	@GetMapping(value = "memberJpa/memberUpdate")
	public String memberUpdate(Member member, Model model) {
		System.out.println("MemberJpaController memberUpdate id : " + member.getId());
		System.out.println("MemberJpaController memberUpdate Name : " + member.getName());
		memberJpaService.memberUpdate(member);
		return "redirect:/members";
	}
}
