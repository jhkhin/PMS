package com.example.oBootMybatis01.service;

import java.util.HashMap;
import java.util.List;

import com.example.oBootMybatis01.model.Dept;
import com.example.oBootMybatis01.model.DeptVO;
import com.example.oBootMybatis01.model.Emp;
import com.example.oBootMybatis01.model.EmpDept;
import com.example.oBootMybatis01.model.Member1;

public interface EmpService {
	
	
	int 			totalEmp(); 								// 1. totalCount 조회
	List<Emp> 		listEmp(Emp emp);							// 2. List 조회
	Emp 			detailEmp(int empno); 						// 3. 직원 정보
	int 			updateEmp(Emp emp);							// 5. 수정 후 업데이트
	List<Emp> 		listManger();								//6-1. 관리자 사번만 가져오는 것
	List<Dept>		deptSelect();								//6-2. 부서코드 (코드, 부서명)
	int 			insertEmp(Emp emp);							// 7. 입력
	int 			deleteEmp(int empno);						// 9. 삭제
	List<Emp>   	listSearchEmp(Emp emp); 					// 10-2. 검색
	int				condTotalEmp(Emp emp);						// 10-1. 검색조건에 따른 Count 조회
	List<EmpDept> 	listEmpDept();			 					// 11. 부서정보 조회
	void 			insertDept(DeptVO deptVO);					// 14. Procedure 통한 Dept 입력후 VO 전달
	void 			selListDept(HashMap<String, Object> map);	// 15. 부서조회(Cursor)
	int 			memCount(String id);
	List<Member1>   listMem(Member1 member1);
	String			deptName(int deptno);						// 4. getDeptName(controller)
	int 			transactionInsertUpdate();					// 16. transactionInsertUpdate
	
}

 
