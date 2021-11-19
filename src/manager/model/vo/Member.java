package manager.model.vo;

import java.sql.Date;

public class Member {
	/*
	 * USER_NO NUMBER USER_ID VARCHAR2(30 BYTE) USER_PWD VARCHAR2(100 BYTE)
	 * USER_NAME VARCHAR2(15 BYTE) PHONE VARCHAR2(13 BYTE) EMAIL VARCHAR2(100 BYTE)
	 * ADDRESS VARCHAR2(100 BYTE) INTEREST VARCHAR2(100 BYTE) ENROLL_DATE DATE
	 * MODIFY_DATE DATE STATUS VARCHAR2(1 BYTE)
	 */

	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String birth;
	private String nickName;
	private String phone;
	private String address;
	private String email;
	private String profilePath;
	private Date enrollDate;
	private Date modifyDate;
	private String manager;
	private String status;
	
	public Member() {}

	public Member(int userNo, String userId, String userPwd, String userName, String birth, String nickName, String phone,
			String address, String email, String profilePath, Date enrollDate, Date modifyDate, String manager,
			String status) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.birth = birth;
		this.nickName = nickName;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.profilePath = profilePath;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.manager = manager;
		this.status = status;
	}
	
	public Member(String userId, String userPwd, String userName, String birth, String nickName, String phone,
			String address, String email, String profilePath) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.birth = birth;
		this.nickName = nickName;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.profilePath = profilePath;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
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
				+ ", nickName=" + nickName + ", phone=" + phone + ", email=" + email + ", address=" + address
				+ ", birth=" + birth + ", profilePath=" + profilePath + ", enrollDate=" + enrollDate + ", modifyDate="
				+ modifyDate + ", manager=" + manager + ", status=" + status + "]";
	}
}
