package bitedu.bipa.member.servlet;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{

	 String encoding;
	
	 FilterConfig filterConfig;
	
	public EncodingFilter() {}
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig = fConfig;
		this.encoding = fConfig.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("encodingFilter..");
		
		if(req.getCharacterEncoding() == null) {
			if(encoding !=null) {
				req.setCharacterEncoding(encoding);
				resp.setCharacterEncoding(encoding);
			}
		}
		chain.doFilter(req, resp);
		
		System.out.println("encodingfilter 끝..");
	}

}
