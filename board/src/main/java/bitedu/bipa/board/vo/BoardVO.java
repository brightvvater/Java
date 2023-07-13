package bitedu.bipa.board.vo;

import java.sql.Timestamp;

public class BoardVO {
	
	private int boardSeq;
	private String boardTitle;
	private String userId;
	private String content;
	private int view;
	private Timestamp regDate;
	
	public BoardVO() {}
	
	public BoardVO(String boardTitle, String userId, String content, int view,
			Timestamp regDate) {
		this.boardTitle = boardTitle;
		this.userId = userId;
		this.content = content;
		this.view = view;
		this.regDate = regDate;
	}

	public BoardVO(int boardSeq, String boardTitle, String userId, String content, int view,
			Timestamp regDate) {
		this.boardSeq = boardSeq;
		this.boardTitle = boardTitle;
		this.userId = userId;
		this.content = content;
		this.view = view;
		this.regDate = regDate;
	}

	public int getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "BoardVO [boardSeq=" + boardSeq + ", boardTitle=" + boardTitle + ", userId=" + userId 
				+ ", content=" + content + ", view=" + view + ", regDate=" + regDate + "]";
	}
	
	
	

}
