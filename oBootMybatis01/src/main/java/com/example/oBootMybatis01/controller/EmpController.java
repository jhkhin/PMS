package com.example.oBootMybatis01.controller;

import java.util.HashMap;
import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.oBootMybatis01.model.Dept;
import com.example.oBootMybatis01.model.DeptVO;
import com.example.oBootMybatis01.model.Emp;
import com.example.oBootMybatis01.model.EmpDept;
import com.example.oBootMybatis01.model.Member1;
import com.example.oBootMybatis01.service.EmpService;
import com.example.oBootMybatis01.service.Paging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EmpController {
	// Service랑 Controller 연결
	private final EmpService es;
	private final JavaMailSender mailSender;	//메일 전송 객체
	
	
	@RequestMapping(value = "listEmp")
	public String empList(Emp emp, String currentPage, Model model) {
		System.out.println("EmpController listEmp START");
		// 1. totalCount 조회 -> Emp 전체 Count 22
		int totalEmp = es.totalEmp();
		
		// Paging 작업 (총 문서중 가져올 문서의 시작번호와 끝번호 EMPDTO에 담아서 Parameter로 전달)
		// class(Paging)으로 page 인스턴스객체가 생성
		// totalEmp = 22, currentPage = null 
		Paging page = new Paging(totalEmp, currentPage);
		//Paging DTO -> Emp(DTO) 값을 가져다 저장
		emp.setStart(page.getStart()); // 시작시 1
		emp.setEnd(page.getEnd());	   // 시작시 10 
		
		// 2. List 조회 -> Service Method 호출 (Parameter : Emp)
		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController list listEmp.size() : " + listEmp.size());
		
		model.addAttribute("totalEmp", totalEmp);
		model.addAttribute("listEmp", listEmp);
		model.addAttribute("page", page);
		return "list";	
	}
	
/*	3. 직원 정보 불러오기 (이름 눌렀을때)
 	과제 조건
	1. EmpService안에 detailEmp method 선언
	   1) parameter : empno
	   2) Return    :  Emp
	2. EmpDao   detailEmp method 선언 
	                  		  mapper ID  , Parameter
	   emp = session.selectOne("tkEmpSelOne",    empno);
*/
	
	// 3. 직원 정보 불러오기 (이름 눌렀을때)
	@GetMapping(value = "detailEmp")
	public String detailEmp(int empno, Model model) {
		System.out.println("EmpController detailEmp START");

		Emp emp = es.detailEmp(empno);	
		model.addAttribute("emp", emp);
		return "detailEmp";
	}
	
	// 4. 수정페이지 조회
	@GetMapping(value = "updateFormEmp")
	public String updateFormEmp(int empno, Model model) {
		System.out.println("EmpController updateFormEmp START");
		Emp emp = es.detailEmp(empno); //detailEmp에 담아서 DTO 로 보냄
		System.out.println("EmpController emp.getEname()" + emp.getEname());
		System.out.println("EmpController emp.getHiredate()" + emp.getHiredate());
		// 문제점 
		// 1. DTO  String hiredate
		// 2.View : 단순조회 OK ,JSP에서 input type="date" 문제 발생
		// 해결책  : 년월일만 짤라 넣어 주어야 함
		String hiredate="";
		if(emp.getHiredate() != null) {
			hiredate = emp.getHiredate().substring(0,10);
			emp.setHiredate(hiredate);
		}
		System.out.println("hiredate : " + hiredate);
		
		model.addAttribute("emp", emp);
		return "updateFormEmp";
	}

/* 수정
  1. EmpService안에 updateEmp method 선언
   1) parameter : Emp
   2) Return      updateCount (int)
  2. EmpDao updateEmp method 선언
                                mapper ID   ,    Parameter
  updateCount = session.update("tkEmpUpdate",emp);
*/
	
	// 5. 수정 후 업데이트
	@PostMapping(value = "updateEmp")
	public String updateEmp(Emp emp, Model model) {
		log.info("EmpController updateEmp START");
		int updateCount  = es.updateEmp(emp);	//업데이트 한 횟수 조회
		System.out.println("EmpController es.updateEmp updateCount : " + updateCount);
		model.addAttribute("uptCnt", updateCount); //Test Controller간 Data 전달
		model.addAttribute("kk3", "Message Test"); //Test Controller간 Data 전달
		return "forward:listEmp";    // model에 저장
		//return "redirect:listEmp"; //model에 저장 되어 있지 않음
	}
	
	// 6. 관리자사번, 부서코드 가져오기 (select Box)
	@RequestMapping(value = "writeFormEmp")
	public String writeFormEmp(Model model) {
		System.out.println("EmpController writeFormEmp START");
		
		//6-1. 관리자 사번만 가져오는 것
		List<Emp> empList = es.listManger();
		System.out.println("EmpController writeFormEmp empList.size : " + empList.size());
		model.addAttribute("empMngList", empList);
		
		//6-2. 부서코드 (코드, 부서명)
		List<Dept> deptList = es.deptSelect();
		model.addAttribute("deptList", deptList); //dept
		System.out.println("EmpController writeFormEmp deptList.size : " + deptList.size());
		
		return "writeFormEmp";
	}
	
	// 7. 입력
	@PostMapping(value = "writeEmp")
	public String writeEmp(Emp emp, Model model) {
		System.out.println("EmpController writeEmp START");
		// Service, Dao, Mapper명 [insertEmp] 까지 -> insert
		int insertResult = es.insertEmp(emp);
		if(insertResult > 0) return "redirect:listEmp";
		else {
			model.addAttribute("msg", "입력실패 확인해 보세요");
			return "forward:writeFormEmp";
		}
	}
	
	// return "forward:writeFormEmp" -> 작성폼으로 돌아가 다시 입력 하게함 
	// 8. 중복확인
	@GetMapping(value = "confirm")
	public String comfirm(int empno, Model model) {
		Emp emp = es.detailEmp(empno);
		model.addAttribute("empno", empno);
		if(emp != null) {
			System.out.println("EmpController comfirm 사번 중복 확인");
			model.addAttribute("msg", "중복된 사번입니다");
			return "forward:writeFormEmp";
		} else {
			System.out.println("EmpController comfirm 사번 사용 가능");
			model.addAttribute("msg", "사용가능한 사번입니다");
			return "forward:writeFormEmp";
		}
	}
	
	// Validation시 참조
	@PostMapping(value = "writeEmp3")
	public String writeEmp3(@ModelAttribute("emp")@Validated Emp emp, 
															BindingResult result, 
															Model model) {
		System.out.println("EmpController writeEmp3 START");
		// Validation 오류시 Result
		if(result.hasErrors()) {
			System.out.println("EmpController writeEmp3 hasErrors");
			model.addAttribute("msg", "BindingResult 입력 실패 확인해보세요");
			return "forward:writeFormEmp3";
		}
		// service, dao 
		int insertResult = es.insertEmp(emp);
		if(insertResult > 0) return "redirect:listEmp";
		else {
			model.addAttribute("msg", "입력실패확인해보세요");
			return "forward:writeFormEmp3";
		}
	}
	
	// Validation Check 용
	@RequestMapping(value = "writeFormEmp3")
	public String writeFormEmp3(Model model) {
		System.out.println("EmpController writeFormEmp3 START");
		
		//6-1. 관리자 사번만 가져오는 것
		List<Emp> empList = es.listManger();
		System.out.println("EmpController writeFormEmp3 empList.size : " + empList.size());
		model.addAttribute("empMngList", empList);
		
		//6-2. 부서코드 (코드, 부서명)
		List<Dept> deptList = es.deptSelect();
		model.addAttribute("deptList", deptList); //dept
		System.out.println("EmpController writeFormEmp3 deptList.size : " + deptList.size());
		
		return "writeFormEmp3";
	}	
/*
	 Controller -->  deleteEmp    
	 1.parameter : empno
	 name -> Service, dao , mapper
	 return -> listEmp
 */
	
	
	// 9. 삭제
	@RequestMapping(value = "deleteEmp")
	public String deleteEmp(int empno, Model model) {
		System.out.println("EmpController deleteEmp START");
		int result = es.deleteEmp(empno);
		return "redirect:listEmp";
	}
	
	// 10. 검색
	@RequestMapping(value = "listSearch3")
	public String listSearch3(Emp emp, String currentPage, Model model) {
		System.out.println("EmpControllerlistSearch3 START");
		// 10-1. 검색조건에 따른 Count 조회
		int totalEmp = es.condTotalEmp(emp);
		
		Paging page = new Paging(totalEmp, currentPage);
		//Paging DTO -> Emp(DTO) 값을 가져다 저장
		emp.setStart(page.getStart()); // 시작시 1
		emp.setEnd(page.getEnd());	   // 시작시 10 
		
		// 10-2. 검색
		List<Emp> listEmp= es.listSearchEmp(emp);
		System.out.println("EmpController listSearch3 listEmp.size() : " + listEmp.size());
		
		model.addAttribute("totalEmp", totalEmp);
		model.addAttribute("listEmp", listEmp);
		model.addAttribute("page", page);
		return "list";	
	}
	
	
	// Service ,DAO -> listEmpDept
	// Mapper만 ->tkListEmpDept
	
	// 11. 부서정보 조회
	// 정보를 EMP, DEPT에서 가져오기만 하기 때문에 전달할 Parameter 값이 없음 -> EMPDEPT 안써도 됨
	@GetMapping(value = "listEmpDept")
	public String listEmpDept(Model model) {
		System.out.println("EmpController listEmpDept START");
		List<EmpDept> empDeptList = es.listEmpDept();
		model.addAttribute("listEmpDept", empDeptList);
		return "listEmpDept";
	}
	
	// 12. Mail
	@RequestMapping(value = "mailTransport")
	public String mailTransport(HttpServletRequest request, Model model) {
		System.out.println("mailSending");
		String tomail = "jhkhin@naver.com";		//받는사람 이메일
		System.out.println(tomail);
		String setfrom = "jhkhin@gmail.com";	// 보내는사람
		String title = "mailTransPort 입니다";	// 제목
		
		try {
			// Mime(Multipurpose Internet Mail Extensions : 인터넷의 전자 메일에서 사용되는 문자 데이터를 표현하기 위한 형식 표준)
			// Mime 전자우편 Internet 표준 Format
			// 위에서 선언한 mailSender를 해놔야 메일 세팅을 통해 메일 보낼수 있음
			MimeMessage message = mailSender.createMimeMessage();
			// MimeMessageHelper 객체를 통해 도움을 받아 메일 세팅 
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setfrom);	  // 보내는 사람 생략하거나 하면 정상작동을 않함
			messageHelper.setTo(tomail);      // 받는사람 이메일
			messageHelper.setSubject(title);  // 메일제목은 생략이 가능 -> 안넣으면 받는 메일에서 스팸으로 뺄수도 있음
			String tempPassword = (int) (Math.random()*999999) + 1 + "";
			messageHelper.setText("임시비밀번호입니다 : " + tempPassword); // 메일 내용
			System.out.println("임시비밀번호입니다 : " + tempPassword);
			DataSource dataSource = new FileDataSource("c:\\log\\hwa.png");
			messageHelper.addAttachment(MimeUtility.encodeText("hwa3.png", "UTF-8", "B"), dataSource);
			mailSender.send(message);
			model.addAttribute("check", 1); //정상전달
			// DB Logic 구성
		} catch (Exception e) {
			System.out.println("mailTransport e.getMessage() : " + e.getMessage());
			model.addAttribute("check", 2); // 메일 전달 실패
		}
		return "mailResult";
	}
	
	// 13. Procedure Test 입력 화면
	@RequestMapping(value = "writeDeptIn")
	public String writeDeptIn(Model model) {
		System.out.println("writeDeptIn START");
		return "writeDept3";
	}
	
	// 14. Procedure 통한 Dept 입력후 VO 전달
	@PostMapping(value = "writeDept")
	public String writeDept(DeptVO deptVO, Model model) {
		es.insertDept(deptVO);
		if(deptVO == null) {
			System.out.println("deptVO NULL");
		} else {
			System.out.println("deptVO.getOdeptno()  :" +deptVO.getOdeptno());
			System.out.println("deptVO.getOdname()  :" +deptVO.getOdname());
			System.out.println("deptVO.getOloc()  :" +deptVO.getOloc());
			model.addAttribute("msg", "정상입력되었습니다");
			model.addAttribute("dept", deptVO);
		}
		return "writeDept3";
	}
	
	// 15. 부서조회(Cursor)
	/*
	 파라메타 전달 방식 (DTO vs Map)
	- DTO : 객체를 통채로 전달 → 컬럼이 2개 이상일때 → 단, 정확하게 DTO에 정의가 되어 있어야 함 (조회용, 버퍼용 등)
	- Map : 개발이 편함, 초기화 할 필요가 없음 → DTO에 기술할 것이 아니기 때문에
	다이나믹하게 즉흥적으로 이끌어 낼 수 있음 → 단점:유지보수 어려움 (정의가 되어 있지 않기 때문에)
	*/
	// MAP 적용
	@GetMapping(value = "writeDeptCursor")
	public String writeDeptCursor(Model model) {
		System.out.println("EmpController writeDeptCursor START");
		//부서범위 조회 (부서번호 : 10~55번까지만)
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sDeptno", 10);
		map.put("eDeptno", 55);
		
		es.selListDept(map);
		List<Dept> deptLists = (List<Dept>)map.get("dept");
		for(Dept dept : deptLists) {
			System.out.println("dept.getDname : " + dept.getDname());
			System.out.println("dept.getLoc : " + dept.getLoc());
		}
		System.out.println("deptList Size : " + deptLists.size());
		model.addAttribute("deptList", deptLists);
		
		return "writeDeptCursor";
	}
	
	// interCeptor 시작화면
	@RequestMapping(value = "interCeptorForm")
	public String interCeptorForm(Model model) {
		System.out.println("interCeptorForm START");
		return "interCeptorForm";
	}
	
	// 2번 interCeptor Number 2
	@RequestMapping(value = "interCeptor")
	public String interCeptor(String id, Model model) {
		System.out.println("EmpController interCeptor Test START");
		System.out.println("EmpController interCeptor id : " + id);
		// 존재 : 1, 비존재 : 0
		int memCnt = es.memCount(id);
		System.out.println("EmpController interCeptor memCnt" + memCnt);
		
		model.addAttribute("id", id);
		model.addAttribute("memCnt", memCnt);
		System.out.println("interCeptor TEST END");
		
		return "interCeptor"; // User 존재하면 User 이용 조회 PAGE
	}
	
	// SampleInterceptor 내용을 받아 처리
	@RequestMapping(value = "doMemberWrite", method = RequestMethod.GET)
	public String doMemberWrite(Model model, HttpServletRequest request) {
		String ID = (String) request.getSession().getAttribute("ID");
		System.out.println("doMemberWrite 부터 하세요");
		model.addAttribute("id", ID);
		return "doMemberWrite";
	}
	
	// inter 
	@RequestMapping(value = "doMemberList")
	public String doMemberList(Model model, HttpServletRequest request) {
		String ID = (String) request.getSession().getAttribute("ID");
		System.out.println("doMemberList TEST START ID : " + ID);
		Member1 member1 = null;
		// Member1 List Get Service
		List<Member1> listMem = es.listMem(member1);
		model.addAttribute("ID", ID);
		model.addAttribute("listMem", listMem);
		return "doMemberList";
	}
	
	// ajaxForm TEST 입력 화면
	@RequestMapping(value = "ajaxForm")
	public String ajaxForm(Model model) {
		System.out.println("ajaxForm START");
		return "ajaxForm";
	}
	
	// 4. getDeptName(controller)
	// deptName : 부서 코드를 주면 부서명을 가지고 옴
	@ResponseBody
	@RequestMapping(value = "getDeptName")
	public String getDeptName(int deptno, Model model) {
		System.out.println("deptno : " +deptno);
		String deptName = es.deptName(deptno);
		System.out.println("deptName : " + deptName);
		return deptName;
	}
	
	// 5. listEmpAjaxForm(aJax JSP 연동)
	// 페이지 조회 
	@RequestMapping("listEmpAjaxForm")
	public String listEmpAjaxForm(Model model) {
		Emp emp = new Emp();
		System.out.println("Ajax List Test START");
		// Parameter emp ---> Page 추가 Setting
		emp.setStart(1); // 시작시 1
		emp.setEnd(10);  // 시작시 10
		
		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController listEmpAjax listEmp.size : " + listEmp.size());
		model.addAttribute("result", "kkk");
		model.addAttribute("listEmp", listEmp);
		return "listEmpAjaxForm";
	}
	
	// 6. listEmpAjaxForm2(aJax JSP 객체리스트 Get)
	@RequestMapping(value = "listEmpAjaxForm2")
	public String listEmpAjaxForm2(Model model) {
		System.out.println("listEmpAjaxForm2 START");
		Emp emp = new Emp();
		System.out.println("Ajax List Test START");
		// Parameter emp ---> Page 추가 Setting
		emp.setStart(1); // 시작시 1
		emp.setEnd(15);  // 시작시 15
		List<Emp> listEmp = es.listEmp(emp);
		model.addAttribute("listEmp", listEmp);
		return "listEmpAjaxForm2";
	}
	
	// 16. transactionInsertUpdate
	@ResponseBody
	@RequestMapping(value = "transactionInsertUpdate")
	public String transactionInsertUpdate(Emp emp, Model model) {
		System.out.println("EmpController transactionInsertUpdate START");
		// Dept Insert  성공과 실패
		int returnMember = es.transactionInsertUpdate();
		System.out.println("EmpController transactionInsertUpdate returnMember : " + returnMember);
		String retrunMemberString = String.valueOf(returnMember);
		
		return retrunMemberString;
	}
}
