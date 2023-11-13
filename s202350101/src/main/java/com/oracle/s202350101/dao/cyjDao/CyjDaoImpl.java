package com.oracle.s202350101.dao.cyjDao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.s202350101.model.BdFree;
import com.oracle.s202350101.model.BdFreeComt;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CyjDaoImpl implements CyjDao {
	
	private final SqlSession session;

	// 총 갯수
	@Override
	public int totalBdFree() {
		System.out.println("CyjDaoImpl totalBdFree Start");
	
		int totalBdFree = 0;
		try {
			totalBdFree = session.selectOne("cyBdFreeTotal");
			System.out.println("CyjDaoImpl totalBdFree-> " + totalBdFree);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl totalBdFree Exception-> " + e.getMessage());
		}	
		return totalBdFree;
	}
	
	// 추천수 가장 높은 row 3개
	@Override
	public List<BdFree> goodList(BdFree bdFree) {
		System.out.println("CyjDaoImpl goodList Start");
		
		List<BdFree> goodList = new ArrayList<BdFree>();
		try {
			goodList = session.selectList("cyGoodList", bdFree);
			System.out.println("CyjDaoImpl goodList-> " + goodList);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl goodList Exception-> " + e.getMessage());
		}
		return goodList;
	}

	// 전체 리스트
	@Override
	public List<BdFree> listBdFree(BdFree bdFree) {
		System.out.println("CyjDaoImpl listBdFree Start");
		
		List<BdFree> bdFreeList = new ArrayList<BdFree>();
		try {
			bdFreeList = session.selectList("cyBdFreeList", bdFree);
			System.out.println("CyjDaoImpl listBdFree bdFreeList.size()-> " + bdFreeList.size());
		} catch (Exception e) {
			System.out.println("CyjDaoImpl listBdFree Exception-> " + e.getMessage());
		}
		return bdFreeList;
	}
	
// -------------------------------------------------------------------
	
	// 새 글 작성
	@Override
	public int insertBdFree(BdFree bdFree) {
		System.out.println("CyjDaoImpl insertBdFree Start");
		
		int insertBdFree = 0;
		try {
			if (bdFree.getAttach_name() == null) bdFree.setAttach_name("");
			if (bdFree.getAttach_path() == null) bdFree.setAttach_path("");			
			insertBdFree = session.insert("cyBdFreeInsert", bdFree);
			System.out.println("CyjDaoImpl insertBdFree-> " + insertBdFree);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl insertBdFree Exception-> " + e.getMessage());
		}		
		return insertBdFree;
	}

// ----------------------------------------------------------------
	
	// 상세페이지
	@Override
	public BdFree bdFreeContent(int doc_no) {
		System.out.println("CyjDaoImpl bdFreeContent Start");
		
		BdFree bdFreeContent = null;
		try {
			bdFreeContent = session.selectOne("cybdFreeContent", doc_no);
			System.out.println("CyjDaoImpl bdFreeContent-> " + bdFreeContent);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl bdFreeContent Exception-> " + e.getMessage());
		}
		return bdFreeContent;
	}
	
	// 조회수 
	@Override
	public int bdCount(int doc_no) {
		System.out.println("CyjDaoImpl bdCount Start");
		
		int bdCount = 0;
		try {
			bdCount = session.update("cyBdCount", doc_no);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl bdCount Exception-> " + e.getMessage());
		}
		return bdCount;
	}

// ----------------------------------------------------------------

	// 수정 
	@Override
	public int bdFreeUpdate2(BdFree bdFree) {
		System.out.println("CyjDaoImpl bdFreeUpdate2 Start");

		// 수정 안됐을 때 : mapper의 sql이 잘못된 건지 DB에서 확인하기 전에 값을 갖고 오는지 확인하기 위함 -> DB와 분리해서 비교하는 작업  
		System.out.println("CyjDaoImpl bdFreeUpdate2  bdFree.getDoc_no()-> "   + bdFree.getDoc_no());
		System.out.println("CyjDaoImpl bdFreeUpdate2  bdFree.getSubject()-> "  + bdFree.getSubject());
		System.out.println("CyjDaoImpl bdFreeUpdate2  bdFree.getDoc_body()-> " + bdFree.getDoc_body());

		int bdFreeUpdate2 = 0;
		try {
			bdFreeUpdate2 = session.update("cybdFreeUpdate2", bdFree);
			System.out.println("CyjDaoImpl bdFreeUpdate2-> " + bdFreeUpdate2);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl bdFreeUpdate2 Exception-> " + e.getMessage());
		}
		return bdFreeUpdate2;
	}

// ----------------------------------------------------------------

	// 삭제
	@Override
	public int boardDelete(int doc_no) {
		System.out.println("CyjDaoImpl boardDelete Start");
		
		int boardDelete = 0;
		try {
			boardDelete = session.delete("cyboardDelete", doc_no);
			System.out.println("CyjDaoImpl boardDelete-> " + boardDelete);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl boardDelete Exception-> " + e.getMessage());
		}
		return boardDelete;
	}
	
// ----------------------------------------------------------------

	// 추천 +1
	@Override
	public int goodCount(int doc_no) {
		System.out.println("CyjDaoImpl goodCount Start");
		
		int gc = 0;
		try {
			gc = session.update("cyGoodCount", doc_no);
			System.out.println("CyjDaoImpl gc-> " + gc);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl goodCount Exception-> " + e.getMessage());
		}
		return gc;
	}

	// 추천수 갖고 오기 
	@Override
	public int goodCountView(int doc_no) {
		System.out.println("CyjDaoImpl goodCountView Start");
		
		int gcv = 0;
		try {
			gcv = session.selectOne("cyGoodCountView", doc_no);
			System.out.println("CyjDaoImpl gcv-> " + gcv);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl goodCountView Exception-> " + e.getMessage());
		}
		return gcv;
	}

// ------------------------------------------------------------------------	
// ------------------------- 이벤트 게시판 ------------------------------------

	// 이벤트_총 갯수 
	@Override
	public int eventTotal() {
		System.out.println("CyjDaoImpl eventTotal Start");
		
		int total = 0;
		try {
			total = session.selectOne("cyEventTotal");
			System.out.println("CyjDaoImpl total-> " + total);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl eventTotal Exception-> " + e.getMessage());
		}
		return total;
	}

	// 이벤트_리스트 
	@Override
	public List<BdFree> listEvent(BdFree bdFree) {
		System.out.println("CyjDaoImpl listEvent Start");
		
		List<BdFree> listEvent = new ArrayList<BdFree>();
		try {
			listEvent = session.selectList("cyEventList", bdFree);
			System.out.println("CyjDaoImpl listEvent-> " + listEvent);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl listEvent Exception-> " + e.getMessage());
		}
		return listEvent;
	}

	// 이벤트_추천수 가장 높은 row 3개 
	@Override
	public List<BdFree> eventGood(BdFree bdFree) {
		System.out.println("CyjDaoImpl eventGood Start");
		
		List<BdFree> eventList = new ArrayList<BdFree>();
		try {
			eventList = session.selectList("cyEventGoodList", bdFree);
			System.out.println("CyjDaoImpl eventList-> " + eventList);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl eventList Exception-> " + e.getMessage());
		}
		return eventList;
	}
	
// ------------------------------------------------------------------------		

	// 이벤트_상세 
	@Override
	public BdFree eventContent(int doc_no) {
		System.out.println("CyjDaoImpl eventContent Start");
		
		BdFree content = null;
		try {
			content = session.selectOne("cyEventContent", doc_no);
			System.out.println("CyjDaoImpl content-> " + content);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl eventContent Exception-> " + e.getMessage());
		}
		return content;
	}

	// 이벤트_추천수
	@Override
	public int eventCount(int doc_no) {
		System.out.println("CyjDaoImpl eventCount Start");
		
		int count = 0;
		try {
			count = session.update("cyEventCount", doc_no);
			System.out.println("CyjDaoImpl count-> " + count);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl eventCount Exception-> " + e.getMessage());
		}
		return count;
	}

	// 이벤트_댓글리스트
	@Override
	public List<BdFreeComt> eventComt(int doc_no) {
		System.out.println("CyjDaoImpl eventComt Start");
		
		List<BdFreeComt> comt = new ArrayList<BdFreeComt>();
		try {
			comt = session.selectList("cyEventComt", doc_no);
			System.out.println("CyjDaoImpl comt-> " + comt);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl eventCount Exception-> " + e.getMessage());
		}
		return comt;
	}
	
	// 이벤트_댓글입력
	@Override
	public int ajaxComt(BdFreeComt bdFreeComt) {
		System.out.println("CyjDaoImpl ajaxComt Start");
		
		int ajaxInsert = 0;
		try {
			ajaxInsert = session.insert("cyAjaxInsert", bdFreeComt);
			System.out.println("CyjDaoImpl ajaxInsert-> " + ajaxInsert);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl ajaxComt Exception-> " + e.getMessage());
		}
		return ajaxInsert;
	}
	
	// 이벤트_입력한 댓글 갖고 오기 
	@Override
	public List<BdFreeComt> eventSelect(BdFreeComt bdFreeComt) {
		System.out.println("CyjDaoImpl eventSelect Start");
		
		List<BdFreeComt> comtSelect = new ArrayList<BdFreeComt>();
		try {
			comtSelect = session.selectList("cyComtSelect", bdFreeComt);
			System.out.println("CyjDaoImpl comtSelect-> " + comtSelect);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl eventSelect Exception-> " + e.getMessage());
		}	
		return comtSelect;
	
	}
	
	
// ------------------------------------------------------------------------		

	// 이벤트_새 글 입력 
	@Override
	public int eventInsert(BdFree bdFree) {
		System.out.println("CyjDaoImpl eventInsert Start");
		
		int eventInsert = 0;
		try {
			if (bdFree.getAttach_name() == null) bdFree.setAttach_name("");
			if (bdFree.getAttach_path() == null) bdFree.setAttach_path("");
			eventInsert = session.insert("cyEventInsert", bdFree);
			System.out.println("CyjDaoImpl eventInsert-> " + eventInsert);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl eventInsert Exception-> " + e.getMessage());
		}
		return eventInsert;
	}
	
// ------------------------------------------------------------------------		
	
	// 이벤트_수정
	@Override
	public int eventUpdate(BdFree bdFree) {
		System.out.println("CyjDaoImpl eventUpdate Start");
		
		int eventUpdate = 0;
		try {
			eventUpdate = session.update("cyEventUpdate", bdFree);
			System.out.println("CyjDaoImpl eventUpdate-> " + eventUpdate);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl eventUpdate Exception-> " + e.getMessage());
		}		
		return eventUpdate;
	}

// ------------------------------------------------------------------------		

	// 이벤트_삭제
	@Override
	public int eventDelete(int doc_no) {
		System.out.println("CyjDaoImpl eventDelete Start");
		
		int eventDelete = 0;
		try {
			eventDelete = session.delete("cyEventDelete", doc_no);
			System.out.println("CyjDaoImpl eventDelete-> " + eventDelete);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl eventDelete Exception-> " + e.getMessage());
		}
		return eventDelete;
	}

// ------------------------------------------------------------------------	
// ------------------------- 자유 게시판 ------------------------------------

	// 자유_총 갯수
	@Override
	public int freeTotal() {
		System.out.println("CyjDaoImpl freeTotal Start");
		
		int freeTotal = 0;
		try {
			freeTotal = session.selectOne("cyFreeTotal");
			System.out.println("CyjDaoImpl freeTotal-> " + freeTotal);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl freeTotal Exception-> " + e.getMessage());
		}
		return freeTotal;
	}
	
	// 자유_추천수 가장 높은 row 3개
	@Override
	public List<BdFree> freeList() {
		System.out.println("CyjDaoImpl freeList Start");
		
		List<BdFree> freeList = new ArrayList<BdFree>();
		try {
			freeList = session.selectList("cyFreeList");
			System.out.println("CyjDaoImpl freeList-> " + freeList);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl freeList Exception-> " + e.getMessage());
		}
		return freeList;
	}

	// 자유_전체 리스트
	@Override
	public List<BdFree> freeTotalList(BdFree bdFree) {
		System.out.println("CyjDaoImpl freeTotalList Start");
		
		List<BdFree> totalList = new ArrayList<BdFree>();
		try {
			totalList = session.selectList("cyTotalList", bdFree);
			System.out.println("CyjDaoImpl totalList.size()-> " + totalList.size());
		} catch (Exception e) {
			System.out.println("CyjDaoImpl totalList Exception-> " + e.getMessage());
		}
		return totalList;
	}

// ------------------------------------------------------------------------	

	// 자유_상세
	@Override
	public BdFree freeContent(int doc_no) {
		System.out.println("CyjDaoImpl freeContent Start");
		
		BdFree content = null;
		try {
			content = session.selectOne("cyFreeContent", doc_no);
			System.out.println("CyjDaoImpl content-> " + content);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl content Exception-> " + e.getMessage());
		}
		return content;
	}

	// 자유_조회수 증가
	@Override
	public int freeCount(int doc_no) {
		System.out.println("CyjDaoImpl freeCount Start");
		
		int freeCount = 0;
		try {
			freeCount = session.update("cyFreeCount", doc_no);
			System.out.println("CyjDaoImpl freeCount-> " + freeCount);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl freeCount Exception-> " + e.getMessage());
		}
		return freeCount;
	}

	// 자유_댓글리스트
	@Override
	public List<BdFree> freeComtList(int doc_no) {
		System.out.println("CyjDaoImpl freeComtList Start");
		
		List<BdFree> freeComt = new ArrayList<BdFree>();
		try {
			freeComt = session.selectList("cyFreeComtList", doc_no);
			System.out.println("CyjDaoImpl freeComt-> " + freeComt);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl freeComtList Exception-> " + e.getMessage());
		}
		return freeComt;
	}
	
	// 자유_댓글 입력
	@Override
	public int ajaxFreeComt(BdFreeComt bdFreeComt) {
		System.out.println("CyjDaoImpl ajaxFreeComt Start");
		
		int ajaxFreeComtInsert = 0;
		try {
			ajaxFreeComtInsert = session.insert("cyAjaxFreeComtInsert", bdFreeComt);
			System.out.println("CyjDaoImpl ajaxFreeComtInsert-> " + ajaxFreeComtInsert);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl ajaxFreeComt Exception-> " + e.getMessage());
		}
		return ajaxFreeComtInsert;
	}

	// 자유_입력한 댓글 갖고 옴
	@Override
	public List<BdFreeComt> freeSelect(BdFreeComt bdFreeComt) {
		System.out.println("CyjDaoImpl freeSelect Start");
		
		List<BdFreeComt> freeComtSelect = new ArrayList<BdFreeComt>();
		try {
			freeComtSelect = session.selectList("cyAjaxComtSelect", bdFreeComt);
			System.out.println("CyjDaoImpl freeComtSelect-> " + freeComtSelect);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl freeSelect Exception-> " + e.getMessage());
		}
		return freeComtSelect;
	}
	
	// 자유_추천수 +1 올리기
	@Override
	public int freeCountUp(int doc_no) {
		System.out.println("CyjDaoImpl freeCountUp Start");
		
		int freeCount = 0;
		try {
			freeCount = session.update("cyFreeCountUp", doc_no);
			System.out.println("CyjDaoImpl freeCount-> " + freeCount);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl freeCountUp Exception-> " + e.getMessage());
		}
		return freeCount;
	}

	// 자유_올린 추천수 갖고 오기 
	@Override
	public int countGet(int doc_no) {
		System.out.println("CyjDaoImpl countGet Start");
		
		int count = 0;
		try {
			count = session.selectOne("cyFreeCountGet", doc_no);
			System.out.println("CyjDaoImpl count-> " + count);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl countGet Exception-> " + e.getMessage());
		}
		return count;
	}

// ------------------------------------------------------------------------	

	// 자유_새 글 입력    
	@Override
	public int freeInsert(BdFree bdFree) {
		System.out.println("CyjDaoImpl freeInsert Start");
		
		int freeInsert = 0;
		try {
			if (bdFree.getAttach_name() == null) bdFree.setAttach_name("");;
			if (bdFree.getAttach_path() == null) bdFree.setAttach_path("");
			freeInsert = session.insert("cyFreeInsert", bdFree);
			System.out.println("CyjDaoImpl freeInsert-> " + freeInsert);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl freeInsert Exception-> " + e.getMessage());
		}
		return freeInsert;
	}

// ------------------------------------------------------------------------	

	// 자유_수정
	@Override
	public int bdFreeUpdate(BdFree bdFree) {
		System.out.println("CyjDaoImpl bdFreeUpdate Start");
		
		int freeUpdate = 0;
		try {
			freeUpdate = session.update("cyFreeUpdate", bdFree);
			System.out.println("CyjDaoImpl freeUpdate-> " + freeUpdate);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl bdFreeUpdate Exception-> " + e.getMessage());
		}
		return freeUpdate;
	}

// ------------------------------------------------------------------------	

	// 자유_삭제
	@Override
	public int freeDelete(int doc_no) {
		System.out.println("CyjDaoImpl freeDelete Start");
		
		int freeDelete = 0;
		try {
			freeDelete = session.delete("cyFreeDelete", doc_no);
			System.out.println("CyjDaoImpl freeDelete-> " + freeDelete);
		} catch (Exception e) {
			System.out.println("CyjDaoImpl bdFreeUpdate Exception-> " + e.getMessage());
		}
		return freeDelete;
	}





	

	

	

	


	



	

	
	
	
	
	
	
	
	
	



		

		
	
	
	
	
	
	
	
	
	
	
	
	
	

}