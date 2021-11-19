package marketplace.model.vo;

public class Search {
	private String searchCategory;  // 조회시 카테고리 값 저장( ex. 의류, 신발, ...)
	private String searchSort;      // 조회시 정렬기준 값 저장 ( ex. 최신순, 가격 높은순, 가격 낮은순 )
	
	
	public Search(String searchCategory, String searchSort) {
		this.searchCategory = searchCategory;
		this.searchSort = searchSort;
	}

	public String getSearchCategory() {
		return searchCategory;
	}

	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}

	public String getSearchSort() {
		return searchSort;
	}

	public void setSearchSort(String searchSort) {
		this.searchSort = searchSort;
	}

	@Override
	public String toString() {
		return "Search [searchCategory=" + searchCategory + ", searchSort=" + searchSort + "]";
	}
	
	
	
	
}
