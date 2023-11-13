package com.example.oBootMybatis01.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.oBootMybatis01.model.Member1;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class Member1DaoImpl implements Member1Dao {
	
	private final PlatformTransactionManager transactionManager;
	//Mybatis DB 연동
	private final SqlSession session;
	
	/* @RequiredArgsConstructor(lombok) 사용하지 않을때
	@Autowired
	public public Member1DaoImpl(SqlSession session) {
		this.session = session;
	}
	*/
	
	@Override
	public int memCount(String id) {
		int result = 0;
		System.out.println("Member1DaoImpl id : " + id);
		try {
			result = session.selectOne("memCount", id);
		} catch (Exception e) {
			System.out.println("Member1DaoImpl memCount Exception : " + e.getMessage());
		}
		return result;
	}

	@Override
	public List<Member1> listMem(Member1 member1) {
		System.out.println("Member1DaoImpl listMem START");
		List<Member1> listMember1 = null;
		try {
			listMember1 = session.selectList("listMember1", member1);
		} catch (Exception e) {
			System.out.println("Member1DaoImpl listMem Exception : " +e.getMessage());
		}
		return listMember1;
	}
	
	//Transaction 작업 안되는것 -> DB에 강유6만 들어감
	@Override
	public int transactionInsertUpdate() {
		int result=0;
		System.out.println("Member1DaoImpl transactionInsertUpdate START");
		Member1 member1 = new Member1();
		Member1 member2 = new Member1();
		
		try {
			// 두개의 transaction Test 성공과 실패
			// 결론 -> SqlSession은 하나 실행 할때마다 자동 commit
			member1.setId("1005");
			member1.setPassword("2345");
			member1.setName("강유6");
			
			result = session.insert("insertMember1", member1);
			System.out.println("Member1DaoImpl transactionInsertUpdate member1 result : " + result);
			// 같은 PK로 실패 유도
			
			member2.setId("1005");
			member2.setPassword("3457");
			member2.setName("이순신7");
			result = session.insert("insertMember1", member2);
			System.out.println("Member1DaoImpl transactionInsertUpdate member2 result : " + result);
		} catch (Exception e) {
			System.out.println("Member1DaoImpl transactionInsertUpdate Exception e : " + e.getMessage());
			result = -1;
		}
		
		return result;
	}
	
	
	//  Transaction 작업 걸리는 것 -> DB에 강유6, 이순신7 둘다 안들어 감 
	@Override
	public int transactionInsertUpdate3() {
		int result=0;
		System.out.println("Member1DaoImpl transactionInsertUpdate START");
		Member1 member1 = new Member1();
		Member1 member2 = new Member1();
		
	TransactionStatus txStatus=
				transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			// 두개의 transaction Test 성공과 실패
			// 결론 -> SqlSession은 하나 실행 할때마다 자동 commit
			// Transaction transactionManager.getTransaction 관리를 가지고 상태에 따라 설정
			member1.setId("1005");
			member1.setPassword("2345");
			member1.setName("강유6");
			
			result = session.insert("insertMember1", member1);
			System.out.println("Member1DaoImpl transactionInsertUpdate member1 result : " + result);
			// 같은 PK로 실패 유도
			
			member2.setId("1005");
			member2.setPassword("3457");
			member2.setName("이순신7");
			result = session.insert("insertMember1", member2);
			System.out.println("Member1DaoImpl transactionInsertUpdate member2 result : " + result);
			transactionManager.commit(txStatus);
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			System.out.println("Member1DaoImpl transactionInsertUpdate Exception e : " + e.getMessage());
			result = -1;
		}
		
		return result;
	}

}