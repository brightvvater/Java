package bitedu.bipa.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitedu.bipa.member.service.ReservationService;

public class ReservationController extends HttpServlet{

	private ReservationService service;
	
	public ReservationController() {
		this.service = new ReservationService();
	}
	

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		int bookSeq = Integer.parseInt(req.getParameter("bookSeq"));
		System.out.println(userId);
		System.out.println(bookSeq);
		
		//예약하기
		boolean flag = service.reserveBook(userId,bookSeq);
		if(!flag) {
			System.out.println("예약 불가");
		}else {
			System.out.println("예약 완료");
		}
		
		PrintWriter pw = resp.getWriter();
		pw.write("{\"result\":" +flag+ "}");
		pw.flush();
		pw.close();
		//예약취소하기
		//service.cancelReservation(userId, bookSeq);
	}
	
	

}
