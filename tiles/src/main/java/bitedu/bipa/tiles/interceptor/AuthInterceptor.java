package bitedu.bipa.tiles.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.tiles.vo.User;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("prehandle");
		HttpSession session = request.getSession();
		if(session ==  null) {
			System.out.println("session null");
			response.sendRedirect("/tiles/member/viewLogin.do");
			return false;
		}
		
		User user = (User)session.getAttribute("user");		
		if ( user == null || !user.getUserId().equals("admin")) {
			System.out.println("userId null");
			response.sendRedirect( "/tiles/member/viewLogin.do");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
