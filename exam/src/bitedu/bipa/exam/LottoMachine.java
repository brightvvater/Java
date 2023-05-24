package bitedu.bipa.exam;


import java.util.ArrayList;
import java.util.Random;


public class LottoMachine {
	
	private ArrayList<LottoBall> balls;
	
	public LottoMachine () {}
	
	//ball 6개 가져오기
	public LottoBall[] startMachine() {
		LottoBall[] selectedBalls = null;
		selectedBalls = new LottoBall[6];
		for(int i=0;i<selectedBalls.length;i++) {
			selectedBalls[i] = this.getBall();
		}
		
		return selectedBalls;
	}
	
	//ball 하나 가져오기
	public LottoBall getBall() {
		LottoBall ball = null;
		Random random = new Random();
		while(true) {
			int num = random.nextInt(balls.size());
			if(!balls.get(num).isSelected()) {
				ball = balls.get(num);
				balls.get(num).setSelected(true);
				break;
			}
		}
		return ball;
	}
	
	public void setBall(ArrayList<LottoBall> balls) {
		this.balls = balls;
	}

}
