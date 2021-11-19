package friend.model.vo;

public class Search {
	private String searchCondition;
	
	public Search() {}

	public Search(String searchCondition) {
		super();
		this.searchCondition = searchCondition;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	@Override
	public String toString() {
		return "Search [searchCondition=" + searchCondition + "]";
	}



	
	
}
