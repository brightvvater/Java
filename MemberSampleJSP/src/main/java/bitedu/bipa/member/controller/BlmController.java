package bitedu.bipa.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
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
    public BlmController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		String cmd = request.getParameter("cmd");
		cmd = cmd == null?"list":cmd;
		String url = "./manager/book_list.jsp";
		System.out.println(cmd);
		BlmService blm = new BlmService();
		boolean isRedirect = false;
		if(cmd.equals("list")) {
			ArrayList<BookCopy> list = blm.searchBookAll();
			request.setAttribute("list", list);
			
		} else if(cmd.equals("regist")) {
			
			BookCopy vo = new BookCopy();
			vo.setIsbn(request.getParameter("isbn"));
			vo.setTitle(request.getParameter("book_title"));
			vo.setAuthor(request.getParameter("author"));
			vo.setPublisher(request.getParameter("publisher"));
			vo.setPublishDate(Date.valueOf(request.getParameter("publish_date")));
			vo.setBookPosition(request.getParameter("book_position"));
			vo.setBookStaus(request.getParameter("book_status"));
			boolean flag = blm.registBook(vo);
			System.out.println("등록 유무 : " + flag);
			url = "./BlmController?cmd=list";
			isRedirect = true;
			
		} else if(cmd.equals("remove")) {
			String bookSeq = request.getParameter("bookSeq");
			boolean flag = blm.removeBook(bookSeq);
			System.out.println("삭제 유무 : " + flag);
			url = "./BlmController?cmd=list&flag=true";
			isRedirect = true;
		} else if(cmd.equals("view_regist")) {
			url ="./manager/book_regist.jsp";
			isRedirect = true;
		} else if(cmd.equals("detail")) {
			String bookSeq = request.getParameter("bookSeq");
			BookCopy bc = blm.bookDetail(bookSeq);
			request.setAttribute("detail", bc);
			url = "./manager/book_detail.jsp";
		} else if(cmd.equals("update")) {
			BookCopy bc = new BookCopy();
			bc.setBookSeq(Integer.parseInt(request.getParameter("book_seq")));
			bc.setIsbn(request.getParameter("isbn"));
			bc.setTitle(request.getParameter("book_title"));
			bc.setAuthor(request.getParameter("author"));
			bc.setPublishDate(Date.valueOf(request.getParameter("publish_date")));
			bc.setBookPosition(request.getParameter("book_position"));
			bc.setBookStaus(request.getParameter("book_status"));
			
			boolean flag = blm.bookModify(bc);
			System.out.println("수정 여부 : " + flag);
			url = "./BlmController?cmd=list";
			isRedirect = true;
		}
		
		
		if(!isRedirect) {
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else {
			response.sendRedirect(url);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
