package com.example.oBootMybatis01.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.oBootMybatis01.dao.DeptDao;
import com.example.oBootMybatis01.dao.EmpDao;
import com.example.oBootMybatis01.dao.Member1Dao;
import com.example.oBootMybatis01.model.Dept;
import com.example.oBootMybatis01.model.DeptVO;
import com.example.oBootMybatis01.model.Emp;
import com.example.oBootMybatis01.model.EmpDept;
import com.example.oBootMybatis01.model.Member1;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {
	// Dao랑 Service 연결 
	// 어노테이션을 이용한 생성자 인젝션
	// 하나의 서비스에서 여러개의 dao 연결가능
	private final EmpDao ed;
	private final DeptDao dd;
	private final Member1Dao md;
	
	// 1. totalCount 조회
	@Override
	public int totalEmp() {
		System.out.println("EmpServiceImpl TOTAL START");
		int totEmpCnt = ed.totalEmp();
		System.out.println("EmpServiceImpl totalEmp totEmpCnt : " +totEmpCnt);
		return totEmpCnt;
	}

	// 2. List 조회
	@Override
	public List<Emp> listEmp(Emp emp) {
		//Dao
		List<Emp> empList = null;
		System.out.println("EmpServiceImpl listManager START");
		empList = ed.listEmp(emp);
		System.out.println("EmpServiceImpl listEmp empList.size : " + empList.size());
		return empList;
	}

	
	// 3. 직원 정보
	@Override
	public Emp detailEmp(int empno) {
		Emp emp = null;
		System.out.println("EmpServiceImpl detailEmp START");
		emp = ed.detailEmp(empno);		
		return emp;
	}

	// 5. 수정 후 업데이트
	@Override
	public int updateEmp(Emp emp) {
		int updateCount = 0;
		System.out.println("EmpServiceImpl updateEmp START");
		updateCount = ed.updateEmp(emp);
		return updateCount;
	}
	
	//6-1. 관리자 사번만 가져오는 것
	@Override
	public List<Emp> listManger() {
		List<Emp> empList = null;
		System.out.println("EmpServiceImpl listMange START");
		empList = ed.listManager();
		System.out.println("EmpServiceImpl listEmp empList.size() : " + empList.size());
		return empList;
	}
	
	//6-2. 부서코드 (코드, 부서명)
	// 부서(코드, 부서명) -> dept 정보 -> dept Dao 만듬
	@Override
	public List<Dept> deptSelect() {
		List<Dept> deptList = null;
		System.out.println("EmpServiceImpl deptSelect START");
		deptList = dd.deptSelect();
		System.out.println("EmpServiceImpl deptSelect deptList.size : " + deptList.size());
		return deptList;
	}

	// 7. 입력
	@Override
	public int insertEmp(Emp emp) {
		int empInsert = 0;
		System.out.println("EmpServiceImpl insertEmp START");
		empInsert = ed.insertEmp(emp);
		return empInsert;
	}
	// 9. 삭제
	@Override
	public int deleteEmp(int empno) {
		int empDelete = 0;
		System.out.println("EmpServiceImpl deleteEmp START");
		empDelete = ed.deleteEmp(empno);
		return empDelete;
	}
	
	
	// 10-2. 검색
	@Override
	public List<Emp> listSearchEmp(Emp emp) {
		List<Emp> empSearchList = null;
		System.out.println("EmpServiceImpl listSearchEmp START");
		empSearchList = ed.empSearchList3(emp);
		System.out.println("EmpServiceImpl listSearchEmp empSearchList.size()" + empSearchList.size());
		return empSearchList;
	}
	// 10-1. 검색조건에 따른 Count 조회
	@Override
	public int condTotalEmp(Emp emp) {
		System.out.println("EmpServiceImpl TOTAL START");
		int totEmpCnt = ed.condTotalEmp(emp);
		System.out.println("EmpServiceImpl totalEmp totEmpCnt : " +totEmpCnt);
		return totEmpCnt;
	}

	// 11. 부서정보 조회
	@Override
	public List<EmpDept> listEmpDept() {
		List<EmpDept> empDeptList = null;
		System.out.println("EmpServiceImpl listEmpDept START");
		empDeptList = ed.listEmpDept();
		System.out.println("EmpServiceImpl listEmpDept empDeptList.size : " + empDeptList.size());
		return empDeptList;
	}

	// 14. Procedure 통한 Dept 입력후 VO 전달
	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("EmpServiceImpl insertDept START");
		dd.insertDept(deptVO);
	}
	
	// 15. 부서조회(Cursor)
	@Override
	public void selListDept(HashMap<String, Object> map) {
		System.out.println("EmpServiceImpl selListDept START");
		dd.selListDept(map);
	}

	@Override
	public int memCount(String id) {
		System.out.println("EmpServiceImpl memCount id : " + id);
		return md.memCount(id);
	}

	@Override
	public List<Member1> listMem(Member1 member1) {
		System.out.println("EmpServiceImpl listMem START");
		return md.listMem(member1);
	}
	
	// 4. getDeptName(controller)
	@Override
	public String deptName(int deptno) {
		System.out.println("EmpServiceImpl deptName START");
		return ed.deptName(deptno);
	}

	// 16. transactionInsertUpdate
	@Override
	public int transactionInsertUpdate() {
		System.out.println("EmpServiceImpl transactionInsertUpdate START");
		// return md.transactionInsertUpdate();	//transaction 안걸린 것
		return md.transactionInsertUpdate3();	//transaction 걸리는 것
	}	
}
