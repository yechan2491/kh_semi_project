package friend.model.vo;

public class PageInfo {
	private int page;        		// 요청 페이지
	private int listCount;   		// 전체 게시글 수
	private int pageLimit;  		// 하단에 보이는 페이지 목록 수
	private int boardLimit;  		// 한 페이지에 보일 게시글 최대 수
	private int maxPage;     		// 가장 마지막 페이지
	private int startPage;   		// 목록 시작 값
	private int endPage;    		// 목록 끝 값
	
	public PageInfo(int page, int listCount, int pageLimit, int boardLimit) {
		this.page=page;
		this.listCount=listCount;
		this.pageLimit=pageLimit;
		this.boardLimit=boardLimit;
		
		this.maxPage=(int)Math.ceil((double)listCount/ boardLimit);
		
		this.startPage = (page - 1) / pageLimit * pageLimit + 1;
		
		this.endPage = startPage + pageLimit - 1;
		
		if(this.maxPage < this.endPage) {
			this.endPage = this.maxPage;
		}
		
		
		
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public int getBoardLimit() {
		return boardLimit;
	}

	public void setBoardLimit(int boardLimit) {
		this.boardLimit = boardLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "PageInfo [page=" + page + ", listCount=" + listCount + ", pageLimit=" + pageLimit + ", boardLimit="
				+ boardLimit + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
	
}
		

