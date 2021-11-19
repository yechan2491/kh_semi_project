package friend.model.vo;

import java.util.Date;
import java.util.List;

public class Board {
	private int bid;           // 게시판번호
	private String btitle;     // 제목
	private String bcontent;   // 내용
	private Date createDate;   // 작성일
	private Date modifyDate;   // 수정일
	private int bcount;        // 조회수
	private String status;     // 게시물삭제여부(Y, N)
	private int btype;         // 게시판유형 (1. 꿀팁게시판, 2. 꿀템게사판 3. 꿀친게시판)
	private int bwriter;    // 작성자번호
	private String userName;   // 게시글 작성자명(Member 테이블 조인 결과 값)
	private int cid;           // 카테고리번호 (30번대 사용)
	private String cname;      // 게시글 카테고리명(category 테이블 조인 결과 값)
	private String phone;		// 사용자 전화번호
	private String brith;		// 사용자 생년 월일(Member 테이블 조인 결과 값)
	private String age;			// 사용자 나이(Member 테이블 조인 결과 값)
	private String gender;		// 성별
	private int fileLevel;		// 메인 사진 0, 서브 사진 1(image 테이블 조인 결과 값)
	private int writetime;	// 작성시간 - 현재시간
	private int writemin;	// 작성 분
	private int writeday;	// 작성 일
	private String nickname; // 닉네임(Member 테이블 조인결과 값)
	private String profilePath;		// 사용자 프로필 사진
	private long price;
	private int writer;			

	private List<Image> friendPhotoList; // 꿀친 게시판 첨부 파일
	private List<Answer> friendAnswerList; // 꿀친 게시판 댓글

	public Board() {
	}


	


	public Board(int bid, String btitle, String bcontent, Date createDate, Date modifyDate, int bcount, String status,
			int btype, int bwriter, String userName, int cid, String cname, String phone, String brith, String age,
			String gender, int fileLevel, int writetime, int writemin, int writeday, String nickname,
			String profilePath, long price, int writer, List<Image> friendPhotoList, List<Answer> friendAnswerList) {
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
		this.phone = phone;
		this.brith = brith;
		this.age = age;
		this.gender = gender;
		this.fileLevel = fileLevel;
		this.writetime = writetime;
		this.writemin = writemin;
		this.writeday = writeday;
		this.nickname = nickname;
		this.profilePath = profilePath;
		this.price = price;

		this.friendPhotoList = friendPhotoList;
		this.friendAnswerList = friendAnswerList;
	}





	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBrith() {
		return brith;
	}

	public void setBrith(String brith) {
		this.brith = brith;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	public int getWritetime() {
		return writetime;
	}

	public void setWritetime(int writetime) {
		this.writetime = writetime;
	}

	public int getWritemin() {
		return writemin;
	}

	public void setWritemin(int writemin) {
		this.writemin = writemin;
	}

	public int getWriteday() {
		return writeday;
	}

	public void setWriteday(int writeday) {
		this.writeday = writeday;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	

	public int getWriter() {
		return writer;
	}





	public void setWriter(int writer) {
		this.writer = writer;
	}





	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public List<Image> getFriendPhotoList() {
		return friendPhotoList;
	}

	public void setFriendPhotoList(List<Image> friendPhotoList) {
		this.friendPhotoList = friendPhotoList;
	}

	public List<Answer> getFriendAnswerList() {
		return friendAnswerList;
	}

	public void setFriendAnswerList(List<Answer> friendAnswerList) {
		this.friendAnswerList = friendAnswerList;
	}

	@Override
	public String toString() {
		return "Board [bid=" + bid + ", btitle=" + btitle + ", bcontent=" + bcontent + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", bcount=" + bcount + ", status=" + status + ", btype=" + btype
				+ ", bwriter=" + bwriter + ", userName=" + userName + ", cid=" + cid + ", cname=" + cname + ", phone="
				+ phone + ", brith=" + brith + ", age=" + age + ", gender=" + gender + ", fileLevel=" + fileLevel
				+ ", writetime=" + writetime + ", writemin=" + writemin + ", writeday=" + writeday + ", nickname="
				+ nickname + ", profilePath=" + profilePath + ", price=" + price + ", writer=" + writer
				+ ", friendPhotoList=" + friendPhotoList + ", friendAnswerList=" + friendAnswerList + "]";
	}


	















}
