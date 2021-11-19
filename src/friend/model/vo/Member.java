package friend.model.vo;

import java.sql.Date;

public class Member {
	private int userNo;			// 회원번호
	private String userId;		// 아이디
	private String userPwd;		// 비밀번호
	private String userName;		// 이름
	private String bitrh;		// 생년월일
	private String nickname; // 닉네임
	private String phone;		// 전화번호
	private String address;		// 주소
	private String eamil;		// 이메일
	private String profilePath;		// 프로필 사진
	private Date enrollDate;  		// 가입일
	private Date modifyDate;		// 수정일
	private String manager;			// 관리자 여부
	private String status;			// 탈퇴여부

	public Member() {}

	public Member(int userNo, String userId, String userPwd, String userName, String bitrh, String nickname,
			String phone, String address, String eamil, String profilePath, Date enrollDate, Date modifyDate,
			String manager, String status) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.bitrh = bitrh;
		this.nickname = nickname;
		this.phone = phone;
		this.address = address;
		this.eamil = eamil;
		this.profilePath = profilePath;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.manager = manager;
		this.status = status;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBitrh() {
		return bitrh;
	}

	public void setBitrh(String bitrh) {
		this.bitrh = bitrh;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEamil() {
		return eamil;
	}

	public void setEamil(String eamil) {
		this.eamil = eamil;
	}

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", bitrh=" + bitrh + ", nickname=" + nickname + ", phone=" + phone + ", address=" + address
				+ ", eamil=" + eamil + ", profilePath=" + profilePath + ", enrollDate=" + enrollDate + ", modifyDate="
				+ modifyDate + ", manager=" + manager + ", status=" + status + "]";
	}
	
	
	
	
	
	
}
