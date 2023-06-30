package bitedu.bipa.member.vo;

public class TestVO {
	private String id;
	private String pwd1;
	
	public TestVO(String id,String pwd1) {
		this.id = id;
		this.pwd1 = pwd1;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd1() {
		return pwd1;
	}

	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}

	@Override
	public String toString() {
		return "TestVO [id=" + id + ", pwd1=" + pwd1 + "]";
	}
	
	
}
