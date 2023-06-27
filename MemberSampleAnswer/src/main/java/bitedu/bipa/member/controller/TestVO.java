package bitedu.bipa.member.controller;

public class TestVO {
	
	private String userId;
	private String pwd;
	private String name;
	private String zipcode;
	private String address1;
	private String address2; 
	private String year;
	private String month; 
	private String day;
	private String gender;
	private String[] interests;
	private String introduction;
	
	

	public TestVO(String userId, String pwd, String name, String zipcode, String address1, String address2, String year,
			String month, String day, String gender, String[] interests, String introduction) {
		this.userId = userId;
		this.pwd = pwd;
		this.name = name;
		this.zipcode = zipcode;
		this.address1 = address1;
		this.address2 = address2;
		this.year = year;
		this.month = month;
		this.day = day;
		this.gender = gender;
		this.interests = interests;
		this.introduction = introduction;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	
	
	
}
