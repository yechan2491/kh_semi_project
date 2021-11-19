package manager.model.vo;

import java.util.Date;

public class Advertise {
//	
//	AD_NO	NUMBER
//	AD_TITLE	VARCHAR2(100 BYTE)
//	AD_WRITER	VARCHAR2(100 BYTE)
//	AD_DATE	DATE
//	AD_CONTENT	VARCHAR2(4000 BYTE)
//	AD_COMPANY	VARCHAR2(100 BYTE)
//	AD_PHONE	NUMBER
//	READ	VARCHAR2(1 BYTE)
//	STATUS	VARCHAR2(1 BYTE)
//	
	private int adNo;
	private String adTitle;
	private String adWriter;
	private Date adDate;
	private String adContent;
	private String adCompany;
	private String adPhone;
	private String read;
	private String status;
	
	public Advertise() {}

	public Advertise(int adNo, String adTitle, String adWriter, Date adDate, String adContent, String adCompany,
			String adPhone, String read, String status) {
		super();
		this.adNo = adNo;
		this.adTitle = adTitle;
		this.adWriter = adWriter;
		this.adDate = adDate;
		this.adContent = adContent;
		this.adCompany = adCompany;
		this.adPhone = adPhone;
		this.read = read;
		this.status = status;
	}

	public int getAdNo() {
		return adNo;
	}

	public void setAdNo(int adNo) {
		this.adNo = adNo;
	}

	public String getAdTitle() {
		return adTitle;
	}

	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}

	public String getAdWriter() {
		return adWriter;
	}

	public void setAdWriter(String adWriter) {
		this.adWriter = adWriter;
	}

	public Date getAdDate() {
		return adDate;
	}

	public void setAdDate(Date adDate) {
		this.adDate = adDate;
	}

	public String getAdContent() {
		return adContent;
	}

	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	public String getAdCompany() {
		return adCompany;
	}

	public void setAdCompany(String adCompany) {
		this.adCompany = adCompany;
	}

	public String getAdPhone() {
		return adPhone;
	}

	public void setAdPhone(String adPhone) {
		this.adPhone = adPhone;
	}

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Advertise [adNo=" + adNo + ", adTitle=" + adTitle + ", adWriter=" + adWriter + ", adDate=" + adDate
				+ ", adContent=" + adContent + ", adCompany=" + adCompany + ", adPhone=" + adPhone + ", read=" + read
				+ ", status=" + status + "]";
	}

	
	

}
