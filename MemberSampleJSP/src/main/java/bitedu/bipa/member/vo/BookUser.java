package bitedu.bipa.member.vo;

import java.sql.Timestamp;

public class BookUser {
	
	
	private int userSeq;
	private String userId;
	private String userPass;
	private String userPhoneNumber;
	private String userStatus;
	private String userGrade;
	private int maxBook;
	private Timestamp serviceStop;
	private int reserveBook;
	
	public BookUser() {}
	
	public BookUser(int userSeq, String userId, String userPass, String userPhoneNumber) {
		super();
		this.userSeq = userSeq;
		this.userId = userId;
		this.userPass = userPass;
		this.userPhoneNumber = userPhoneNumber;
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

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}

	public int getMaxBook() {
		return maxBook;
	}

	public void setMaxBook(int maxBook) {
		this.maxBook = maxBook;
	}

	public Timestamp getServiceStop() {
		return serviceStop;
	}

	public void setServiceStop(Timestamp serviceStop) {
		this.serviceStop = serviceStop;
	}

	public int getReserveBook() {
		return reserveBook;
	}

	public void setReserveBook(int reserveBook) {
		this.reserveBook = reserveBook;
	}
	
	
	
	

}
