package bitedu.bipa.member.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("servlet context 종료");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("servlet context 시작");
		
		 ServletContext servletContext = sce.getServletContext();
		 String userId ="admin";
	     servletContext.setAttribute("user", userId);
	}

	
}
