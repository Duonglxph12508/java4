package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.User;

@WebFilter({"/admin/*","/user/*"})
public class FilterServlet implements Filter {

    public FilterServlet() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		User entity = (User) httpRequest.getSession().getAttribute("auth");
		
		if(entity == null  ) {
			//error
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect("/asg/Login");
		}
//		else if (entity.getRole()==0) {
//			HttpServletResponse httpResponse = (HttpServletResponse) response;
//			httpResponse.sendRedirect("/asg/Home");
//		}
		else {
			
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
