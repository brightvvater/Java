package bitedu.bipa.test;

import java.util.ArrayList;

//controller?
public class Studio {

	public static void main(String[] args) {
		
		Studio sbs = new Studio();
		//sbs.ready();
		sbs.onAir();
	
	}
	
	//로또 머신과 로또 볼을 준비해야 함
	public LottoMachine ready() {
		LottoMachine machine = new LottoMachine();
		ArrayList<LottoBall> balls = new ArrayList<>();
		for(int i=1;i<=45;i++) {
		LottoBall ball = new LottoBall(i);
		balls.add(ball);
		//ball.setSelected(false);
		}
		machine.setBalls(balls);
		return machine;
	}
			
	public void onAir() {
		//로또 머신에게 볼을 뽑도록 지시
		System.out.println("지금부터 로또 추첨 방송을 시작합니다.");
		LottoMachine machine = this.ready();
		System.out.println("추첨을 시작합니다.");
		LottoBall[] balls = machine.startMachine();
		//balls의 내용을 출력 
		System.out.println("제 XXX회 로또 번호는 ");
		for(LottoBall ball: balls) {
			System.out.print(ball+"번 ");
		}
		System.out.println("이었습니다.");
	}

}
