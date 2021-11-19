package manager.model.vo;

public class BoardReport {
	
	private int bid;
	private int rid;
	public BoardReport(int bid, int rid) {
		super();
		this.bid = bid;
		this.rid = rid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	@Override
	public String toString() {
		return "BoardReport [bid=" + bid + ", rid=" + rid + "]";
	}
	
	

}
