package bitedu.bipa.quiz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
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
		String userId = req.getParameter("name");
		String bookSeq = req.getParameter("bookSeq");
		
		//System.out.println(bookSeq);
		List<Integer> bookSeqList = new ArrayList<Integer>();
		String[] books = bookSeq.split(",");
		if(books.length==0) {
			throw new RuntimeException("대출할 책을 선택해주세요");
		}
		for(String book: books) {
			try {
				int bookNum = Integer.parseInt(book);
				bookSeqList.add(bookNum);
			}catch(NumberFormatException e) {
				throw new RuntimeException("숫자를 입력해주세요.");
			}
						
		}
		for(int i=0;i<bookSeqList.size();i++) {
			this.rentBook(userId, bookSeqList.get(i));
		}
		
		LibraryListener listener = new LibraryListener();
		String data = listener.getData(userId);
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(data);
		out.close();
	
	}
	
	private void rentBook(String userId, int bookSeq) {
		
		boolean isRented = service.rentBook(userId, bookSeq);
		
		if(!isRented) {
			throw new RentFailedException();
		}else {
			System.out.println("대출성공");
		}
		
		
	}
}
