package bitedu.bipa.calendar;

import java.util.Scanner;

public class CalendarService {

	public static void main(String[] args) {
		CalendarService cs = new CalendarService();
		cs.startService();
		
	}
	
	public void startService() {
		//달력을 만들어 내는 객체를 이용하여 서비스하는 내용
		//원하는 만큼 달력을 만들어 낼 수 있다
		//원하는 달, 또는 원하는 년도의 모든 월을 볼 수 있다.
		Scanner sc = new Scanner(System.in);
		MyCalendar2 mc = new MyCalendar2();
		boolean flag = false;
		while(!flag) {
			
			 System.out.print("원하는 년도 입력하세요");
			 int year = sc.nextInt(); 
			 System.out.print("원하는 월 입력하세요[해당년도 월 전체 출력은 13입력]");
			 int month =sc.nextInt(); 
			 if(month ==13) {
				 for(int i=0;i<12;i++) {
					 mc.viewMonth(year, i+1);
				 }
			 }else {
				 mc.viewMonth(year, month);
			 }
			 
			 
			System.out.println("계속 이용하시겠습니까? y/n");
			String cmd = sc.next();
			if(cmd.equals("n")) {
				System.out.println("이용해주셔서 감사합니다.");
				flag = true;
			}
		}
		sc.close();
		
	}

}
