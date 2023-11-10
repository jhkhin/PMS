package com.example.oBootMybatis01.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.oBootMybatis01.model.Dept;
import com.example.oBootMybatis01.model.DeptVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DeptDaoImpl implements DeptDao {
	private final SqlSession session;
	
	//6-2. 부서코드 (코드, 부서명)
	@Override
	public List<Dept> deptSelect() {
		List<Dept> deptList = null;
		System.out.println("DeptDaoImpl deptSelect START");
		try {
			deptList = session.selectList("tkSelectDept");
		}catch (Exception e) {
			System.out.println("DeptDaoImpl deptSelect Excption : " + e.getMessage());
		}
		return deptList;
	}

	// 14. Procedure 통한 Dept 입력후 VO 전달
	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("DeptDaoImpl insertDept START");
		session.selectOne("procDeptInsert", deptVO);	
	}
	
	// 15. 부서조회(Cursor)
	@Override
	public void selListDept(HashMap<String, Object> map) {
		System.out.println("DeptDaoImpl selListDept START");
		session.selectOne("procDeptList", map);
	}
}
