package information.model.vo;

public class Answer {
//	AID	NUMBER							//	댓글번호
//	ACONTENT	VARCHAR2(4000 BYTE)		//	내용
//	CREATE_DATE	DATE					//	작성일시
//	MODIFY_DATE	DATE					//	수정일시
//	BID	NUMBER							//	게시판번호
//	WRITER	NUMBER						//	회원번호
//	AID2	NUMBER						//	대댓글번호

	
	int aid;  		// 댓글번호
	int acontent; 	// 내용
	int createDate; // 작성일시
	int modifyDate; // 수정일시
	int bid; 		// 게시판번호
	int writer; 	// 회원번호
	int aid2;  		// 대댓글번호
	
	public Answer() {}

	public Answer(int aid, int acontent, int createDate, int modifyDate, int bid, int writer, int aid2) {
		super();
		this.aid = aid;
		this.acontent = acontent;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.bid = bid;
		this.writer = writer;
		this.aid2 = aid2;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getAcontent() {
		return acontent;
	}

	public void setAcontent(int acontent) {
		this.acontent = acontent;
	}

	public int getCreateDate() {
		return createDate;
	}

	public void setCreateDate(int createDate) {
		this.createDate = createDate;
	}

	public int getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(int modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getWriter() {
		return writer;
	}

	public void setWriter(int writer) {
		this.writer = writer;
	}

	public int getAid2() {
		return aid2;
	}

	public void setAid2(int aid2) {
		this.aid2 = aid2;
	}

	@Override
	public String toString() {
		return "Answer [aid=" + aid + ", acontent=" + acontent + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + ", bid=" + bid + ", writer=" + writer + ", aid2=" + aid2 + "]";
	}
	
	
}
