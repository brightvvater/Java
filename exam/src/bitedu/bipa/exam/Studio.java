package bitedu.bipa.exam;

import java.util.ArrayList;

public class Studio {

	public static void main(String[] args) {
		Studio studio = new Studio();
		studio.start();

	}
	
	//로또머신 초기화, 로또볼 초기화
	public LottoMachine ready() {
		LottoMachine machine = new LottoMachine();
		
		ArrayList<LottoBall> balls = new ArrayList<>();
		for(int i=1;i<=45;i++) {
			LottoBall ball = new LottoBall(i);
			balls.add(ball);
		}
		machine.setBall(balls);
	
		return machine;
	}

	//로또머신 실행하기
	public void start() {
		System.out.println("지금부터 로또 추첨 방송을 시작합니다.");
		LottoMachine machine = this.ready();
		System.out.println("추첨을 시작합니다.");
		LottoBall[] balls = machine.startMachine();
		System.out.println("제 XXX회 로또 번호는");
		for(LottoBall ball: balls) {
			System.out.print(ball +"번 ");
		}
		
		
	}
}
