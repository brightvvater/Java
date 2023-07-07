package bitedu.bipa.book.utils;

import java.util.List;

public class PageDTO<T> {

	private int currentPage; //현재페이지
	private int count; //전체 리스트 개수
	private int totalPageCount; //페이지당 보여줄 리스트의 개수
	private int range = 5; //보여줄 페이지의 범위(예 -5페이지씩 보이도록 하기)
	private int lastPage; //마지막 페이지
	private int firstPageInThisPage; //현재 페이지의 첫 페이지
	private int lastPageInThisPage; //현재 페이지의 마지막 페이지
	private List<T> content;
	
	public PageDTO(int curruentPage, int count,int totalPageCount, List<T> content) {
		this.currentPage = curruentPage;
		this.count = count;
		this.totalPageCount = totalPageCount;
		this.content = content;
		if(count%totalPageCount !=0) {
			this.lastPage = count/totalPageCount +1;
		}else {
			this.lastPage = count/totalPageCount;
		}
		
		if(currentPage%range !=0) {
			this.firstPageInThisPage = (currentPage/range)*range +1;
			this.lastPageInThisPage = (currentPage/range +1)*range;
		}else {
			this.firstPageInThisPage =(currentPage/range -1)*range+1;
			this.lastPageInThisPage = (currentPage/range)*range;
		}
		
		if(this.lastPage <this.lastPageInThisPage) {
			this.lastPageInThisPage = this.lastPage;
		}
		
	}
	

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	
	

	public int getRange() {
		return range;
	}


	public void setRange(int range) {
		this.range = range;
	}


	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}


	public int getFirstPageInThisPage() {
		return firstPageInThisPage;
	}


	public void setFirstPageInThisPage(int firstPageInThisPage) {
		this.firstPageInThisPage = firstPageInThisPage;
	}


	public int getLastPageInThisPage() {
		return lastPageInThisPage;
	}


	public void setLastPageInThisPage(int lastPageInThisPage) {
		this.lastPageInThisPage = lastPageInThisPage;
	}
	
	
	
	
	

}
