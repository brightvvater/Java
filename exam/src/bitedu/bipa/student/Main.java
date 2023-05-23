package bitedu.bipa.student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.startTest();
	}
	
	public void startTest() {
		System.out.println("시험지를 배부합니다.");
		ArrayList<Student> list = this.readFile();
		//System.out.println(list.get(0).getEmail());
		System.out.println("시험을 시작합니다.");
		StudentService service = new StudentService(list);
		String answer = null;
		//1번문제
		answer = service.solve1();
		//System.out.println(answer);
		this.submitAnswer(1, answer);		
		//2번문제
		answer = service.solve2();
		//System.out.println(answer);
		this.submitAnswer(2, answer);
		//3번문제
		answer = service.solve3();
		//System.out.println(answer);
		this.submitAnswer(3, answer);
		//4번문제
		answer = service.solve4();
		//System.out.println(answer);
		this.submitAnswer(4, answer);
		System.out.println("답안지를 모두 제출합니다.");
		System.out.println("시험을 종료합니다.");
	}
	
	private void submitAnswer(int num, String answer) {
		String path = "C:\\C_it\\data\\Ans"+num+".txt";
		try(OutputStream os = new FileOutputStream(path);
			OutputStreamWriter writer = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(writer);) {
			
			bw.write(answer);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//파일 읽어서 가공하기
	public ArrayList<Student> readFile() {
		ArrayList<Student> list = new ArrayList<>();
		
		try(InputStream is = new FileInputStream( "C:\\Abc1115.txt");
				InputStreamReader reader = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(reader);) {
			
			
			for(int i=0;i<1000;i++) {
				
				String inputData = br.readLine();
				
				//가공하여 student 객체로 만들기
				
				int sno = Integer.parseInt(inputData.substring(0, 6));
				String email = inputData.substring(6,10);
				int kor = Integer.parseInt(inputData.substring(10, 13).trim());
				int eng = Integer.parseInt(inputData.substring(13, 16).trim());
				int mat = Integer.parseInt(inputData.substring(16, 19).trim());
				int sci = Integer.parseInt(inputData.substring(19, 22).trim());
				int his = Integer.parseInt(inputData.substring(22, 25).trim());
				int total = Integer.parseInt(inputData.substring(25, 28).trim());
				String teacherCode = inputData.substring(28,29);
				String scoreCode = inputData.substring(29,30);
				String localCode = inputData.substring(30,31);
	
				
				Student student = new Student(sno, email, kor, eng, mat, sci, his, total, teacherCode, scoreCode, localCode);
				
				
				list.add(student);
				//System.out.println(total); //읽어서 student 저장
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
