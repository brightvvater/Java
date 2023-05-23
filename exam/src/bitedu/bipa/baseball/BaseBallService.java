package bitedu.bipa.baseball;

public class BaseBallService {

	public static void main(String[] args) {
		Client c = new Client();
		BallMatcher m = new BallMatcher();
		System.out.println("야구 게임을 시작합니다. 숫자 4자리를 입력하세요");
		boolean bool = true;
		while(bool) {
			System.out.print("숫자: ");
			int[] clientBalls = c.play();
			if(clientBalls == null) {
				break;
			}
			if(c.getFlag()==true) {
				c.setFlag(false);
				continue;
			}
		
			bool = m.matching(clientBalls);
		}
		
		
	}
	
		


}
