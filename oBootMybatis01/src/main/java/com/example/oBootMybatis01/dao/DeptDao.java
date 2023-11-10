package com.example.oBootMybatis01.dao;

import java.util.HashMap;
import java.util.List;

import com.example.oBootMybatis01.model.Dept;
import com.example.oBootMybatis01.model.DeptVO;

public interface DeptDao {

	public List<Dept> deptSelect();							//6-2. 부서코드 (코드, 부서명)

	public void insertDept(DeptVO deptVO);					// 14. Procedure 통한 Dept 입력후 VO 전달

	public void selListDept(HashMap<String, Object> map);	// 15. 부서조회(Cursor)
	
}
