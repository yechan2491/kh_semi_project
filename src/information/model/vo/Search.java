package information.model.vo;

public class Search {
	private String searchCategory;  // 조회시 카테고리 값 저장( ex. 청소, 꾸미기, 리폼, 생활정보)

	
	
	public Search() {
		
	}



	public Search(String searchCategory) {
		super();
		this.searchCategory = searchCategory;
	}



	public String getSearchCategory() {
		return searchCategory;
	}



	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}



	@Override
	public String toString() {
		return "Search [searchCategory=" + searchCategory + "]";
	}
	
	
	
	
	
}
