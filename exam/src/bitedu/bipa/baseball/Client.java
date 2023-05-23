package bitedu.bipa.baseball;

import java.util.Scanner;

public class Client {
	
	boolean flag = false;
	
	public int[] play () {
		Scanner sc = new Scanner(System.in);
		String clientBall = sc.next();
		if(clientBall.equals("기권")) {
			System.out.println("시스템을 종료합니다.");
			return null;
		}
		
		int[] clientBalls = new int[clientBall.length()];
		Label: for(int i=0;i<clientBall.length();i++) {
			clientBalls[i] = Integer.parseInt(String.valueOf(clientBall.charAt(i)));
			for(int j=0;j<i;j++) {
				if(clientBalls[j]==clientBalls[i]) {
					flag = true; 
					System.out.println("중복숫자를 입력하지 마세요.");
					break Label;
				}
			}
		}
		if(clientBalls.length>4 || clientBalls.length<4) {
			System.out.println("4자리 숫자를 입력하세요");
			flag = true;
		}
		
		
		return clientBalls;
	}
	
	public boolean getFlag() {
		return this.flag;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
				
	}
	

}
