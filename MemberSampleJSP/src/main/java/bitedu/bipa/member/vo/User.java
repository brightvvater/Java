package bitedu.bipa.member.vo;

import java.util.Arrays;

public class User {
	private String userId;
	private String pwd;
	private String userName;
	private String zipcode;
	private String addr1;
	private String addr2;
	private String[] birthday;
	private String gender;
	private String[] interests;
	private String introduce;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String[] getBirthday() {
		return birthday;
	}
	public void setBirthday(String[] birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String[] getInterests() {
		return interests;
	}
	public void setInterests(String[] interests) {
		this.interests = interests;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", pwd=" + pwd + ", userName=" + userName + ", zipcode=" + zipcode
				+ ", addr1=" + addr1 + ", addr2=" + addr2 + ", birthday=" + Arrays.toString(birthday) + ", gender="
				+ gender + ", interests=" + Arrays.toString(interests) + ", introduce=" + introduce + "]";
	}
	
	
	
	
}
