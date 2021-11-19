package marketplace.model.vo;

import java.sql.Date;

public class Image {
//	IID	NUMBER
//	BID	NUMBER
//	ORIGIN_NAME	VARCHAR2(255 BYTE)
//	FILE_PATH	VARCHAR2(1000 BYTE)
//	CHANGE_NAME	VARCHAR2(255 BYTE)
//	FILE_LEVEL	NUMBER
//	STATUS	VARCHAR2(1 BYTE)
//	게시판번호
//	원본파일명
//	경로
//	변경된파일명
//
//	삭제여부
	

	private int iid; 				//	이미지파일번호
	private int bid; 				//	게시판번호
	private String originName; 		//	원본파일명
	private String filePath; 		//	경로
	private String changeName; 		//	변경된파일명
	private int fileLevel; 		// 섬네일 0 나머지 1
	private String status; 			//	삭제여부
	private String deletedName;			// 삭제 시 파일명
	public Image() {
		super();
	}
	public Image(int iid, int bid, String originName, String filePath, String changeName, int fileLevel, String status,
			String deletedName) {
		this.iid = iid;
		this.bid = bid;
		this.originName = originName;
		this.filePath = filePath;
		this.changeName = changeName;
		this.fileLevel = fileLevel;
		this.status = status;
		this.deletedName = deletedName;
	}
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
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
	public int getFileLevel() {
		return fileLevel;
	}
	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeletedName() {
		return deletedName;
	}
	public void setDeletedName(String deletedName) {
		this.deletedName = deletedName;
	}
	@Override
	public String toString() {
		return "Image [iid=" + iid + ", bid=" + bid + ", originName=" + originName + ", filePath=" + filePath
				+ ", changeName=" + changeName + ", fileLevel=" + fileLevel + ", status=" + status + ", deletedName="
				+ deletedName + "]";
	}
	
	
}
