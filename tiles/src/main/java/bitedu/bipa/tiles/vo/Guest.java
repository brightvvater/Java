package bitedu.bipa.tiles.vo;

import java.sql.Date;

public class Guest {
	
	private int bno;
	private String title;
	private String writer;
	private String content;
	private int regNo;
	private Date regDate;
	
	public Guest() {}

	public Guest(int bno, String title, String writer, String content, int regNo, Date regDate) {
		super();
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regNo = regNo;
		this.regDate = regDate;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRegNo() {
		return regNo;
	}

	public void setRegNo(int regNo) {
		this.regNo = regNo;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	

}
