package bitedu.bipa.calendar;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class MyCalendar {
	public void viewMonth(int year, int month) {
		System.out.println(year +"년 " +month+"월");
		Calendar c = Calendar.getInstance();
		c.set(year, month-1,1);
		int day = c.getTime().getDay(); //1일의 요일
		//int day = c.getFirstDayOfWeek();
		int actualMaximum = c.getActualMaximum(Calendar.DATE); //마지막 날짜
		
		
	     //List<String> days = List.of("일", "월", "화", "수", "목", "금", "토");
	     List<String> days = Collections.unmodifiableList(List.of("일","월","화","수","목","금","토"));
	     
	     StringBuilder sb = new StringBuilder();
	     sb.setLength(0);
	     
	     for(String a: days) {
	    	 sb.append(a+"\t");
	     }
	     sb.append("\n");
	     
	     //첫 요일에 맞게 공백 만들기
	     int week =0;
	     for(int i=0;i<day;i++) {
	    	 sb.append("\t");
	    	 week++;
	     }
	     
	     //마지막날까지 출력, 개행
	     for(int k=1;k<=actualMaximum;k++) {
			 sb.append(k+"\t");
			 if((week+k)%7==0) {
				 sb.append("\n");
			 }
		 }
	     System.out.println(sb);
	     System.out.println();
	     
	}
}
