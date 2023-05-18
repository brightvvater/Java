package bitedu.bipa.test;

import java.util.ArrayList;
import java.util.Random;

//로직 클래스(Service)
public class LottoMachine {
	
	private ArrayList<LottoBall> balls;
	
	//6개의 로또번호 꺼내기
	public LottoBall[] startMachine() {
		LottoBall[] selecteBalls = new LottoBall[6];
		for(int i=0;i<selecteBalls.length;i++) {
			selecteBalls[i] = this.getBall();
		}
		
		return selecteBalls;
	}
	
	//1개의 로또번호 꺼내기
	private LottoBall getBall() {
		while(true) {
			int random = (int)(Math.random()*45);
			if(balls.get(random).isSelected()==false) {
				balls.get(random).setSelected(true);
				return balls.get(random);
			}
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
