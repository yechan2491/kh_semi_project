package manager.model.vo;

import java.util.Date;

/**
 * @author calls
 *
 */
public class Report {
	
	private int rid;			// 신고번호
	private String rTitle;		// 신고사유
	private String rContent;	// 신고내용
	private Date reportDate;	// 신고일자
	private int writer;			// 신고당한사람번호
	private String status;		// 읽음여부
	private int rwriter;		// 신고글쓴사람
	private String nickName;	// 신고당한사람 닉네임
	private String rnickName;	// 신고한사람 닉네임
	private int bid;			// 신고가 포함된 게시물번호
	private int nno;            // 신고가 포함된 쪽지번호
	private int btype;			// 게시물타입
	private int aid;			// 신고된 댓글번호
	private String reason;		// 신고된 content
	
	public Report() {}

	public Report(int rid, String rTitle, String rContent, Date reportDate, int writer, String status, int rwriter,
			String nickName, String rnickName, int bid, int nno, int btype, int aid, String reason) {
		this.rid = rid;
		this.rTitle = rTitle;
		this.rContent = rContent;
		this.reportDate = reportDate;
		this.writer = writer;
		this.status = status;
		this.rwriter = rwriter;
		this.nickName = nickName;
		this.rnickName = rnickName;
		this.bid = bid;
		this.nno = nno;
		this.btype = btype;
		this.aid = aid;
		this.reason = reason;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getrTitle() {
		return rTitle;
	}

	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}

	public String getrContent() {
		return rContent;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
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

	public int getRwriter() {
		return rwriter;
	}

	public void setRwriter(int rwriter) {
		this.rwriter = rwriter;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRnickName() {
		return rnickName;
	}

	public void setRnickName(String rnickName) {
		this.rnickName = rnickName;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getNno() {
		return nno;
	}

	public void setNno(int nno) {
		this.nno = nno;
	}

	public int getBtype() {
		return btype;
	}

	public void setBtype(int btype) {
		this.btype = btype;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "Report [rid=" + rid + ", rTitle=" + rTitle + ", rContent=" + rContent + ", reportDate=" + reportDate
				+ ", writer=" + writer + ", status=" + status + ", rwriter=" + rwriter + ", nickName=" + nickName
				+ ", rnickName=" + rnickName + ", bid=" + bid + ", nno=" + nno + ", btype=" + btype + ", aid=" + aid
				+ ", reason=" + reason + "]";
	}

	
	


}
