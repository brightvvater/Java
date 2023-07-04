package bitedu.bipa.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitedu.bipa.member.service.BlmService;
import bitedu.bipa.member.vo.BookCopy;

/**
 * Servlet implementation class BlmController
 */
@WebServlet("/BlmController")
public class BlmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	BlmService service; 
	
    public BlmController() {
        this.service = new BlmService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("controller.......................");
		String cmd = request.getParameter("cmd");
		//System.out.println("cmd: "+cmd);
		cmd = cmd == null?"list":cmd;
		String url = "./manager/book_list.jsp";
		
		if(cmd.equals("list")) {
			ArrayList<BookCopy> list = service.searchBookAll();
			request.setAttribute("list", list);
		}else if(cmd.equals("detail")) {
			String bookSeq = request.getParameter("bookSeq");
			BookCopy vo = service.bookDetail(bookSeq);
			request.setAttribute("detail", vo);
			url = "./manager/book_detail.jsp";
		}else if(cmd.equals("remove")) {
			this.doDelete(request, response);
		}else if(cmd.equals("userDetail")) {
			String bookSeq = request.getParameter("bookSeq");
			BookCopy vo = service.bookDetail(bookSeq);
			request.setAttribute("detail", vo);
			url = "./manager/book_detail_user.jsp";
		}else if(cmd.equals("view_regist")) {
			url = "./manager/book_regist.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		//System.out.println("cmd: "+cmd);
		
		cmd = cmd == null?"list":cmd;
		
		if(cmd.equals("list")) {
			ArrayList<BookCopy> list = service.searchBookAll();
			request.setAttribute("list", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("./manager/book_list.jsp");
			rd.forward(request, response);
			
		}else if(cmd.equals("update")) {
			this.doPut(request, response);
		}else if(cmd.equals("regist")) {
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("book_title");
			String author =request.getParameter("author");
			Date publishDate = Date.valueOf(request.getParameter("publish_date"));
			String bookPosition = request.getParameter("book_position");
			String bookStatus = request.getParameter("book_status");
			BookCopy bc = new BookCopy(isbn, title, author, author, publishDate, bookPosition, bookStatus);
			boolean flag = service.registBook(bc);
			if(flag) {
				System.out.println("등록 성공");
			}else {
				System.out.println("실패!");
			}
			response.sendRedirect("./BlmController?cmd=list");
		}
		
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bookSeq = req.getParameter("bookSeq");
		boolean flag = service.removeBook(bookSeq);
		
		String url = "./BlmController?cmd=list&flag"+flag;
		resp.sendRedirect(url);
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int bookSeq = Integer.parseInt(req.getParameter("book_seq"));
		String isbn = req.getParameter("isbn");
		String title = req.getParameter("book_title");
		String author =req.getParameter("author");
		Date publishDate = Date.valueOf(req.getParameter("publish_date"));
		String bookPosition = req.getParameter("book_position");
		String bookStatus = req.getParameter("book_status");
		BookCopy bc = new BookCopy(bookSeq, isbn, title, author, author, publishDate, bookPosition, bookStatus);
		boolean flag = service.bookModify(bc);
		if(flag) {
			System.out.println("update 성공");
		}else {
			System.out.println("실패!");
		}
		resp.sendRedirect("./BlmController?cmd=list");
	}
	
	
	
	

}
