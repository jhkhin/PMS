package com.oracle.s202350101.service.cyjSer;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.s202350101.dao.cyjDao.CyjDao;
import com.oracle.s202350101.model.BdFree;
import com.oracle.s202350101.model.BdFreeComt;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CyjServiceImpl implements CyjService {

	private final CyjDao cd;

	// 총 갯수
	@Override
	public int totalBdFree() {
		System.out.println("CyjServiceImpl totalBdFree Start");
		int totalBdFree = cd.totalBdFree();
		System.out.println("CyjServiceImpl totalBdFree-> " + totalBdFree);
		
		return totalBdFree;
	}

	// 추천수 가장 높은 row 3개 
	@Override
	public List<BdFree> goodRow(BdFree bdFree) {
		System.out.println("CyjServiceImpl goodRow Start");
		List<BdFree> goodList = cd.goodList(bdFree);
		System.out.println("CyjServiceImpl goodList-> " + goodList);
		
		return goodList;
	}

	// 전체 리스트
	@Override
	public List<BdFree> listBdFree(BdFree bdFree) {
		System.out.println("CyjServiceImpl listBdFree start");
		List<BdFree> listBdFree = cd.listBdFree(bdFree);
		System.out.println("CyjServiceImpl listBdFree-> " + listBdFree);
		
		return listBdFree;
	}

// ------------------------------------------------------------------
	
	// 새 글 입력 
	@Override
	public int insertBdFree(BdFree bdFree) {
		System.out.println("CyjServiceImpl insertBdFree start");
		int insertBdFree = cd.insertBdFree(bdFree);
		System.out.println("CyjServiceImpl insertBdFree-> " + insertBdFree);
		
		return insertBdFree;
	}
		
// -------------------------------------------------------------
		
	// 상세페이지
	@Override
	public BdFree bdFreeContent(int doc_no) {
		System.out.println("CyjServiceImpl bdFreeContent start");
		BdFree bdFreeContent = cd.bdFreeContent(doc_no);
		System.out.println("CyjServiceImpl bdFreeContent-> " + bdFreeContent);
		
		return bdFreeContent;
	}
	
	// 조회수 
	@Override
	public int bdCount(int doc_no) {
		System.out.println("CyjServiceImpl bdCount start");
		int bdCount = cd.bdCount(doc_no);
		System.out.println("CyjServiceImpl bdCount-> " + bdCount);
		
		return bdCount;
	}
		
// -------------------------------------------------------------

	// 수정
	@Override
	public int bdFreeUpdate2(BdFree bdFree) {
		System.out.println("CyjServiceImpl bdFreeUpdate2 start");
		int bdFreeUpdate2 = cd.bdFreeUpdate2(bdFree);
		System.out.println("CyjServiceImpl bdFreeUpdate2-> " + bdFreeUpdate2);
		
		return bdFreeUpdate2;
	}

// -------------------------------------------------------------

	// 삭제
	@Override
	public int boardDelete(int doc_no) {
		System.out.println("CyjServiceImpl boardDelete start");
		int boardDelete = cd.boardDelete(doc_no);
		System.out.println("CyjServiceImpl boardDelete-> " + boardDelete);
		
		return boardDelete;
	}

// -------------------------------------------------------------

	// 추천수 +1
	@Override
	public int goodCount(int doc_no) {
		System.out.println("CyjServiceImpl goodCount start");
		int gc = cd.goodCount(doc_no);
		System.out.println("CyjServiceImpl gc-> " + gc);
		
		return gc;
	}

	// 추천수 갖고 오기 
	@Override
	public int goodCountView(int doc_no) {
		System.out.println("CyjServiceImpl goodCountView start");
		int gcv = cd.goodCountView(doc_no);
		System.out.println("CyjServiceImpl gcv-> " + gcv);
		
		return gcv;
	}
	
// ------------------------------------------------------------------------	
// ------------------------- 이벤트 게시판 ------------------------------------

	// 이벤트_총 갯수  
	@Override
	public int eventCount() {
		System.out.println("CyjServiceImpl eventCount start");
		int eventTotal = cd.eventTotal();
		System.out.println("CyjServiceImpl eventTotal-> " + eventTotal);
		
		return eventTotal;
	}

	// 이벤트_전체 리스트 
	@Override
	public List<BdFree> eventList(BdFree bdFree) {
		System.out.println("CyjServiceImpl eventList start");
		List<BdFree> listEvent = cd.listEvent(bdFree);
		System.out.println("CyjServiceImpl listEvent-> " + listEvent);
		
		return listEvent;
	}
	
	// 이벤트_추천수 가장 높은 row 3개 
	@Override
	public List<BdFree> eventCount(BdFree bdFree) {
		System.out.println("CyjServiceImpl eventCount start");
		List<BdFree> bList = cd.eventGood(bdFree);
		System.out.println("CyjServiceImpl bList.size()-> " + bList.size());
		
		return bList;
	}

// ------------------------------------------------------------------------	

	// 이벤트_상세 
	@Override
	public BdFree eventContent(int doc_no) {
		System.out.println("CyjServiceImpl eventContent start");
		BdFree content = cd.eventContent(doc_no);
		System.out.println("CyjServiceImpl content-> " + content);
		
		return content;
	}
	
	// 이벤트_조회수 
	@Override
	public int eventBdCount(int doc_no) {
		System.out.println("CyjServiceImpl eventBdCount start");
		int eventCount = cd.eventCount(doc_no);
		System.out.println("CyjServiceImpl eventCount-> " + eventCount);
		
		return eventCount;
	}
	
	// 이벤트_댓글리스트
	@Override
	public List<BdFreeComt> eventComt(int doc_no) {
		System.out.println("CyjServiceImpl eventComt start");
		List<BdFreeComt> comt = cd.eventComt(doc_no);
		System.out.println("CyjServiceImpl comt-> " + comt);
		
		return comt;
	}
	
	// 이벤트_댓글입력 
	@Override
	public int ajaxComt(BdFreeComt bdFreeComt) {
		System.out.println("CyjServiceImpl ajaxComt start");
		int comtInsert = cd.ajaxComt(bdFreeComt);
		System.out.println("CyjServiceImpl comtInsert-> " + comtInsert);
		
		return comtInsert;
	}

	// 이벤트_입력한 댓글 갖고 오기 
	@Override
	public List<BdFreeComt> eventSelect(BdFreeComt bdFreeComt) {
		System.out.println("CyjServiceImpl eventSelect start");
		List<BdFreeComt> eventSelect = cd.eventSelect(bdFreeComt);
		System.out.println("CyjServiceImpl eventSelect-> " + eventSelect);
		
		return eventSelect;
	}
	
// ------------------------------------------------------------------------	

	// 이벤트_새 글 입력
	@Override
	public int eventInsert(BdFree bdFree) {
		System.out.println("CyjServiceImpl eventInsert start");
		int eventInsert = cd.eventInsert(bdFree);
		System.out.println("CyjServiceImpl eventInsert-> " + eventInsert);
		
		return eventInsert;
	}

// ------------------------------------------------------------------------	
	
	// 이벤트_수정
	@Override
	public int eventUpdate(BdFree bdFree) {
		System.out.println("CyjServiceImpl eventUpdate start");
		int eventUpdate = cd.eventUpdate(bdFree);
		System.out.println("CyjServiceImpl eventUpdate-> " + eventUpdate);
		
		return eventUpdate;
	}

// ------------------------------------------------------------------------	

	// 이벤트_삭제
	@Override
	public int eventDelete(int doc_no) {
		System.out.println("CyjServiceImpl eventDelete start");
		int delete = cd.eventDelete(doc_no);
		System.out.println("CyjServiceImpl delete-> " + delete);
		
		return delete;
	}

// ------------------------------------------------------------------------	
// ------------------------- 자유 게시판 ------------------------------------

	// 자유_총 갯수
	@Override
	public int freeTotal() {
		System.out.println("CyjServiceImpl freeTotal start");
		int freeTotal = cd.freeTotal();
		System.out.println("CyjServiceImpl freeTotal-> " + freeTotal);
		
		return freeTotal;
	}

	// 자유_추천수 가장 높은 row 3개 
	@Override
	public List<BdFree> freeRow() {
		System.out.println("CyjServiceImpl freeRow start");
		List<BdFree> freeList = cd.freeList();
		System.out.println("CyjServiceImpl freeList-> " + freeList);
		
		return freeList;
	}

	// 자유_전체 리스트
	@Override
	public List<BdFree> freeList(BdFree bdFree) {
		System.out.println("CyjServiceImpl freeList start");
		List<BdFree> freeTotalList = cd.freeTotalList(bdFree);
		System.out.println("CyjServiceImpl freeTotalList.size()-> " + freeTotalList.size());
		
		return freeTotalList;
	}
	
// ------------------------------------------------------------------------	

	// 자유_상세
	@Override
	public BdFree freeContent(int doc_no) {
		System.out.println("CyjServiceImpl freeContent start");
		BdFree freeContent = cd.freeContent(doc_no);
		System.out.println("CyjServiceImpl freeContent-> " + freeContent);
		
		return freeContent;
	}

	// 자유_조회수 증가
	@Override
	public int freeCount(int doc_no) {
		System.out.println("CyjServiceImpl freeCount start");
		int freeCount = cd.freeCount(doc_no);
		System.out.println("CyjServiceImpl freeCount-> " + freeCount);
		
		return freeCount;
	}

	// 자유_댓글리스트
	@Override
	public List<BdFree> freeComtList(int doc_no) {
		System.out.println("CyjServiceImpl freeComtList start");
		List<BdFree> freeComtList = cd.freeComtList(doc_no);
		System.out.println("CyjServiceImpl freeComtList-> " + freeComtList);
		
		return freeComtList;
	}

	// 자유_댓글 입력
	@Override
	public int ajaxFreeComt(BdFreeComt bdFreeComt) {
		System.out.println("CyjServiceImpl ajaxfreeComt start");
		int ajaxFree = cd.ajaxFreeComt(bdFreeComt);
		System.out.println("CyjServiceImpl ajaxFree-> " + ajaxFree);
		
		return ajaxFree;
	}

	// 자유_입력한 댓글 갖고 옴
	@Override
	public List<BdFreeComt> freeComtList(BdFreeComt bdFreeComt) {
		System.out.println("CyjServiceImpl freeComtList start");
		List<BdFreeComt> freeSelect = cd.freeSelect(bdFreeComt);
		System.out.println("CyjServiceImpl freeSelect-> " + freeSelect);
		
		return freeSelect;
	}
	
	// 자유_추천수 +1 올리기
	@Override
	public int freeGoodCount(int doc_no) {
		System.out.println("CyjServiceImpl freeGoodCount start");
		int freeCount = cd.freeCountUp(doc_no);
		System.out.println("CyjServiceImpl freeCount-> " + freeCount);
		
		return freeCount;
	}

	// 자유_올린 추천수 갖고 오기 
	@Override
	public int freeGoodGet(int doc_no) {
		System.out.println("CyjServiceImpl freeGoodGet start");
		int countGet = cd.countGet(doc_no);
		System.out.println("CyjServiceImpl countGet-> " + countGet);
		
		return countGet;
	}
	
// ------------------------------------------------------------------------	
	
	// 자유_새 글 입력 
	@Override
	public int freeInsert(BdFree bdFree) {
		System.out.println("CyjServiceImpl freeInsert start");
		int freeInsert = cd.freeInsert(bdFree);
		System.out.println("CyjServiceImpl freeInsert-> " + freeInsert);
		
		return freeInsert;
	}
	
// ------------------------------------------------------------------------		

	// 자유_수정
	@Override
	public int freeUpdate(BdFree bdFree) {
		System.out.println("CyjServiceImpl freeUpdate start");
		int freeUpdate = cd.bdFreeUpdate(bdFree);
		System.out.println("CyjServiceImpl freeUpdate-> " + freeUpdate);
		
		return freeUpdate;
	}

// ------------------------------------------------------------------------		

	// 자유_삭제
	@Override
	public int freeDelete(int doc_no) {
		System.out.println("CyjServiceImpl freeDelete start");
		int delete = cd.freeDelete(doc_no);
		System.out.println("CyjServiceImpl delete-> " + delete);
		
		return delete;
	}



	
	
	

	
	

	
	





	
	
	
	
	
	
	
	
	
	
	
	
	
	

}