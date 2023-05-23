package bitedu.bipa.baseball;

import java.util.ArrayList;
import java.util.Random;

public class BallProvider {
	
	public int[] makeBall() {
		int[] systemBalls = new int[4];
		//볼 생성
		Random r = new Random();
		
		//중복검사
		for(int i=0;i<4;i++) {
			systemBalls[i] = r.nextInt(9);
			if(systemBalls[0]==0) i--;
			for(int j=0;j<i;j++) {
				if(systemBalls[j] ==systemBalls[i]) {
					i--;
				}
			}
		}
		
		return systemBalls;
	}


}
