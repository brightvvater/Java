package bitedu.bipa.student;

public class Student {
	
	private int sno;
	
	private String email;
	
	private int kor;
	
	private int eng;
	
	private int mat;
	
	private int sci;
	
	private int his;
	
	private int total;
	
	private String teacherCode;
	
	private String scoreCode;
	
	private String localCode;
	
	public Student() {
		
	}

	public Student(int sno, String email, int kor, int eng, int mat, int sci, int his, int total,
			String teacherCode, String scoreCode, String localCode) {
		super();
		this.sno = sno;
		this.email = email;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.sci = sci;
		this.his = his;
		this.total = total;
		this.teacherCode = teacherCode;
		this.scoreCode = scoreCode;
		this.localCode = localCode;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMat() {
		return mat;
	}

	public void setMat(int mat) {
		this.mat = mat;
	}

	public int getSci() {
		return sci;
	}

	public void setSci(int sci) {
		this.sci = sci;
	}

	public int getHis() {
		return his;
	}

	public void setHis(int his) {
		this.his = his;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getTeacherCode() {
		return teacherCode;
	}

	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}

	public String getScoreCode() {
		return scoreCode;
	}

	public void setScoreCode(String scoreCode) {
		this.scoreCode = scoreCode;
	}

	public String getLocalCode() {
		return localCode;
	}

	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}

	

	
	
	
	

	
	
	
	

}
