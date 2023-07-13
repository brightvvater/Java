package bitedu.bipa.board.vo;

public class UserVO {
	private int userSeq;
	private String userId;
	private String userPass;
	
	public UserVO() {}
	
	public UserVO(int userSeq, String userId, String userPass) {
		this.userSeq = userSeq;
		this.userId = userId;
		this.userPass = userPass;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	@Override
	public String toString() {
		return "UserVO [userSeq=" + userSeq + ", userId=" + userId + ", userPass=" + userPass + "]";
	}
	
	
}
