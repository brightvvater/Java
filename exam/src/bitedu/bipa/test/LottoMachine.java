package bitedu.bipa.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

//로직 클래스(Service)
public class LottoMachine {
	
	private ArrayList<LottoBall> balls;
	
	//6개의 로또번호 꺼내기
	public LottoBall[] startMachine() {
		LottoBall[] selecteBalls = new LottoBall[6];
		for(int i=0;i<selecteBalls.length;i++) {
			Collections.shuffle(balls);
			selecteBalls[i] = this.getBall();
			System.out.println(selecteBalls[i]+"번이 선택되었습니다.");
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//Arrays.sort(selecteBalls); ??
		return selecteBalls;
	}
	
	//1개의 로또번호 꺼내기
	private LottoBall getBall() {
		while(true) {
			int random = (int)(Math.random()*balls.size());
			return balls.remove(random);
		}
	}
	
	/*//1개의 로또번호 꺼내기
	 private LottoBall getBall() {
	 	LottoBall ball = null;
	 	Random rnd = new Random();
	 	while(true) {
	 		ball = balls.get(rnd.nextInt(balls.size()));
	 		if(!ball.isSelected()) {
	 			ball.setSelected(true);
	 			break;
	 		}
	 	}
	 	return ball;
	 }
	 */
	
	public void setBalls(ArrayList<LottoBall> balls) {
		this.balls = balls;
	}

}
