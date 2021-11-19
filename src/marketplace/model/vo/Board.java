package marketplace.model.vo;

import java.util.Date;
import java.util.List;



public class Board {
//	BID	NUMBER
//	BTITLE	VARCHAR2(100 BYTE)
//	BCONTENT	VARCHAR2(4000 BYTE)
//	CREATE_DATE	DATE
//	MODIFY_DATE	DATE
//	BCOUNT	NUMBER
//	STATUS	VARCHAR2(1 BYTE)
//	BTYPE	NUMBER
//	BWRITER	NUMBER
//	CID	NUMBER
	
//	게시판번호
//	제목
//	내용
//	작성일
//	수정일
//	조회수
//	게시물삭제여부
//	게시판유형
//	작성자번호
//	카테고리번호
	
	
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
	private int bwriter; 	//	작성자번호
	private String userName; 	// 게시글 작성자명(Member 테이블 조인 결과 값)
	private int cid; 			//	카테고리번호
	private String cname;    	// 게시글 카테고리명(category 테이블 조인 결과 값)
	private long price;       // 게시글 가격 (Marketplace 조인결과 값)
	private String location;  //  거래장소(Marketplace 조인결과 값)
	private String condition; // 제품상태(Marketplace 조인결과 값)
	private int mid;       // 마켓플레이스 기본키(Marketplace 조인결과 값)
	private String nickname; // 닉네임(Member 테이블 조인결과 값)
	private int likeCount;  // 좋아요를 누른 수(MY_LIST_BOARD 테이블에서 가져와 저장하려고 사용한 변수)
	
	private String profilePath; // (MEMBER 테이블 프로필 사진 경로)
	
	private List<Image> imageList; // 사진게시판 첨부파일
	private List<Reply> ReplyList;      // 댓글 목록
	
	private List<Board> relationList; // 카테고리 연관된 제품 가지고 있는 리스트
	private String filePath; // 카테고리 연관된 제품 가지고 있는 리스트
	private String changeName; // 카테고리 연관된 제품 가지고 있는 리스트
	
	// 몇분전, 몇시간전, 몇년전 ... 표시위한 필드
	private String timeView;
	
	public Board() {
		super();
	}

	public Board(int bid, String btitle, String bcontent, Date createDate, Date modifyDate, int bcount, String status,
			int btype, int bwriter, String userName, int cid, String cname, long price, String location,
			String condition, int mid, String nickname, int likeCount, String profilePath, List<Image> imageList,
			List<Reply> replyList, List<Board> relationList, String filePath, String changeName, String timeView) {
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
		this.price = price;
		this.location = location;
		this.condition = condition;
		this.mid = mid;
		this.nickname = nickname;
		this.likeCount = likeCount;
		this.profilePath = profilePath;
		this.imageList = imageList;
		ReplyList = replyList;
		this.relationList = relationList;
		this.filePath = filePath;
		this.changeName = changeName;
		this.timeView = timeView;
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

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
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

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	public List<Image> getImageList() {
		return imageList;
	}

	public void setImageList(List<Image> imageList) {
		this.imageList = imageList;
	}

	public List<Reply> getReplyList() {
		return ReplyList;
	}

	public void setReplyList(List<Reply> replyList) {
		ReplyList = replyList;
	}

	public List<Board> getRelationList() {
		return relationList;
	}

	public void setRelationList(List<Board> relationList) {
		this.relationList = relationList;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getTimeView() {
		return timeView;
	}

	public void setTimeView(String timeView) {
		this.timeView = timeView;
	}

	@Override
	public String toString() {
		return "Board [bid=" + bid + ", btitle=" + btitle + ", bcontent=" + bcontent + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", bcount=" + bcount + ", status=" + status + ", btype=" + btype
				+ ", bwriter=" + bwriter + ", userName=" + userName + ", cid=" + cid + ", cname=" + cname + ", price="
				+ price + ", location=" + location + ", condition=" + condition + ", mid=" + mid + ", nickname="
				+ nickname + ", likeCount=" + likeCount + ", profilePath=" + profilePath + ", imageList=" + imageList
				+ ", ReplyList=" + ReplyList + ", relationList=" + relationList + ", filePath=" + filePath
				+ ", changeName=" + changeName + ", timeView=" + timeView + "]";
	}


	
	

	

	
	
}
