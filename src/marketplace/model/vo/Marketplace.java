package marketplace.model.vo;

public class Marketplace {
//	MID	NUMBER
//	PRICE	NUMBER
//	CONDITION	VARCHAR2(1 BYTE)
//	LOCATIOM	VARCHAR2(100 BYTE)
//	BID	NUMBER
//	장터게시판번호
//	가격
//	상품상태
//	거래지역
//	게시판 번호
	private int mid; 			// 장터게시판 번호 pk
	private int price; 			// 가격
	private String condition; 	// 상품상태
	private String location; 	// 거래지역
	private int bid; 			// 게시판 번호 fk
	public Marketplace() {
		super();
	}
	public Marketplace(int mid, int price, String condition, String location, int bid) {
		super();
		this.mid = mid;
		this.price = price;
		this.condition = condition;
		this.location = location;
		this.bid = bid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	@Override
	public String toString() {
		return "Marketplace [mid=" + mid + ", price=" + price + ", condition=" + condition + ", location=" + location
				+ ", bid=" + bid + "]";
	}
	
	
	
}
