package bitedu.bipa.lesson3;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class Main {

	public static void main(String[] args) {
		
			Main main = new Main();
			main.startTestfromDataBase();
		
	}
	
	//데이터베이스로 테스트하기
	public void startTestfromDataBase() {
		System.out.println("시험지를 배부합니다.");
		System.out.println("시험을 시작합니다.");
		String answer = null;
		StudentService service = new StudentService();
		answer = service.solveQuiz1();
		this.submitAnswer(1, answer);
		answer = service.solveQuiz2();
		this.submitAnswer(2, answer);
		answer = service.solveQuiz3();
		this.submitAnswer(3, answer);
		answer = service.solveQuiz4();
		this.submitAnswer(4, answer);
		System.out.println("답안지를 모두 제출합니다.");
		System.out.println("시험을 종료합니다.");
	}
	
	
	
	//답안지 제출
	private void submitAnswer(int num, String answer) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(num+"번 답: "+answer);
		String path = "C:\\C_it\\data\\Ans"+num+".txt";
		try(OutputStream os = new FileOutputStream(path);
			OutputStreamWriter writer = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(writer);) {
			
			bw.write(answer);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	

}
