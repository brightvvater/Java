package bitedu.bipa.quiz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitedu.bipa.exception.RentFailedException;
import bitedu.bipa.quiz.service.LibraryBookService;

public class RentListener extends HttpServlet {
	
	private LibraryBookService service;
	
	public RentListener() {
		service = new LibraryBookService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bookSeq = Integer.parseInt(req.getParameter("bookSeq"));
		String userId = req.getParameter("name");
		
		boolean isRented = service.rentBook(userId, bookSeq);
		
			
		if(!isRented) {
			throw new RentFailedException();
		}
		
	
	}
}
