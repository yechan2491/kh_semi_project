package total.model.vo;

public class Search {
	private String search;  // 조회시 카테고리 값 저장( ex. 의류, 신발, ...)
	
	public Search() {
		
	}

	public Search(String search) {
		super();
		this.search = search;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	@Override
	public String toString() {
		return "Search [search=" + search + "]";
	}
}
