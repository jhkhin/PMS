package com.example.oBootMybatis01.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Paging Class
public class Paging {
	private int currentPage = 1;	private int rowPage = 10;
	private int pageBlock = 10;		
	private int start;				private int end;
	private int startPage;			private int endPage;
	private int total;				private int totalPage;
	
	// currentPage1이 null 이면 초기값을 1로 설정해 놓아서 1로 세팅이 됨
	//				최초의 값 : 22			null
	public Paging(int total, String currentPage1) {
		this.total = total; //22
		if(currentPage1 != null) {
			this.currentPage = Integer.parseInt(currentPage1); // 2
		}
		//			1					10
		start = (currentPage - 1) * rowPage + 1;	// 시작시 1	11
		end = start + rowPage -1;					// 시작시 10	20
		//									  	  22/10			=  2.2 (올림해서 3)
		totalPage = (int) Math.ceil((double) total / rowPage); // 시작시 3	 
		//				2				2
		startPage = currentPage - (currentPage - 1) % pageBlock; // 시작시 1
		endPage = startPage + pageBlock - 1;	//10
		//	  10		14
		if(endPage > totalPage) {
			endPage = totalPage;	// 남은 페이지 방지
		}
	}
}
