package bitedu.bipa.gisafile;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class StudentService {
	
	private ArrayList<Student> students;
	
	public StudentService() {}
	
	public StudentService(ArrayList<Student> students) {
		this.students = students;
	}
	
	public ArrayList<Student> getBListWithArraySort() {
		ArrayList<Student> list = new ArrayList<>();
		for(int i=0;i<1000;i++) {
			Student student = students.get(i);
			if(student.getLocalCode().equals("B")) {
				list.add(student);
			}
		}
		
		list.sort(new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				if((o1.getKor()+o1.getEng())<(o2.getKor()+o2.getEng())) {
					return 1;
				}else if((o1.getKor()+o1.getEng())>(o2.getKor()+o2.getEng())){
					return -1;
				}else {
					if(o1.getEmail().charAt(0)<o2.getEmail().charAt(0)) {
						return -1;
					}else if(o1.getEmail().charAt(0)>o2.getEmail().charAt(0)) {
						return 1;
					}else {
						return 0;
					}
				}
				
			}
			
		});
		
		
		return list;
	}
	
	// 지역코드가 B 인 자료에 대하여 (국어점수 + 영어점수)으로 내림차순 정렬했을때 5번째 학번 출력하시오. 동일값 발생시 학번에 대한 오름차순 정렬하시오.
	public String solve1() {
		String answer = null;
		ArrayList<Student> list =  this.getBListWithArraySort();
		
	
		
		answer = String.valueOf(list.get(4).getSno());
		return answer;
	}
	
	//지역코드가 B 인 자료에 대하여 (국어점수 + 영어점수) 중 가장 큰값을 출력하되, 만약 동일 값 발생시는 한번만 출력하시오
	public String solve2() {
		String answer = null;
		ArrayList<Student> list =  this.getBListWithArraySort();
		
		answer = String.valueOf(list.get(0).getKor()+list.get(0).getEng());
		
		return answer;
	}

	//위의 표를 참조하여 (영어점수 + 수학점수)가 120점 이상인 자료의 (총점 + 점수포인트) 합계를 출력하시오.
	public String solve3() {	
		String answer = null;
		ArrayList<Student> list = new ArrayList<>();
		for(int i=0;i<1000;i++) {
			Student student = students.get(i);
			if(student.getEng()+student.getMat()>=120) {
				list.add(student);
			}
		}
		int sum = 0;
		for(int i=0;i<list.size();i++) {
			//System.out.println(list.get(i).getMat()+list.get(i).getEng());
			sum += list.get(i).getTotal();
			String scoreCode = list.get(i).getScoreCode();
			if(scoreCode.equals("A")) {
				sum +=5;
			}else if(scoreCode.equals("B")) {
				sum +=15;
			}else if(scoreCode.equals("C")) {
				sum +=20;
			}
		}
		answer = String.valueOf(sum);
		
		return answer;
	}

	//위의 표를 참조하여 성취도가 A, B인 자료에 대해 (국어점수 + 점수포인트)의 50 이상인자료의 건수를 출력하시오
	public String solve4() {
		String answer = null;
		
		ArrayList<Student> list = new ArrayList<>();
		for(int i=0;i<1000;i++) {
			Student student = students.get(i);
			if(student.getScoreCode().equals("A")|| student.getScoreCode().equals("B")) {
				list.add(student);
			}
		}
		int point =0;
		int count = 0;
		for(int i=0;i<list.size();i++) {
			String localCode = list.get(i).getLocalCode();
			if(localCode.equals("A")) {
				point =5;
			}else if(localCode.equals("B")) {
				point= 10;
			}else if(localCode.equals("C")) {
				point = 15;
			}
			
			if(point+list.get(i).getKor()>=50) {
				//System.out.println(list.get(i).getKor()+point);
				count++;
			}
		}
		answer = String.valueOf(count);
		return answer;
	}
	
	// 지역코드가 B 인 자료에 대하여 (국어점수 + 영어점수)으로 내림차순 정렬했을때 5번째 학번 출력하시오. 동일값 발생시 학번에 대한 오름차순 정렬하시오.
	public String dataSolve1() {
		System.out.println("A");
		String answer = null;
		Connection conn = null;
		 try {
			 //JDBC Driver 등록
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 
			 //연결하기
			 conn = DriverManager.getConnection(
					 "jdbc:mysql://localhost:3306/new_schema",
					 "root",
					 "mysql"
					 );
		 
			 System.out.println("연결 성공");
			 
			 
			 String sql = "" +
					 "select studentNo from student "
					 +"where locale ='B' "
					 +"ORDER BY (kor+eng) desc, studentNo asc limit 4,1";
			 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			 
			//SQL 문 실행 후, ResultSet을 통해 데이터 읽기
			 ResultSet rs = pstmt.executeQuery();
			 if(rs.next()) { //1개의 데이터 행을 가져왔을 경우
				  answer = rs.getString("studentNo");
			 
			 } else { //데이터 행을 가져오지 않았을 경우
			 System.out.println("사용자 아이디가 존재하지 않음");
			 }
			 rs.close();
			 
			 
			 //PreparedStatement 닫기
			 pstmt.close();
		 } catch (ClassNotFoundException e) {
			 e.printStackTrace();
			} catch (SQLException e) { e.printStackTrace(); }
			finally {
			 if(conn != null) {
				 try {
					 //연결 끊기
					 conn.close();
					 System.out.println("연결 끊기");
				 } catch (SQLException e) {}
			 }
		 }
		return answer;
	}
	
	//지역코드가 B 인 자료에 대하여 (국어점수 + 영어점수) 중 가장 큰값을 출력하되, 만약 동일 값 발생시는 한번만 출력하시오
	public String dataSolve2() {
		String answer = null;
		
		Connection conn = null;
		 try {
			 //JDBC Driver 등록
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 
			 //연결하기
			 conn = DriverManager.getConnection(
					 "jdbc:mysql://localhost:3306/new_schema",
					 "root",
					 "mysql"
					 );
		 
			 System.out.println("연결 성공");
			 
			 
			 /*String sql = "" +
					 "select * from student "
					 +"where locale ='B' "
					 +"ORDER BY (kor+eng) desc limit 0,1"; */
			 
			 String sql = "select max(kor+eng) "
					 	+"from student where locale='B'";
			 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			 
			//SQL 문 실행 후, ResultSet을 통해 데이터 읽기
			 ResultSet rs = pstmt.executeQuery();
			 if(rs.next()) { //1개의 데이터 행을 가져왔을 경우
				 answer = rs.getString(1);
			 } else { //데이터 행을 가져오지 않았을 경우
			 System.out.println("사용자 아이디가 존재하지 않음");
			 }
			 rs.close();
			 
			 
			 
			 
			 //PreparedStatement 닫기
			 pstmt.close();
		 } catch (ClassNotFoundException e) {
			 e.printStackTrace();
			} catch (SQLException e) { e.printStackTrace(); }
			finally {
			 if(conn != null) {
				 try {
					 //연결 끊기
					 conn.close();
					 System.out.println("연결 끊기");
				 } catch (SQLException e) {}
			 }
		 }
		return answer;
	}

	//위의 표를 참조하여 (영어점수 + 수학점수)가 120점 이상인 자료의 (총점 + 점수포인트) 합계를 출력하시오.
	public String dataSolve3() {
		String answer = null;
		
		Connection conn = null;
		 try {
			 //JDBC Driver 등록
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 
			 //연결하기
			 conn = DriverManager.getConnection(
					 "jdbc:mysql://localhost:3306/new_schema",
					 "root",
					 "mysql"
					 );
		 
			 System.out.println("연결 성공");
			 
			 String sql = "" +
					 "select sum((case when accCode='A' then 5 when accCode='B' then 15 when accCode='C' then 20 end) + total) as total "
					 +"from student where  eng+math>=120";
			 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			 
			//SQL 문 실행 후, ResultSet을 통해 데이터 읽기
			 ResultSet rs = pstmt.executeQuery();
			 if(rs.next()) { //1개의 데이터 행을 가져왔을 경우
				 answer = rs.getString("total");
				 
			 } else { //데이터 행을 가져오지 않았을 경우
			 System.out.println("사용자 아이디가 존재하지 않음");
			 }
			 rs.close();
			 
			 //PreparedStatement 닫기
			 pstmt.close();
		 } catch (ClassNotFoundException e) {
			 e.printStackTrace();
			} catch (SQLException e) { e.printStackTrace(); }
			finally {
			 if(conn != null) {
				 try {
					 //연결 끊기
					 conn.close();
					 System.out.println("연결 끊기");
				 } catch (SQLException e) {}
			 }
		 }
		return answer;
	}

	//위의 표를 참조하여 성취도가 A, B인 자료에 대해 (국어점수 + 점수포인트)의 50 이상인자료의 건수를 출력하시오
	public String dataSolve4() {
		String answer = null;
		
		Connection conn = null;
		 try {
			 //JDBC Driver 등록
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 
			 //연결하기
			 conn = DriverManager.getConnection(
					 "jdbc:mysql://localhost:3306/new_schema",
					 "root",
					 "mysql"
					 );
		 
			 System.out.println("연결 성공");
			 
			 
			 String sql = "" +
					 "select count(*) as count from student "
					 +"where (kor+(case when locale='A' then 5 when locale='B' then 10 when locale='C' then 15 end) >=50) "
					 +"and (accCode in('A','B'))";
					 
			 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			 
			//SQL 문 실행 후, ResultSet을 통해 데이터 읽기
			 ResultSet rs = pstmt.executeQuery();
			 if(rs.next()) { //1개의 데이터 행을 가져왔을 경우
				 answer = rs.getString("count");
			 } else { //데이터 행을 가져오지 않았을 경우
			 System.out.println("사용자 아이디가 존재하지 않음");
			 }
			 rs.close();
			 
			 
			 //PreparedStatement 닫기
			 pstmt.close();
		 } catch (ClassNotFoundException e) {
			 e.printStackTrace();
			} catch (SQLException e) { e.printStackTrace(); }
			finally {
			 if(conn != null) {
				 try {
					 //연결 끊기
					 conn.close();
					 System.out.println("연결 끊기");
				 } catch (SQLException e) {}
			 }
		 }
		return answer;
	}
	
	
	
	
	
	

}
