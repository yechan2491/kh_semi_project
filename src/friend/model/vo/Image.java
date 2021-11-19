package friend.model.vo;

public class Image {
	private int iid;			// 첨부파일 pk
	private int bid;			// 참조게시글 아이디
	private String originName;	// 원본파일 명
	private String filePath;	// 파일 저장 경로
	private String changeName;	// 변경 파일 명
	private String deletedName;		// 삭제 시 파일명
	private int fileLevel;		// 메인 사진 0, 서브 사진 1
	private String status;		// 삭제여부
	
	public Image() {}



	public Image(int iid, int bid, String originName, String filePath, String changeName, String deletedName,
			int fileLevel, String status) {
		super();
		this.iid = iid;
		this.bid = bid;
		this.originName = originName;
		this.filePath = filePath;
		this.changeName = changeName;
		this.deletedName = deletedName;
		this.fileLevel = fileLevel;
		this.status = status;
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
				+ ", changeName=" + changeName + ", deletedName=" + deletedName + ", fileLevel=" + fileLevel
				+ ", status=" + status + "]";
	}





	

}
