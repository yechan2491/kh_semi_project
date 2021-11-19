package information.model.vo;

import java.util.Date;
import java.util.List;




public class Board {
//	BID	NUMBER                          //	게시판번호
//	BTITLE	VARCHAR2(100 BYTE)			//	제목
//	BCONTENT	VARCHAR2(4000 BYTE)		//	내용
//	CREATE_DATE	DATE					//	작성일
//	MODIFY_DATE	DATE					//	수정일
//	BCOUNT	NUMBER						//	조회수
//	STATUS	VARCHAR2(1 BYTE)			//	게시물삭제여부
//	BTYPE	NUMBER						//	게시판유형
//	BWRITER	NUMBER						//	작성자번호
//	CID	NUMBER							//	카테고리번호
	

	
//	private int bid;         // 게시글 고유 번호
//	private int btype;       // 게시글 타입 (1. 일반게시판, 2. 사진게시판)
//	private int cid;         // 게시글 카테고리 id
//	private String cname;    // 게시글 카테고리명(category 테이블 조인 결과 값)
//	private String btitle;   // 게시글 제목
//	private String bcontent; // 게시글 내용
//	private int bwriter;     // 게시글 작성자(user_no 참조 값)
//	private String userName; // 게시글 작성자명(Member 테이블 조인 결과 값)
//	private int bcount;      // 게시글 조회수
//	private Date createDate; // 게시글 작성일   포맷팅 따로 처리 안하는것은 SQL DATE가 편하지만 시분초 데이터를 필요로 하는 경우에는 util.Date가 좋다.
//	private Date modifyDate; // 게시글 수정일
//	private String status;   // 게시글 상태(Y, N)
//	
//	// 게시글 하나당 첨부파일 여러개 발생할 수 있음
//	private List<Attachment> photoList; // 사진 게시판 첨부 파일
//	private List<Reply> replyList;      // 댓글 목록
	
	private int bid; 			//	게시판번호
	private String btitle; 		//	제목
	private String bcontent; 	//	내용
	private Date createDate; 	//	작성일
	private Date modifyDate; 	//	수정일
	private int bcount; 		//	조회수
	private String status; 		//	게시물삭제여부(Y, N)
	private int btype; 			//	게시판유형 (1. 꿀팁게시판, 2. 꿀템게사판 3. 꿀친게시판)
	private int bwriter; 		//	작성자번호
	private String userName; 	// 게시글 작성자명(Member 테이블 조인 결과 값)
	private int cid; 			//	카테고리번호
	private String cname;    	// 게시글 카테고리명(category 테이블 조인 결과 값)
	private String nickname; 	// 닉네임(Member 테이블 조인결과 값)
	private int likeCount;  	// 좋아요를 누른 수(MY_LIST_BOARD 테이블에서 가져와 저장하려고 사용한 변수)
	
	private List<Image> photoList; // 사진게시판 첨부파일
	private List<Reply> replyList;       // 댓글 목록
	
	
	
	public Board() {}



	public Board(int bid, String btitle, String bcontent, Date createDate, Date modifyDate, int bcount, String status,
			int btype, int bwriter, String userName, int cid, String cname, String nickname, int likeCount,
			List<Image> photoList, List<Reply> replyList) {
		super();
		this.bid = bid;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.bcount = bcount;
		this.status = status;
		this.btype = btype;
		this.bwriter = bwriter;
		this.userName = userName;
		this.cid = cid;
		this.cname = cname;
		this.nickname = nickname;
		this.likeCount = likeCount;
		this.photoList = photoList;
		this.replyList = replyList;
	}



	public int getBid() {
		return bid;
	}



	public void setBid(int bid) {
		this.bid = bid;
	}



	public String getBtitle() {
		return btitle;
	}



	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}



	public String getBcontent() {
		return bcontent;
	}



	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}



	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public Date getModifyDate() {
		return modifyDate;
	}



	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}



	public int getBcount() {
		return bcount;
	}



	public void setBcount(int bcount) {
		this.bcount = bcount;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public int getBtype() {
		return btype;
	}



	public void setBtype(int btype) {
		this.btype = btype;
	}



	public int getBwriter() {
		return bwriter;
	}



	public void setBwriter(int bwriter) {
		this.bwriter = bwriter;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public int getCid() {
		return cid;
	}



	public void setCid(int cid) {
		this.cid = cid;
	}



	public String getCname() {
		return cname;
	}



	public void setCname(String cname) {
		this.cname = cname;
	}



	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public int getLikeCount() {
		return likeCount;
	}



	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}



	public List<Image> getPhotoList() {
		return photoList;
	}



	public void setPhotoList(List<Image> photoList) {
		this.photoList = photoList;
	}



	public List<Reply> getReplyList() {
		return replyList;
	}



	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}



	@Override
	public String toString() {
		return "Board [bid=" + bid + ", btitle=" + btitle + ", bcontent=" + bcontent + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", bcount=" + bcount + ", status=" + status + ", btype=" + btype
				+ ", bwriter=" + bwriter + ", userName=" + userName + ", cid=" + cid + ", cname=" + cname
				+ ", nickname=" + nickname + ", likeCount=" + likeCount + ", photoList=" + photoList + ", replyList="
				+ replyList + "]";
	}



	

	

	

	
	
	


	

	
	
}
