package model;


public class File {

	private int unq;
	private String name;
	private String title;
	private String fileName;
	private int hits;
	private String uploadDate;

	
	
	public int getUnq() {
		return unq;
	}

	public void setUnq(int unq) {
		this.unq = unq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String string) {
		this.uploadDate = string;
	}

}
