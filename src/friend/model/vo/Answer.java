package friend.model.vo;

import java.util.Date;

public class Answer {

	private int aid;
	private String acontent;
	private Date createDate;
	private Date modifyDate;
	private int bid;
	private String userName;
	private String nickName;
	private int writer;
	private String status;
	private int aid2;
	
	
	private String profilePath;		// 사용자 프로필 사진(Member)

	
	public Answer() {
	}


	public Answer(int aid, String acontent, Date createDate, Date modifyDate, int bid, String userName, String nickName,
			int writer, String status, int aid2, String profilePath) {
		super();
		this.aid = aid;
		this.acontent = acontent;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.bid = bid;
		this.userName = userName;
		this.nickName = nickName;
		this.writer = writer;
		this.status = status;
		this.aid2 = aid2;
		this.profilePath = profilePath;
	}


	public int getAid() {
		return aid;
	}


	public void setAid(int aid) {
		this.aid = aid;
	}


	public String getAcontent() {
		return acontent;
	}


	public void setAcontent(String acontent) {
		this.acontent = acontent;
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


	public int getBid() {
		return bid;
	}


	public void setBid(int bid) {
		this.bid = bid;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public int getWriter() {
		return writer;
	}


	public void setWriter(int writer) {
		this.writer = writer;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getAid2() {
		return aid2;
	}


	public void setAid2(int aid2) {
		this.aid2 = aid2;
	}


	public String getProfilePath() {
		return profilePath;
	}


	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}


	@Override
	public String toString() {
		return "Answer [aid=" + aid + ", acontent=" + acontent + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + ", bid=" + bid + ", userName=" + userName + ", nickName=" + nickName + ", writer="
				+ writer + ", status=" + status + ", aid2=" + aid2 + ", profilePath=" + profilePath + "]";
	}

	
	
	
}