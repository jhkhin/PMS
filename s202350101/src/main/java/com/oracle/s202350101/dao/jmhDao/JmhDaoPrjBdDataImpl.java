package com.oracle.s202350101.dao.jmhDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.s202350101.model.BdDataComt;
import com.oracle.s202350101.model.BdDataGood;
import com.oracle.s202350101.model.Code;
import com.oracle.s202350101.model.PrjBdData;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JmhDaoPrjBdDataImpl implements JmhDaoPrjBdData {

	//Mybatis DB 연동
	private final SqlSession session;

	
	@Override
	public int totalCount() {
		
		System.out.println("JmhDaoImpl totalCount START...");
		int totalCnt = 0;				
		try {
			//---------------------------------------------------------
			totalCnt = session.selectOne("jmhPrjBdDataListTotalCount");
			//---------------------------------------------------------
			System.out.println("JmhDaoImpl totalCount totalCnt->"+totalCnt);
		} catch (Exception e) {
			System.out.println("JmhDaoImpl totalCount Exception->"+e.getMessage());
		}
		System.out.println("JmhDaoImpl totalCount END...");
		return totalCnt;
	}
	
	@Override
	public List<PrjBdData> boardList(PrjBdData prjBdData) {
		
		System.out.println("JmhDaoImpl boardList START...");
		List<PrjBdData> prjBdDataList = null;		
		try {
			//----------------------------------------------------------------
			prjBdDataList = session.selectList("jmhPrjBdDataList", prjBdData);
			//----------------------------------------------------------------
			if(prjBdDataList != null) {
				System.out.println("JmhDaoImpl boardList prjBdDataList.get(0).getSubject()->"+((PrjBdData) prjBdDataList.get(0)).getSubject());
			}else {
				System.out.println("SQL오류");
			}
		} catch (Exception e) {
			System.out.println("JmhDaoImpl boardList Exception->"+e.getMessage());
		}
		System.out.println("JmhDaoImpl boardList END...");
		return prjBdDataList;
	}

	@Override
	public List<Code> codeList(Code code) {
		
		System.out.println("JmhDaoImpl codeList START...");
		List<Code> reCodeList = null;		
		try {
			//------------------------------------------------------------
			reCodeList = session.selectList("jmhPrjBdDataCodeList", code);
			//------------------------------------------------------------
			System.out.println("reCodeList.size()->"+reCodeList.size());
			if(reCodeList.size() > 0) {
				//성공
				System.out.println("JmhDaoImpl codeList code->"+reCodeList.get(0).getCate_code());
				System.out.println("JmhDaoImpl codeList name->"+reCodeList.get(0).getCate_name());
			}else {
				System.out.println("SQL오류");
			}
		} catch (Exception e) {
			System.out.println("JmhDaoImpl codeList Exception->"+e.getMessage());
		}
		System.out.println("JmhDaoImpl codeList END...");
		return reCodeList;
	}

	@Override
	public int insertBoard(PrjBdData prjBdData) {
		
		System.out.println("JmhDaoImpl insertBoard START...");
		int resultCount = 0;		
		try {
			//-----------------------------------------------------------------
			resultCount = session.insert("jmhPrjBdDataInsertBoard", prjBdData);
			//-----------------------------------------------------------------
			System.out.println("resultCount->"+resultCount);
			if(resultCount > 0) {
				//성공
			}else {
				System.out.println("SQL오류");
			}
		} catch (Exception e) {
			System.out.println("JmhDaoImpl insertBoard Exception->"+e.getMessage());
		}
		System.out.println("JmhDaoImpl insertBoard END...");
		return resultCount;
	}

	@Override
	public PrjBdData selectBoard(PrjBdData prjBdData) {
		
		System.out.println("JmhDaoImpl selectBoard START...");
		PrjBdData selectPrjBdData = null;		
		try {
			//------------------------------------------------------------------------
			selectPrjBdData = session.selectOne("jmhPrjBdDataSelectBoard", prjBdData);
			//------------------------------------------------------------------------
			if(selectPrjBdData != null) {
				//성공
				System.out.println("selectPrjBdData.getSubject()->"+selectPrjBdData.getSubject());
			}else {
				System.out.println("SQL오류");
			}
		} catch (Exception e) {
			System.out.println("JmhDaoImpl selectBoard Exception->"+e.getMessage());
		}
		System.out.println("JmhDaoImpl selectBoard END...");
		return selectPrjBdData;
	}

	@Override
	public int updateBoard(PrjBdData prjBdData) {
		
		System.out.println("JmhDaoImpl updateBoard START...");
		int resultCount = 0;		
		try {
			//-----------------------------------------------------------------
			resultCount = session.update("jmhPrjBdDataUpdateBoard", prjBdData);
			//-----------------------------------------------------------------
			System.out.println("resultCount->"+resultCount);
			if(resultCount > 0) {
				//성공
			}else {
				System.out.println("SQL오류");
			}
		} catch (Exception e) {
			System.out.println("JmhDaoImpl updateBoard Exception->"+e.getMessage());
		}
		System.out.println("JmhDaoImpl updateBoard END...");
		return resultCount;
	}

	@Override
	public int deleteBoard(PrjBdData prjBdData) {

		System.out.println("JmhDaoImpl deleteBoard START...");
		int resultCount = 0;		
		try {
			//-----------------------------------------------------------------
			resultCount = session.delete("jmhPrjBdDataDeleteBoard", prjBdData);
			//-----------------------------------------------------------------
			System.out.println("JmhDaoImpl resultCount->"+resultCount);
			if(resultCount > 0) {
				//성공
			}else {
				System.out.println("SQL오류");
			}
		} catch (Exception e) {
			System.out.println("JmhDaoImpl deleteBoard Exception->"+e.getMessage());
		}
		System.out.println("JmhDaoImpl deleteBoard END...");
		return resultCount;
	}

	@Override
	public int readCount(PrjBdData prjBdData) {
		
		System.out.println("JmhDaoImpl readCount START...");
		int resultCount = 0;		
		try {
			//----------------------------------------------------------------
			resultCount = session.update("jmhPrjBdDataReadCount", prjBdData);
			//----------------------------------------------------------------
			System.out.println("resultCount->"+resultCount);
			if(resultCount > 0) {
				//성공
			}else {
				System.out.println("SQL오류");
			}
		} catch (Exception e) {
			System.out.println("JmhDaoImpl readCount Exception->"+e.getMessage());
		}
		System.out.println("JmhDaoImpl readCount END...");
		return resultCount;
	}

	@Override
	public BdDataGood checkGoodList(BdDataGood bdDataGood) {

		System.out.println("JmhDaoImpl checkGoodList START...");
		BdDataGood resultBdDataGood = null;		
		try {
			//----------------------------------------------------------------------------
			resultBdDataGood = session.selectOne("jmhPrjBdDataCheckGoodList", bdDataGood);
			//----------------------------------------------------------------------------
			if(resultBdDataGood != null) {
				//성공
				System.out.println("추천글 번호->"+resultBdDataGood.getDoc_no());
				System.out.println("추천글 프로젝트ID->"+resultBdDataGood.getProject_id());
				System.out.println("추천자 ID->"+resultBdDataGood.getUser_id());
			} else {
				System.out.println("추천자 정보 없음");
			}
		} catch (Exception e) {
			System.out.println("JmhDaoImpl checkGoodList Exception->"+e.getMessage());
		}
		System.out.println("JmhDaoImpl checkGoodList END...");
		return resultBdDataGood;
	}

	@Override
	public int insertGoodList(BdDataGood bdDataGood) {
		
		System.out.println("JmhDaoImpl insertGoodList START...");
		int resultCount = 0;		
		try {
			//---------------------------------------------------------------------
			resultCount = session.insert("jmhPrjBdDataInsertGoodList", bdDataGood);
			//---------------------------------------------------------------------
			System.out.println("resultCount->"+resultCount);
			if(resultCount > 0) {
				//성공
			}else {
				System.out.println("SQL오류");
			}
		} catch (Exception e) {
			System.out.println("JmhDaoImpl insertGoodList Exception->"+e.getMessage());
		}
		System.out.println("JmhDaoImpl insertGoodList END...");
		return resultCount;
	}
	
	@Override
	public List<BdDataGood> selectGoodList(BdDataGood bdDataGood) {
		
		System.out.println("JmhDaoImpl insertGoodList START...");
		List<BdDataGood> resultBdDataGoodList = null;		
		try {
			//-----------------------------------------------------------------------------------
			resultBdDataGoodList = session.selectList("jmhPrjBdDataSelectGoodList", bdDataGood);
			//-----------------------------------------------------------------------------------
			System.out.println("resultBdDataGoodList.size()->"+resultBdDataGoodList.size());
		} catch (Exception e) {
			System.out.println("JmhDaoImpl insertGoodList Exception->"+e.getMessage());
		}
		System.out.println("JmhDaoImpl insertGoodList END...");
		return resultBdDataGoodList;
	}
	

	@Override
	public int updateGoodCount(PrjBdData prjBdData) {

		System.out.println("JmhDaoImpl updateGoodCount START...");
		int resultCount = 0;		
		try {
			//---------------------------------------------------------------------
			resultCount = session.update("jmhPrjBdDataUpdateGoodCount", prjBdData);
			//---------------------------------------------------------------------
			System.out.println("resultCount->"+resultCount);
			if(resultCount > 0) {
				//성공
			}else {
				System.out.println("SQL오류");
			}
		} catch (Exception e) {
			System.out.println("JmhDaoImpl updateGoodCount Exception->"+e.getMessage());
		}
		System.out.println("JmhDaoImpl updateGoodCount END...");
		return resultCount;
	}

	@Override
	public int updateOtherReply(PrjBdData prjBdData) {

		System.out.println("JmhDaoImpl updateOtherReply START...");
		int resultCount = 0;
		try {
			//-------------------------------------------------------------
			resultCount = session.update("jmhUpdateOtherReply", prjBdData);
			//-------------------------------------------------------------
			System.out.println("resultCount->"+resultCount);
			if(resultCount > 0) {
				//성공
			}else {
				System.out.println("SQL오류");
			}
		} catch (Exception e) {
			System.out.println("JmhDaoImpl updateOtherReply Exception->"+e.getMessage());
		}
		System.out.println("JmhDaoImpl updateOtherReply END...");
		return resultCount;
	}

	@Override
	public int insertComment(BdDataComt bdDataComt) {
		
		System.out.println("JmhDaoImpl insertComment START...");
		int resultCount = 0;	
		try {
			//--------------------------------------------------------------------
			resultCount = session.insert("jmhPrjBdDataInsertComment", bdDataComt);
			//--------------------------------------------------------------------
			System.out.println("resultCount->"+resultCount);
			if(resultCount > 0) {
				//성공
			}else {
				System.out.println("SQL오류");
			}
		} catch (Exception e) {
			System.out.println("JmhDaoImpl insertComment Exception->"+e.getMessage());
		}
		System.out.println("JmhDaoImpl insertComment END...");
		return resultCount;
	}

	@Override
	public List<BdDataComt> selectCommentList(BdDataComt bdDataComt) {
		
		System.out.println("JmhDaoImpl selectCommentList START...");
		List<BdDataComt> resultBdDataCommentList = null;		
		try {
			//----------------------------------------------------------------------------------------
			resultBdDataCommentList = session.selectList("jmhPrjBdDataSelectCommentList", bdDataComt);
			//----------------------------------------------------------------------------------------
			System.out.println("resultBdDataGoodList.size()->"+resultBdDataCommentList.size());
		} catch (Exception e) {
			System.out.println("JmhDaoImpl selectCommentList Exception->"+e.getMessage());
		}
		System.out.println("JmhDaoImpl selectCommentList END...");
		return resultBdDataCommentList;
	}

	@Override
	public int deleteComment(BdDataComt bdDataComt) {
		
		System.out.println("JmhDaoImpl deleteComment START...");
		int resultCount = 0;	
		try {
			//--------------------------------------------------------------------
			resultCount = session.insert("jmhPrjBdDataDeleteComment", bdDataComt);
			//--------------------------------------------------------------------
			System.out.println("resultCount->"+resultCount);
			if(resultCount > 0) {
				//성공
			}else {
				System.out.println("SQL오류");
			}
		} catch (Exception e) {
			System.out.println("JmhDaoImpl deleteComment Exception->"+e.getMessage());
		}
		System.out.println("JmhDaoImpl deleteComment END...");
		return resultCount;
	}

}