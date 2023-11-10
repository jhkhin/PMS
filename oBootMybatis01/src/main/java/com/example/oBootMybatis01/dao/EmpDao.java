package com.example.oBootMybatis01.dao;

import java.util.List;

import com.example.oBootMybatis01.model.Emp;
import com.example.oBootMybatis01.model.EmpDept;

public interface EmpDao {
	
		
	int 			totalEmp(); 			 // 1. totalCount 조회
	List<Emp>		listEmp(Emp emp); 		 // 2. List 조회
	Emp 			detailEmp(int empno);	 // 3. 직원 정보
	int 			updateEmp(Emp emp);		 // 5. 수정 후 업데이트
	List<Emp> 		listManager();			 // 6-1. 관리자 사번만 가져오는 것
	int 			insertEmp(Emp emp);		 // 7. 입력
	int 			deleteEmp(int empno);	 // 9. 삭제
	List<Emp>   	empSearchList3(Emp emp); // 10-2. 검색
	int        		condTotalEmp(Emp emp);   // 10-1. 검색조건에 따른 Count 조회
	List<EmpDept> 	listEmpDept();			 // 11. 부서정보 조회
	String 			deptName(int deptno);	 // 4. getDeptName(controller)
	
}

