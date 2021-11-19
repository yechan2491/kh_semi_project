package marketplace.model.vo;

public class UpdateImage {
	private String deletecontentImg1;
	private String deletecontentImg2;
	private String deletThumnail;
	public UpdateImage(String deletecontentImg1, String deletecontentImg2, String deletThumnail) {
		this.deletecontentImg1 = deletecontentImg1;
		this.deletecontentImg2 = deletecontentImg2;
		this.deletThumnail = deletThumnail;
	}
	public UpdateImage() {
	}
	public String getDeletecontentImg1() {
		return deletecontentImg1;
	}
	public void setDeletecontentImg1(String deletecontentImg1) {
		this.deletecontentImg1 = deletecontentImg1;
	}
	public String getDeletecontentImg2() {
		return deletecontentImg2;
	}
	public void setDeletecontentImg2(String deletecontentImg2) {
		this.deletecontentImg2 = deletecontentImg2;
	}
	public String getDeletThumnail() {
		return deletThumnail;
	}
	public void setDeletThumnail(String deletThumnail) {
		this.deletThumnail = deletThumnail;
	}
	@Override
	public String toString() {
		return "updateImage [deletecontentImg1=" + deletecontentImg1 + ", deletecontentImg2=" + deletecontentImg2
				+ ", deletThumnail=" + deletThumnail + "]";
	}
	
	
}
