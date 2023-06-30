package bitedu.bipa.member.controller;

import java.sql.Date;
import java.sql.Timestamp;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Timestamp now = new Timestamp(new Date(2023,4,1).getTime());
		System.out.println(now.getDate()+","+now.getDay());
		
	}

}
