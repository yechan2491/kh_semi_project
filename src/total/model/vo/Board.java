package total.model.vo;

import java.util.Date;
import java.util.List;

public class Board {
	private int bid; 			//	게시판번호
	private String btitle; 		//	제목
	private int bcount; 		//	조회수
	private String status; 		//	게시물삭제여부(Y, N)
	private String btype; 			//	게시판유형 (1. 꿀팁게시판, 2. 꿀템게사판 3. 꿀친게시판)
	private String nickname; // 닉네임(Member 테이블 조인결과 값)
	
	private String filePath; // 카테고리 연관된 제품 가지고 있는 리스트
	private String changeName; // 카테고리 연관된 제품 가지고 있는 리스트
	private String profilePath;	//프로파일 경오
	public Board() {
		super();
	}
	public Board(int bid, String btitle, int bcount, String status, String btype, String nickname, String filePath,
			String changeName, String profilePath) {
		super();
		this.bid = bid;
		this.btitle = btitle;
		this.bcount = bcount;
		this.status = status;
		this.btype = btype;
		this.nickname = nickname;
		this.filePath = filePath;
		this.changeName = changeName;
		this.profilePath = profilePath;
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
	public String getBtype() {
		return btype;
	}
	public void setBtype(String btype) {
		this.btype = btype;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public String getProfilePath() {
		return profilePath;
	}
	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}
	@Override
	public String toString() {
		return "Board [bid=" + bid + ", btitle=" + btitle + ", bcount=" + bcount + ", status=" + status + ", btype="
				+ btype + ", nickname=" + nickname + ", filePath=" + filePath + ", changeName=" + changeName
				+ ", profilePath=" + profilePath + "]";
	}

	
}
