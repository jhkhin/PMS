package com.example.oBootMybatis01.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oBootMybatis01.model.Dept;
import com.example.oBootMybatis01.model.Emp;
import com.example.oBootMybatis01.model.SampleVO;
import com.example.oBootMybatis01.service.EmpService;

import lombok.RequiredArgsConstructor;

// @Controller + @ResponseBody
@RestController
@RequiredArgsConstructor
public class EmpRestController {
	
	private final EmpService es;
	
	// Controller에서 String으로 오면 원래 View Resolver를 타게 되어 있음 
	// → 예외 상황 : @ResponseBody가 들어가면 HttpMessageConverter이 받음
	
	// 1. helloText
	@RequestMapping("/helloText")
	public String helloText() {
		System.out.println("EmpRestController START");
		String hello = "안녕";
		// StringConverter로 호출 → 시스템콘솔이 아닌 내가 호출한 창으로 호출됨
		return hello;
	}
	
	// 2. sample/sendVO2(객체)
	// ajaxForm.jsp에서 deptno의 값을 달아서 보냄
	// Json viewr : http://jsonviewer.stack.hu
	@RequestMapping("/sample/sendVO2")
	public SampleVO sendVO2(int deptno) {
		System.out.println("@RestController deptno -> " + deptno);
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
	    vo.setLastName("홍");
	    vo.setMno(deptno);
	    // 객체일때는 JsonConverter을 호출 
	    return vo;
	}
	
	// 3. sendVO3
	@RequestMapping("/sendVO3")
	public List<Dept> sendVO3(){
		System.out.println("@RestController sendVO3 START");
		List<Dept> deptList = es.deptSelect();
		return deptList;
	}
	
	@RequestMapping("/empnoDelete")
	// 2개 이상 값을 받으려면 dto로 받아야됨 -> Emp
	public String empnoDelete(Emp emp) {
		System.out.println("@RestController empnoDelete START");
		System.out.println("@RestController empnoDelete emp.getEname():"+emp.getEname());
		int delStatus = es.deleteEmp(emp.getEmpno());
		String delStatusStr = Integer.toString(delStatus);
		return delStatusStr;
	}
	
}
