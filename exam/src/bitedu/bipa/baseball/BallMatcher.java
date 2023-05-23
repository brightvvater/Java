package bitedu.bipa.baseball;

import java.util.Arrays;

public class BallMatcher {
	private int[] systemBalls;
	
	public BallMatcher() {
		BallProvider p = new BallProvider();
		this.systemBalls = p.makeBall();
	}
	
	public boolean matching(int[] clientBalls) {
		boolean bool = true;
		System.out.println(systemBalls[0]+" "+systemBalls[1]+" "+ systemBalls[2]+ " "+systemBalls[3]);
		int strike=0, ball = 0;
		for(int i=0;i<clientBalls.length;i++) {
			for(int j=0;j<systemBalls.length;j++) {
				if(clientBalls[i]==systemBalls[j] && clientBalls[i] ==systemBalls[i]) {
					strike++;
				}else if(clientBalls[i]== systemBalls[j]) {
					ball++;
				}
			}
		}
		System.out.println(strike+"스트라이크 "+ball + "볼");
		if(strike ==4) {
			System.out.println("게임을 종료합니다.");
			return false;
		}
		return bool;
	}
	
}
