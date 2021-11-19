package note.model.vo;

import java.util.Date;

public class Note {
//	NNO	NUMBER
//	NCONTENT	VARCHAR2(4000 BYTE)
//	SEND_DATE	DATE
//	RECEIVE_DELETE_STATUS	VARCHAR2(1 BYTE)
//	SEND_DELETE_STATUS	VARCHAR2(1 BYTE)
//	READ_CHECK	VARCHAR2(1 BYTE)
//	RECEIVE_ID	NUMBER
//	SEND_ID	NUMBER
//	NTITLE	VARCHAR2(100 BYTE)
	
	private int nno;
	private String ntitle;
	private String ncontent;
	private Date sendDate;
	private String receiveDeleteStatus;
	private String sendDeleteStatus;
	private String readCheck;
	private int receiveId;
	private int sendId;
	
	private String nickName; // 멤버 테이블 조인 결과물
	
	private int rnum; // 쪽지 번호 저장을 위한 필드
	
	
	// 아래 두 필드는 디테일뷰에서 사용하는 필드(수신자 발신자 닉네임 둘다 표시하기 위해서)
	private String receiveNickname;
	private String sendNickname; 
	
	// 몇분전, 몇시간전, 몇년전 ... 표시위한 필드
	private String timeView;
	
	public Note() {
	}

	public Note(int nno, String ntitle, String ncontent, Date sendDate, String receiveDeleteStatus,
			String sendDeleteStatus, String readCheck, int receiveId, int sendId, String nickName, int rnum,
			String receiveNickname, String sendNickname, String timeView) {
		this.nno = nno;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.sendDate = sendDate;
		this.receiveDeleteStatus = receiveDeleteStatus;
		this.sendDeleteStatus = sendDeleteStatus;
		this.readCheck = readCheck;
		this.receiveId = receiveId;
		this.sendId = sendId;
		this.nickName = nickName;
		this.rnum = rnum;
		this.receiveNickname = receiveNickname;
		this.sendNickname = sendNickname;
		this.timeView = timeView;
	}

	public int getNno() {
		return nno;
	}

	public void setNno(int nno) {
		this.nno = nno;
	}

	public String getNtitle() {
		return ntitle;
	}

	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}

	public String getNcontent() {
		return ncontent;
	}

	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getReceiveDeleteStatus() {
		return receiveDeleteStatus;
	}

	public void setReceiveDeleteStatus(String receiveDeleteStatus) {
		this.receiveDeleteStatus = receiveDeleteStatus;
	}

	public String getSendDeleteStatus() {
		return sendDeleteStatus;
	}

	public void setSendDeleteStatus(String sendDeleteStatus) {
		this.sendDeleteStatus = sendDeleteStatus;
	}

	public String getReadCheck() {
		return readCheck;
	}

	public void setReadCheck(String readCheck) {
		this.readCheck = readCheck;
	}

	public int getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(int receiveId) {
		this.receiveId = receiveId;
	}

	public int getSendId() {
		return sendId;
	}

	public void setSendId(int sendId) {
		this.sendId = sendId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getReceiveNickname() {
		return receiveNickname;
	}

	public void setReceiveNickname(String receiveNickname) {
		this.receiveNickname = receiveNickname;
	}

	public String getSendNickname() {
		return sendNickname;
	}

	public void setSendNickname(String sendNickname) {
		this.sendNickname = sendNickname;
	}

	public String getTimeView() {
		return timeView;
	}

	public void setTimeView(String timeView) {
		this.timeView = timeView;
	}

	@Override
	public String toString() {
		return "Note [nno=" + nno + ", ntitle=" + ntitle + ", ncontent=" + ncontent + ", sendDate=" + sendDate
				+ ", receiveDeleteStatus=" + receiveDeleteStatus + ", sendDeleteStatus=" + sendDeleteStatus
				+ ", readCheck=" + readCheck + ", receiveId=" + receiveId + ", sendId=" + sendId + ", nickName="
				+ nickName + ", rnum=" + rnum + ", receiveNickname=" + receiveNickname + ", sendNickname="
				+ sendNickname + ", timeView=" + timeView + "]";
	}

	
	
}
