package bitedu.bipa.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberController extends HttpServlet {
	
	
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("hello");
		String user_id = req.getParameter("user_id");
		if(user_id !=null) {
			ServletContext servletContext = getServletContext();
	        String userId = (String) servletContext.getAttribute("user"); 
	        //System.out.println("userId: " + userId);
	        PrintWriter pw = resp.getWriter();
	        if(user_id.equals(userId)) {
	        	//System.out.println("false");
	        	pw.write("false");
	        }else {
	        	//System.out.println("true");
	        	pw.write("true");
	        }
			
			pw.close();
		}else if(user_id == null) {
			
			String userId = req.getParameter("userId");
			String pwd = req.getParameter("pwd");
			String name = req.getParameter("name");
			String zipcode = req.getParameter("zipcode");
			String address1 = req.getParameter("address1");
			String address2 = req.getParameter("address2");
			String year = req.getParameter("year");
			String month = req.getParameter("month");
			String day = req.getParameter("day");
			String gender = req.getParameter("gender");
			String[] interests = req.getParameterValues("interests");
			String introduction = req.getParameter("introduction");
			
			//System.out.println(pwd+", "+name+", "+ zipcode+", "+address1+", "+ address2+ ", "+year +", "+month+", "+day+", "+gender+", "+introduction);
			//System.out.println(interests[0]+" "+interests[1]);
			TestVO test = new TestVO(userId, pwd, name, zipcode, address1, address2, year, month, day, gender, interests, introduction);
			
			req.setAttribute("test", test);
			//resp.sendRedirect("./member/view_update.jsp/user_id="+userId);
			//resp.sendRedirect("./member/view_update.jsp"); //새로운 request를 반환하므로 req.set 에 저장된 값이 안넘어감
			RequestDispatcher rd = req.getRequestDispatcher("./member/view_update.jsp");
			rd.forward(req, resp);
		}
		
	}
}
