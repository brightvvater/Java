package bitedu.bipa.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitedu.bipa.member.vo.TestVO;
import bitedu.bipa.member.vo.User;

public class MemberController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("hello");
		
		resp.setContentType("text/html; charset=UTF-8");
		//한글처리
		req.setCharacterEncoding("UTF-8");
		String userId = req.getParameter("user_id");
		String pwd = req.getParameter("pwd");
		String userName = req.getParameter("user_name");
		String zipcode = req.getParameter("zipcode");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String[] birthday = new String[3];
		birthday[0] = req.getParameter("year");
		birthday[1] = req.getParameter("month");
		birthday[2] = req.getParameter("days");
		String gender = req.getParameter("gender");
		String[] interests = new String[6];
		String[] temp = req.getParameterValues("interests");
		for(int i=0;i<temp.length;i++) {
			interests[Integer.parseInt(temp[i])] = temp[i];
		}
		String intro = req.getParameter("introduce");
		User user = new User();
		user.setUserId(userId);
		user.setPwd(pwd);
		user.setAddr1(addr1);
		user.setAddr2(addr2);
		user.setBirthday(birthday);
		user.setGender(gender);
		user.setInterests(interests);
		user.setIntroduce(intro);
		user.setUserName(userName);
		user.setZipcode(zipcode);
		
		
		
		req.setAttribute("user", user);
		
		RequestDispatcher rd = req.getRequestDispatcher("./member/view_update.jsp");
		rd.forward(req, resp);
	
	}
	
	public void test2() {
		//String userId = req.getParameter("userId");
		//System.out.println(userId);
		TestVO test = new TestVO("admin","1234");
		//req.setAttribute("test", test);
		//resp.sendRedirect("./member/view_update.jsp?user_id="+userId);
		//resp.sendRedirect("./member/view_update.jsp");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post method");
		this.doGet(req, resp);
	}
	
	public void test() {
//		String id = req.getParameter("user_id");
//		boolean flag = false;
//		if(id.equals("admin")) {
//			flag = true;
//		} 
//		String result = "{\"result\":"+flag+"}"; //json
//		PrintWriter out = resp.getWriter();
//		out.print(result);
//		out.close();
	}
}
