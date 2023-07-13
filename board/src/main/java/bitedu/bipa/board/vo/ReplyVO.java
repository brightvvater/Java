package bitedu.bipa.board.vo;


import java.sql.Timestamp;

public class ReplyVO {
	
	private int replyId;
	private int boardSeq;
	private String userId;
	private String rContent;
	private Timestamp regDate;
	private int depth; //계층
	private int groupId; //계층아이디
	private int groupOrd; //계층댓글 순서
	
	public ReplyVO() {}

	public ReplyVO(int boardSeq, String userId, String rContent, Timestamp regDate) {
		this.boardSeq = boardSeq;
		this.userId = userId;
		this.rContent = rContent;
		this.regDate = regDate;
	}

	public ReplyVO(int boardSeq, String userId, String rContent, Timestamp regDate, int depth, int groupId,
			int groupOrd) {
		super();
		this.boardSeq = boardSeq;
		this.userId = userId;
		this.rContent = rContent;
		this.regDate = regDate;
		this.depth = depth;
		this.groupId = groupId;
		this.groupOrd = groupOrd;
	}

	public ReplyVO(int replyId, int boardSeq, String userId, String rContent, Timestamp regDate, int depth, int groupId,
			int groupOrd) {
		this.replyId = replyId;
		this.boardSeq = boardSeq;
		this.userId = userId;
		this.rContent = rContent;
		this.regDate = regDate;
		this.depth = depth;
		this.groupId = groupId;
		this.groupOrd = groupOrd;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	

	public String getrContent() {
		return rContent;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getGroupOrd() {
		return groupOrd;
	}

	public void setGroupOrd(int groupOrd) {
		this.groupOrd = groupOrd;
	}

	@Override
	public String toString() {
		return "ReplyVO [replyId=" + replyId + ", boardSeq=" + boardSeq + ", userId=" + userId + ", rContent=" + rContent
				+ ", regDate=" + regDate + ", depth=" + depth + ", groupId=" + groupId + ", groupOrd=" + groupOrd + "]";
	}

	
	
	
	

}
