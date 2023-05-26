package bitedu.bipa.lesson3;

public class StudentService {
	
	private GisaDao dao;
	public StudentService() {
		dao = new GisaDao();
	}
	
	public String solveQuiz1() {
		String answer = null;
		StringBuffer sb = new StringBuffer(0);
		sb.append("select studentNo from student ");
		sb.append("where locale ='B' order by (kor+eng) desc, ");
		sb.append("studentNo asc limit 4,1");
		int stdNo = dao.selectQuiz1(sb.toString());
		answer = String.valueOf(stdNo);
		
		return answer;
	}
	
	public String solveQuiz2() {
		String answer = null;
		StringBuffer sb = new StringBuffer(0);
		sb.append("select max(kor+eng) from student ");
		sb.append("where locale='B'");
		
		int max = dao.selectQuiz1(sb.toString());
		answer = String.valueOf(max);
		return answer;
	}
	
	public String solveQuiz3() {
		String answer = null;
		StringBuffer sb = new StringBuffer(0);
		sb.append("select sum(total+(case when accCode='A' then 5 when accCode='B' then 15 when accCode='C' then 20 end)) ");
		sb.append("from student ");
		sb.append("where eng+math>=120");
		
		int sum = dao.selectQuiz1(sb.toString());
		answer = String.valueOf(sum);
		return answer;
	}
	
	public String solveQuiz4() {
		String answer = null;
		StringBuffer sb = new StringBuffer(0);
		sb.append("select count(*) ");
		sb.append("from student ");
		sb.append("where (kor+(case when locale='A' then 5 when locale='B' then 10 when locale='C' then 15 end))>=50 ");
		sb.append("and accCode in('A','B')");
		
		int count = dao.selectQuiz1(sb.toString());
		answer = String.valueOf(count);
		return answer;
	}

}
