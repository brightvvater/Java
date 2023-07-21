package bitedu.bipa.tiles.vo;

public class User {
	
	private String userId;
	private String password;
	private String phoneNumber;
	
	
	public User() {}
	
	public User(String userId, String password, String phoneNumber) {
		super();
		this.userId = userId;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", phoneNumber=" + phoneNumber + "]";
	}
	
	
	
	
}
