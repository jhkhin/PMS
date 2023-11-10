package com.example.oBootMybatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.oBootMybatis01.model.Emp;
import com.example.oBootMybatis01.model.EmpDept;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmpDaoImpl implements EmpDao {
	// Mybatis DB 연동
	private final SqlSession session;
	
	// 1. totalCount 조회
	@Override
	public int totalEmp() {
		int totEmpCount = 0;
		System.out.println("EmpDaoImpl TOTAL START");
		
		//Tip) 1 row 을 이용하더라도 pk를 이용한 것이 아니면 List를 걸어야 됨
		try {
	       totEmpCount = session.selectOne("empTotal");
			
			System.out.println("EmpDaoImpl totalEmp totEmpCount->" +totEmpCount);		
		} catch (Exception e) {
			System.out.println("EmpDaoImpl totalEmp Exception : " + e.getMessage());
		}
		return totEmpCount;
	}
	// 2. List 조회
	@Override
	// Emp : start, end 만 존재
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl listEmp START");
		try {
			// Emp 객체가 그대로 parameter로 넘어가서 그 객체가 empList에 그대로 받아서 들어옴
			//							 Map Id		    parameter
			empList = session.selectList("tkEmpListAll", emp);
			System.out.println("EmpDaoImpl listEmp empList.size() : " + empList.size());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmp Exception : " + e.getMessage());
		}
		return empList;
	}
	
	// 3. 직원 정보
	@Override
	public Emp detailEmp(int empno) {
		Emp emp = null;
		System.out.println("EmpDaoImpl detailEmp START");
		try {
			//					    Map Id      parameter
			emp = session.selectOne("tkEmpOne", empno);
			System.out.println("EmpDaoImpl detailEmp emp.getEname() : " + emp.getEname());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl detailEmp Exception : " + e.getMessage());
		}
		return emp;
	}
	
	/*
	  1. EmpService안에 updateEmp method 선언
	   1) parameter : Emp
	   2) Return      updateCount (int)

	  2. EmpDao updateEmp method 선언
//	                          mapper ID   ,    Parameter
	  updateCount = session.update("tkEmpUpdate",emp);
	*/
	
	// 5. 수정 후 업데이트
	@Override
	public int updateEmp(Emp emp) {
		int updateCount = 0;
		System.out.println("EmpDaoImpl updateEmp START");
		try {
			// 성공하면 updateCount 됨
			// returnType								parametaType
			 updateCount = session.update("tkEmpUpdate",emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl updateEmp Exception : " + e.getMessage());
		}
		return updateCount;
	}
	
	//6-1. 관리자 사번만 가져오는 것 
	@Override
	public List<Emp> listManager() {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl listManager START");
		try {
			//							 	Map Id		  
			empList = session.selectList("tkSelectManager");
			System.out.println("EmpDaoImpl listManager empList.size() : " + empList.size());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listManager Exception : " + e.getMessage());
		}
		return empList;
	}
	
	// 7. 입력
	@Override
	public int insertEmp(Emp emp) {
		int empInsert = 0;
		System.out.println("EmpDaoImpl insertEmp START");
		try {
			empInsert = session.insert("insertEmp", emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl insertEmp Exception : " + e.getMessage());
		}
		return empInsert;
	}
	
	// 9. 삭제
	@Override
	public int deleteEmp(int empno) {
		int empDelete = 0;
		System.out.println("EmpDaoImpl deleteEmp START");
		try {
			empDelete = session.delete("ijDeleteEmp", empno);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl deleteEmp Exception : " + e.getMessage());
		}
		return empDelete;
	}
	
	// 10-2. 검색
	@Override
	public List<Emp> empSearchList3(Emp emp) {
		List<Emp> empSearchList3 = null;
		System.out.println("EmpDaoImpl empSearchList3 START");
		try {
			// keyword 검색
			// Naming Rule							Map ID			parameter
			empSearchList3 = session.selectList("tkEmpSearchList3", emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl empSearchList3 Exception : " + e.getMessage());
		}
		return empSearchList3;
	}
	
	// 10-1. 검색조건에 따른 Count 조회
	@Override
	public int condTotalEmp(Emp emp) {
		int totEmpCount = 0;
		System.out.println("EmpDaoImpl TOTAL START");
		
		//Tip) 1 row 을 이용하더라도 pk를 이용한 것이 아니면 List를 걸어야 됨
		try {
			totEmpCount = session.selectOne("condEmpTotal", emp);
			System.out.println("EmpDaoImpl condtotalEmp totEmpCount : " + totEmpCount);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl condtotalEmp Exception : " + e.getMessage());
		}
		return totEmpCount;
	}
	
	// 11. 부서정보 조회
	@Override
	public List<EmpDept> listEmpDept() {
		List<EmpDept> empDeptList = null;
		System.out.println("EmpDaoImpl empDeptList START");
		try {
			//							 Map Id		    parameter
			empDeptList = session.selectList("tkListEmpDept");
			System.out.println("EmpDaoImpl listEmpDept empDeptList.size() : " + empDeptList.size());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmpDept Exception : " + e.getMessage());
		}
		return empDeptList;
	}
	
	// 4. getDeptName(controller)
	@Override
	public String deptName(int deptno) {
		System.out.println("EmpDaoImpl deptName START");
		String resultStr = "";
		try {
			System.out.println("EmpDaoImpl deptName deptno : " + deptno);
			resultStr = session.selectOne("tkDeptName", deptno);
			System.out.println("EmpDaoImpl deptName resultStr : " + resultStr);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl deptName Exception : " + e.getMessage());
		}
		return resultStr;
	}
	
}
