package bitedu.bipa.student;


import java.util.ArrayList;
import java.util.Comparator;

public class StudentService {
	
	private ArrayList<Student> students;
	
	
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
					if(o1.getSno()<o2.getSno()) {
						return -1;
					}else if(o1.getSno()>o2.getSno()) {
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
	
	
	
	
	

}
