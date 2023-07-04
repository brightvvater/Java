package bitedu.bipa.member.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	FilterConfig config;
	

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}




	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String encoding = config.getInitParameter("encoding");
		
		if(request.getCharacterEncoding() == null) {
			if(encoding !=null) {
				//System.out.println("encoding....");
				request.setCharacterEncoding(encoding);
			}
			
		}
		
		chain.doFilter(request, response);
		
		
	}
	
	

}
